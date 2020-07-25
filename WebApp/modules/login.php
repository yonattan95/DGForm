<?php

if(!empty($_SERVER['HTTP_X_REQUESTED_WITH']) && strtolower($_SERVER['HTTP_X_REQUESTED_WITH']) == 'xmlhttprequest'){
	
	require '../global/config.php';

	$username = $_POST['txtUsername'];
	$password = $_POST['txtPassword'];

	$query_url = "user_auth/login";
	$url = API_URL . $query_url;

	$data = json_encode(array(
		'username' => $username,
		'password' => $password));

	$ch = curl_init();
	curl_setopt($ch, CURLOPT_URL, $url);
	curl_setopt($ch, CURLOPT_POST, true);
	curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
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
		session_start();

		$_SESSION['loggedInUser'] = array(
			'USERID' => $res['data']['userId'],
			'USERNAME' => $res['data']['username'],
			'PHOTO_URL' => 'default-avatar.png',
			'JOB' => 'Administrador',
			'EMPLOYEE_NAME' => 'Usuario ADM',
			'TOKEN' => $res['data']['accessToken']);

		echo json_encode(array(
			'status' => 1));

	}else if($res['status'] == 0){
		echo json_encode(array(
			'status' => 0,
			'message' => $res['errorMessage']));

	}else{
		echo json_encode(array('status' => -2));
	}

}

?>