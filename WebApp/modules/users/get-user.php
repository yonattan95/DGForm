<?php

require '../../global/config.php';

$FILTER_USER = $_POST["FILTER"];
if ($FILTER_USER == "ALL") {
    
    $query_url = "users";
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
        foreach ($res['data'][0] as $ROW) {
            $ROWDATA['ID'] = $ROW['id'];
            $ROWDATA['NAME'] = $ROW['name'];
            $ROWDATA['SURNAME1'] = $ROW['surname1'];
            $ROWDATA['SURNAME2'] = $ROW['surname2'];
            $ROWDATA['USERNAME'] = $ROW['username'];
            $ROWDATA['EMAIL'] = $ROW['email'];

            if ($ROW['state'] == 1){
                $ROWDATA['STATE'] = "Activo";
            }else{
                $ROWDATA['STATE'] = "Inactivo";
            }

            if ($ROW['image'] === null){
                $ROWDATA['IMG'] = "";
            }else{
                $ROWDATA['IMG'] = "<img src='" . $ROW['image'] . "' width='100' height='100'>";
            }
            
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
}