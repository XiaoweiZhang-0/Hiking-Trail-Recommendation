<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete a Review</title>
</head>
<body>
<%--	<h1>${messages.title}</h1>--%>
	<form action="reviewdelete" method="post">
		<p>
			<div>
				<label for="reviewId">reviewId</label>
				<input id="reviewId" name="reviewId" value="${fn:escapeXml(param.reviewId)}">
			</div>
		</p>
		<p>
			<span id="submitButton" >
			<input type="submit">
			</span>
		</p>
	</form>
	<br/><br/>
	
</body>
</html>