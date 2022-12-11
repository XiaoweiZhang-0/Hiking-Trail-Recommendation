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
    <title>Hiking Histories</title>
</head>

<body class="text-center mt-3 bg-secondary text-white">

<h1>Hiking Histories</h1>

<div class="w-75 mx-auto">
    <div id="userCreate" class="mt-5 ml-5 text-left">
        <a href="hikinghistorycreate?userId=${fn:escapeXml(param.userId)}" type="button" class="btn btn-light">Add New History</a>
    </div>
    <table class="w-50 mt-5 mx-auto table table-secondary table-responsive text-secondary">
        <tr>
            <th>Trail ID</th>
            <th>Trail Name</th>
            <th>Date</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${hikingHistories}" var="hikingHistory">
            <tr>
                <td><c:out value="${hikingHistory.getHikingTrail().getTrailId()}"/></td>
                <td><c:out value="${hikingHistory.getHikingTrail().getTrailName()}"/></td>
                <td><c:out value="${hikingHistory.getTravelTime()}"/></td>
                <td>
                    <a href="hikinghistoryupdate?hikingHistoryId=<c:out value="${hikingHistory.getHikingHistoryId()}"/>&userId=${fn:escapeXml(param.userId)}">Update</a>
                </td>
                <td>
                    <a href="hikinghistorydelete?hikingHistoryId=<c:out value="${hikingHistory.getHikingHistoryId()}"/>&userId=${fn:escapeXml(param.userId)}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
