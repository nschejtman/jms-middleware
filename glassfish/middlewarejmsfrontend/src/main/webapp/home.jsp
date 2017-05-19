<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" isELIgnored="false" %>
<html>
<head>
    <title>Instatweet</title>
    <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/home.css">
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Instatweet</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">
                        ${sessionScope.username}
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="home.jsp">Timeline</a></li>
                        <li><a href="new_post.jsp">New post</a></li>
                        <li><a href="logout">Sign out</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div id="timeline">
    <div class="post">
        <img src="http://www.rdiconnect.com/wp-content/uploads/2014/11/asd-research-study.png">
        <h4 class="username">nschejtman <span class="date">17/05/2017</span></h4>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam blandit elit et odio posuere egestas.
            Vestibulum pulvinar est libero, vel aliquet quam bibendum vitae. Integer sodales eget tellus lobortis
            accumsan.</p>
    </div>

</div>
<script src="bower_components/jquery/dist/jquery.min.js"></script>
<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
</body>
</html>
