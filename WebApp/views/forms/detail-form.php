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
                <form id="FRM_INSERT_PRODUCTO" method="post" action="<?php echo $funciones->direct_sistema(); ?>/modules/productos/insert-update-producto.php" enctype="multipart/form-data">
                    <input type="hidden" name="producto_id" id="">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="card card-primary">
                                <div class="card-header">
                                    <div class="card-title">Campos de Formulario</div>
                                </div>
                                <div class="card-body">
                                    <div class="card card-secondary">
                                      <div class="card-header">
                                        <h3 class="card-title">Item 1</h3>

                                        <div class="card-tools">
                                          <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i>
                                          </button>
                                        </div>
                                        <!-- /.card-tools -->
                                      </div>
                                      <!-- /.card-header -->
                                      <div class="card-body" style="display: block;">
                                        <p>¿Pregunta ABC?</p>
                                        <p>Tipo: Texto corto</p>
                                      </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <div class="card card-secondary">
                                      <div class="card-header">
                                        <h3 class="card-title">Item 2</h3>
                                        <div class="card-tools">
                                          <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i>
                                          </button>
                                        </div>
                                        <!-- /.card-tools -->
                                      </div>
                                      <!-- /.card-header -->
                                      <div class="card-body" style="display: block;">
                                        <p>¿Pregunta XYZ?</p>
                                        <p>Tipo: Numeros enteros</p>
                                      </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <div class="card card-secondary">
                                      <div class="card-header">
                                        <h3 class="card-title">Item 3</h3>
                                        <div class="card-tools">
                                          <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i>
                                          </button>
                                        </div>
                                        <!-- /.card-tools -->
                                      </div>
                                      <!-- /.card-header -->
                                      <div class="card-body" style="display: block;">
                                        <p>¿Pregunta Final?</p>
                                        <p>Tipo: Checklist</p>
                                      </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="card card-danger">
                                <div class="card-header">
                                    <div class="card-title">Componentes de Formularios</div>
                                </div>
                                <div class="card-body">

                                    <div class="card-body p-0">
                                        <div class="card card-warning">
                                          <div class="card-header">
                                            <h3 class="card-title">Textos</h3>
                                            <div class="card-tools">
                                              <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i>
                                              </button>
                                            </div>
                                          </div>
                                          <!-- /.card-header -->
                                          <div class="card-body" style="display: block;">
                                            <button type="button" class="btn btn-block btn-primary">Nuevo Texto Corto</button>
                                            <button type="button" class="btn btn-block btn-primary">Nuevo Texto Largo</button>
                                          </div>
                                        </div>
                                    </div>

                                    <div class="card-body p-0">
                                        <div class="card card-warning">
                                          <div class="card-header">
                                            <h3 class="card-title">Listas</h3>
                                            <div class="card-tools">
                                              <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i>
                                              </button>
                                            </div>
                                          </div>
                                          <!-- /.card-header -->
                                          <div class="card-body" style="display: block;">
                                            <button type="button" class="btn btn-block btn-primary">Checklist</button>
                                            <button type="button" class="btn btn-block btn-primary">Opciones Múltiples</button>
                                          </div>
                                        </div>
                                    </div>

                                    <div class="card-body p-0">
                                        <div class="card card-warning">
                                          <div class="card-header">
                                            <h3 class="card-title">Fechas</h3>
                                            <div class="card-tools">
                                              <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i>
                                              </button>
                                            </div>
                                          </div>
                                          <!-- /.card-header -->
                                          <div class="card-body" style="display: block;">
                                            <button type="button" class="btn btn-block btn-primary">Fecha Corta</button>
                                            <button type="button" class="btn btn-block btn-primary">Fecha Completa</button>
                                          </div>
                                        </div>
                                    </div>

                                    <div class="card-body p-0">
                                        <div class="card card-warning">
                                          <div class="card-header">
                                            <h3 class="card-title">Números</h3>
                                            <div class="card-tools">
                                              <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i>
                                              </button>
                                            </div>
                                          </div>
                                          <!-- /.card-header -->
                                          <div class="card-body" style="display: block;">
                                            <button type="button" class="btn btn-block btn-primary">Enteros</button>
                                            <button type="button" class="btn btn-block btn-primary">Decimales</button>
                                          </div>
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
                                    <a href="list-forms" id="btn-save-product" class="btn btn-success btn-md btn-block"><i class="fa fa-arrow-right fa-1x"></i>&nbsp;&nbsp;<font>Continuar</font></a>
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