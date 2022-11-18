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
		<h1>Search for a BlogUser by FirstName</h1>
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
	<div id="userCreate"><a href="usercreate">Create BlogUser</a></div>
	<br/>
	<h1>Matching BlogUsers</h1>
        <table border="1">
            <tr>
                <th>UserName</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>DoB</th>
                <th>BlogPosts</th>
                <th>Comments</th>
                <th>Delete BlogUser</th>
                <th>Update BlogUser</th>
            </tr>
            <c:forEach items="${blogUsers}" var="blogUser" >
                <tr>
                    <td><c:out value="${blogUser.getUserName()}" /></td>
                    <td><c:out value="${blogUser.getFirstName()}" /></td>
                    <td><c:out value="${blogUser.getLastName()}" /></td>
                    <td><fmt:formatDate value="${blogUser.getDob()}" pattern="yyyy-MM-dd"/></td>
                    <td><a href="userblogposts?username=<c:out value="${blogUser.getUserName()}"/>">BlogPosts</a></td>
                    <td><a href="blogcomments?username=<c:out value="${blogUser.getUserName()}"/>">BlogComments</a></td>
                    <td><a href="userdelete?username=<c:out value="${blogUser.getUserName()}"/>">Delete</a></td>
                    <td><a href="userupdate?username=<c:out value="${blogUser.getUserName()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
