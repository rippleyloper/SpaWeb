<%-- 
    Document   : AdmonUsuario
    Created on : 23-11-2017, 1:49:59
    Author     : Tulio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.usuario==null}">
    <c:redirect url="index.jsp"></c:redirect>
</c:if>
<!DOCTYPE html>
<c:if test="${sessionScope.trabajadores==null && sessionScope.usuarios==null}">
    <c:redirect url="ServletUsuario?data=1"/>
</c:if>
<c:if test="${sessionScope.msg!=null}">
    <script>
        var msg=[];
        msg.push({name:"id",value:${sessionScope.msg.getId()}});
        msg.push({name:"nombre",value:"${sessionScope.msg.getNombres()}"});
    </script>
    <c:set var="msg" scope="session" value="${null}"/>
</c:if>
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
        
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="assets/css/icons.css" rel="stylesheet" type="text/css">
        <link href="assets/css/style.css" rel="stylesheet" type="text/css">
        <link href="assets/plugins/bootstrap-sweetalert/sweet-alert.css" rel="stylesheet" type="text/css"/>
        <script src="assets/plugins/bootstrap-sweetalert/sweet-alert.min.js" type="text/javascript"></script>
        
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
                            <h4 class="page-title">Administración Usuario</h4>
                        </div>
                    </div>

                    <div class="page-content-wrapper ">

                        <div class="container">
                            <div class="row">
                                <div class="col-md-4">
                                    <table class="table-responsive table table-default" id="trabajadores">
                                        <caption>Trabajadores</caption>
                                        <tr>
                                            <th>
                                                DNI
                                            </th>
                                            <th>
                                                Nombre
                                            </th>
                                            <th>
                                                Apellido
                                            </th>
                                        </tr>
                                        <c:forEach items="${sessionScope.trabajadores}" var="tra">
                                            <tr>
                                                <th>
                                                    ${tra.getDni()}
                                                </th>
                                                <th>
                                                    ${tra.getNombre()}
                                                </th>
                                                <th>
                                                    ${tra.getApellido()}
                                                </th>
                                            </tr>
                                        </c:forEach>
                                        <c:set var="trabajadores" scope="session" value="${null}"/>
                                    </table>
                                </div>
                                <div class="col-md-4">
                                        <form action="ServletUsuario" method="POST" id="btnAccion2" class="form-horizontal">
                                                <div class="form-group row">
                                                        <label for="DNI" class="col-md-2 col-xs-2 control-label">DNI</label>
                                                        <div class="col-md-6 col-xs-6">
                                                                <input type="text" id="DNI" name="dni" class="form-control">
                                                        </div>
                                                        <div class="col-md-4 col-xs-4">
                                                                <input type="button" class="form-control btn-info" value="Validar" id="validar" disabled="true">
                                                        </div>
                                                </div>
                                                <div class="form-group row">
                                                        <label for="rol" class="col-md-2 col-xs-2 control-label">Rol</label>
                                                        <div class="col-md-10 col-xs-10">
                                                                <select name="rol" id="rol" class="form-control"> 
                                                                        <option value="0">Seleccione</option>
                                                                </select>
                                                        </div>
                                                </div>
                                                <div class="form-group row">
                                                        <label for="pass1" class="control-label col-md-4 col-xs-4">Contraseña</label>
                                                        <div class="col-md-8 col-xs-8">
                                                                <input type="password" id="pass1" name="password" class="form-control ">
                                                        </div>
                                                </div>
                                                <div class="form-group row">
                                                        <label for="pass2" class="col-md-4 col-xs-4 control-label">Repetir contraseña</label>
                                                        <div class="col-md-8 col-xs-8">
                                                                <input type="password" name="password2" class="form-control">
                                                        </div>
                                                </div>
                                                <div class="form-group">
                                                        <div class="col-md-6 col-xs-6">
                                                                <input type="submit" id="btnAccion" class="form-control btn btn-success" value="Agregar" name="btnAccion">
                                                        </div>
                                                        <div class="col-md-6 col-xs-6">
                                                            <input type="submit" class="form-control btn btn-danger" value="Eliminar"  name="btnAccion">
                                                        </div>
                                                    <script>
                                                        function submit(){
                                                            return false;
                                                        }
                                                    </script>
                                                </div>
                                        </form>
                                </div>
                                <div class="col-md-4">
                                    <table class="table table-default table-responsive" id="usuarios">
                                        <caption>Usuario</caption>
                                            <tr>
                                                <th>
                                                    DNI
                                                </th>
                                                <th>
                                                    Nombre
                                                </th>
                                                <th>
                                                    Apellido
                                                </th>
                                            </tr>
                                            <c:forEach items="${sessionScope.usuarios}" var="user">
                                                <tr>
                                                    <th>
                                                        ${user.getDni()}
                                                    </th>
                                                    <th>
                                                        ${user.getNombre()}
                                                    </th>
                                                    <th>
                                                        ${user.getApellido()}
                                                    </th>
                                                </tr>
                                            </c:forEach>
                                            <c:set var="usuarios" scope="session" value="${null}"/>
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
        
        <!-- Begin JS only this page -->
        
        
        <!-- End js only page -->
        <script src="assets/pages/agregarUsuario.js" type="text/javascript"></script>
        <!-- Begin JS all page -->
        <script src="assets/js/app.js"></script>
        
        <!-- End js all page -->
        
    </body>
</html>
