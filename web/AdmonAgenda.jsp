<%-- 
    Document   : AdmonAgenda
    Created on : 23-11-2017, 1:59:18
    Author     : Tulio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat"%>
<c:if test="${sessionScope.usuario==null}">
    <c:redirect url="index.jsp"></c:redirect>
</c:if>
<!DOCTYPE html>
<!DOCTYPE html>

<html>
    <head>
        <style>

            </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <title>Pagina principal</title>
        <meta content="" name="description" />
        <meta content="" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- Icono de pestaña -->
        <!-- <link rel="shortcut icon" href="assets/images/favicon.ico"> -->
        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <link href="assets/css/fullcalendar.min.css" rel="stylesheet" type="text/css"/>
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
<%
  Date dNow = new Date();
   SimpleDateFormat ft = 
   new SimpleDateFormat ("MM/dd/yyyy hh:mm");
   String currentDate = ft.format(dNow);
   
SimpleDateFormat fh = 
   new SimpleDateFormat ("yyyy-MM-dd");
   String hoy = fh.format(dNow);
   
   

   
%>

<a type="button" id="pint" onclick="pintar()">Pintar</a>
 

                <div class="">
                    <div class="page-header-title">
                        <h4 class="page-title">Administración agenda</h4>
                    </div>
                </div>

                <div class="page-content-wrapper ">
                    <!--
                                            <div class="container">
                                                   <a class="btn btn-primary" id="hola">CargaR</a>
                                                   <a class="btn btn-primary" id="perro">CargaR</a>
                                                <div class="row">
                                                    <div class="col-md-10 col-md-offset-1">
                                                        <div id="calendar">
                    
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                    <!-- Inicio modal -->

                 
                    <div id="modalFecha" class="modal fade" role="dialog">
                        <div class="modal-dialog ">
                  

                  
                     <div class="modal-content">
                       <div class="modal-header">
                         <button type="button" class="close" data-dismiss="modal">&times;</button>
                         <h4 class="modal-title">Agendar Cita</h4>
                       </div>
                       <div class="modal-body">
                         <div class="row">
                             <div class="col-md-10 col-md-offset-1">
                                     <form action="#" id="form" method="#">
                                             <div class="row">
                                                     <div class="col-md-6">
                                                             <div class="form-group">
                                                                     <label for="DNi">DNI</label>
                                                                     <input required="true" type="number" name="dni" id="dni" class="form-control">
                                                             </div>
                                                     </div>
                                                     <div class="col-md-3">
                                                             <label for=""></label>
                                                             <input required="true" type="button" value="Buscar" id="buscarDni" class="btn btn-success form-control">
                                                     </div>
                                                     <div class="col-md-3">a
                                                             <label for=""></label>
                                                             <input required="true" type="button" value="Registrar" id="registrarDni" onclick="window.location.href='AdmonCliente.jsp'" class="btn btn-info form-control">
                                                     </div>
                                             </div>
                                             <div class="row">
                                                     <div class="col-md-12">
                                                             <div class="form-group">
                                                                     <label for="nombre">Nombre</label>
                                                                     <input required="true" type="text" name="nombre" id="nombre" class="form-control">
                                                             </div>
                                                     </div>
                                             </div>
                                             <div class="row">
                                                     <div class="col-md-6">
                                                             <div class="form-group">
                                                                     <label for="Tipo">Tipo de servicio</label>
                                                                     <select required="true" name="tipo" id="tipo" class="form-control">
                                                                             <option selected="true" value="0">Seleccione</option>
                                                                     </select>
                                                             </div>
                                                     </div>
                                                     <div class="col-md-6">
                                                             <div class="form-group">
                                                                     <label for="ids">Categoria</label>
                                                                     <select required="true" name="ids" id="ids" class="form-control">
                                                                             <option selected="true" value="0">Seleccione</option>
                                                                     </select>
                                                             </div>
                                                     </div>
                                             </div>
                             <div class="row">
                                 <div class="col-md-12">
                                     <div class="form-group">
                                         <label for="servicio">Servicio</label>
                                         <select name="servicio" class="form-control" id="servicio">
                                             <option selected="true" value="0">Seleccione</option>
                                         </select>
                                     </div>
                                 </div>
                             </div>
                                                 <div class="row">
                                                         <div class="col-md-3">
                                                                 <div class="form-group">
                                                                         <label for="Duracion">Duración</label>
                                                                         <input required="true" type="text" name="duracion" disabled="true" id="duracion" class="form-control">
                                                                 </div>
                                                         </div>
                                                         <div class="col-md-3">
                                                                 <div class="form-group">
                                                                         <label for="inicio">inicio</label>
                                                                         <input required="true" type="text" name="incio" id="inicio" disabled="true" class="form-control">
                                                                 </div>
                                                         </div>
                                                         <div class="col-md-3">
                                                                 <div class="form-group">
                                                                         <label for="Termino">Termino</label>
                                                                         <input required="true" type="text" name="termino" id="termino" disabled="true" class="form-control">
                                                                 </div>
                                                         </div>
                                                         <div class="col-md-3">
                                                                 <div class="form-group">
                                                                         <label for="precio">Precio</label>
                                                                         <input required="true" type="text" name="precio" id="precio" disabled="true" class="form-control">
                                                                 </div>
                                                         </div>
                                                 </div>
                                                 <div class="row">
                                                     <div class="col-md-4 col-xs-4">
                                                         <label for="descuento">Descuento % (0-100)</label>
                                                        <input type="number" min="0" max="100" name="descuento" id="descuento" placeholder="Ingrese descuento" class="form-control" value="0"> 
                                                     </div>
                                                     <div class="col-md-4 col-xs-4">
                                                         <label for="desApl">Total descuento</label>
                                                         <input type="number" name="desApl" id="desApl" disabled="true" class="form-control">
                                                     </div>
                                                     <div class="col-md-4 col-xs-4">
                                                         <label for="total2">Total</label>
                                                         <input type="number" name="total2" id="total2" class="form-control" disabled="true">
                                                     </div>
                                                 </div>

                                                         <div class="row">
                                                                 <div class="col-md-6">
                                                                         <div class="form-group">
                                                                                 <label for="Categoria">Especialidad</label>
                                                                                 <select required="true" name="categoria" id="categoria" class="form-control">
</select>
                                                                         </div>
                                                                 </div>
                                                                 <div class="col-md-6">
                                                                         <div class="form-group">
                                                                           
                                                                               <label for="Trabajador">Trabajador</label>
                                                                                  <input type="text" name="total2" id="trabajadortexto" class="form-control" disabled="true" value="Thomas Defecto" >
                                                                                    <input type="text" name="total23" id="trabajador" class="form-control" disabled="true" value="Thomas Defecto" > 
                                                                          <input type="text" name="total234" id="fecha" class="form-control" disabled="true" value="Thomas Defecto" > 
                                                                         
                                                                         </div>
                                                                 </div>
                                                         </div>
                             <div class="row">
                                 <div class="form-group">
                                     <label for="Detalle">Detalle</label>
                                     <textarea name="detalle" id="detalle" class="form-control" cols="30" rows="5" maxlength="500"></textarea>
                                 </div>
                             </div>
                             <div class="row">
                                 <div class="col-md-6 col-xs-6 col-md-offset-3 col-xs-offset-3">
                                     <label for="abonado">Abonado por el cliente</label>
                                     <input type="number" min="0" value="0" required="true"  name="pagado" id="pagado" class="form-control">
                                 </div>
                             </div>
                                                         <div class="row">
                                                                 <div class="col-md-6 col-md-offset-3">
                                                                         <input type="button" class="btn btn-success form-control" id="btnAgendar" value="Agendar">
                                                                 </div>
                                                         </div>
                                         </form>
                                 </div>
                         </div>
                       </div>
                       <div class="modal-footer">
                         <button type="button" class="btn btn-warning" id="cancelar" data-dismiss="modal">Cancelar</button>
                       </div>
                     </div>
                 </div>
         </div>

                    <!-- End modal -->

                    <!--
            </div>
                    <!-- End row -->
                    <!-- Begin modal detail -->
                    <!--
                    <div class="row">   
                        <div id="modalDetalle" class="modal fade" role="dialog">
                    
                    <!--
                    <div class="modal-dialog ">
                    <!-- Modal content-->
                    <!--
                    <div class="modal-content">
                      <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Detalle cita</h4>
                      </div>
                      <div class="modal-body">
                          <div class="row">
                            <div class="col-md-12 col-xs-12">
                                <label class="control-label col-md-6 col-xs-6">Dni <span class="pull-right">:</span></label>
                                <label type="text"  disabled="true" readonly="true" name="dniD" id="dniD" class="control-label col-md-6 col-xs-6"></label>  
                            </div>
                            <div class="col-md-12 col-xs-12">
                                <label class="control-label col-md-6 col-xs-6">Nombre <span class="pull-right">:</span></label>
                                <label class="control-label col-md-6 col-xs-6" id="nombreD"></label>
                            </div>
                            <div class="col-md-12 col-xs-12">
                                <label class="control-label col-md-6 col-xs-6">Inicio <span class="pull-right">:</span></label>
                                <label class="control-label col-md-6 col-xs-6" id="inicioD"></label>
                            </div>
                            <div class="col-md-12 col-xs-12">
                                <label class="control-label col-md-6 col-xs-6">Termino <span class="pull-right">:</span></label>
                                <label class="control-label col-md-6 col-xs-6" id="terminoD"></label>
                            </div>
                            <div class="col-md-12 col-xs-12">
                                <label class="control-label col-md-6 col-xs-6">Tipo de servicio <span class="pull-right">:</span></label>
                                <label class="control-label col-md-6 col-xs-6" id="tipoD"></label>
                            </div>
                            <div class="col-md-12 col-xs-12">
                                <label class="control-label col-md-6 col-xs-6">Categoria de servicio <span class="pull-right">:</span></label>
                                <label class="control-label col-md-6 col-xs-6" id="categoriaD"></label>
                            </div> 
                            <div class="col-md-12 col-xs-12">
                                <label class="control-label col-md-6 col-xs-6">Servicio <span class="pull-right">:</span></label>
                                <label class="control-label col-md-6 col-xs-6" id="servicioD"></label>
                            </div>
                            <div class="col-md-12 col-xs-12">
                                <label class="control-label col-md-6 col-xs-6">Estado <span class="pull-right">:</span></label>
                                <label class="control-label col-md-6 col-xs-6" id="estadoD"></label>
                            </div>
                            <div class="col-md-12 col-xs-12">
                                <label class="control-label col-md-6 col-xs-6">Valor <span class="pull-right">:</span></label>
                                <label class="control-label col-md-6 col-xs-6" id="valorD"></label>
                            </div>
                            <div class="col-md-12 col-xs-12">
                                <label class="control-label col-md-6 col-xs-6">Descuento <span class="pull-right">:</span></label>
                                <label class="control-label col-md-6 col-xs-6" id="descuentoD"></label>
                            </div>
                            <div class="col-md-12 col-xs-12">
                                <label class="control-label col-md-6 col-xs-6">Total <span class="pull-right">:</span></label>
                                <label class="control-label col-md-6 col-xs-6" id="totalD"></label>
                            </div>
                            <div class="col-md-12 col-xs-12">
                                <label class="control-label col-md-6 col-xs-6">Pagado <span class="pull-right">:</span></label>
                                <label class="control-label col-md-6 col-xs-6" id="pagadoD"></label>
                            </div>
                            <div class="col-md-12 col-xs-12">
                                <label class="control-label col-md-6 col-xs-6">Trabajador/es <span class="pull-right">:</span></label>
                                <label class="control-label col-md-6 col-xs-6" id="trabajadorD"></label>
                            </div>
                            <div class="col-md-12 col-xs-12">
                                <label class="control-label col-md-6 col-xs-6">Detalle <span class="pull-right">:</span></label>
                                <label class="control-label col-md-6 col-xs-6" id="detalleD"></label>
                            </div>
                            <div class="col-md-12 col-xs-12">
                                <br/>
                            </div>
                            <div class="col-md-12 col-xs-12">
                                <div class="col-md-6">
                                    <input type="button" value="Asistir" onclick="action('Asistir');" name="btnAgendar" class="btn btn-success form-control">
                                </div>
                                <div class="col-md-6">
                                    <input type="button" value="Cancelar"  onclick="action('Cancelar');" name="btnAgendar" class="btn btn-danger form-control">
                                </div>    
                            </div>
                          </div>
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-warning" id="cancelar" data-dismiss="modal">Cerrar</button>
                      </div>
                    </div>
                </div>
        </div>
        </div>
                    <!-- End modal detail -->
                </div>    

                <!-- container -->
          <div class="container">

<div class="row">
<div class="col-md-10 col-md-offset-1">

<div  id="calendar3" class="fc fc-unthemed fc-ltr " >
<div class="fc-toolbar fc-header-toolbar">
   <div class="fc-left">
      <div class="fc-button-group"><button type="button" class="fc-prev-button fc-button fc-state-default fc-corner-left" aria-label="prev"><span class="fc-icon fc-icon-left-single-arrow"></span></button><button type="button" class="fc-next-button fc-button fc-state-default fc-corner-right" aria-label="next"><span class="fc-icon fc-icon-right-single-arrow"></span></button></div>
      <button type="button" class="fc-today-button fc-button fc-state-default fc-corner-left fc-corner-right fc-state-disabled" disabled="">today</button>
   </div>
   <div class="fc-right">
      <div class="fc-button-group"><button type="button" class="fc-month-button fc-button fc-state-default fc-corner-left">mes</button><button type="button" class="fc-agendaWeek-button fc-button fc-state-default fc-state-active">semana</button><button type="button" class="fc-agendaDay-button fc-button fc-state-default fc-corner-right">Día</button></div>
   </div>
   <div class="fc-center">
       <h2>Fecha desde el Servidor: <input type="text" id="fechaHoy" readonly value="<%=hoy%>"> </h2>
   </div>
   <div class="fc-clear"></div>
</div>
  
                  <hr class="fc-divider fc-widget-header">
                 
                  <div class="fc-scroller fc-time-grid-container" style=" overflow-y: scroll; height: 569px;">
                     <div class="fc-time-grid fc-unselectable"  >
                        <!--
                            <div class="table-responsive" >
                                
                           <table id="cabecera" class="table">
                              <tbody>
                                      <thead>
                           <tr>
                              <th class="fc-axis fc-widget-header" style="width: 58px;"></th>
                              <th class="fc-day-header fc-widget-header fc-sun fc-past" data-date="2018-07-22"><a data-goto="{&quot;date&quot;:&quot;2018-07-22&quot;,&quot;type&quot;:&quot;day&quot;}">Sun 7/22</a></th>
                              <th class="fc-day-header fc-widget-header fc-mon fc-past" data-date="2018-07-23"><a data-goto="{&quot;date&quot;:&quot;2018-07-23&quot;,&quot;type&quot;:&quot;day&quot;}">Mon 7/23</a></th>
                              <th class="fc-day-header fc-widget-header fc-tue fc-past" data-date="2018-07-24"><a data-goto="{&quot;date&quot;:&quot;2018-07-24&quot;,&quot;type&quot;:&quot;day&quot;}">Tue 7/24</a></th>
                              <th class="fc-day-header fc-widget-header fc-wed fc-past" data-date="2018-07-25"><a data-goto="{&quot;date&quot;:&quot;2018-07-25&quot;,&quot;type&quot;:&quot;day&quot;}">Wed 7/25</a></th>
                              <th class="fc-day-header fc-widget-header fc-thu fc-past" data-date="2018-07-26"><a data-goto="{&quot;date&quot;:&quot;2018-07-26&quot;,&quot;type&quot;:&quot;day&quot;}">Thu 7/26</a></th>
                              <th class="fc-day-header fc-widget-header fc-fri fc-today" data-date="2018-07-27"><a data-goto="{&quot;date&quot;:&quot;2018-07-27&quot;,&quot;type&quot;:&quot;day&quot;}">Fri 7/27</a></th>
                              <th class="fc-day-header fc-widget-header fc-sat fc-future" data-date="2018-07-28"><a data-goto="{&quot;date&quot;:&quot;2018-07-28&quot;,&quot;type&quot;:&quot;day&quot;}">Sat 7/28</a></th>
                           </tr>
                        </thead>
                                 <tr>
                                    <td class="fc-axis fc-widget-content" style="width: 58px;"></td>
                                    <td class="fc-day fc-widget-content fc-sun fc-past" data-date="2018-07-22"></td>
                                    <td class="fc-day fc-widget-content fc-mon fc-past" data-date="2018-07-23"></td>
                                    <td class="fc-day fc-widget-content fc-tue fc-past" data-date="2018-07-24"></td>
                                    <td class="fc-day fc-widget-content fc-wed fc-past" data-date="2018-07-25"></td>
                                    <td class="fc-day fc-widget-content fc-thu fc-past" data-date="2018-07-26"></td>
                                    <td class="fc-day fc-widget-content fc-fri fc-today " data-date="2018-07-27"></td>
                                    <td class="fc-day fc-widget-content fc-sat fc-future" data-date="2018-07-28"></td>
                                 </tr>
                              </tbody>
                           </table>
                    !-->
                        <div class="fc-slats">
                            <div style="table-responsive">
                                
                           <table id="tabla-horas" class="table table-striped " >
                              
                            
                               <thead >
                            
                           <tr>
                           </tr>
                                                     

                        </thead>
                             
                              
                              <tbody id="cuerpo-horas">
                                
                            
                                 <tr id="h08:30:00" data-time="08:30:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>8:30am</span></td>
                                 
                                 </tr>
                                 <tr id="h08:45:00" data-time="08:45:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                   
                                 </tr>
                                 <tr id="h09:00:00" data-time="09:00:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>9am</span></td>
                                  
                                 </tr>
                                 <tr id="h09:15:00" data-time="09:15:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                    
                                 </tr>
                                 <tr id="h09:30:00" data-time="09:30:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>9:30am</span></td>
                                   
                                 </tr>
                                 <tr id="h09:45:00" data-time="09:45:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                    
                                 </tr>
                                 <tr id="h10:00:00" data-time="10:00:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>10am</span></td>
                                  
                                 </tr>
                                 <tr id="h10:15:00" data-time="10:15:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                  
                                 </tr>
                                 <tr id="h10:30:00" data-time="10:30:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>10:30am</span></td>
                               
                                 </tr>
                                 <tr id="h10:45:00" data-time="10:45:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                  
                                 </tr>
                                 <tr id="h11:00:00" data-time="11:00:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>11am</span></td>
                                 
                                 </tr>
                                 <tr id="h11:15:00" data-time="11:15:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                   
                                 </tr>
                                 <tr id="h11:30:00" data-time="11:30:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>11:30am</span></td>
                                   
                                 </tr>
                                 <tr id="h11:45:00" data-time="11:45:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                 
                                 </tr>
                                 <tr id="h12:00:00" data-time="12:00:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>12pm</span></td>
                                   
                                 </tr>
                                 <tr id="h12:15:00" data-time="12:15:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                 
                                 </tr>
                                 <tr id="h12:30:00" data-time="12:30:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>12:30pm</span></td>
                                   
                                 </tr>
                                 <tr id="h12:45:00" data-time="12:45:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                
                                 </tr>
                                 <tr id="h13:00:00" data-time="13:00:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>1pm</span></td>
                                  
                                 </tr>
                                 <tr id="h13:15:00" data-time="13:15:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                   
                                 </tr>
                                 <tr id="h13:30:00" data-time="13:30:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>1:30pm</span></td>
                                  
                                 </tr>
                                 <tr id="h13:45:00" data-time="13:45:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                  
                                 </tr>
                                 <tr id="h14:00:00" data-time="14:00:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>2pm</span></td>
                               
                                 </tr>
                                 <tr id="h14:15:00" data-time="14:15:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                 
                                 </tr>
                                 <tr id="h14:30:00" data-time="h14:30:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>2:30pm</span></td>
                                  
                                 </tr>
                                 <tr id="h14:45:00" data-time="14:45:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                
                                 </tr>
                                 <tr id="h15:00:00" data-time="15:00:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>3pm</span></td>
                              
                                 </tr>
                                 <tr id="h15:15:00" data-time="15:15:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                   
                                 </tr>
                                 <tr id="h15:30:00" data-time="15:30:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>3:30pm</span></td>
                                  
                                 </tr>
                                 <tr id="h15:45:00" data-time="15:45:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                   
                                 </tr>
                                 <tr id="h16:00:00" data-time="16:00:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>4pm</span></td>
                                    
                                 </tr>
                                 <tr id="h16:15:00" data-time="16:15:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                  
                                 </tr>
                                 <tr id="h16:30:00" data-time="16:30:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>4:30pm</span></td>
                                
                                 </tr>
                                 <tr id="h16:45:00" data-time="16:45:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                 
                                 </tr>
                                 <tr id="h17:00:00" data-time="17:00:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>5pm</span></td>
                                    
                                 </tr>
                                 <tr id="h17:15:00" data-time="17:15:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                   
                                 </tr>
                                 <tr id="h17:30:00" data-time="17:30:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>5:30pm</span></td>
                                  
                                 </tr>
                                 <tr id="h17:45:00" data-time="17:45:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                
                                 </tr>
                                 <tr id="h18:00:00" data-time="18:00:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>6pm</span></td>
                                 
                                 </tr>
                                 <tr id="h18:15:00" data-time="18:15:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                               
                                 </tr>
                                 <tr id="h18:30:00" data-time="18:30:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>6:30pm</span></td>
                               
                                 </tr>
                                 <tr id="h18:45:00" data-time="18:45:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                  
                                 </tr>
                                 <tr id="h19:00:00" data-time="19:00:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>7pm</span></td>
                                  
                                 </tr>
                                 <tr id="h19:15:00" data-time="19:15:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                 
                                 </tr>
                                 <tr id="h19:30:00" data-time="19:30:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>7:30pm</span></td>
                                
                                 </tr>
                                 <tr id="h19:45:00" data-time="19:45:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                               
                                 </tr>
                                 <tr id="h20:00:00" data-time="20:00:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>8pm</span></td>
                        
                                 </tr>
                                 <tr id="h20:15:00" data-time="20:15:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                
                                 </tr>
                                 <tr id="h20:30:00" data-time="20:30:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>8:30pm</span></td>
                                   
                                 </tr>
                                 <tr id="h20:45:00" data-time="20:45:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                
                                 </tr>
                                 <tr id="h21:00:00" data-time="21:00:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>9pm</span></td>
                                   
                                 </tr>
                                 <tr id="h21:15:00" data-time="21:15:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                  
                                 </tr>
                                 <tr id="h21:30:00" data-time="21:30:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>9:30pm</span></td>
                                
                                 </tr>
                                 <tr id="h21:45:00" data-time="21:45:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                 
                                 </tr>
                                 <tr id="h22:00:00" data-time="22:00:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>10pm</span></td>
                                   
                                 </tr>
                                 <tr id="h22:15:00" data-time="22:15:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                   
                                 </tr>
                                 <tr id="h22:30:00" data-time="22:30:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>10:30pm</span></td>
                                   
                                 </tr>
                                 <tr id="h22:45:00" data-time="h22:45:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                 
                                 </tr>
                                 <tr id="h23:00:00" data-time="h23:00:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>11pm</span></td>
                                 
                                 </tr>
                                 <tr id="h23:15:00" data-time="h23:15:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                   
                                 </tr>
                                 <tr id="h23:30:00" data-time="h23:30:00">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"><span>11:30pm</span></td>
                                   
                                 </tr>
                                 <tr id="h23:45:00" data-time="h23:45:00" class="fc-minor">
                                    <td class="fc-axis fc-time fc-widget-content" style="width: 58px;"></td>
                                
                                 </tr>
                              </tbody>
                           </table>
                            </div>
                                 </div>
                            </div>
                        </div>
                        <hr class="fc-divider fc-widget-header" style="display:none">
                        <div class="fc-content-skeleton"style="overflow-x:auto!important;" >
                           <table>
                              <tbody>
                                 <tr>
                                    <td class="fc-axis" style="width: 58px;"></td>
                                    <td>
                                       <div class="fc-content-col">
                                          <div class="fc-event-container fc-helper-container"></div>
                                          <div class="fc-event-container"></div>
                                          <div class="fc-highlight-container"></div>
                                          <div class="fc-bgevent-container"></div>
                                          <div class="fc-business-container"></div>
                                       </div>
                                    </td>
                                    <td>
                                       <div class="fc-content-col">
                                          <div class="fc-event-container fc-helper-container"></div>
                                          <div class="fc-event-container"></div>
                                          <div class="fc-highlight-container"></div>
                                          <div class="fc-bgevent-container"></div>
                                          <div class="fc-business-container"></div>
                                       </div>
                                    </td>
                                    <td>
                                       <div class="fc-content-col">
                                          <div class="fc-event-container fc-helper-container"></div>
                                          <div class="fc-event-container"></div>
                                          <div class="fc-highlight-container"></div>
                                          <div class="fc-bgevent-container"></div>
                                          <div class="fc-business-container"></div>
                                       </div>
                                    </td>
                                    <td>
                                       <div class="fc-content-col">
                                          <div class="fc-event-container fc-helper-container"></div>
                                          <div class="fc-event-container"></div>
                                          <div class="fc-highlight-container"></div>
                                          <div class="fc-bgevent-container"></div>
                                          <div class="fc-business-container"></div>
                                       </div>
                                    </td>
                                    <td>
                                       <div class="fc-content-col">
                                          <div class="fc-event-container fc-helper-container"></div>
                                          <div class="fc-event-container"></div>
                                          <div class="fc-highlight-container"></div>
                                          <div class="fc-bgevent-container"></div>
                                          <div class="fc-business-container"></div>
                                       </div>
                                    </td>
                                    <td>
                                       <div class="fc-content-col">
                                          <div class="fc-event-container fc-helper-container"></div>
                                          <div class="fc-event-container"></div>
                                          <div class="fc-highlight-container"></div>
                                          <div class="fc-bgevent-container"></div>
                                          <div class="fc-business-container"></div>
                                       </div>
                                    </td>
                                    <td>
                                       <div class="fc-content-col">
                                          <div class="fc-event-container fc-helper-container"></div>
                                          <div class="fc-event-container"></div>
                                          <div class="fc-highlight-container"></div>
                                          <div class="fc-bgevent-container"></div>
                                          <div class="fc-business-container"></div>
                                       </div>
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
                     </div>
                  </div>
               </td>
            </tr>
         </tbody>
      </table>
   </div>
   </div>
</div>
                                <!--new container !-->



                            </div> <!-- Page content Wrapper -->

                        </div> <!-- content -->

                        <footer class="footer">
                            © 2017 Upbond - By Themesdesign.
                        </footer>

                    </div>
                    <!-- End Right content here -->


                    <!-- END wrapper -->
                    <script>


                    </script>
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
                    <script src="assets/js/frontutil.js"></script>
                    <script src="assets/js/jquery.nicescroll.js"></script>
                    <script src="assets/js/jquery.scrollTo.min.js"></script>
                    <script src="assets/pages/agenda.js"></script>
                


                    <!--Morris Chart-->

                    <!-- Begin JS only this page -->

                    <!-- End js only page -->
                    <script src="assets/js/moment.min.js" type="text/javascript"></script>
                    <script src="assets/js/fullcalendar.min.js" type="text/javascript"></script>
                    <script src="assets/pages/menu.js" type="text/javascript"></script>
                    <!-- Begin JS all page -->
                    <script src="assets/js/app.js"></script>
                    <script src="assets/plugins/bootstrap-sweetalert/sweet-alert.min.js" type="text/javascript"></script>
                    <!-- End js all page -->
                    </body>
                    </html>
