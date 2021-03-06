<?php 
session_start();

if (isset($_SESSION['loggedInUser'])) {
  header('Location: views/home');
}
?>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="DigiForms">
    <meta name="author" content="ESG Peru">
    <meta name="theme-color" content="#563d7c">
    <title>Inicio de Sesión - DigiForms</title>
    <link href="./css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">

    <link href="./plugins/toastr/toastr.min.css" rel="stylesheet">
    <link rel="icon" href="./img/favicons/form-32x32.png" sizes="32x32" type="image/png">
    <link rel="icon" href="./img/favicons/form-16x16.png" sizes="16x16" type="image/png">

    <link href="./css/floating-labels.css" rel="stylesheet">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
  </head>
  <body>
    <form class="form-signin" id="login-form">
      <div class="text-center mb-4">
        <img class="mb-2" src="./img/form_128.png" alt="" width="128" height="128">
        <h1 class="app-title h1 mb-3 font-weight-normal">DigiForms</h1>
        <p></p>
      </div>

      <div class="form-label-group">
        <input type="text" name="txtUsername" pattern="[A-Za-z0-9_-]{1,50}" maxlength="50" class="form-control" placeholder="Usuario" required="" autofocus="" autocomplete="username">
        <label for="inputUser">Usuario</label>
      </div>

      <div class="form-label-group">
        <input type="password" name="txtPassword" pattern="[A-Za-z0-9_-]{1,72}" maxlength="72" class="form-control" placeholder="Contraseña" required="" autocomplete="current-password">
        <label for="inputPassword">Contraseña</label>
      </div>
      <!--
      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me"> Recordarme
        </label>
      </div>
      -->
      <button type="submit" class="btn btn-lg btn-primary btn-block" name="btnLogin" id="btnLogin">Ingresar</button>

      <p class="footer mt-5 mb-3 text-center">© 2020 DigiForms</p>
    </form>
    <script src="./plugins/jquery/jquery.min.js"></script>
    <script src="./plugins/toastr/toastr.min.js"></script>
    <script type="text/javascript">
      toastr.options = {
        "closeButton": false,
        "debug": false,
        "newestOnTop": false,
        "progressBar": false,
        "positionClass": "toast-top-center",
        "preventDuplicates": true,
        "onclick": null,
        "showDuration": "500",
        "hideDuration": "1000",
        "timeOut": "5000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
        }
    </script>

    <script src="./ajax/login.js"></script>

  </body>
</html>