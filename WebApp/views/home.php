<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
  <!-- Content Header (Page header) -->
  <div class="content-header">
    <div class="container-fluid">
      <div class="row mb-2">
        <div class="col-sm-6">
          <h1 class="m-0 text-dark">Inicio</h1>
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

          <div class="card card-primary card-outline">
            <div class="card-header">
              <h5 class="m-1"><i class="far fa-hand-spock"></i> ¡Bienvenido(a)! <strong><?php echo $_SESSION['loggedInUser']['NAME']; ?></strong></h5>
            </div>

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
                      <h3>9</h3>
                      <p>Usuarios activos</p>
                    </div>
                    <div class="icon">
                      <i class="ion ion-stats-bars"></i>
                    </div>
                    <a href="#" class="small-box-footer text-lg">Asignados</a>
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
                <div class="col-md-12"> 
                  <div class="card">
                    <div class="card-header border-0 bg-danger">
                      <h3 class="card-title">Actividad</h3>
                      <div class="card-tools">
                        <a href="#" class="btn btn-tool btn-sm">
                          <i class="fas fa-bars"></i>
                        </a>
                      </div>
                    </div>
                    <div class="card-body table-responsive p-0">
                      <table class="table table-striped table-valign-middle">
                        <thead>
                        <tr>
                          <th>Fecha</th>
                          <th>Nombre de Formulario</th>
                          <th>Código</th>
                          <th>Usuario</th>
                          <th>Opciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                          <td>
                            01/05/2020 15:30
                          </td>
                          <td>
                            Encuesta 1
                          </td>
                          <td>1352878</td>
                          <td>
                            Christian Cano
                          </td>
                          <td>
                            <a href="#" class="text-muted">
                              <i class="fas fa-search"></i>
                            </a>
                          </td>
                        </tr>
                        <tr>
                          <td>
                            15/05/2020 14:15
                          </td>
                          <td>
                             Encuesta 1
                          </td>
                          <td>1254878</td>
                          <td>
                            Eduardo Tenorio
                          </td>
                          <td>
                            <a href="#" class="text-muted">
                              <i class="fas fa-search"></i>
                            </a>
                          </td>
                        </tr>
                        <tr>
                          <td>
                            30/05/2020 12:30
                          </td>
                          <td>
                             Encuesta 3
                          </td>
                          <td>1252378</td>
                          <td>
                            Jean Paul Ferro
                          </td>
                          <td>
                            <a href="#" class="text-muted">
                              <i class="fas fa-search"></i>
                            </a>
                          </td>
                        </tr>
                        <tr>
                          <td>
                            12/06/2020 09:45
                          </td>
                          <td>
                             Encuesta 4
                          </td>
                          <td>1134872</td>
                          <td>
                            Yonattan Vallejos
                          </td>
                          <td>
                            <a href="#" class="text-muted">
                              <i class="fas fa-search"></i>
                            </a>
                          </td>
                        </tr>
                        </tbody>
                      </table>
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