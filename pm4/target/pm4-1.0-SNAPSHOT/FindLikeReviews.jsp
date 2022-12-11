<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LikeReviews</title>
</head>
<body>
<h1>LikeReviews</h1>
<form action=likereview method="post">
	<h1>Hello</h1>
	<p>
		<label for=reviewId>reviewId</label>
		<input id="reviewId" name="reviewId" value="${fn:escapeXml(param.likeReviewId)}">
	</p>
	<p>
		<input type="submit">
		<br/><br/><br/>
		<%-- <span id="successMessage"><b>${messages.title}</b></span> --%>
	</p>
</form>
<br/>
<br/>
 	<h1>Matching Users</h1>
		<table border="1">
			<tr>
				<th>LikeReview</th>
			</tr>
			<c:forEach items="${likeReviews}" var="likeReview">
				<tr>
					<td><c:out value="${likeReview.getUser().getUserId()}" /></td>
				</tr>
			</c:forEach>
		</table>

</body>
</html>