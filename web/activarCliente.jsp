<%-- 
    Document   : Base
    Created on : 23-11-2017, 0:59:29
    Author     : Tulio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.usuario==null}">
    <c:redirect url="index.jsp"></c:redirect>
</c:if>
<c:if test="${sessionScope.clientes==null}">
    <c:redirect url="ServletCliente?data=8"/>
</c:if>
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
                            <h4 class="page-title">Administración cliente</h4>
                        </div>
                    </div>

                    <div class="page-content-wrapper ">

                        <div class="container">
                            <div class="row">
                                <center>
                                    <h2>Cliente eliminados</h2>
                                </center>
                            </div>
                            <div class="row">
                                <div class="col-md-12 ">
                                    <table class="table table-default table-responsive">
                                        <tr>
                                            <th>DNI</th>
                                            <th>Nombre</th>
                                            <th>Apellido</th>
                                            <th>Genero</th>
                                            <th colspan="3">Acciones</th>
                                        </tr>
                                                            <c:forEach items="${sessionScope.clientes}" var="clie">
                                                                <tr>
                                                                    <td>${clie.getDni()}</td>
                                                                    <td>${clie.getNombre()}</td>
                                                                    <td>${clie.getApellido()}</td>
                                                                    <td>
                                                                        <c:if test="${clie.getIdGenero()==1}">
                                                                            <c:out value="Hombre"/>
                                                                        </c:if>
                                                                        <c:if test="${clie.getIdGenero()==2}">
                                                                            <c:out value="Mujer"/>
                                                                        </c:if>
                                                                    </td>
                                                                   <td><input type="button" class="btn btn-primary" value="Activar" onclick="window.location.href='ServletCliente?data=9&dni=${clie.getDni()}'"></td>
                                                                </tr>
                                                            </c:forEach>   
                                                            <c:set scope="session" var="clientes"  value="${null}" />
                                    </table>
                                </div>
                            </div>
                            <!-- container -->
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
        <c:if test="${sessionScope.msg!=null}">
            <script>
                var msg=[];
                msg.push({name:"id",value:${sessionScope.msg.getId()}});
                msg.push({name:"nombre",value:"${sessionScope.msg.getNombres()}"});
            </script>
            <c:set var="msg" scope="session" value="${null}"/>
        </c:if>
        <!-- Begin JS only this page -->
        <script>
            $(document).ready(function(){
               if(typeof msg !== 'undefined'){
                   if(msg[0].value==1){
                       swal("Correcto",msg[1].value,"success");
                   }
                   if(msg[0].value==2){
                       swal("Error",msg[1].value,"error");
                   }
               } 
            });
        </script>
        <!-- Begin JS only this page -->
        
        <!-- End js only page -->
        <!-- Begin JS all page -->
        <script src="assets/js/app.js"></script>
        <!-- End js all page -->
    </body>
</html>


