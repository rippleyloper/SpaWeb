<%-- 
    Document   : AdmonTrabajador
    Created on : 23-11-2017, 0:58:42
    Author     : Tulio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.usuario==null}">
    <c:redirect url="index.jsp"></c:redirect>
</c:if>
<c:if test="${sessionScope.trabajadores==null}">
    <c:redirect url="ServletTrabajador?data=1"/>
</c:if>
<c:if test="${sessionScope.msg!=null}">
    
    <script>
        var msg=[];
        msg.push({name:"id",value:${sessionScope.msg.getId()}});
        
      msg.push({name:"nombre",value:"${sessionScope.msg.getNombres()}"});
   
       
        
    
    //window.location.reload();
               
    
    </script>
    <c:set  var="msg" scope="session" value="${null}"/>
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
 
 <!-- <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
 >script src="http://code.jquery.com/mobile/1.4.3/jquery.mobile-1.4.3.min.js"></script>!-->
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
                          
                        <h4 class="page-title">Administración de trabajadores
                    </div>
                </div>

                <div class="page-content-wrapper ">

                    <div class="container">
                        <!--Inicio modal-->
                        <div>
                            <div  id="myModal" tabindex="1" class="modal fade" role="dialog"  data-keyboard="false" data-backdrop="static">
			  <div class="modal-dialog">

			    <!-- Modal content-->
			    <div class="modal-content">
                                <div class="modal-header">
                                  <button type="button" id="cerrar" class="close" data-dismiss="modal">&times;</button>
                                  <center><h4 class="modal-title">Agregar trabajador</h4></center>
                                </div>
                                <div class="modal-body">
                                  <div class="container-fluid">
                                        <form action="ServletTrabajador" id="form" method="POST">
                                                <div class="row separacion">
                                                        <div class="col-md-12">
                                                                <div class="col-md-12">
                                                                        <label for="">DNI</label>
                                                                        <input autocomplete="off"  required="true" onchange="darFormato();" type="text" name="dni" id="dni" class="form-control">
                                                                       
                                                                </div>
                                                                <div class="col-md-6">
            
                                                                        <label for="Nombre">Nombre</label>
                                                                        <input id="nombre" onchange="inicialMayus(this);" autocomplete="off" required="true" type="text" name="nombre" maxlength="45" minlength="0" class="form-control">
                                                                </div>
                                                                <div class="col-md-6">
                                                                        <label for="Apellido">Apellido</label>
                                                                        <input id="apellido" onchange="inicialMayus(this);"  autocomplete="off" maxlength="45" minlength="0" required="true" type="text" name="apellido" class="form-control">
                                                                </div>
                                                                <div class="col-md-6">
                                                                        <label for="Genero">Genero</label>
                                                                        <select required="true" name="genero" id="genero" class="form-control">
                                                                                <option value="1">Selecione</option>
                                                                        </select>
                                                                </div>
                                                            <div class="col-md-6">
                                                                <label for="Numero">Telefono</label>
                                                                <input  onchange="cargarDepartamento()" autocomplete="off" required="true" type="number" name="numero" minlength="0" class="form-control">
                                                            </div>
                                                                 
                                                                <div class="col-md-6">
                                                                        <label for="Correo">Correo</label>
                                                                        <input maxlength="60" minlength="0" required="true" type="email" name="correo" class="form-control">
                                                                </div>
                                                                <div class="col-md-6">
                                                                        <label for="Fecha">Fecha de nacimiento</label>
                                                                        <input autocomplete="off" max="1999-12-31" required="true" type="date" name="fecha" class="form-control">
                                                                </div>
                                                                <div class="col-md-12">
                                                                        <label for="Direccion">Dirección</label>
                                                                        <input id="direccion" onchange="inicialMayus(this);"  autocomplete="off" maxlength="45" minlength="0" required="true" type="text" name="direccion" class="form-control">
                                                                </div>
                                                            <body class="col-md-12" >
                                                                        <label for="Departamento">Departamento</label>
                                                                        <select required="true" name="departamento" id="departamento" class="form-control">
                                                                                <option value="0" selected="false">Seleccione</option>
                                                                        </select>
                                                                </body>
                                                                <div class="col-md-12">
                                                                        <label for="Barrio">Barrio</label>
                                                                        <select required="true" name="barrio2" id="barrio2" class="form-control">
                                                                                <option value="0" selected="true">Seleccione</option>
                                                                        </select>
                                                                </div>
                                                                <div class="col-md-12">
                                                                        <label for="Especialidad">Especialdad</label>
                                                                        <select required="true" multiple="true" name="especialidad" id="especialidad" class="form-control">
                                                                                <option value="1">Seleccione un departamento</option>
                                                                        </select>
                                                                </div>
                                                                <div class="col-md-6 col-md-offset-3">
                                                                    <input  type="submit" name="btnSubmit" class="btn btn-success form-control">
                                                                </div>
                                                        </div>
                                                </div>
                                        </form>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                  <button type="button" class="btn btn-warning" data-dismiss="modal">Volver</button>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <!--Fin modal-->
                        <!--Buton abrir modal-->
                        <div class="row">
                            <div class="container-fluid">
                                <div class="col-md-4 col-md-offset-2 col-xs-4 col-xs-offset-2">
                                        <button class="btn btn-primary" id="agregar">Agregar trabajador</button>
                                        <button class="btn btn-primary" id="cargarP">Ag</button>
                                </div>
                                <div class="col-md-4 col-md-offset-1 col-xs-4 col-xs-offset-1">
                                        <div class="input-group">
                                            <input type="text"  id="dniBus" class="form-control" placeholder="Ingrese DNI Ej: xxxxxxx">
                                            <span class="input-group-btn">
                                                <button type="button" class="btn btn-default" onclick="detalle($('#dniBus').val());"><span class="glyphicon glyphicon-search" ></span></button>
                                            </span>
                                        </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <br/>
                        </div>
                        <div class="row">
                            <div  class="col-md-12">
                             
                        <!--Fin boton abrir modal-->
                        <!--Tabla-->
                        <div class="row">
                            <div  class="col-md-12">
                                <table  class="table table-default table-responsive">
                                        <tr>
                                                <th>DNI</th>
                                                <th>Nombre</th>
                                                <th>Apellido</th>
                                                <th colspan="3">Acciones</th>
                                        </tr>
                 
                                        <c:forEach items="${sessionScope.trabajadores}" var="tra">
                                            <tr>
                                                <td id="${tra.getDni()}">${tra.getDni()}</td>
                                                <td>${tra.getNombre()}</td>
                                                <td>${tra.getApellido()}</td>                              
               <td><a href="#" class="btn btn-info" onclick="detalle('${tra.getDni()}');">Detalle</a></td>
                                                <td><button class="btn btn-default" onclick="modificar('${tra.getDni()}');">Modificar</button></td>
                                                <td>
                                                    <input type="button" class="btn btn-danger" value="Eliminar" onclick="eliminar('${tra.getDni()}')">
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        <c:set var="trabajadores" scope="session" value="${null}"/>
                                </table>
                            </div>
                        </div>
                        <!--Fin tabla-->
                    </div>
                    <!-- container -->
                </div> <!-- Page content Wrapper -->

            </div> <!-- content -->

            <footer class="footer">
                 © 2017 Upbond - By Themesdesign.
            </footer>

        </div>
            <!-- End Right content here -->

        <!-- END wrapper -->

     
        <!-- jQuery  -->
        
   
           <script>

</script>


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
        <script src="assets/js/frontutil.js"></script>
        <!-- Begin JS only this page -->
        <script src="assets/pages/agregarTrabajador.js"></script>
        <!-- End js only page -->
        <!-- Begin JS all page -->
      
        <script src="assets/js/app.js"></script>
        
        <!-- End js all page -->
    </body>
</html>