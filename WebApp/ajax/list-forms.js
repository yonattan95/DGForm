$("#col-btn-delete-form").hide();

var tabla_forms = $('#table-forms');

tabla_forms.dataTable({
    "ajax": {
        "url": "../../modules/forms/get-form.php",
        "type": "POST",
        "data": { "FILTER": "ALL" },
    },
    "columns": [
        { "data": "ID" },
        { "data": "NAME" },
        { "data": "DESCRIPTION" },
        { "data": "STATE" },
        { "data": "STATE_TEXT" },
        { "data": "START_DATE" },
        { "data": "END_DATE" },
        { "data": "ALL_QUIZ_ASSIGNED" },
        { "data": "CREATED_DATE" },
        { "data": "CATEGORY_ID" },
        { "data": "CATEGORY_NAME" }
    ],
    "columnDefs": [
        {
            "targets": [3],
            "visible": false,
            "searchable": false
        },
        {
            "targets": [9],
            "visible": false,
            "searchable": false
        }
    ],
    "order": [[0, "asc"]],
    "language": {
            "url": "../../plugins/datatables/Spanish.json"
        }
});

/*
$("#frmInsertUser").submit(function (e) {
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
                    form.find("input, textarea, select").val("");
                    //form.find("select").trigger("change");

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
*/

tabla_forms.on('click', 'tr', function () {
    var data = tabla_forms.fnGetData(this);
    if (data == null) return;

    var id_row = data["ID"];

    window.location.replace("../../modules/forms/detail-form");

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
                $.post("../../modules/users/delete-user.php", { user_id: id_val }, function (data) {
                    if (data == true) {
                        $("#frmInsertUser").find("input, textarea, select").val("");
                        $("#frmInsertUser").find("select").trigger("change");

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