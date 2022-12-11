<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<html>

<head>
    <title>Find a User</title>
</head>

<body class="text-center mt-3 bg-secondary text-white">

<h1>Matching Users</h1>

<div id="userCreate" class="mt-5 ml-5 text-left"><a href="usercreate">Create User</a></div>

<div>
    <div class="mt-5 m-5 text-left">
        <form action="findusers" method="post">
            <h5>Search for a User by UserId</h5>
            <p>
                <label for="userId">UserId</label>
                <input id="userId" name="userId" value="${fn:escapeXml(param.userid)}">
            </p>
            <p>
                <input type="submit">
                <br/><br/><br/>
                <%--			<span id="successMessage"><b>${messages.success}</b></span>--%>
            </p>
        </form>
    </div>

    <div>
        <table border="1" class="m-auto">
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
                <%--                <th>HikingHistories</th>--%>
                <%--                <th>Reviews</th>--%>
                <%--                <th>LikesReviews</th>--%>
                <%--                <th>ReviewComments</th>--%>
                <%--                <th>LikesComments</th>--%>
                <th>Delete User</th>
                <th>Update User</th>
            </tr>
            <%--            <c:item="${user}" var="user" >--%>
            <%--            <jsp:useBean id="users" scope="request" type="java.util.List"/>--%>
            <c:forEach items="${users}" var="user">
                <%--            <c:item var="${user}" var="user">--%>
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
                        <%--                    <td><a href="userreviews?userid=<c:out value="${User.getUserId()}"/>">Reviews</a></td>--%>
                        <%--                    <td><a href="likesreviews?userid=<c:out value="${User.getUserId()}"/>">LikesReviews</a></td>--%>
                        <%--                    <td><a href="reviewcomments?userid=<c:out value="${User.getUserId()}"/>">ReviewComments</a></td>--%>
                        <%--                    <td><a href="likescomments?userid=<c:out value="${User.getUserId()}"/>">LikesComments</a></td>--%>
                    <td><a href="userdelete?userid=<c:out value="${user.getUserId()}"/>">Delete</a></td>
                    <td><a href="userupdate?userid=<c:out value="${user.getUserId()}"/>">Update</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
