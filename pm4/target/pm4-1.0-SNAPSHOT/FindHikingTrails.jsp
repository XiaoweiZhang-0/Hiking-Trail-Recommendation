<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%--<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">--%>
<%--    <href findusers></href>--%>
<title>Find a Hiking Trail</title>
</head>
<body>
	<form action="findhikingtrails" method="post">
		<h1>Search for a Trail by trailId</h1>
		<p>
			<label for="trailId">trailId</label>
			<input id="trailId" name="trailId" value="${fn:escapeXml(param.trailId)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
<%--			<span id="successMessage"><b>${messages.success}</b></span>--%>
		</p>
	</form>
	<br/>
<!-- 	<div id="userCreate"><a href="usercreate">Create User</a></div> -->
	<br/>
	<h1>Matching Trail</h1>
        <table border="1">
            <tr>
                <th>trailId</th>
                <th>trailName</th>
                <th>county</th>
                <th>length</th>
                <th>trailSystem</th>
                <th>surface</th>
                <th>canBicycle</th>
                <th>canCarDrive</th>
            </tr>

             <c:forEach items="${hikingTrails}" var="hikingTrail">

                <tr>
                    <td><c:out value="${hikingTrail.getTrailId()}" /></td>
                    <td><c:out value="${hikingTrail.getTrailName()}" /></td>
                    <td><c:out value="${hikingTrail.getCounty()}" /></td>
                    <td><c:out value="${hikingTrail.getLength()}" /></td>
                    <td><c:out value="${hikingTrail.getTrailSystem()}" /></td>
                    <td><c:out value="${hikingTrail.getSurface()}" /></td>
                    <td><c:out value="${hikingTrail.isCanBicycle()}" /></td>
                    <td><c:out value="${hikingTrail.isCanCarDrive()}" /></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
