<%--
  Created by IntelliJ IDEA.
  User: blackcatecho
  Date: 12/10/22
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Add a new hiking history</title>
</head>
<body>
<h1>Add Hiking History</h1>
<form action="hikinghistorycreate" method="post">
  		<p>
  			<label for="userId">UserId</label>
  			<input id="userId" name="userId" value="${fn:escapeXml(param.userId)}">
  		</p>
<%--  <p>--%>
<%--    <label for="">FirstName</label>--%>
<%--    <input id="firstName" name="firstName" value="">--%>
<%--  </p>--%>
  <p>
    <label for="trailId">Trail ID</label>
    <input id="trailId" name="trailId" value="">
  </p>
  <p>
    <label for="travelTime">Date ( in the format "yyyy-mm-dd" )</label>
    <input id="travelTime" name="travelTime" value="">
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
