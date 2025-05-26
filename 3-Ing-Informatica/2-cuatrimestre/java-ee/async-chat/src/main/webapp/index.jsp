<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Async Servlet Chat - Login</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>Login</h1>
<form method="post" action="chat">
    <input type="text" name="username" required>
    <input type="submit" name="login" value="login">
</form>
</body>
</html>