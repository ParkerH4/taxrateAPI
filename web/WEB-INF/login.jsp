<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" href="./assets/styles/login.css">
        <title>atVenu Tax Rate API - Login</title>
    </head>

    <body>
        <div class="login-box">
            <div class="logo">
                <img src="./assets/images/logo_name.png" alt="AtVenu Logo" />
            </div>
            <br />
            <h1>atVenu Admin Login</h1>
            <br />
            
            <form action="login" method="post">
                <input type="text" name="username" placeholder="Username" />
                <input type="password" name="password" placeholder="Password" />
                <input type="submit" value="Login" />
            </form>
        </div>

        <p class="errorMSG">
            <c:if test="${message eq 'notfound'}"
                  >Authentication has failed. That user does not exist.</c:if
            >
            <c:if test="${message eq 'logout'}"
                  >You have successfully logged out.</c:if
            >
            <c:if test="${message eq 'empty'}"
                  >Please enter both your username and your password.</c:if
            >
        </p>

    </body>
</html>