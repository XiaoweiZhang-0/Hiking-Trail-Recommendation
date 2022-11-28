<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a User</title>
</head>
<body>
	<h1>Create User</h1>
	<form action="usercreate" method="post">
<%--		<p>--%>
<%--			<label for="userid">UserId</label>--%>
<%--			<input id="userid" name="userid" value="">--%>
<%--		</p>--%>
		<p>
			<label for="firstname">FirstName</label>
			<input id="firstname" name="firstname" value="">
		</p>
		<p>
			<label for="lastname">LastName</label>
			<input id="lastname" name="lastname" value="">
		</p>
		<p>
			<label for="password">Password</label>
			<input id="password" name="password" value="">
		</p>
		<p>
			<label for="gender">Gender</label>
			<input id="gender" name="gender" value="">
		</p>
		<p>
			<label for="age">Age</label>
			<input id="age" name="age" value="">
		</p>
		<p>
			<label for="weight">Weight</label>
			<input id="weight" name="weight" value="">
		</p>
		<p>
			<label for="height">Height</label>
			<input id="height" name="height" value="">
		</p>
		<p>
			<label for="hikingLevel">HikingLevel</label>
			<input id="hikingLevel" name="hikingLevel" value="">
		</p>
		<p>
			<label for="address">Address</label>
			<input id="address" name="address" value="">
		</p>
		<p>
			<label for="phone">Phone</label>
			<input id="phone" name="phone" value="">
		</p>
		<p>
			<label for="email">Email</label>
			<input id="email" name="email" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
<%--	<p>--%>
<%--		<span id="successMessage"><b>${messages.success}</b></span>--%>
<%--	</p>--%>
</body>
</html>