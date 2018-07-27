F<%-- 
    Document   : nav
    Created on : 22-11-2017, 23:37:57
    Author     : Tulio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="topbar">
<!-- LOGO -->
<div class="topbar-left">
    <!--<a href="index.html" class="logo"><span>Up</span>Bond</a>-->
    <!--<a href="index.html" class="logo-sm"><span>U</span></a>-->
    <!-- Logo grande -->
    <a href="Inicio.jsp" class="logo"><img class="center" alt="logo" class="img-responsive"  width="245" height="70" src="assets/logo/logo-largo.png"></a>
    <!-- Logo pequeño -->
    <a href="Inicio.jsp" class="logo-sm"><img src="assets/logo/logo-chico.png" height="30" alt="logo"></a>
</div>
<!-- End logo -->
<!-- Button mobile view to collapse sidebar menu -->
<div class="navbar navbar-default" role="navigation">
    <div class="container">
        <div class="">
            <div class="pull-left">
                <button type="button" class="button-menu-mobile open-left waves-effect waves-light">
                    <i class="ion-navicon"></i>
                </button>
                <span class="clearfix"></span>
            </div>

            <ul class="nav navbar-nav navbar-right pull-right">
                <li class="dropdown hidden-xs">
                    <a href="#" data-target="#" class="dropdown-toggle waves-effect waves-light notification-icon-box" data-toggle="dropdown" aria-expanded="true">
                        <i class="ion-ios7-bell"></i> <span class="badge badge-xs badge-danger" id="cantidadOrdenes2">0</span>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-lg">
                        <li class="text-center notifi-title">Notificaciones</li>
                        <li class="list-group">
                           <!-- list item-->
                           <a href="javascript:void(0);" class="list-group-item">
                              <div class="media">
                                  <i class="glyphicon glyphicon-time text-danger noti-sm-icon"></i>
                                  <div class="noti-content">
                                      <div class="media-heading">Ordenes para hoy</div>
                                      <p class="m-0">
                                          <small id="cantidadOrdenes" ></small>
                                      </p>
                                  </div>
                              </div>
                           </a>

                        </li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="" class="dropdown-toggle profile waves-effect waves-light" data-toggle="dropdown" aria-expanded="true">
                        <span class="profile-username">
                            <c:out value="${sessionScope.usuario}"/><span class="caret"></span>
                        </span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="divider"></li>
                        <li><a href="ServletUsuario?data=6"> Salir</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</div>
