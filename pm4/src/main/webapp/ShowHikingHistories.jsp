<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Hiking Histories</title>
</head>
<body>
    <h1>Hiking Histories</h1>
        <table border="1">
            <a href="hikinghistorycreate?userId=${fn:escapeXml(param.userId)}">Add New History</a>
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
                    <td><a href="hikinghistoryupdate?hikingHistoryId=<c:out value="${hikingHistory.getHikingHistoryId()}"/>&userId=${fn:escapeXml(param.userId)}">Update</a></td>
                    <td><a href="hikinghistorydelete?hikingHistoryId=<c:out value="${hikingHistory.getHikingHistoryId()}"/>&userId=${fn:escapeXml(param.userId)}">Delete</a></td>
            </tr>
            </c:forEach>
        </table>
</body>
</html>
