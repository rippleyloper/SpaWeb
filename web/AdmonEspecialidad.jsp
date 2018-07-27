<%-- 
    Document   : AdmonCategorias
    Created on : 26-11-2017, 19:09:24
    Author     : Tulio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.usuario==null}">
    <c:redirect url="index.jsp"></c:redirect>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
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
                            <h4 class="page-title">Administración Especialidad</h4>
                        </div>
                    </div>

                    <div class="page-content-wrapper ">

                        <div class="container">
                            <!-- Opcions -->
                            <div class="row">
                                <div class="col-md-10 col-md-offset-1 col-xs-10 col-xs-offset-1">
                                    <div class="col-md-3 col-xs-3 col-md-offset-4 col-xs-offset-4">
                                        <button class="btn btn-info form-control" id="btnAgregar" >Agregar</button>
                                    </div>
                                </div>
                                <br>
                            </div>
                            <div class="row">
                            <!-- Fin opciones -->
                            </div>
                            <!-- Tabla -->
                            <div class="row">
                                <div class="col-md-10 col-md-offset-1 col-xs-10 col-xs-offset-1">
                                    <table class="table table-default" id="tabla">
                                        <thead>
                                            <th>Id</th>
                                            <th>Nombre</th>
                                            <th colspan="2">Acción</th>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <!-- End tabla -->
                            <!-- Modal Agregar -->
                            <div class="row">
                                  <div class="modal fade" id="modalAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                  <div class="modal-dialog" role="document">
                                    Modal contenido    <!--  -->
                                    <div class="modal-content">
                                        <!-- Modal header -->
                                      <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title" id="myModalLabel">Agregar especialidad</h4>
                                      </div>
                                        <!-- Modal header -->
                                        <!-- Modal body -->
                                      <div class="modal-body">
                                        <div class="container-flui">
                                            <form action="" method="POST" id="addCate">
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label for="nombre">Nombre</label>
                                                        <input type="text" id="nombre" name="nombre" class="form-control" required="true">
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-4 col-md-offset-4 col-xs-4 col-xs-offset-4">
                                                        <input type="button" value="1" id="btnSubmit" class="btn btn-success form-control">
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                      </div>
                                        <!-- Modal body -->
                                        <!-- Modal footer -->
                                      <div class="modal-footer">
                                        <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
                                      </div>
                                      <!-- Modal footer -->
                                    </div>
                                    <!-- Modal contenido -->
                                  </div>
                                </div>
                            </div>
                            <!-- End modal Agregar -->
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

        <!--Morris Chart-->
        
        <!-- Begin JS only this page -->
        
        <!-- End js only page -->
        <script src="assets/pages/especialidad.js" type="text/javascript"></script>
        <!-- Begin JS all page -->
        <script src="assets/plugins/bootstrap-sweetalert/sweet-alert.min.js" type="text/javascript"></script>
        <script src="assets/js/app.js"></script>
       
        <!-- End js all page -->
    </body>
</html>

