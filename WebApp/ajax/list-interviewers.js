$("#col-btn-delete-user").hide();

var tabla_usuarios = $('#table-users');

tabla_usuarios.dataTable({
    "ajax": {
        "url": "../../modules/interviewers/get-interviewer.php",
        "type": "POST",
        "data": { "FILTER": "ALL" },
    },
    "columns": [
        { "data": "ID" },
        { "data": "NAME" },
        { "data": "SURNAME1" },
        { "data": "SURNAME2" },
        { "data": "USERNAME" },
        { "data": "EMAIL" },
        { "data": "IMG_RENDERED" },
        { "data": "IMG" }
        //{ "data": "STATE" }
    ],
    "columnDefs": [
        {
            "targets": [ 7 ],
            "visible": false,
            "searchable": false
        }
    ],
    "order": [[0, "asc"]],
    "language": {
            "url": "../../plugins/datatables/Spanish.json"
        }
});

$("#frmInsertInterviewer").submit(function (e) {
    e.preventDefault();
    var form = $(this);
    var idform = form.attr("id");
    var url = form.attr('action');
    var formElement = document.getElementById(idform);
    var formData_rec = new FormData(formElement);
    var id_usuario = $('input[name="user_id"]').val();

    if ( $("#user_pass").val() != "" || $("#user_pass_conf").val() != "" ){
        if( $("#user_pass").val() != $("#user_pass_conf").val() ){
            $.Notification.notify("error", "bottom-right", "Contraseña", "Las contraseñas ingresadas no coinciden.");
            return;
        }
    }

    $.ajax({
        type: "POST",
        url: url,
        data: formData_rec,
        contentType: false,
        cache: false,
        processData: false,
        beforeSend: function () {
            Swal.fire({
                html: '<h4>Guardando información</h4>',
                allowOutsideClick: false,
                onBeforeOpen: () => {
                    Swal.showLoading();
                }
            })
        },
        success: function (data) {
            if (data == "ERROR") {
                $.Notification.notify("error", "bottom-right", "Error de guardado", "No se pudo guardar datos del usuario");
                Swal.close();
            } else if (data == "EXISTE") {
                $.Notification.notify("error", "bottom-right", "Error de guardado", "Usuario ya existe en la base de datos");
                Swal.close();
            } else if (data == "OK_INSERT") {
                form.find("input, textarea, select").val("");
                //form.find("select").trigger("change");
                $("#user_fecreg").val(function(){return this.defaultValue;});
                $('#table-users').DataTable().ajax.reload();
                $.Notification.notify("success", "bottom-right", "Usuario creado", "Datos almacenados");
                Swal.close();

            } else if (data == "OK_UPDATE") {
                if (id_usuario != "" && id_usuario != null) {
                    $('input[name="user_id"]').val("");
                    $("#btn-save-user font").html("Guardar usuario");
                    $("#col-btn-save-user").attr("class", "col-md-12");
                    $("#col-btn-delete-user").hide();

                    $user_parent_id=$("input[name=user_parent_id]").val();
                    $token=$("input[name=token]").val();
                    form.find("input, textarea, select").val("");
                    $("input[name=token]").val($token);
                    $("input[name=user_parent_id]").val($user_parent_id);

                    $("#password-card").attr("class", "card card-secondary");
                    $("#password-card-header font").html("Contraseña");
                    $("#pass-label-1 font").html("Contraseña");
                    $("#pass-label-2 font").html("Confirmar Contraseña");

                    $("#user_pass").prop('required',true);
                    $("#user_pass_conf").prop('required',true);

                    $("#user_fecreg").val(function(){return this.defaultValue;});
                }
                $.Notification.notify("success", "bottom-right", "Usuario actualizado", "Datos actualizados");
                Swal.close();
                $('#table-users').DataTable().ajax.reload();
            }
        }
    });
});

tabla_usuarios.on('click', 'tr', function () {
    var data = tabla_usuarios.fnGetData(this);
    if (data == null) return;

    var id_row = data["ID"];

    $('input[name="user_nombre"]').focus();
    $('#btn-delete-user').attr("js-id", data["ID"]);

    $('input[name="user_id"]').val(data["ID"]);
    $('input[name="user_code"]').val("INT-" + data["ID"]);

    $('input[name="user_nombre"]').val(data["NAME"]);
    $('input[name="user_apepat"]').val(data["SURNAME1"]);
    $('input[name="user_apemat"]').val(data["SURNAME2"]);
    $('input[name="user_username"]').val(data["USERNAME"]);
    $('input[name="user_email"]').val(data["EMAIL"]);
    $('input[name="user_image_url"]').val(data["IMG"]);

    //$('input[name="user_fecreg"]').val(data_json[0]["FEC_REG"]);

    $("#password-card").attr("class", "card card-secondary collapsed-card");
    $("#password-card-header font").html("Cambiar contraseña");
    $("#pass-label-1 font").html("Nueva Contraseña (dejar en blanco para dejar sin cambios)");
    $("#pass-label-2 font").html("Confirmar Nueva Contraseña");
    $("#user_pass").removeAttr('required');
    $("#user_pass_conf").removeAttr('required');

    $("#btn-save-user font").html("Actualizar usuario");
    $("#col-btn-save-user").attr("class", "col-md-6");
    $("#col-btn-delete-user").show("fast");

    /*
    Swal.fire({
        html: '<h4>Cargando información deL usuario</h4>',
        allowOutsideClick: false,
        onBeforeOpen: () => {
            Swal.showLoading();
        }
    });
    $.post("../../modules/usuarios/consultar-usuario.php", { FILTER: id_row }, function (data) {
        var data_json = JSON.parse(data);

        $('input[name="usuario_codigo"]').focus();
        $('#btn-delete-user').attr("js-id", data_json[0]["CODIGO"]);

        $('input[name="usuario_id"]').val(data_json[0]["CODIGO"]);
        $('input[name="usuario_codigo"]').val("USR-" + data_json[0]["CODIGO"]);
        $('input[name="usuario_nombre"]').val(data_json[0]["USERNAME"]);

        $('select[name="usuario_empleado_id"]').val(data_json[0]["CODIGO_EMP"]);
        $('select[name="usuario_empleado_id"]').trigger('change');

        $('input[name="usuario_fecreg"]').val(data_json[0]["FEC_REG"]);

        $("#password-card").attr("class", "card card-secondary collapsed-card");
        $("#password-card-header font").html("Cambiar contraseña");
        $("#pass-label-1 font").html("Nueva Contraseña (dejar en blanco para dejar sin cambios)");
        $("#pass-label-2 font").html("Confirmar Nueva Contraseña");
        $("#usuario_pass").removeAttr('required');
        $("#usuario_pass_conf").removeAttr('required');

        $("#btn-save-user font").html("Actualizar usuario");
        $("#col-btn-save-user").attr("class", "col-md-6");
        $("#col-btn-delete-user").show("fast");

    }).done(function(){
        $(window).scrollTop(0);    
    });

    Swal.close();
    */
});

$("#btn-delete-user").click(function () {
    element = $(this);
    id_val = element.attr("js-id");
    if (id_val != "" && id_val != null) {
        Swal.fire({
            title: 'Se eliminará este usuario',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Eliminar',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.value) {
                $.post("../../modules/interviewers/delete-interviewer.php", { user_id: id_val }, function (data) {
                    if (data == true) {
                        $("#frmInsertInterviewer").find("input, textarea, select").val("");
                        $("#frmInsertInterviewer").find("select").trigger("change");

                        $('#table-users').DataTable().ajax.reload();
                        $('input[name="user_id"]').val("");                    
                        $("#btn-save-user font").html("Guardar usuario");
                        $("#col-btn-save-user").attr("class", "col-md-12");
                        $("#col-btn-delete-user").hide();

                        $("#password-card").attr("class", "card card-secondary");
                        $("#password-card-header font").html("Contraseña");
                        $("#pass-label-1 font").html("Contraseña");
                        $("#pass-label-2 font").html("Confirmar Contraseña");
                        $("#user_pass").prop('required',true);
                        $("#user_pass_conf").prop('required',true);

                        $("#user_fecreg").val(function(){return this.defaultValue;});

                        $.Notification.notify("success", "bottom-right", "Usuario eliminado", "Información eliminada correctamente");
                    }
                });
            }
        })
    }
});