<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />

<html>

<head>
    <title>Find a User</title>
</head>

<body class="text-center mt-3 bg-secondary text-white">

<h1>Matching Users</h1>

<div id="userCreate" class="mt-5 ml-5 text-left">
    <a href="usercreate" type="button" class="btn btn-light">Create User</a>
</div>

<div class="w-75 mx-auto">
    <div class="mt-5 text-left">
        <form action="findusers" method="post">
            <h5>Search for a User by UserId</h5>
            <p>
                <label for="userId">UserId</label>
                <input id="userId" name="userId" value="${fn:escapeXml(param.userid)}">
            </p>
            <p>
                <input type="submit">
            </p>
        </form>
    </div>

    <div>
        <table class="m-auto table table-secondary table-responsive text-secondary">
            <tr>
                <th>UserId</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Password</th>
                <th>Gender</th>
                <th>Age</th>
                <th>Weight</th>
                <th>Height</th>
                <th>HikingLevel</th>
                <th>Address</th>
                <th>Phone</th>
                <th>Email</th>

                <th>HikingHistories</th>
                <th>Reviews</th>
                <th>Recommendations</th>

                <th>Delete User</th>
                <th>Update User</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>

                    <td><c:out value="${user.getUserId()}"/></td>
                    <td><c:out value="${user.getFirstName()}"/></td>
                    <td><c:out value="${user.getLastName()}"/></td>
                    <td><c:out value="${user.getPassword()}"/></td>
                    <td><c:out value="${user.getGender()}"/></td>
                    <td><c:out value="${user.getAge()}"/></td>
                    <td><c:out value="${user.getWeight()}"/></td>
                    <td><c:out value="${user.getHeight()}"/></td>
                    <td><c:out value="${user.getHikingLevel()}"/></td>
                    <td><c:out value="${user.getAddress()}"/></td>
                    <td><c:out value="${user.getPhoneNumber()}"/></td>
                    <td><c:out value="${user.getEmail()}"/></td>

                    <td><a href="showhikinghistories?userId=<c:out value="${user.getUserId()}"/>">Show Hiking Histories</a></td>
                    <td><a href="reviews?userId=<c:out value="${user.getUserId()}"/>">Reviews</a></td>
                    <td><a href="userrecommendations?userId=<c:out value="${user.getUserId()}"/>">Recommendations</a></td>
                    <td><a href="userdelete?userId=<c:out value="${user.getUserId()}"/>">Delete</a></td>
                    <td><a href="userupdate?userId=<c:out value="${user.getUserId()}"/>">Update</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
