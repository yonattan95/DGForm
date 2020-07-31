<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-0">
                <div class="col-md-12">
                    <div class="m-0 text-dark text-center text-lg">
                        <i class="fas fa-users"></i>&nbsp;&nbsp;Usuarios Encuestadores
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="content">
        <div class="container-fluid">
            <div style="max-width: 1140px;margin: 0 auto;">
                <form id="frmInsertInterviewer" method="post" action="<?php echo $funciones->direct_sistema(); ?>/modules/interviewers/insert-update-interviewer.php" enctype="multipart/form-data">
                    <input type="hidden" name="user_id">
                    <input type="hidden" name="user_parent_id" value="<?php echo $_SESSION['loggedInUser']['USERID']?>">
                    <div class="card card-primary">
                        <div class="card-header">
                            <div class="card-title">Datos de Usuario</div>
                            <div class="float-right" style="height: 2rem; width: 150px">
                                <input type="text" placeholder="Código de usuario" class="form-control" name="user_code" readonly>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Nombre</label>
                                        <div class="input-group mb-3">
                                            <input type="text" class="form-control" placeholder="Ingrese nombre" name="user_nombre" pattern="[A-Za-z0-9_-]{1,50}" maxlength="50" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Apellido Paterno</label>
                                        <div class="input-group mb-3">
                                            <input type="text" class="form-control" placeholder="Ingrese apellido paterno" name="user_apepat" pattern="[A-Za-z0-9_-]{1,50}" maxlength="50" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Apellido Materno</label>
                                        <div class="input-group mb-3">
                                            <input type="text" class="form-control" placeholder="Ingrese apellido materno" name="user_apemat" pattern="[A-Za-z0-9_-]{1,50}" maxlength="50" required>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Usuario</label>
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text">
                                                    <i class="fas fa-at"></i>
                                                </span>
                                            </div>
                                            <input type="text" class="form-control" placeholder="Ingrese nombre de usuario" name="user_username" pattern="[A-Za-z0-9_-]{1,50}" maxlength="50" autocomplete="username" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-5">
                                    <div class="form-group">
                                        <label>Email</label>
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text">
                                                    <i class="fas fa-envelope"></i>
                                                </span>
                                            </div>
                                            <input type="text" class="form-control" placeholder="Ingrese email" name="user_email" maxlength="50" autocomplete="email">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label>Fecha de Registro</label>
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text">
                                                    <i class="far fa-calendar-alt"></i>
                                                </span>
                                            </div>
                                            <input type="date" id="user_fecreg" class="form-control" name="user_fecreg" defaultValue="<?php echo date('Y-m-d'); ?>" value="<?php echo date('Y-m-d'); ?>" readonly>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Imagen para mostrar de usuario (URL)</label>
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text">
                                                    <i class="fas fa-link"></i>
                                                </span>
                                            </div>
                                            <input type="text" class="form-control" placeholder="Ingrese URL de imagen" name="user_image_url">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card card-secondary" id="password-card">
                                        <div class="card-header">
                                            <div id="password-card-header" class="btn-block" data-card-widget="collapse" data-toggle="tooltip" title="Colapsar"><i class="fas fa-plus"></i>&nbsp;&nbsp;<font>Contraseña</font></div>
                                        </div>
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label id="pass-label-1"><font>Contraseña</font></label>
                                                        <div class="input-group mb-3">
                                                            <div class="input-group-prepend">
                                                                <span class="input-group-text">
                                                                    <i class="fas fa-key"></i>
                                                                </span>
                                                            </div>
                                                            <input type="password" id="user_pass" class="form-control" placeholder="Ingrese contraseña" name="user_pass" pattern="[A-Za-z0-9_-]{1,72}" maxlength="72" autocomplete="new-password" required>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label id="pass-label-2"><font>Confirmar Contraseña</font></label>
                                                        <div class="input-group mb-3">
                                                            <div class="input-group-prepend">
                                                                <span class="input-group-text">
                                                                    <i class="fas fa-key"></i>
                                                                </span>
                                                            </div>
                                                            <input type="password" id="user_pass_conf" class="form-control" placeholder="Confirme contraseña" name="user_pass_conf" pattern="[A-Za-z0-9_-]{1,72}" maxlength="72" autocomplete="new-password" required>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div id="col-btn-save-user" class="col-md-12" style="alignment-baseline: central;">
                            <button type="submit" id="btn-save-user" class="btn btn-success btn-block"><i class="fa fa-save fa-1x"></i>&nbsp;&nbsp;<font>Crear Usuario</font></button>
                        </div>
                        <div id="col-btn-delete-user" class="col-md-6">
                            <button type="button" id="btn-delete-user" js-id="" class="btn btn-danger btn-block"><i class="fa fa-trash fa-1x"></i>&nbsp;&nbsp;Eliminar usuario</button>
                        </div>
                    </div>
                </form>
                <div class="card mt-3">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table id="table-users" class="table table-bordered table-hover" style="width: 100%">
                                <thead>
                                    <tr>
                                        <th>Cód. Encuestador</th>
                                        <th>Nombres</th>
                                        <th>A. Paterno</th>
                                        <th>A. Materno</th>
                                        <th>Usuario</th>
                                        <th>Email</th>
                                        <th>Imagen</th>
                                    </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
    </div>

</div>