<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<form method="post" action="/newuser">
  <label for="username">Username:</label>
  <input type="text" name="username" id="username">

  <label for="password">Password:</label>
  <input type="password" name="password" id="password">

  <input type="hidden"
         name="${_csrf.parameterName}"
         value="${_csrf.token}"/>

  <button type="submit">Sign Up</button>
</form>
</body>
</html>
