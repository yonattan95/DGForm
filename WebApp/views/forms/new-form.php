<div class="content-wrapper">
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-0">
                <div class="col-md-12">
                    <div class="m-0 text-dark text-center text-lg">
                        <i class="fas fa-table"></i>&nbsp;&nbsp;Nuevo Formulario
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="content">
        <div class="container-fluid">
            <div style="max-width: 1140px;margin: 0 auto;">
                <form id="frmInsertForm" method="post" action="<?php echo $funciones->direct_sistema(); ?>/modules/forms/insert-update-form.php" enctype="multipart/form-data">
                    <input type="hidden" name="form_id" id="">
                    <input type="hidden" name="user_parent_id" value="<?php echo $_SESSION['loggedInUser']['USERID']?>">
                    <div class="card card-primary">
                        <div class="card-header">
                            <div class="card-title">Formulario</div>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Nombre</label>
                                        <input type="text" class="form-control" placeholder="Ingrese nombre" name="form_name" required>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Descripción</label>
                                        <textarea type="text" class="form-control" placeholder="Ingrese descripción" name="form_description" required></textarea>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-2">
                                    <div class="form-group">
                                        <label>Nro. de Encuestas</label>
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text">
                                                    <i class="fas fa-table"></i>
                                                </span>
                                            </div>
                                            <input type="number" min="0" class="form-control" name="form_quizzes" placeholder="Cantidad" value="1" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Categoría</label>
                                        <select class="form-control select2" style="width: 100%;" name="form_category" required>
                                            <option value="">Seleccione...</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label>Fecha de Inicio</label>
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text">
                                                    <i class="far fa-calendar-alt"></i>
                                                </span>
                                            </div>
                                            <input type="date" class="form-control" name="form_start_date" value="<?php echo date('Y-m-d'); ?>" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label>Fecha de Finalización</label>
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text">
                                                    <i class="far fa-calendar-alt"></i>
                                                </span>
                                            </div>
                                            <input type="date" class="form-control" name="form_end_date" value="<?php echo date('Y-m-d'); ?>" required>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <div class="row">
                                <div class="col-md-6">
                                    <button type="submit" href="detail-form" id="btn-save-product" class="btn btn-success btn-md btn-block"><i class="fa fa-arrow-right fa-1x"></i>&nbsp;&nbsp;<font>Continuar</font></a>
                                </div>
                                <div class="col-md-6">
                                    <a href="list-forms" id="btn-delete-product" class="btn btn-danger btn-block"><i class="fa fa-window-close fa-1x"></i>&nbsp;&nbsp;Cancelar</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>