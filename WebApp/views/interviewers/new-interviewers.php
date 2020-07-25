<div class="content-wrapper">
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-0">
                <div class="col-md-12">
                    <div class="m-0 text-dark text-center text-lg">
                        <i class="fas fa-table"></i>&nbsp;&nbsp;Nuevo Encuestador
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="content">
        <div class="container-fluid">
            <div style="max-width: 1140px;margin: 0 auto;">
                <form id="FRM_INSERT_PRODUCTO" method="post" action="<?php echo $funciones->direct_sistema(); ?>/modules/productos/insert-update-producto.php" enctype="multipart/form-data">
                    <input type="hidden" name="producto_id" id="">
                    <div class="card card-primary">
                        <div class="card-header">
                            <div class="card-title">Encuestador</div>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Nombre</label>
                                        <input type="text" class="form-control" placeholder="Ingrese nombre" name="producto_code" required>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Correo</label>
                                        <input type="text" class="form-control" placeholder="Ingrese correo" name="producto_code" required>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Usuario</label>
                                        <input type="text" class="form-control" placeholder="Ingrese nombre" name="producto_code" required>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Contraseña</label>
                                        <input type="password" class="form-control" placeholder="Ingrese correo" name="producto_code" required>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <div class="row">
                                <div class="col-md-6">
                                    <a href="list-pollsters" id="btn-save-product" class="btn btn-success btn-md btn-block"><i class="fa fa-check fa-1x"></i>&nbsp;&nbsp;<font>Registrar</font></a>
                                </div>
                                <div class="col-md-6">
                                    <a href="list-pollsters" id="btn-delete-product" class="btn btn-danger btn-block"><i class="fa fa-window-close fa-1x"></i>&nbsp;&nbsp;Cancelar</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>