<?php

if (!empty($_SERVER['HTTP_X_REQUESTED_WITH']) && strtolower($_SERVER['HTTP_X_REQUESTED_WITH']) == 'xmlhttprequest') {

    require '../../global/config.php';

    $u_name = trim($_POST['user_nombre']);
    $u_surname1 = trim($_POST['user_apepat']);
    $u_surname2 = trim($_POST['user_apemat']);
    $u_email = trim($_POST['user_email']);

    $u_username = trim($_POST['user_username']);
    $u_password = trim($_POST['user_pass']);

    $u_fecreg = date("Y-m-d H:i:s");
    $u_image = trim($_POST['user_image_url']);

    $u_iduser = $_POST['user_id'];

    $token = $_POST['token'];

    if ($u_iduser == "" || $u_iduser == null) {
        
        $query_url = "users";
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
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
        curl_setopt($ch, CURLOPT_HTTPHEADER, Array(
            'Content-Type: application/json',
            'Authorization: Bearer ' . $token));
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        
        $res = json_decode(curl_exec($ch),true);
        curl_close($ch);

        echo "OK_INSERT";

    } else {

        if ($u_password == "" || $u_password == null) {

            $query_url = "users/profile/" . $u_iduser;
            $url = API_URL . $query_url;

            $data = json_encode(array(
                'name' => $u_name,
                'surname1' => $u_surname1,
                'surname2' => $u_surname2,
                'username' => $u_username,
                'email' => $u_email,
                'image' => $u_image));

            $ch = curl_init();
            curl_setopt($ch, CURLOPT_URL, $url);
            curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "PUT");
            curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
            curl_setopt($ch, CURLOPT_HTTPHEADER, Array(
                'Content-Type: application/json',
                'Authorization: Bearer ' . $token));
            curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
            
            $res = json_decode(curl_exec($ch),true);
            curl_close($ch);

            echo "OK_UPDATE";

        }else{

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
            curl_setopt($ch, CURLOPT_HTTPHEADER, Array(
                'Content-Type: application/json',
                'Authorization: Bearer ' . $token));
            curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
            
            $res = json_decode(curl_exec($ch),true);
            curl_close($ch);

            echo "OK_UPDATE";
            
        }
    }

} else {
    echo "ERROR";
}