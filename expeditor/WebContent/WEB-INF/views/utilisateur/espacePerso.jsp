<%--
  Created by IntelliJ IDEA.
  User: Administrateur
  Date: 25/10/2016
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Expeditor</title>

    <!-- JQuery -->
    <script src="${pageContext.request.contextPath}/resources/JQuery/jquery-3.1.1.min.js"></script>

    <!-- Semantic UI -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/semanticUI/semantic.min.css">
    <script src="${pageContext.request.contextPath}/resources/semanticUI/semantic.min.js"></script>

    <!-- Expeditor scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/espacePerso.js"></script>

    <!-- Expeditor stylesheets -->
    <link rel="stylesheet" type="text/css" href="stylesheets/espacePerso.css">

</head>
<body>
    <div class="ui secondary  menu">
        <div class="right menu">
            <div class="item">
                <div class="ui dropdown">
                    Jeanne Oscour<i class="user icon"></i>
                </div>
            </div>
        </div>
            <div class="item">
                <div class="ui dropdown">
                    <i class="content icon"></i>
                </div>
            </div>
        </div>
    </div>

    <div class="flex-container">
        <div class="ui large form">
            <div class="two fields">
                <div class="field">
                    <label>First Name</label>
                    <input placeholder="First Name" type="text">
                </div>
                <div class="field">
                    <label>Last Name</label>
                    <input placeholder="Last Name" type="text">
                </div>
            </div>
            <div class="ui submit button">Submit</div>
        </div>
    </div>

</body>
</html>
