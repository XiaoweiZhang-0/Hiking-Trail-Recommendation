<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Review</title>
</head>
<body>
	<h1>Create Review</h1>
	<form action="reviewcreate" method="post">
<%--		<p>--%>
<%--			<label for="userid">UserId</label>--%>
<%--			<input id="userid" name="userid" value="">--%>
<%--		</p>--%>
		<p>
			<label for="userId">UserId</label>
			<input id="userId" name="userId" value="">
		</p>
		<p>
			<label for="trailId">TrailId</label>
			<input id="trailId" name="trailId" value="">
		</p>
		<p>
			<label for="review">Review</label>
			<input id="review" name="review" value="">
		</p>
		<p>
			<label for="rating">Rating</label>
			<input id="rating" name="rating" value="">
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