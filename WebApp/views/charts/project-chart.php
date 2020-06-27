<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
  <!-- Content Header (Page header) -->
  <div class="content-header">
    <div class="container-fluid">
      <div class="row mb-2">
        <div class="col-sm-6">
          <h1 class="m-0 text-dark">Gráficas</h1>
        </div>
        <div class="col-sm-6">
          <!--
          <ol class="breadcrumb float-sm-right">
            <li class="breadcrumb-item"><a href="#">Inicio</a></li>
            <li class="breadcrumb-item active">Starter Page</li>
          </ol>
          -->
        </div> 
      </div><!-- /.row -->
    </div><!-- /.container-fluid -->
  </div>
  <!-- /.content-header -->

  <!-- Main content -->
  <div class="content">
    <div class="container-fluid">
      <div class="row">

        <div class="col-md-12">

          <div class="card card-primary">

            <div class="card-body">
              <div class="row">
                <div class="col-lg-3 col-6">
                  <!-- small box -->
                  <div class>
                    
                  </div>
                  <div class="small-box bg-info">
                    <div class="inner">
                      <h3>15</h3>
                      <p>Total creados</p>
                    </div>
                    <div class="icon">
                      <i class="ion ion-bag"></i>
                    </div>
                    <a href="#" class="small-box-footer text-lg">Creados</a>
                  </div>
                </div>
                <!-- ./col -->
                <div class="col-lg-3 col-6">
                  <!-- small box -->
                  <div class="small-box bg-success">
                    <div class="inner">
                      <h3>5</h3>
                      <p>Usuarios activos</p>
                    </div>
                    <div class="icon">
                      <i class="ion ion-stats-bars"></i>
                    </div>
                    <a href="#" class="small-box-footer text-lg">Encuestadores</a>
                  </div>
                </div>
                <!-- ./col -->
                <div class="col-lg-3 col-6">
                  <!-- small box -->
                  <div class="small-box bg-primary">
                    <div class="inner">
                      <h3>5</h3>
                      <p>Total completados</p>
                    </div>
                    <div class="icon">
                      <i class="ion ion-person-add"></i>
                    </div>
                    <a href="#" class="small-box-footer text-lg">Completados</a>
                  </div>
                </div>
                <!-- ./col -->
                <div class="col-lg-3 col-6">
                  <!-- small box -->
                  <div class="small-box bg-danger">
                    <div class="inner">
                      <h3>4</h3>
                      <p>Total pendientes</p>
                    </div>
                    <div class="icon">
                      <i class="ion ion-pie-graph"></i>
                    </div>
                    <a href="#" class="small-box-footer text-lg">Pendientes</a>
                  </div>
                </div>
                <!-- ./col -->
              </div>

              <div class="row">
                <div class="col-md-12">
                  <div class="card card-success">
                        <div class="card-header ">
                            <div class="card-title">Filtros</div>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-2">
                                    <div class="form-group">
                                        <label>Usuario</label>
                                        <select class="form-control select2" style="width: 100%;" name="cliente_departamento" required>
                                            <option value="">Seleccione...</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-group">
                                        <label>Formulario</label>
                                        <select class="form-control select2" style="width: 100%;" name="cliente_departamento" required>
                                            <option value="">Seleccione...</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-group">
                                        <label>Desde</label>
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text">
                                                    <i class="far fa-calendar-alt"></i>
                                                </span>
                                            </div>
                                            <input type="date" class="form-control" name="cliente_fecreg" value="<?php echo date("Y-m-d"); ?>">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-group">
                                        <label>Hasta</label>
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text">
                                                    <i class="far fa-calendar-alt"></i>
                                                </span>
                                            </div>
                                            <input type="date" class="form-control" name="cliente_fecreg" value="<?php echo date("Y-m-d"); ?>">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-group">
                                        <label>Estado</label>
                                        <select class="form-control select2" style="width: 100%;" name="cliente_departamento" required>
                                            <option value="">Seleccione...</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                  <div class="form-group">
                                      <label>&nbsp;&nbsp;</label>
                                    <button type="submit" id="btn-save-product" class="btn btn-success btn-md btn-block">Filtrar</button>
                                  </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
              </div>

              <div class="row">

                <div class="col-md-6">
                  <div class="card card-primary">
                      <div class="card-header">
                          <div class="card-title">Formularios completados por usuario</div>
                      </div>
                      <div class="card-body">
                          <div id="header">
                              <h2></h2>
                          </div>

                          <div id="content">

                              <div id="bar-chart" style="width: 100%; height: 250px;"></div>

                              <!--
                              <div class="form-inline mt-2">
                                  <p>Intervalo de actualización &nbsp;<input id="updateInterval" type="number" step="100" min="3000" class="form-control" style="text-align: right; width:5em"> &nbsp;milisegundos</p>
                              </div>
                              -->
                          </div>

                      </div>
                  </div>
                </div>

                <div class="col-md-6">
                  <img class="mt-0 mb-1" src="/DGForm/WebApp/img/pie_chart.png" width="100%" height="96%">
                </div>

              </div>
              </div>
              </div>
            </div>

          </div>

        </div>
        <!-- /.col-md-6 -->
      </div>
      <!-- /.row -->
    </div><!-- /.container-fluid -->
  </div>
  <!-- /.content -->
</div>
<!-- /.content-wrapper -->