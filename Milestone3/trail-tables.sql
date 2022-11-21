CREATE SCHEMA IF NOT EXISTs Trail; 
USE Trail;
DROP TABLE IF EXISTS LikeComments; 
DROP TABLE IF EXISTS LikeReviews; 
DROP TABLE IF EXISTS HikingHistories; 
DROP TABLE IF EXISTS Recommendations; 
DROP TABLE IF EXISTS Comments;
DROP TABLE IF EXISTS Reviews; 
DROP TABLE IF EXISTS HikingTrails; 
DROP TABLE IF EXISTS Friendship;
DROP TABLE IF EXISTS Users;

CREATE TABLE Users(
userId INT AUTO_INCREMENT,
firstName VARCHAR(255),
lastName VARCHAR(255),
password VARCHAR(255),
gender ENUM('Male', 'Female', 'Not specified', 'Non-binary'),
age INT,
weight INT,
height DECIMAL,
hikingLevel ENUM('Beginner', 'Intermediate', 'Advanced', 'Professional'), 
address VARCHAR(255),
phoneNumber VARCHAR(30),
email VARCHAR(255),
CONSTRAINT pk_Users_userId PRIMARY KEY(userId)
);

CREATE TABLE HikingTrails( 
trailId INT AUTO_INCREMENT, 
trailName VARCHAR(255), 
county VARCHAR(255),
length DECIMAL,
trailSystem VARCHAR(255),
surface VARCHAR(255),
canBicycle BOOLEAN DEFAULT TRUE,
canCarDrive BOOLEAN DEFAULT TRUE,
CONSTRAINT pk_HikingTrails_trailId PRIMARY KEY(trailId)
);

CREATE TABLE Reviews(
reviewId INT AUTO_INCREMENT,
userId INT,
trailId INT,
createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
review VARCHAR(255), -- made some changes
rating DECIMAL(2,1),
 CONSTRAINT pk_Reviews_reviewId PRIMARY KEY(reviewId), 
 CONSTRAINT fk_Reviews_userId FOREIGN KEY(userId) 
 REFERENCES Users(userId)
ON UPDATE CASCADE ON DELETE SET NULL, 
CONSTRAINT fk_Reviews_trailId FOREIGN KEY (trailId) 
REFERENCES HikingTrails(trailId)
ON UPDATE CASCADE ON DELETE SET NULL );

CREATE TABLE Comments(
commentId INT AUTO_INCREMENT,
userId INT,
reviewId INT,
comment TEXT,
CONSTRAINT pk_Comments_commentId PRIMARY KEY(commentId), 
CONSTRAINT fk_Comments_userId FOREIGN KEY(userId) 
REFERENCES Users(userId)
ON UPDATE CASCADE ON DELETE SET NULL,
CONSTRAINT fk_Comments_reviewId FOREIGN KEY(reviewId) 
REFERENCES Reviews(reviewId)
ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Recommendations(
recommendationId INT AUTO_INCREMENT,
userId INT,
trailId INT,
CONSTRAINT pk_Recommendations_recommendationId PRIMARY
KEY(recommendationId),
CONSTRAINT fk_Recommendations_userId FOREIGN KEY(userId) 
REFERENCES Users(userId)
ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT fk_Recommendations_trailId FOREIGN KEY(trailId) 
REFERENCES HikingTrails(trailId)
ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE HikingHistories(
hikingHistoryId INT AUTO_INCREMENT,
userId INT,
trailId INT,
travelTime DATE,
CONSTRAINT pk_HikingHistories_hikingHistoryId PRIMARY KEY(hikingHistoryId), CONSTRAINT fk_HikingHistories_userId FOREIGN KEY(userId)
REFERENCES Users(userId)
ON UPDATE CASCADE ON DELETE SET NULL,
CONSTRAINT fk_HikingHistories_trailId FOREIGN KEY(trailId)
REFERENCES HikingTrails(trailId)
ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE LikeReviews(
likeReviewId INT AUTO_INCREMENT,
userId INT,
reviewId INT,
CONSTRAINT pk_LikeReviews_likeReviewId PRIMARY KEY(likeReviewId), CONSTRAINT fk_LikeReviews_userId FOREIGN KEY(userId) REFERENCES Users(userId)
ON UPDATE CASCADE ON DELETE SET NULL,
CONSTRAINT fk_LikeReviews_reviewId FOREIGN KEY(reviewId) REFERENCES Reviews(reviewId)
ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE LikeComments(
likeCommentId INT AUTO_INCREMENT,
userId INT,
commentId INT,
CONSTRAINT pk_LikeComments_likeCommentId PRIMARY KEY(likeCommentId), CONSTRAINT fk_LikeComments_userId FOREIGN KEY(userId)
REFERENCES Users(userId)
ON UPDATE CASCADE ON DELETE SET NULL,
CONSTRAINT fk_LikeComments_commentId FOREIGN KEY(commentId) REFERENCES Comments(commentId)
ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Friendship(
userId1 INT,
userId2 INT,
CONSTRAINT pk_Friendship PRIMARY KEY (userId1, userId2),
CONSTRAINT fk_Friendship_userId1 FOREIGN KEY (userId1)
REFERENCES Users(userId)
ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT fk_Friendship_userId2 FOREIGN KEY (userId2)
REFERENCES Users(userId)
ON UPDATE CASCADE ON DELETE CASCADE
);

