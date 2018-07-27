<%-- 
    Document   : sidebar
    Created on : 22-11-2017, 23:38:43
    Author     : Tulio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="sidebar-inner slimscrollleft">
    <!--- Divider -->
    <div id="sidebar-menu">
        <ul>
            <br>
            <br>
            <br>
            <c:if test="${sessionScope.permiso==1 || sessionScope.permiso==2 || sessionScope.permiso==3}">
            <li class="has_sub">
                <a href="javascript:void(0);" class="waves-effect"><i class="mdi mdi-account-card-details"></i><span> Agenda </span><span class="pull-right"><i class="mdi mdi-chevron-right"></i></span></a>
                <ul class="list-unstyled">
                    <li class=""><a class="" href="AdmonAgenda.jsp">Agendar Servicios</a></li>
                    <li class=""><a class="" href="AdmonAgendaTratamientos.jsp">Agendar Tratamientos</a></li>
                </ul>
            </li>
            </c:if>
            <c:if test="${sessionScope.permiso==1 || sessionScope.permiso==2}">
            <li>
                <a href="Inicio.jsp" class="waves-effect"><i class="ti-home"></i><span> Dashboard <span class="badge badge-primary pull-right"></span></span></a>
            </li>
            </c:if>
            <c:if test="${sessionScope.permiso==1 || sessionScope.permiso==2}">
            <li class="has_sub">
                <a href="javascript:void(0);" class="waves-effect"><i class="glyphicon glyphicon-user"></i><span> Clientes </span><span class="pull-right"><i class="mdi mdi-chevron-right"></i></span></a>
                <ul class="list-unstyled">
                    <li class=""><a class="" href="AdmonCliente.jsp">Administrar</a></li>
                    <li><a href="activarCliente.jsp">Clientes Eliminados</a></li>
                </ul>
            </li>
            </c:if>
            <c:if test="${sessionScope.permiso==1}">
            <li class="has_sub">
                <a href="javascript:void(0);" class="waves-effect"><i class="glyphicon glyphicon-briefcase"></i><span>Trabajadores</span><span class="pull-right"><i class="mdi mdi-chevron-right"></i></span></a>
                <ul class="list-unstyled">
                    <li class=""><a class="" href="AdmonTrabajador.jsp">Administrar</a></li>
                    <li><a href="activarTrabajador.jsp">Trabajadores Eliminados</a></li>
                </ul>
            </li>
            </c:if>
            <c:if test="${sessionScope.permiso==1}">
            <li class="has_sub">
                <a href="javascript:void(0);" class="waves-effect"><i class="ti-user"></i><span>Usuarios Sistema</span><span class="pull-right"><i class="mdi mdi-chevron-right"></i></span></a>
                <ul class="list-unstyled">
                    <li class=""><a class="" href="AdmonUsuario.jsp">Administrar</a></li>
                    <li><a href="activarUsuario.jsp">Activar Usuario</a></li>
                </ul>
            </li>
            </c:if>
            
            <c:if test="${sessionScope.permiso==1}">
            <li class="has_sub">
                <a href="javascript:void(0);" class="waves-effect"><i class="fa fa-cogs"></i><span> Categorias </span><span class="pull-right"><i class="mdi mdi-chevron-right"></i></span></a>
                <ul class="list-unstyled">
                    <li class=""><a class="" href="AdmonTipo.jsp">Administrar Items</a></li>
                    <li class=""><a class="" href="AdmonCategorias.jsp">Administrar categorias</a></li>
                    <li class=""><a class="" href="AdmonServicios.jsp">Administrar Servicios</a></li>
                    <li class=""><a class="" href="AdmonEspecialidad.jsp">Administrar Especialidad</a></li>
                </ul>
            </li>
            </c:if>
        </ul>
    </div>
    <div class="clearfix"></div>
</div>
