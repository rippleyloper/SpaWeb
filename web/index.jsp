<%-- 
    Document   : index
    Created on : 26-10-2017, 18:12:47
    Author     : Tulio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="ES-ch"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
   <meta charset="utf-8" />
   <title>Login</title>
   <meta content="width=device-width, initial-scale=1.0" name="viewport" />
   <meta content="" name="description" />
   <meta content="" name="author" />
   <link href="assets/index/bootstrap.min.css" rel="stylesheet" />
   <link href="assets/index/bootstrap-responsive.min.css" rel="stylesheet" />
   <link href="assets/index/font-awesome.css" rel="stylesheet" type="text/css"/>
   <link href="assets/index/style.css" rel="stylesheet" />
   <link href="assets/index/style-responsive.css" rel="stylesheet" />
   <link href="assets/index/style-default.css" rel="stylesheet" id="style_color" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="lock">
    <div class="lock-header">
        <!-- BEGIN LOGO -->
        <a class="center" id="logo" href="index.jsp">
            <img class="center" alt="logo" class="img-responsive" width="300" src="assets/logo/logo-largo.png">
        </a>
        <!-- END LOGO -->
    </div>
    <div class="login-wrap">
        <div class="metro single-size red">
            <div class="locked">
                <i class="icon-lock"></i>
                <span>Login</span>
            </div>
        </div>
        <form action="ServletUsuario" method="POST">
           <div class="metro double-size green">
                <div class="input-append lock-input">
                    <input type="text" name="usuario" class="input-medium" placeholder="DNI">
                </div>
            </div>
            <div class="metro double-size yellow">
                    <div class="input-append lock-input">
                        <input type="password" name="password" minlength="5" class="input-medium" placeholder="Contraseña">
                    </div>
            </div>
            <div class="metro single-size terques login">
                    <button type="submit" class="btn login-btn">
                        Entrar
                        <input type="text" name="btnAccion" value="Entrar" style="display: none">
                        <i class=" icon-long-arrow-right"></i>
                    </button>
            </div>
        </form>
        
    </div>
</body>
<!-- END BODY -->
</html>
