<%@page import="com.bancandes.mb.Cuenta"%>
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
                                Cuentas:


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
                                    <i class="fa fa-info-circle"></i> ${msgOperacion}
                                </div>
                            </div>
                        </div>
                    </c:if>

                    
                    <c:if test="${tipoOperacion=='RETIRAR'}" >
                        
                        <div class="col-lg-12">
                            <form action="${pageContext.request.contextPath}/ServletCuentas">
                                
                             <div class="form-group">
                                <label>Numero Cuenta:</label>
                                <input class="form-control" name="idCuenta" value="${idCuenta}" >
                            </div>
                                
                            <div class="form-group">
                                <label>Cantidad</label>
                                <input class="form-control" name="monto" placeholder="cantidad">
                            </div>
                                
                             <div class="form-group">
                               <label>Metodo</label>
                              <select name="metodo">
                              <option>EFECTIVO</option>
                                </select>
                            </div>
                                
                            <button type="submit" class="btn btn-default" name="confirmarOperacion" value="RETIRAR">RETIRAR</button>
                            
                            </form>
                        </div>
                    </c:if>
                    
                    
                    <c:if test="${tipoOperacion=='CONSIGNAR'}" >
                      <div class="col-lg-12">
                            <form action="${pageContext.request.contextPath}/ServletCuentas">
                                
                             <div class="form-group">
                                <label>Numero Cuenta:</label>
                                <input class="form-c    ontrol" name="idCuenta" value="${idCuenta}" >
                            </div>
                                
                            <div class="form-group">
                                <label>Cantidad</label>
                                <input class="form-control" name="monto" placeholder="cantidad">
                            </div>
                                
                             <div class="form-group">
                               <label>Metodo</label>
                              <select name="metodo">
                              <option>EFECTIVO</option>
                                </select>
                            </div>
                                
                            <button type="submit" class="btn btn-default" name="confirmarOperacion" value="CONSIGNAR">CONSIGNAR</button>
                            
                            </form>
                        </div>    
                    </c:if>
                    
                      <c:if test="${ usuarioLogeado.getRol()=='CAJERO' && tipoOperacion==null}">
                        <h2>Ingrese el correo del propietario</h2>
                        <form>
                        <input name="correoPropietario">
                        <input type="submit" name="buscarCuentas" value="buscar">
                        </form>
                        
                        
                    </c:if>
                    
                    
                    
                        <%-- Ocultar la tabla--%>
                        <c:if test="${listaCuentas!=null}" >
                    <div class="col-lg-12">
                        <h2>Cuentas</h2>
                        <div class="table-responsive">
                            <form action="${pageContext.request.contextPath}/ServletCuentas">
                            <table class="table table-hover table-striped">
                                <thead>
                                    <tr>
                                        <th>Correo Propietario</th>
                                        <th>Propietario</th>
                                        <th>Tipo</th>
                                        <th>Saldo</th>
                                        <th>Estado</th>
                                        <th>Fecha Creacion</th>                                        
                                        <th>Accion</th>                                        

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="cuenta" items="${listaCuentas}">
                                    <form action="${pageContext.request.contextPath}/ServletCuentas">
                                        <tr>
                                            <td>${cuenta.getCorreoPropietario()}</td>
                                            <td>${cuenta.getNombrePropietario()}</td>
                                            <td>${cuenta.getTipo()}</td>                                            
                                            <td>${cuenta.getSaldo()}</td>
                                            <td>${cuenta.getEstado()}              
                                            <td>${cuenta.getFechaCreacion()}</td>
                                            <td>
                                                <% Cuenta cuenta=(Cuenta)pageContext.getAttribute("cuenta");
                                                   Usuario logeado=(Usuario)session.getAttribute("usuarioLogeado");
                                                   //si no es gerente y esta cerrada no deja hacer operaiones
                                                   boolean ac=!logeado.getRol().equals(Usuario.GERENTE_OFICINA) && cuenta.getEstado().equals(Cuenta.CERRADA) ;
                                                   String disabled=(ac==true)?"disabled":"asfaf";
                                                   pageContext.setAttribute("disabled", disabled);
                                                %>
                                                <select name="tipoAccion"  >
                                                    
                                              <<c:forEach var="operacion" items="${usuarioLogeado.getOperacionesCuenta()}">
                                                  <option>${operacion}</option>
                                                </c:forEach>
                                                    
                                                </select>
                                            </td>
                                        <input hidden="true" name="idCuenta" value="${cuenta.getId()}">
                                            <td><input type="submit" ${disabled} value="Confirmar"></td>
                                            
                                        </tr>
                                    </form> 
                                    </c:forEach>
                              

                                </tbody>
                            </table>
                            </form>
                        </div>
                    </div>
                </div>
                        </c:if>






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

