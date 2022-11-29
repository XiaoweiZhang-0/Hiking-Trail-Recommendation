<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update a User</title>
</head>
<body>
	<h1>Update User</h1>
	<form action="userupdate" method="post">
		<p>
			<label for="userId">UserId</label>
			<input id="userId" name="userId" value="${fn:escapeXml(param.userid)}">
		</p>
<%--		<p>--%>
<%--			<label for="firstname">New FirstName</label>--%>
<%--			<input id="firstname" name="firstname" value="">--%>
<%--		</p>--%>
		<p>
			<label for="lastName">New LastName</label>
			<input id="lastName" name="lastName" value="">
		</p>
<%--		<p>--%>
<%--			<label for="gender">New Gender</label>--%>
<%--			<input id="gender" name="gender" value="">--%>
<%--		</p>--%>
<%--		<p>--%>
<%--			<label for="age">New Age</label>--%>
<%--			<input id="age" name="age" value="">--%>
<%--		</p>--%>
<%--		<p>--%>
<%--			<label for="weight">New Weight</label>--%>
<%--			<input id="weight" name="weight" value="">--%>
<%--		</p>--%>
<%--		<p>--%>
<%--			<label for="height">New Height</label>--%>
<%--			<input id="height" name="height" value="">--%>
<%--		</p>--%>
<%--		<p>--%>
<%--			<label for="hikingLevel">New HikingLevel</label>--%>
<%--			<input id="hikingLevel" name="hikingLevel" value="">--%>
<%--		</p>--%>
<%--		<p>--%>
<%--			<label for="address">New Address</label>--%>
<%--			<input id="address" name="address" value="">--%>
<%--		</p>--%>
<%--		<p>--%>
<%--			<label for="phone">New Phone</label>--%>
<%--			<input id="phone" name="phone" value="">--%>
<%--		</p>--%>
<%--		<p>--%>
<%--			<label for="email">New Email</label>--%>
<%--			<input id="email" name="email" value="">--%>
<%--		</p>--%>
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