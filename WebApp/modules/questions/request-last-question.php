<?php

if (!empty($_SERVER['HTTP_X_REQUESTED_WITH']) && strtolower($_SERVER['HTTP_X_REQUESTED_WITH']) == 'xmlhttprequest') {

    require '../../global/config.php';

    $FORM_ID = $_POST["FID"];
        
    $query_url = "form/" . $FORM_ID . "/interviewer/0/questions";
    $url = API_URL . $query_url;

    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, $url);
    curl_setopt($ch, CURLOPT_HTTPHEADER, Array("Content-Type: application/json"));
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    
    $res = json_decode(curl_exec($ch),true);
    curl_close($ch);

    if($res['status']===null){
        echo json_encode(array(
            'status' => -1,
            'statusCode' => $res['statusCode'],
            'message' => $res['message']));

    }else if($res['status'] == 1){

        echo $res['data']['totalQuestion'] + 1;

    }else if($res['status'] == 0){
        echo json_encode(array(
            'status' => 0,
            'message' => $res['errorMessage']));

    }else{
        echo json_encode(array('status' => -2));
    }

} else {
    echo "ERROR";
}