<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Recommendations</title>
</head>

<body class="text-center mt-3 bg-secondary text-white">
<h1>${messages.title}</h1>

<div id="recommendationcreate" class="mt-5 ml-5 text-left">
    <a href="recommendationcreate" type="button" class="btn btn-light">Create Recommendation</a>
</div>

<div class="w-50 mx-auto text-left">
    <div>
        <table class="mt-5 mx-auto table table-secondary table-responsive text-secondary">
            <tr>
                <th>RecommendationId</th>
                <th>Users</th>
                <th>HikingTrails</th>
                <th>Delete Recommendation</th>
            </tr>
            <c:forEach items="${recommendations}" var="recommendation">
                <tr>
                    <td><c:out value="${recommendation.getRecommendationId()}"/></td>
                    <td><c:out value="${recommendation.getUser().getUserId()}"/></td>
                    <td><c:out value="${recommendation.getHikingTrail().getTrailId()}"/></td>
                    <td>
                        <a href="recommendationdelete?recommendationId=<c:out value="${recommendation.getRecommendationId()}"/>">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
