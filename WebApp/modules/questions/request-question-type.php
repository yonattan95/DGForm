<?php

require '../../global/config.php';

$REQUEST_MODE = $_POST["REQUEST_MODE"];
$RETURN = "";

switch ($REQUEST_MODE) {
    case 'ALL':

        $query_url = "generals/question_types";
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

            $json_data = array();

            foreach ($res['data'] as $ROW) {

                $ROWDATA = [
                    "id" => $ROW["id"],
                    "text" => $ROW["name"]
                ];
                
                array_push($json_data, $ROWDATA);
            }

            $RETURN = json_encode($json_data);

        }else if($res['status'] == 0){
            echo json_encode(array(
                'status' => 0,
                'message' => $res['errorMessage']));

        }else{
            echo json_encode(array('status' => -2));
        }

    case 'OTHER':

        break;

}

echo $RETURN;