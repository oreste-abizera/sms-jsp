<%--
  Created by IntelliJ IDEA.
  User: orest
  Date: 7/11/2022
  Time: 12:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create School</title>
    <%@ include file="../components/layout/page-header.jsp" %>
</head>

<body>
<%@ include file="../components/atoms/navbar.jsp" %>

<div class="mt-2">
    <h3 class="alert alert-light">Create School</h3>
</div>

<form class="mt-5 col-sm-4" style="margin: 0 auto" method="post" action="/schools/create">
    <div class="form-group">
        <label for="name">School Name</label>
        <input required type="text" class="form-control" name="name" id="name" placeholder="Enter School Name">
    </div>
    <div class="form-group mt-2">
        <label for="location">School location</label>
        <input required type="text" class="form-control" id="location" name="location" placeholder="Enter School Location">
    </div>
    <button type="submit" class="btn btn-primary mt-4">Add School</button>
</form>

<%@ include file="../components/layout/page-footer.jsp" %>
</body>