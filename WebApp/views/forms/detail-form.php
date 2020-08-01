<div class="content-wrapper">
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-0">
                <div class="col-md-12">
                    <div class="m-0 text-dark text-center text-lg">
                        <i class="fas fa-table"></i>&nbsp;&nbsp;Detalle de Formulario
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="content">
        <div class="container-fluid">
            <div style="max-width: 1140px;margin: 0 auto;">
                <form id="frmInsertFormDetail" method="post" action="<?php echo $funciones->direct_sistema(); ?>/modules/forms/insert-update-question.php" enctype="multipart/form-data">
                    <input type="hidden" name="token" value="<?php echo $_SESSION['loggedInUser']['TOKEN'];?>">
                    <input type="hidden" name="form_id" value="<?php echo $_SESSION['FORMID'];?>">
                    <input type="hidden" name="question_number" value="1">
                    <div class="row">
                      <div class="col-md-12">
                        <div class="card card-danger">
                            <div class="card-header">
                                <div class="card-title">Items de Formularios</div>
                            </div>
                            <div class="card-body">
                              <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Nombre</label>
                                        <input type="text" class="form-control" placeholder="Ingrese nombre" name="question_name" required>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Descripción</label>
                                        <textarea type="text" class="form-control" placeholder="Ingrese descripción" name="question_description" required></textarea>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4">
                                  <div class="form-group">
                                    <label>Tipo</label>
                                    <select class="form-control select2" style="width: 100%;" name="question_type" required>
                                        <option value="">Seleccione...</option>
                                    </select>
                                  </div>
                                </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-12">
                        <button type="submit" href="detail-form" id="btn-save-product" class="btn btn-success btn-md btn-block"><i class="fa fa-arrow-right fa-1x"></i>&nbsp;&nbsp;<font>Añadir</font></button>
                      </div>
                    </div>
                </form>
                <div class="card mt-2">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table id="table-questions" class="table table-bordered table-hover" style="width: 100%">
                                <thead>
                                  <tr>
                                    <th>#</th>
                                    <th>Cód.</th>
                                    <th>Nombre</th>
                                    <th>Descripción</th>
                                    <th>Tipo</th>
                                  </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="row">
                  <div class="col-12">
                      <div class="row">
                          <div class="col-md-6">
                              <a href="list-forms" id="btn-save-product" class="btn btn-success btn-md btn-block"><i class="fa fa-save fa-1x"></i>&nbsp;&nbsp;<font>Finalizar</font></a>
                          </div>
                          <div class="col-md-6">
                              <a href="list-forms" id="btn-delete-product" class="btn btn-danger btn-block"><i class="fa fa-window-close fa-1x"></i>&nbsp;&nbsp;Cancelar</a>
                          </div>
                      </div>
                  </div>
                </div>
            </div>
        </div>
    </div>
</div>