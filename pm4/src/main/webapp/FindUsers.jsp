<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a User</title>
</head>
<body>
	<form action="findusers" method="post">
		<h1>Search for a User by FirstName</h1>
		<p>
			<label for="firstname">FirstName</label>
			<input id="firstname" name="firstname" value="${fn:escapeXml(param.firstname)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="userCreate"><a href="usercreate">Create User</a></div>
	<br/>
	<h1>Matching Users</h1>
        <table border="1">
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
                <th>LikesReviews</th>
                <th>ReviewComments</th>
                <th>LikesComments</th>
                <th>Delete User</th>
                <th>Update User</th>
            </tr>
            <c:forEach items="${Users}" var="User" >
                <tr>
                    <td><c:out value="${User.getUserId()}" /></td>
                    <td><c:out value="${User.getFirstName()}" /></td>
                    <td><c:out value="${User.getLastName()}" /></td>
                    <td><c:out value="${User.getPassword()}" /></td>
                    <td><c:out value="${User.getGender()}" /></td>
                    <td><c:out value="${User.getAge()}" /></td>
                    <td><c:out value="${User.getWeight()}" /></td>
                    <td><c:out value="${User.getHeight()}" /></td>
                    <td><c:out value="${User.getHikingLevel()}" /></td>
                    <td><c:out value="${User.getAddress()}" /></td>
                    <td><c:out value="${User.getPhone()}" /></td>
                    <td><c:out value="${User.getEmail()}" /></td>
                    <td><a href="userreviews?userid=<c:out value="${User.getUserId()}"/>">Reviews</a></td>
                    <td><a href="likesreviews?userid=<c:out value="${User.getUserId()}"/>">LikesReviews</a></td>
                    <td><a href="reviewcomments?userid=<c:out value="${User.getUserId()}"/>">ReviewComments</a></td>
                    <td><a href="likescomments?userid=<c:out value="${User.getUserId()}"/>">LikesComments</a></td>
                    <td><a href="userdelete?userid=<c:out value="${User.getUserId()}"/>">Delete</a></td>
                    <td><a href="userupdate?userid=<c:out value="${User.getUserId()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
