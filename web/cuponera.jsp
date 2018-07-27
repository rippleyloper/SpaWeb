<%-- 
    Document   : Base
    Created on : 23-11-2017, 0:59:29
    Author     : Tulio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Pagina principal</title>
        <meta content="" name="description" />
        <meta content="" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- Icono de pestaña -->
        <!-- <link rel="shortcut icon" href="assets/images/favicon.ico"> -->
        <!--Morris Chart CSS -->
        <link rel="stylesheet" href="assets/plugins/morris/morris.css">

        <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="assets/css/icons.css" rel="stylesheet" type="text/css">
        <link href="assets/css/style.css" rel="stylesheet" type="text/css">
        <link href="assets/plugins/bootstrap-sweetalert/sweet-alert.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/fullcalendar.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/smart_wizard_theme_arrows.min.css" rel="stylesheet" type="text/css"/>
    </head>


    <body class="fixed-left">

        <!-- Begin page -->
        <div id="wrapper">

            <!-- Top Bar Start -->
                <%@include file="include/nav.jsp" %>
        </div>
            <!-- Top Bar End -->


            <!-- ========== Left Sidebar Start ========== -->

            <div class="left side-menu">
                <%@include file="include/sidebar.jsp" %>
                <!-- end sidebarinner -->
            </div>
            <!-- Left Sidebar End -->

            <!-- Start right Content here -->

            <div class="content-page">
                <!-- Start content -->
                <div class="content">

                    <div class="">
                        <div class="page-header-title">
                            <h4 class="page-title">Dashboard</h4>
                        </div>
                    </div>
                    <div class="page-content-wrapper ">
                        <div class="container">
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg">Agregar cuponera</button>

                            <div class="modal fade bs-example-modal-lg" id="addCupo" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" data-backdrop="static" data-keyboard="false">
                              <div class="modal-dialog modal-lg" role="document">
                                <div class="modal-content">
                                  <div class="modal-header">
                                      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                      <h4 class="modal-title" id="myLargeModalLabel">Registro de cuponera</h4>
                                  </div>
                                  <div class="modal-body">
                                      <!-- progreso -->
                                      <div class="row">
                                         <div id="wizard" class="sw-main sw-theme-arrows">
                                             <ul>
                                                <li><a href="#step-1">Registro<br /><small>Creación de la cuponera</small></a></li>
                                                <li><a href="#step-2">Asignación<br /><small>Asignación de ageda</small></a></li>
                                                <li><a href="#step-3">Totales<br /><small>Visualización de totales</small></a></li>
                                                <li><a href="#step-4">Confirmación<br /><small>Confirmación de la cuponera</small></a></li>
                                            </ul>
                                            <div class="sw-container tab-content" style="min-height: 377px;">
                                                
                                                <div id="step-1" class="step-content" style="display:none;">
                                                    <h2>Creación de la cuponera</h2>
                                                    <div id="form-step-0" role="form" data-toggle="validator">
                                                        <div class="form-group">
                                                            <div class="container">
                                                                <label for="tipo">tipo cuponera</label>
                                                                <select name="tipo" id="tipo" required="true" class="form-control">
                                                                    <option value="">Seleccione</option>
                                                                </select>
                                                                <div class="help-block with-errors"></div>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <div class="container">
                                                                <label for="codigo">Codigo cuponera</label>
                                                                <input type="text" id="codigo" name="codigo" class="form-control" required="true" placeholder="Codigo de la cuponera" minlength="4" maxlength="20">
                                                                <div class="help-block with-errors"></div>
                                                            </div>
                                                        </div>
                                                        <div class="container">
                                                            <div class="col-md-9 col-xs-9">
                                                                    <div class="form-group">      
                                                                        <label for="dni">DNI</label>
                                                                        <input type="text" pattern="^[0-9]{1,}$" name="dni" id="dni" class="form-control " placeholder="Ingrese dni" required="true" minlength="6" maxlength="10">
                                                                        <div class="help-block with-errors"></div>
                                                                    </div> 
                                                                </div>
                                                                <div class="col-md-3 col-xs-3">
                                                                    <div class="form-group">
                                                                            <label for="validar">Validar</label>
                                                                            <button class="btn btn-success form-control" id="validar">
                                                                                Validar
                                                                            </button>
                                                                    </div>
                                                                </div>
                                                        </div>
                                                        <div class="container">
                                                            <div class="col-md-6 col-xs-6" >
                                                                <div class="form-group">
                                                                        <label for="fechaI">Fecha inicio</label>
                                                                        <input type="date" name="fechaC" id="fechaC" class="form-control" required="true">
                                                                        <div class="help-block with-errors"></div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-6 col-xs-6" >
                                                                <div class="form-group">
                                                                        <label for="duracion">duracion (Meses)</label>
                                                                        <input type="number" name="duracion" id="duracion" min="0" max="12" value="12" class="form-control" required="true">
                                                                        <div class="help-block with-errors"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div id="step-2" class="step-content" style="display:none;">
                                                  <h2>Asignacioón de agendas</h2> 
                                                    <style>
                                                        .ScrollStyle
                                                        {
                                                            max-height: 220px;
                                                            overflow-y: scroll;
                                                        }
                                                    </style>
                                                  <div class="row ScrollStyle">
                                                      <table class="table table-responsive "   id="tblSer">
                                                          <thead>
                                                              <tr>
                                                                  <td>Nombre de servicio</td>
                                                                  <td>Fecha de servicio</td>
                                                                  <td>Duración</td>
                                                              </tr>
                                                          </thead>
                                                          <tbody>
                                                                
                                                          </tbody>
                                                      </table>
                                                  </div>
                                                  <div class="row">
                                                      <div class="col-md-3 col-xs-3">
                                                          <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modalAgregar">Agrear servicio</button>
                                                      </div>
                                                  </div>
                                                  
                                                <div id="modalAgregar" class="modal fade " role="dialog">
                                                    <div class="modal-dialog modal-lg" data-backdrop="static" data-keyboard="false" style="background-color: #ECECEC;">

                                                    <!-- Modal content-->
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" onclick="$('#modalAgregar').modal('hide');">&times;</button>
                                                                <h4 class="modal-title">Seleccione el servicio a agregar</h4>
                                                            </div>
                                                            <div class="modal-body">
                                                                <div class="container-fluid">
                                                                    <div class="sw-main sw-theme-arrows" id="wizard2">
                                                                        <ul class="nav nav-tabs step-anchor">
                                                                            <li>
                                                                                <a href="#step1">
                                                                                    Seleccion de servicio
                                                                                </a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="#step2">
                                                                                    Calendario
                                                                                </a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="#step3">
                                                                                    Finalización
                                                                                </a>
                                                                            </li>
                                                                        </ul>
                                                                        <div class="sw-container tab-content">
                                                                            <!-- inicio paso 1 -->
                                                                            <div class="step-content" style="display:none;" id="step1">
                                                                                <div class="container">
                                                                                    
                                                                                        <h2>Seleccion de servicio</h2>
                                                                                        <div id="form-step0" role="form" data-toggle="validator">
                                                                                    <div class="form-group">
                                                                                        <div class="col-md-6 col-xs-6">
                                                                                            <label for="tipoS">Tipo de servicio</label>
                                                                                            <select name="tServicio" required="true" id="tServicio" class="form-control">
                                                                                                <option value="">
                                                                                                    Seleccione un tipo de servicio
                                                                                                </option>
                                                                                            </select>
                                                                                        </div>
                                                                                        <div class="help-block with-errors"></div>
                                                                                    </div>
                                                                                    <div class="form-group">
                                                                                        <div class="col-md-6 col-xs-6">
                                                                                            <label for="tipoC">Categoria</label>
                                                                                            <select name="categoria" required="true" id="categoria" class="form-control">
                                                                                                <option value="">
                                                                                                    Seleccione una categoria
                                                                                                </option>
                                                                                            </select>
                                                                                        </div>
                                                                                        <div class="help-block with-errors"></div>
                                                                                    </div>
                                                                                    <div class="form-group">
                                                                                        <div class="col-md-12 col-xs-12">
                                                                                            <label for="servicio">Servicio</label>
                                                                                            <select name="servicio" required="true" id="servicio" class="form-control">
                                                                                                <option value="">
                                                                                                    Seleccione un servicio
                                                                                                </option>
                                                                                            </select>
                                                                                        </div>
                                                                                        <div class="help-block with-errors"></div>
                                                                                    </div>
                                                                                </div>
                                                                                </div>
                                                                            </div>
                                                                            <!-- inicio paso 2 -->
                                                                            <div class="step-content" style="display:none;" id="step2">
                                                                               <div class="container">
                                                                                   <div class="col-md-8 col-xs-8 col-md-offset-2 col-xs-offset-2">
                                                                                       <div id="calendario">
                                                                                           
                                                                                       </div>
                                                                                   </div>
                                                                               </div> 
                                                                            </div>
                                                                            <!-- inicio paso 3 -->
                                                                            <div class="step-content" style="display:none;" id="step3">
                                                                                <div class="container">
                                                                                    <div id="form-step2" role="form" data-toggle="validator">
                                                                                        <div class="form-group">
                                                                                            <div class="col-md-6 col-xs-6">
                                                                                                <label for="fecha">Fecha Inicio</label>
                                                                                                <input type="text" required="true" class="form-control" disabled="true" readonly="true" id="fechaI" name="fechaI">
                                                                                                <div class="help-block with-errors"></div>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="form-group">
                                                                                            <div class="col-md-3 col-xs-3">
                                                                                                <label for="Repetir">Repetir</label>
                                                                                                <input type="number" min="1" class="form-control" max="10" required="true" value="3" name="repetir" id="repetir">
                                                                                                <div class="help-block with-errors"></div>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="form-group">
                                                                                            <div class="col-md-3 col-xs-3">
                                                                                                <label for="dias">descanso (días)</label>
                                                                                                <input type="number" min="10" max="45" value="10" required="true" id="descanse" name="descanso" class="form-control">
                                                                                                <div class="help-block with-errors"></div>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="form-group">
                                                                                            <div class="col-md-6 col-xs-6">
                                                                                                <label for="categoriaT">Categoria Trabajador</label>
                                                                                                <select name="categoriaT" required="true" id="categoriaT" class="form-control">
                                                                                                    <option value="">Seleccione una categoria</option>
                                                                                                </select>
                                                                                                <div class="help-block with-errors"></div>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="form-group">
                                                                                            <div class="col-md-6 col-xs-6">
                                                                                                <label for="Trabajador">Trabajador</label>
                                                                                                <select name="Trabajador" required="true" id="trabajador" class="form-control">
                                                                                                    <option value="">Seleccione un trabajador</option>
                                                                                                </select>
                                                                                                <div class="help-block with-errors"></div>
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

                                                    </div>
                                                </div>
                                                </div>
                                                <div id="step-3" class="step-content" style="display:none;">
                                                    
                                                </div>
                                                <div id="step-4" class="step-content" style="display:none;">
                                                    
                                                </div>

                                            </div>
                                         </div>  
                                      </div>
                                      <!-- end progreso -->
                                  </div>
                                  <div class="modal-footer">
                                      
                                  </div>
                                </div>
                              </div>
                            </div>    
                            

                        </div>
                    </div> <!-- Page content Wrapper -->

                </div> <!-- content -->

                <footer class="footer">
                     © 2017 Upbond - By Themesdesign.
                </footer>

            </div>
            <!-- End Right content here -->

        </div>
        <!-- END wrapper -->


        <!-- jQuery  -->
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/modernizr.min.js"></script>
        <script src="assets/js/detect.js"></script>
        <script src="assets/js/fastclick.js"></script>
        <script src="assets/js/jquery.slimscroll.js"></script>
        <script src="assets/js/jquery.blockUI.js"></script>
        <script src="assets/js/waves.js"></script>
        <script src="assets/js/wow.min.js"></script>
        <script src="assets/js/jquery.nicescroll.js"></script>
        <script src="assets/js/jquery.scrollTo.min.js"></script>
        <script src="assets/js/jquery.smartWizard.min.js" type="text/javascript"></script>
        <!--Morris Chart-->
        <script src="assets/plugins/bootstrap-sweetalert/sweet-alert.min.js" type="text/javascript"></script>
        <script src="assets/js/validator.min.js" type="text/javascript"></script>
        <script src="assets/js/moment.min.js" type="text/javascript"></script>
        <script src="assets/js/fullcalendar.min.js" type="text/javascript"></script>
        <!-- Begin JS only this page -->
        <script src="assets/pages/cuponera.js" type="text/javascript"></script>
        <!-- End js only page -->
        <!-- Begin JS all page -->
        <script src="assets/js/app.js"></script>
        <!-- End js all page -->
    </body>
</html>

