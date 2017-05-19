<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Instatweet</title>
    <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<div id="login">
    <h1>Instatweet</h1>
    <form id="login-form">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" placeholder="Username" name="j_username">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" placeholder="Password" name="j_password">
        </div>
    </form>
        <button class="btn btn-info" id="login-btn" onclick="login()">Sign In</button>
        <button class="btn btn-default" id="register-btn" onclick="register()">Register</button>
</div>

<script src="bower_components/jquery/dist/jquery.min.js"></script>
<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="js/login.js"></script>
</body>
</html>
