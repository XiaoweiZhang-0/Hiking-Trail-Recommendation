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
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add a new hiking history</title>
</head>
<body class="text-center mt-3 bg-secondary text-white">
<h1>Add Hiking History</h1>
<div class="w-75 mx-auto text-left">
    <form action="hikinghistorycreate" method="post">
        <p>
            <label for="userId">UserId</label>
            <input id="userId" name="userId" value="${fn:escapeXml(param.userId)}">
        </p>
        <p>
            <label for="trailId">Trail ID</label>
            <input id="trailId" name="trailId" value="">
        </p>
        <p>
            <label for="travelTime">Date ( in the format "yyyy-mm-dd" )</label>
            <input id="travelTime" name="travelTime" value="">
        </p>
        <p>
        <div class="mx-auto w-25 text-center">
            <input type="submit">
        </div>
        </p>
    </form>
</div>
</body>
</html>
