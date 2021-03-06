  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="<?php echo $funciones->direct_paginas()."home" ?>" class="brand-link">
      <img src="<?php echo $funciones->direct_sistema(); ?>/img/favicons/form-32x32.png" alt="DigiForms" class="brand-image elevation-1"
           style="opacity: .8">
      <span class="brand-text font-weight-dark text-cyan">Digi<strong>Forms</strong></span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user panel (optional)
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="image">
          <img src="../img/avatar5.png" class="img-circle elevation-2" alt="User Image">
        </div>
        <div class="info">
          <a href="#" class="d-block">Usuario XYZ</a>
        </div>
      </div>
      -->
      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
          <li class="nav-item">
            <a href="<?php echo $funciones->direct_paginas()."home" ?>" class="nav-link active">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                Inicio
                <span class="right badge badge-danger"></span>
              </p>
            </a>
          </li>

          <!-- 
          <li class="nav-item">
            <a href="<?php echo $funciones->direct_paginas()."charts/project-chart" ?>" class="nav-link">
              <i class="nav-icon fas fa-chart-bar"></i>
              <p>Gráficas</p>
            </a>
          </li>
          -->

          <li class="nav-item">
            <a href="<?php echo $funciones->direct_paginas()."forms/list-forms" ?>" class="nav-link">
              <i class="nav-icon fas fa-table"></i>
              <p>Formularios</p>
            </a>
          </li>

          <li class="nav-item">
            <a href="<?php echo $funciones->direct_paginas()."users/list-users" ?>" class="nav-link">
              <i class="nav-icon fas fa-users"></i>
              <p>Usuarios</p>
            </a>
          </li>

          <li class="nav-item">
            <a href="<?php echo $funciones->direct_paginas()."interviewers/list-interviewers" ?>" class="nav-link">
              <i class="nav-icon fas fa-users"></i>
              <p>Encuestadores</p>
            </a>
          </li>

          <!-- 
          <li class="nav-item">
            <a href="<?php echo $funciones->direct_paginas()."assignments/list-assignments" ?>" class="nav-link">
              <i class="nav-icon fas fa-user-plus"></i>
              <p>Asignar Formularios</p>
            </a>
          </li>
          -->

          <!-- 
          <li class="nav-item">
            <a href="<?php echo $funciones->direct_paginas()."settings/project" ?>" class="nav-link">
              <i class="nav-icon fas fa-cog"></i>
              <p>Ajustes</p>
            </a>
          </li>
          -->

          <li class="nav-item">
            <a href="<?php echo $funciones->direct_paginas()."app/download" ?>" class="nav-link">
              <i class="nav-icon fas fa-arrow-circle-down"></i>
              <p>Descargar APP</p>
            </a>
          </li>

          <!--

          <li class="nav-header">VENTAS</li>

          <li class="nav-item has-treeview menu-close">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-file-invoice-dollar"></i>
              <p>
                Cotización
                <i class="right fas fa-angle-left nav-icon"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="<?php echo $funciones->direct_paginas()."cotizacion/registro-cotizacion" ?>" class="nav-link">
                  <i class="nav-icon far fa-circle text-danger"></i>
                  <p>Registro de Cotización</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="<?php echo $funciones->direct_paginas()."cotizacion/resumen-cotizaciones" ?>" class="nav-link">
                  <i class="nav-icon far fa-circle text-info"></i>
                  <p>Resumen Cotizaciones</p>
                </a>
              </li>
            </ul>
          </li>

          <li class="nav-item has-treeview menu-close">
            <a href="#" class="nav-link">
            <i class="nav-icon fas fa-file-invoice"></i>
              <p>
              Facturación
                <i class="right fas fa-angle-left nav-icon"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="<?php echo $funciones->direct_paginas()."facturacion/registro-factura" ?>" class="nav-link">
                  <i class="nav-icon far fa-circle text-danger"></i>
                  <p>Registro de Factura</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="<?php echo $funciones->direct_paginas()."facturacion/resumen-facturas" ?>" class="nav-link">
                  <i class="nav-icon far fa-circle text-info"></i>
                  <p>Resumen de Facturas</p>
                </a>
              </li>
            </ul>
          </li>

          <li class="nav-header">COMPRAS / SERVICIOS</li>

          <li class="nav-item has-treeview menu-close">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-receipt"></i>
              <p>
                Órdenes
                <i class="right fas fa-angle-left nav-icon"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="<?php echo $funciones->direct_paginas()."ordenes/orden-de-compra" ?>" class="nav-link">
                  <i class="nav-icon far fa-circle text-danger"></i>
                  <p>Orden de Compra</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="<?php echo $funciones->direct_paginas()."ordenes/orden-de-servicio" ?>" class="nav-link">
                  <i class="nav-icon far fa-circle text-danger"></i>
                  <p>Orden de Servicio</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="<?php echo $funciones->direct_paginas()."ordenes/resumen-ordenes" ?>" class="nav-link">
                  <i class="nav-icon far fa-circle text-info"></i>
                  <p>Resumen de Órdenes</p>
                </a>
              </li>
            </ul>
          </li>

          <li class="nav-header"><i class="nav-icon fas fa-chart-line"></i> REPORTES</li>

          <li class="nav-item">
            <a href="<?php echo $funciones->direct_paginas()."reportes/score-ventas" ?>" class="nav-link">
              <i class="fas fa-chart-bar nav-icon"></i>
              <p>Score de Ventas</p>
            </a>
          </li>
          <li class="nav-item">
            <a href="<?php echo $funciones->direct_paginas()."reportes/periodo-ventas" ?>" class="nav-link">
              <i class="fas fa-chart-bar nav-icon"></i>
              <p>Ventas por Periodo</p>
            </a>
          </li>

          <li class="nav-header"><i class="nav-icon fas fa-layer-group"></i> SISTEMA</li>

          <li class="nav-item">
            <a href="<?php echo $funciones->direct_paginas()."empleados/registro-empleado" ?>" class="nav-link">
              <i class="nav-icon fas fa-user-tie"></i>
              <p>Empleados</p>
            </a>
          </li>

          <li class="nav-item">
            <a href="<?php echo $funciones->direct_paginas()."usuarios/registro-usuario" ?>" class="nav-link">
              <i class="nav-icon fas fa-user-cog"></i>
              <p>Usuarios</p>
            </a>
          </li>

          -->

        </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>