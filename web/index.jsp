<%-- 
    Document   : index
    Created on : Sep 22, 2015, 4:14:16 PM
    Author     : Juan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
  <head>
    <meta charset="UTF-8">
    <title>Log-in</title>
    
    
    
    <link rel='stylesheet prefetch' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css'>

    <link rel="stylesheet" href="./resources/css/style.css">

    
    
    
  </head>

  <body>

    <div class="login-card">
    <h1>Log-in</h1><br>
    <form action="${pageContext.request.contextPath}/ServletLogin" method="GET">
    <input type="text" name="user" placeholder="Username">
    <input type="password" name="password" placeholder="Password">
    <input type="submit" name="login" class="login login-submit" value="login">
  </form>
    
  <div class="login-help">
    <a href="#">Register</a> • <a href="#">Forgot Password</a>
  </div>
</div>


    
    
    
    
    
  </body>
</html>
