<?php

if (!empty($_SERVER['HTTP_X_REQUESTED_WITH']) && strtolower($_SERVER['HTTP_X_REQUESTED_WITH']) == 'xmlhttprequest') {

    require '../../global/config.php';

    $q_name = trim($_POST['question_name']);
    $q_description = trim($_POST['question_description']);
    $q_type = trim($_POST['question_type']);
    $q_number = trim($_POST['question_number']);
    $q_form_id = $_POST['form_id'];
    $q_id = "";

    if ($q_id == "" || $q_id == null) {
        
        $query_url = "form/" . $q_form_id . "/question";
        $url = API_URL . $query_url;

        $data = json_encode(array(
            'name' => $q_name,
            'description' => $q_description,
            'questionType' => $q_type,
            'questionNumber' => $q_number));

        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
        curl_setopt($ch, CURLOPT_HTTPHEADER, Array("Content-Type: application/json"));
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        
        $res = json_decode(curl_exec($ch),true);
        curl_close($ch);

        /*
        session_start();
        $_SESSION['FORMID'] = $res['data']['id'];
        */

        echo "OK_INSERT" ;

    } else {

        /*
        $query_url = "users/profile/" . $u_iduser;
        $url = API_URL . $query_url;

        $data = json_encode(array(
            'name' => $u_name,
            'surname1' => $u_surname1,
            'surname2' => $u_surname2,
            'username' => $u_username,
            'email' => $u_email,
            'password' => $u_password,
            'image' => $u_image));

        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "PUT");
        curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
        curl_setopt($ch, CURLOPT_HTTPHEADER, Array("Content-Type: application/json"));
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        
        $res = json_decode(curl_exec($ch),true);
        curl_close($ch);

        echo "OK_UPDATE";
        */
    }

} else {
    echo "ERROR";
}