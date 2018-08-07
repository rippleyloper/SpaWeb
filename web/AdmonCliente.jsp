<%-- 
    Document   : AdmonCliente
    Created on : 22-11-2017, 22:39:48
    Author     : Tulio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.usuario==null}">
    <c:redirect url="index.jsp"></c:redirect>
</c:if>
<c:if test="${sessionScope.clientes==null}">
    <c:redirect url="ServletCliente?data=1"/>
</c:if>
<c:if test="${sessionScope.msg!=null}">
    <script>
        var msg=[];
        msg.push({name:"id",value:${sessionScope.msg.getId()}});
        msg.push({name:"nombre",value:"${sessionScope.msg.getNombres()}"});
    </script>
    <c:set var="msg" scope="session" value="${null}"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <title>Administración de clintes</title>
        <meta content="" name="description" />
        <meta content="" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- Icono de pestaña -->
        <!-- <link rel="shortcut icon" href="assets/images/favicon.ico"> -->
        <!-- End icono de pestaña -->
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
                 <!-- end sidebarinner -->
                <%@include file="include/sidebar.jsp" %>
            </div>
            <!-- Left Sidebar End -->

            <!-- Start right Content here -->

            <div class="content-page">
                <!-- Start content -->
                <div class="content">

                    <div class="">
                        <div class="page-header-title">
                            <h4 class="page-title">Administración Cliente</h4>
                        </div>
                    </div>

                    <div class="page-content-wrapper ">

                        <div class="container">
                            <!-- Inicio modal -->
                            <div class="row">
                                <div id="myModal" class="modal fade" role="dialog">
              <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <center><h4 class="modal-title">Agregar Cliente</h4></center>
                  </div>
                  <div class="modal-body">
                    <div class="container-fluid">
                        <div class="col-md-12 col-xs-12">
                                            <form action="ServletCliente" id="form" method="POST">
                            <div class="row separacion">
                                <div class="col-md-12">
                                    <label for="">DNI</label>
                                    <input autocomplete="off" required="true" type="text" name="dni" onchange="darFormato();" class="form-control" id="dni">
                                </div>
                                <div class="col-md-6">
                                    <label for="Nombre">Nombre</label>
                                    <input id="nombres" onchange="inicialMayus(this);" autocomplete="off" required="true" type="text" name="nombre"  maxlength="45" minlength="0" class="form-control">
                                </div>
                                <div class="col-md-6">
                                    <label for="Apellido">Apellido</label>
                                    <input id="appellidos" onchange="inicialMayus(this);" autocomplete="off" required="true" type="text" maxlength="45" minlength="0" name="apellido" class="form-control">
                                </div>
                                <div class="col-md-6">
                                    <label for="Genero">Genero</label>
                                    <select onchange="cargarDepartamento()" required="true"  name="genero" id="genero" class="form-control">
                                        <option value="0">Selecione</option>
                                    </select>
                                </div>
                                <div class="col-md-6">
                                    <label for="Numero">Telefono</label>
                                    <input required="true" type="number" name="numero" min="0" onkeypress="if(this.value.length<=10)"  class="form-control">
                                </div>
                                <div class="col-md-6">
                                    <label for="Correo">Correo</label>
                                    <input required="true" maxlength="60" minlength="0" type="email" name="correo" class="form-control">
                                </div>
                                <div class="col-md-6">
                                    <label for="Fecha">Fecha de nacimiento</label>
                                    <input required="true" type="date" name="fecha" class="form-control">
                                </div>
                                <div class="col-md-12">
                                    <label for="Direccion">Dirección</label>
                                    <input id="dir" onchange="inicialMayus(this);" autocomplete="off" required="true" maxlength="45" minlength="0" type="text" name="direccion" class="form-control">
                                </div>
                                <div class="col-md-12">
                                    <label for="Departamento">Departamento</label>
                                    <select required="true" name="departamento" id="departamento" class="form-control">
                                        <option value="0" selected="true">Seleccione</option>
                                    </select>
                                </div>
                                <div class="col-md-12">
                                    <label for="Barrio">Barrio</label>
                                    <select required="true" name="barrio" id="barrio" class="form-control">
                                        <option value="0" selected="true">Seleccione</option>
                                    </select>
                                </div>
                                <div class="col-md-6 col-md-offset-3">
                                                                    <input type="submit" value="Agregar" name="btnSubmit" class="btn btn-success form-control">
                                </div>
                            </div>
                        </form>
                    </div>
                    </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-warning" data-dismiss="modal">Volver</button>
                  </div>
                </div>

              </div>
            </div>
                            </div>
                            <!-- Fin modal -->
                            <!-- Abrir modal -->
                            <div class="row">
                                <div class="col-md-4 col-md-offset-2 col-xs-4 col-xs-offset-2">
                                            <button type="button" id="btnAgregar" class="btn btn-primary">Agregar cliente</button>
                                </div>
                                <div class="col-md-4 col-md-offset-1 col-xs-4 col-xs-offset-1">
                                        <div class="input-group">
                                            <input type="text" id="dniBus" class="form-control" placeholder="Ingrese DNI Ej: xxxxxxx">
                                            <span class="input-group-btn">
                                                <button type="button" class="btn btn-default" onclick="detalle($('#dniBus').val());"><span class="glyphicon glyphicon-search" ></span></button>
                                            </span>
                                        </div>
                                </div>
                            </div>
                            <div class="row">
                                <br>
                            </div>
                            <!-- Fin abrir modal -->
                            <!-- Inicio tabla -->
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
                                                                   <td><a href="#" class="btn btn-info"  onclick="event.preventDefault();detalle('${clie.getDni()}');">Detalle</a></td>
                                               <td><button class="btn btn-default" onclick="event.preventDefault();modificar('${clie.getDni()}');">Modificar</button></td>
                                                                   <td><input type="button" class="btn btn-danger" value="Eliminar" onclick="eliminar('${clie.getDni()}');"></td>
                                                                </tr>
                                                            </c:forEach>   
                                                            <c:set scope="session" var="clientes"  value="${null}" />
                                    </table>
                                </div>
                            </div>
                            <!-- Fin tabla -->
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
        <script src="assets/js/frontutil.js"></script>
        <script src="assets/js/jquery.scrollTo.min.js"></script>

        <!-- Script solo para esta pagina -->
        <script src="assets/pages/agregarCliente.js"></script>
        <!-- Fin Script solo para esta pagina -->
        <!-- Begin JS all page -->
        <script src="assets/js/app.js"></script>
        <!-- End js all page -->
    </body>
</html>