<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" session="true" %>
<html>
<head>
    <title>Instatweet</title>
    <link rel="stylesheet" href="resources/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/new_post.css">
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
                       aria-expanded="false">${sessionScope.username}<span class="caret"></span></a>
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
<div id="new-post">
    <form id="new-post-form" action="new_post" method="post" enctype="multipart/form-data">
        <input type="file" name="j_file" id="j_file">
        <br>
        <textarea class="form-control" placeholder="Write some text" name="j_text" id="j_text"></textarea>
        <br>
        <%--suppress HtmlFormInputWithoutLabel --%>
        <input type="datetime" name="j_date_time" id="j_date_time" hidden="hidden">
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<script src="resources/bower_components/jquery/dist/jquery.min.js"></script>
<script src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="resources/js/new_post.js"></script>
</body>
</html>
