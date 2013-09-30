<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <title>登陆</title>
    
    <!-- Stylesheets -->
    <link rel="stylesheet" href="css/reset.css" />
    <link rel="stylesheet" href="css/formalize.css" />
    <link rel="stylesheet" href="css/main.css" />
    <script src="./js/app/login/login.js"></script>


  </head>
  
  <body id="login">

  
    <div id="login_container">
      <div id="login_form" ng-app="Login" ng-controller="LoginCtrl">

        <c:if test="${not empty error}">
            <span class="error">${error}</span>
        </c:if>

        <form method="post" action="j_spring_security_check">
          <p>
            <input type="text" id="username" name="j_username" placeholder="Username" class="{validate: {required: true}}" required/>
          </p>
          <p>
            <input type="password" id="password" name="j_password" placeholder="Password" class="{validate: {required: true}}" required/>
          </p>
          <button type="submit" name="submit" class="button blue">Login</button>
        </form>
      </div>
    </div>
  </body>
</html>