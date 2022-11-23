USE Trail;

-- 1. List all trails that allow bicycles.
select trailId
from HikingTrails
where canBicycle = true;

-- 2. List the top 10 hiking trails with the most visited history. 
select trailId, count(*) AS vistedCOUNT
from HikingHistories
group by trailID
order by count(*) desc
limit 10;

-- 3. List the top 5 reviews with the most "likes"
select reviewId, count(*) as likeCount
from LikeReviews
group by reviewId
order by count(*) desc
limit 5;

-- 4. List the 5 most recommended hiking trails. 
select trailId, count(*) as recommendCount
from Recommendations
group by trailId
order by count(*) desc
limit 5;

-- 5. List the 10 longest hiking trails. 
select trailId, length
from HikingTrails
order by length desc
limit 10;

-- 6. What is the percentage of professional-level female users out of all female users?
SELECT 
((SELECT COUNT(*) FROM Users WHERE gender = "Female" AND hikingLevel = "Professional") / 
(SELECT COUNT(*) FROM Users WHERE gender = "Female")) AS ratio;

-- 7. Assume that userId 1 visited trailId 1, list all users that are not Friendships with this userId 1, but also have visited trrailId 1.
SELECT userId
FROM 
(SELECT userId, COALESCE(userId1, 0) AS userId1, COALESCE(userId2, 0) AS userId2
FROM Users LEFT JOIN Friendship 
ON Users.userId = Friendship.userId2
WHERE COALESCE(userId1, 0) != 1 AND userId != 1) AS NotFriendshipsWithUser1
INNER JOIN 
HikingHistories USING(userId) 
WHERE trailId = 1;

-- 8. Reorder the reviews of a certain trail by its "like" count.
SELECT	Reviews.*, COUNT(*) AS likesCount
FROM Reviews INNER JOIN LikeReviews USING(reviewId)
GROUP BY reviewId
ORDER BY COUNT(*) DESC;

-- 9. For user whose userId = 1, list the top 10  users having visited same trails, ordering by the count of same trails in descending order.
SELECT HikingHistories.userId, COUNT(*) AS sameTrailsCount
FROM HikingHistories INNER JOIN 
	(SELECT trailId 
	FROM HikingHistories
	WHERE userId = 1) AS userTrails
ON HikingHistories.trailId = userTrails.trailId AND UserId != 1
GROUP BY HikingHistories.userId
ORDER BY COUNT(*) DESC;

-- 10.What is the most frequent hiking level for all the users having visited trail with trailId = 1?
SELECT Users.hikingLevel, COUNT(*) as count
FROM HikingHistories INNER JOIN Users USING(userId)
WHERE HikingHistories.trailId = 1
GROUP BY Users.hikingLevel
ORDER BY COUNT(*) DESC
LIMIT 1;







