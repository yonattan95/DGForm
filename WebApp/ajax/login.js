$(document).on('submit', '#login-form', function(event){

	event.preventDefault();

	$.ajax({
		url: 'modules/login.php',
		type: 'POST',
		dataType: 'json',
		data: $(this).serialize(),
		beforeSend: function(){
			$('#btnLogin').val('Validando...');
		}
	})
	.done(function(response){

		if(response.status == 1){
			location.href = 'views/home';
		}else if(response.status == 0){
			console.log(response);
			toastr.error('Las credenciales que ha ingresado no son válidas','Acceso Denegado');
			$('#btnLogin').val('Ingresar');
			return;
		}else if(response.status == -2){
			console.log(response);
			toastr.error(response,'Error desconocido');
			return;
		}else if(response.status == -1){
			console.log(response);
			toastr.error(response,'Error al obtener respuesta de API');
			return;
		}

	})
	.fail(function(response){
		console.log(response.responseText);
		toastr.error(response.responseText,'No se pudo recibir una respuesta del servidor');
	})
	.always(function(){
		//console.log('AJAX call complete');
	});
});