<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>List of Schools</title>
    <%@ include file="../components/layout/page-header.jsp" %>
</head>
<body>
<%@ include file="../components/atoms/navbar.jsp" %>
    <div class="mt-2">
        <h3 class="alert alert-light">Schools list</h3>
    </div>


<div class="bottom">
    <div class="col">
        <div class="container">

            <button class="btn btn-success nBtn" onclick="window.location = '/schools/create'"> New School </button>
            <div class="card mt-2">
                <div class="card-block">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>School ID</th>
                            <th>School NAME</th>
                            <th>School Location</th>
                            <th> </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="school" items="${schools}">
                            <tr>
                                <td>${school.id}</td>
                                <td>${school.name}</td>
                                <td>${school.location}</td>
                                <td>
                                    <button class="btn btn-primary" onclick="window.location = '/schools/edit?id=${school.id}&name=${school.name}&location=${school.location}'">Edit</button>
                                    <button class="btn btn-danger" onclick="window.location = '/schools/delete?id='+${school.id}">Delete</button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <hr/>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../components/layout/page-footer.jsp" %>
</body>
</html>