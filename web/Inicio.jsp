<%-- 
    Document   : Inicio
    Created on : 22-11-2017, 23:01:14
    Author     : Tulio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.usuario==null}">
    <c:redirect url="index.jsp"></c:redirect>
</c:if>
<c:if test="${sessionScope.permiso==3}">
    <c:redirect url="AdmonAgenda.jsp"/>
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
                            <h4 class="page-title">inicio</h4>
                        </div>
                    </div>

                    <div class="page-content-wrapper ">

                        <div class="container">

                            <div class="row">
                                <div class="col-sm-6 col-lg-3">
                                    <div class="panel">
                                        <div class="panel-body p-t-10">
                                            <div class="widget-box-one">
                                                <i class="ti-user widget-box-icon"></i>
                                                <h4 class="panel-title m-b-15 text-muted font-light">Total Clientes</h4>
                                                <h2 class="m-t-0 text-primary m-b-15" id="cliente"><i class="mdi mdi-arrow-up-bold-circle-outline m-r-10" ></i>0</h2>
                                                
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-6 col-lg-3">
                                    <div class="panel">
                                        <div class="panel-body p-t-10">
                                            <div class="widget-box-one">
                                                <i class="ti-eye widget-box-icon"></i>
                                                <h4 class="panel-title text-muted m-b-15 font-light">Trabajadores</h4>
                                                <h2 class="m-t-0 text-primary m-b-15" id="trabajador"><i class="mdi mdi-arrow-up-bold-circle-outline m-r-10"></i>0</h2>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-6 col-lg-3">
                                    <div class="panel">
                                        <div class="panel-body p-t-10">
                                            <div class="widget-box-one">
                                                <i class="ti-stats-up widget-box-icon"></i>
                                                <h4 class="panel-title text-muted m-b-15 font-light">Total ordenes</h4>
                                                <h2 class="m-t-0 text-primary m-b-15" id="ordene"><i class="mdi mdi-arrow-up-bold-circle-outline m-r-10"></i>0</h2>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-6 col-lg-3">
                                    <div class="panel">
                                        <div class="panel-body p-t-10">
                                            <div class="widget-box-one">
                                                <i class="ti-world widget-box-icon"></i>
                                                <h4 class="panel-title text-muted m-b-15 font-light">Total recaudado</h4>
                                                <h2 class="m-t-0 text-primary m-b-15" id="recaudado"><i class="mdi mdi-arrow-up-bold-circle-outline m-r-10"></i>0</h2>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">

                                <div class="col-lg-4">
                                    <div class="panel panel-primary">
                                        <div class="panel-body">
                                            <h4 class="m-t-0">Total agendas</h4>

                                            <ul class="list-inline widget-chart m-t-20 text-center" id="agenda">
                                                <li>
                                                    <h4 class=""><b>0</b></h4>
                                                    <p class="text-muted m-b-0">Total</p>
                                                </li>
                                            </ul>

                                            <div id="donut" style="height: 300px"></div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-lg-4">
                                    <div class="panel panel-primary">
                                        <div class="panel-body">
                                            <h4 class="m-t-0">Recaudado</h4>

                                            <ul class="list-inline widget-chart m-t-20 text-center" id="recau">
                                                <li>
                                                    <h4 class=""><b>0</b></h4>
                                                    <p class="text-muted m-b-0">Total</p>
                                                </li>
                                                <li>
                                                    <h4 class=""><b>0</b></h4>
                                                    <p class="text-muted m-b-0">Descuento apli.</p>
                                                </li>
                                                <li>
                                                    <h4 class=""><b>0</b></h4>
                                                    <p class="text-muted m-b-0">Total final</p>
                                                </li>
                                            </ul>

                                            <div id="bar" style="height: 300px"></div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-lg-4">
                                    <div class="panel panel-primary">
                                        <div class="panel-body">
                                            
                                            <h4 class="m-t-0">Cumpleaños</h4>
                                             <a href="">
                                            <ul class="list-inline widget-chart m-t-20 text-center" id="cumple">
                                                <li>
                                                    <h4 class=""><b>0</b></h4>
                                                    <p class="text-muted m-b-0">Hoy</p>
                                                </li>
                                                <li>
                                                    <h4 class=""><b>0</b></h4>
                                                    <p class="text-muted m-b-0">Mensual</p>
                                                </li>
                                                <li>
                                                    <h4 class=""><b>0</b></h4>
                                                    <p class="text-muted m-b-0">Anual</p>
                                                </li>
                                            </ul>

                                            <div id="donut2" style="height: 300px"></div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div><!-- container -->


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
        <script src="assets/plugins/morris/morris.min.js"></script>
        <script src="assets/plugins/raphael/raphael-min.js"></script>
        <!-- Begin JS only this page -->
        <script src="assets/pages/dashborad.js"></script>
        <!-- End js only page -->
        <!-- Begin JS all page -->
        <script src="assets/js/app.js"></script>
        <!-- End js all page -->
    </body>
</html>
