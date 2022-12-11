<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reviews</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
                <th>ReviewId</th>
                <th>UserId</th>
                <th>TrailId</th>
                <th>CreateTime</th>
                <th>Review</th>
                <th>Rating</th>
                <th>LikesCount</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${reviews}" var="review" >
                <tr>
                    <td><c:out value="${review.getReviewId()}" /></td>
                    <td><c:out value="${review.getUser().getUserId()}" /></td>
                    <td><c:out value="${review.getHikingTrail().getTrailId()}" /></td>
                    <td><fmt:formatDate value="${review.getCreatedTime()}" pattern="MM-dd-yyyy hh:mm:sa"/></td>
                    <td><c:out value="${review.getReview()}" /></td>
                    <c:choose>
					    <c:when test="${review.getRating()!=null}">
					        <td><c:out value="${review.getRating()}" /></td> 
					    </c:when>    
					    <c:otherwise>
					        <td><c:out value="null" /></td> 
					        <br />
					    </c:otherwise>
					</c:choose>
                    <td><a href="likereview?reviewId=<c:out value="${review.getReviewId()}"/>"><c:out value="${reviewsToLikes.get(review)}" /></a></td>
                    <td><a href="reviewdelete?reviewId=<c:out value="${review.getReviewId()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
       </table>
       <div id="reviewCreate"><a href="reviewcreate">Create Review</a></div>
</body>
</html>
