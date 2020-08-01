<?php

if (!empty($_SERVER['HTTP_X_REQUESTED_WITH']) && strtolower($_SERVER['HTTP_X_REQUESTED_WITH']) == 'xmlhttprequest') {

    require '../../global/config.php';

    $f_name = trim($_POST['form_name']);
    $f_description = trim($_POST['form_description']);
    $f_start_date = trim($_POST['form_start_date']);
    $f_end_date = trim($_POST['form_end_date']);
    $f_quizzes = trim($_POST['form_quizzes']);
    $f_category_id = trim($_POST['form_category']);
    $f_user_id = $_POST['user_parent_id'];

    $f_id = $_POST['form_id'];

    if ($f_id == "" || $f_id == null) {
        
        $query_url = "forms";
        $url = API_URL . $query_url;

        $data = json_encode(array(
            'name' => $f_name,
            'description' => $f_description,
            'startDate' => $f_start_date,
            'endDate' => $f_end_date,
            'allQuizAssigned' => $f_quizzes,
            'category' => $f_category_id,
            'user' => $f_user_id));

        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
        curl_setopt($ch, CURLOPT_HTTPHEADER, Array("Content-Type: application/json"));
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        
        $res = json_decode(curl_exec($ch),true);
        curl_close($ch);

        //print_r($res);
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