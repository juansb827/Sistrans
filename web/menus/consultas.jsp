<%@page import="java.util.ArrayList"%>
<%@page import="com.bancandes.dao.FilaEnConsulta"%>
<%@page import="com.bancandes.mb.Usuario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : registrarUsuario
    Created on : Sep 21, 2015, 2:13:57 PM
    Author     : Juan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>SB Admin - Bootstrap Admin Template</title>

        <!-- Bootstrap Core CSS -->
        <link href="${pageContext.request.contextPath}/resources/menu-template/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="${pageContext.request.contextPath}/resources/menu-template/css/sb-admin.css" rel="stylesheet">

        <!-- Morris Charts CSS -->
        <link href="${pageContext.request.contextPath}/resources/menu-template/css/plugins/morris.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="${pageContext.request.contextPath}/resources/menu-template/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>

    <body>

        <div id="wrapper">

            <!-- Navigation -->
            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.html">SB Admin</a>
                </div>
                <!-- Top Menu Items -->
                <ul class="nav navbar-right top-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b class="caret"></b></a>
                        <ul class="dropdown-menu message-dropdown">
                            <li class="message-preview">
                                <a href="#">
                                    <div class="media">
                                        <span class="pull-left">
                                            <img class="media-object" src="http://placehold.it/50x50" alt="">
                                        </span>
                                        <div class="media-body">
                                            <h5 class="media-heading"><strong>John Smith</strong>
                                            </h5>
                                            <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                            <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="message-preview">
                                <a href="#">
                                    <div class="media">
                                        <span class="pull-left">
                                            <img class="media-object" src="http://placehold.it/50x50" alt="">

                                        </span>
                                        <div class="media-body">
                                            <h5 class="media-heading"><strong>John Smith</strong>
                                            </h5>
                                            <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                            <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="message-preview">
                                <a href="#">
                                    <div class="media">
                                        <span class="pull-left">
                                            <img class="media-object" src="http://placehold.it/50x50" alt="">
                                        </span>
                                        <div class="media-body">
                                            <h5 class="media-heading"><strong>John Smith</strong>
                                            </h5>
                                            <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                            <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="message-footer">
                                <a href="#">Read All New Messages</a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i> <b class="caret"></b></a>
                        <ul class="dropdown-menu alert-dropdown">
                            <li>
                                <a href="#">Alert Name <span class="label label-default">Alert Badge</span></a>
                            </li>
                            <li>
                                <a href="#">Alert Name <span class="label label-primary">Alert Badge</span></a>
                            </li>
                            <li>
                                <a href="#">Alert Name <span class="label label-success">Alert Badge</span></a>
                            </li>
                            <li>
                                <a href="#">Alert Name <span class="label label-info">Alert Badge</span></a>
                            </li>
                            <li>
                                <a href="#">Alert Name <span class="label label-warning">Alert Badge</span></a>
                            </li>
                            <li>
                                <a href="#">Alert Name <span class="label label-danger">Alert Badge</span></a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">View All</a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> John Smith <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
              <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li>
                        <a href="${pageContext.request.contextPath}/menus/menu.jsp"><i class="fa fa-fw fa-dashboard"></i> Inicio</a>
                    </li>
                    
                   
                   <li class="active">
                        <a href="${pageContext.request.contextPath}/menus/usuarios.jsp"><i class="fa fa-fw fa-edit"></i> Usuarios</a>
                    </li>
                    
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/menus/puntos_de_venta.jsp"><i class="fa fa-fw fa-edit"></i> Puntos de venta</a>
                    </li>
                    
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/ServletCuentas"><i class="fa fa-fw fa-edit"></i> Cuentas</a>
                    </li>
                    
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/ServletPrestamos?menu=solicitudes"><i class="fa fa-fw fa-edit"></i> Solicitudes Prestamos</a>
                    </li>
                    
                      <li class="active">
                        <a href="${pageContext.request.contextPath}/ServletPrestamos?menu=info"><i class="fa fa-fw fa-edit"></i> Informacion Prestamos</a>
                    </li>
                    
                    
                      <li class="active">
                        <a href="${pageContext.request.contextPath}/ServletConsultas"><i class="fa fa-fw fa-database"></i> Consultas</a>
                    </li>
                    
                </ul>
            </div>
                <!-- /.navbar-collapse -->
            </nav>

            <div id="page-wrapper">

                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">
                                Consultas:


                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="fa fa-dashboard"></i>  <a href="index.html">Dashboard</a>
                                </li>
                                <li class="active">
                                    <i class="fa fa-edit"></i> Forms
                                </li>
                            </ol>
                        </div>
                    </div>

                    <c:if test="${msgOperacion!=null}">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="alert alert-info alert-dismissable">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                    <i class="fa fa-info-circle"></i>  ${msgOperacion} <Strong># ${idPrestamo}</strong>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${consulta=='CONSULTA_CUENTAS' }">
                        <form>
                            <input hidden="true" value="${consulta}">
                            <select name="filtro">
                                <option value="TIPO">Tipo de cuenta</option>
                                <option value="RANGO_SALDOS">Rango saldos saldo1,saldo2</option>
                                <option value="FECHA_CREACION">Fecha creacion</option>
                                <option value="FECHA_ULTIMA_MOVIMIENTO">Fecha ultimo movimiento</option>                                
                            </select>
                            <select name="grupo">
                                <option value="TIPO">Tipo de cuenta</option>
                                <option value="RANGO_SALDOS">Rango saldos saldo1,saldo2</option>
                                    <option value="FECHA_CREACION">Fecha creacion</option>
                                <option value="FECHA_ULTIMA_MOVIMIENTO">Fecha ultimo movimiento</option>                                
                            </select>
                            <input placeholder="ORDEN" 
                            <input type="submit" name="confirmarOperacion" value="BUSCAR">
                        </form>
                    </c:if>
                    
                    <c:if test="${listaBusuqeda==null && consulta==null}">
                        <form>
                            <select name="consulta">
                                <option value="CONSULTA_CLIENTE">Consultar clientes</option>
                                <option value="CONSULTA_CUENTAS">Consultar cuentas</option>
                                <option value="CONSULTA_OPERACIONES">Consultar operaciones</option>
                                <option value="CONSULTA__MAS ACTIVOS">Usuarios mas activos</option>
                                
                            </select>
                            <input type="submit" name="consultar" value="BUSCAR">
                        </form>
                    </c:if>
                    
                    <c:if test="${listaBusqueda!=null}">

                    <div class="col-lg-12">
                        <h2>.</h2>
                        <div class="table-responsive">
                            
                            <table class="table table-hover table-striped">
                                <thead>
                                    <tr>
                                        <c:forEach var="columna" items="${listaBusqueda.get(0).getDatos()}">
                                            <th>
                                                ${columna}
                                            </th>    
                                        </c:forEach>
                                            

                                    </tr>
                                </thead>
                                <tbody>
                                    
                                   
                                
                                    <c:forEach var="filaEnConsulta" items="${listaBusqueda}" begin="1">
                                        <tr>
                                        <c:forEach var="columna" items="${filaEnConsulta.getDatos()}">
                                            <td>
                                                ${columna}
                                            </td>
                                        
                                    </c:forEach>
                                        </tr>
                                    </c:forEach>
                                    
                                     
                                    
                                        
                                          

                                </tbody>
                            </table>
                            
                        </div>
                    </div>
                  </c:if>
                    
                    
                </div>






                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/resources/menu-template/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/menu-template/js/bootstrap.min.js"></script>

</body>

</html>

