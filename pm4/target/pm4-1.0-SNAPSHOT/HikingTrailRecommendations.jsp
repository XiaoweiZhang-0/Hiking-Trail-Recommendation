<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recommendations</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
                <th>RecommendationId</th>
                <th>Users</th>
                <th>HikingTrails</th>
                <th>Delete Recommendation</th>
            </tr>
            <c:forEach items="${recommendations}" var="recommendation" >
                <tr>
                    <td><c:out value="${recommendation.getRecommendationId()}" /></td>
                    <td><c:out value="${recommendation.getUser().getUserId()}" /></td>
                    <td><c:out value="${recommendation.getHikingTrail().getTrailId()}" /></td>
                    <td><a href="recommendationdelete?recommendationId=<c:out value="${recommendation.getRecommendationId()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
