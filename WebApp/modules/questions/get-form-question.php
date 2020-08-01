<?php

if (!empty($_SERVER['HTTP_X_REQUESTED_WITH']) && strtolower($_SERVER['HTTP_X_REQUESTED_WITH']) == 'xmlhttprequest') {

    require '../../global/config.php';

    $FILTER_USER = $_POST["FILTER"];
    $FORM_ID = $_POST["FID"];
    if ($FILTER_USER == "ALL") {
        
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

            $json_data = array();
            foreach ($res['data']['questionList'] as $ROW) {

                $ROWDATA['NUMBER'] = $ROW['questionNumber'];
                $ROWDATA['ID'] = $ROW['id'];
                $ROWDATA['NAME'] = $ROW['name'];
                $ROWDATA['DESCRIPTION'] = $ROW['description'];
                $ROWDATA['TYPE'] = $ROW['questionType']['id'];
                $ROWDATA['TYPE_TEXT'] = $ROW['questionType']['name'];
                
                array_push($json_data, $ROWDATA);
                
            }

            echo json_encode(array('data' => $json_data,'status' => 1));

        }else if($res['status'] == 0){
            echo json_encode(array(
                'status' => 0,
                'message' => $res['errorMessage']));

        }else{
            echo json_encode(array('status' => -2));
        }

    } else {
        /*
        $ID_REAL = str_replace("USR-","",$FILTER_USER);
        $sqlStatement = $pdo->prepare("SELECT * FROM tbl_user
            WHERE id=:USER_ID");
        $sqlStatement->bindParam("USER_ID", $ID_REAL, PDO::PARAM_STR);
        $sqlStatement->execute();
        $rowsNumber = $sqlStatement->rowCount();
        if ($rowsNumber > 0) {
            $json_data = array();
            foreach ($sqlStatement as $ROW) {
                $ROWDATA['CODIGO'] = $ROW["id"];
                $ROWDATA['USERNAME'] = $ROW["username"];
                $ROWDATA['CODIGO_EMP'] = $ROW["employee_id"];
                $ROWDATA['FEC_REG'] = date("Y-m-d",strtotime($ROW["registration_date"]));

                array_push($json_data, $ROWDATA);
            }
            echo json_encode($json_data);
        }
        */
    }

} else {
    echo "ERROR";
}