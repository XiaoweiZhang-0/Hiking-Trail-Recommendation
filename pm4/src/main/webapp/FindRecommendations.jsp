<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Recommendation</title>
</head>
<body>
	<form action="findrecommendations" method="post">
		<h1>Search for a Recommendation by recommendationId</h1>
		<p>
			<label for="recommendationId">recommendationId</label>
			<input id="recommendationId" name="recommendationId" value="${fn:escapeXml(param.recommendationId)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="recommendationCreate"><a href="recommendationcreate">Create Recommendation</a></div>
	<br/>
	<h1>Matching Recommendations</h1>
        <table border="1">
            <tr>
                <th>RecommendationId</th>
                <th>Users</th>
                <th>HikingTrails</th>
                <th>Delete Recommendation</th>
                <th>Update Recommendation</th>
            </tr>
            <c:forEach items="${recommmendations}" var="recommendation" >
                <tr>
                	<td><c:out value="${recommendation.getRecommendationId()}" /></td>
                    <td><c:out value="${recommendation.getUser().getUserId()}" /></td>
                    <td><c:out value="${recommendation.getHikingTrail().getTrailId()}" /></td>

<%--                    <td><a href="recommendationuser?recommendationId=<c:out value="${recommendation.getRecommendationId()}"/>">Users</a></td>
                    <td><a href="recommendationhikingtrail?recommendationId=<c:out value="${recommendation.getRecommendationId()}"/>">HikingTrails</a></td>
--%>
                    <td><a href="recommendationdelete?recommendationId=<c:out value="${recommendation.getRecommendationId()}"/>">Delete</a></td>
                    <td><a href="recommendationupdate?recommendationId=<c:out value="${recommendation.getRecommendationId()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
