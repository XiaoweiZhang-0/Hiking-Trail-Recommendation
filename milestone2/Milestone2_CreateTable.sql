CREATE SCHEMA IF NOT EXISTs LetGoTrailing;
USE LetGoTrailing;

DROP TABLE IF EXISTS LikeComments;
DROP TABLE IF EXISTS LikeReviews;
DROP TABLE IF EXISTS HikingHistories;
DROP TABLE IF EXISTS Recommendations;
DROP TABLE IF EXISTS Comments;
DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS HikingTrails;
DROP TABLE IF EXISTS Users;

CREATE TABLE Users(
  userID INT AUTO_INCREMENT,
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
  CONSTRAINT pk_Users_userID PRIMARY KEY(userID)
);

CREATE TABLE HikingTrails(
  trailID INT AUTO_INCREMENT,
  trailName VARCHAR(255),
  county VARCHAR(255),
  length DECIMAL,
  trailSystem VARCHAR(255),
  surface VARCHAR(255),
  canBicycle BOOLEAN DEFAULT TRUE,
  canCarDrive BOOLEAN DEFAULT TRUE,
  CONSTRAINT pk_HikingTrails_trailID PRIMARY KEY(trailID)
);

CREATE TABLE Reviews(
  reviewID INT AUTO_INCREMENT,
  userID INT,
  trailID INT,
  createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  review TEXT,
  rating DECIMAL(2,1),
  CONSTRAINT pk_Reviews_reviewID PRIMARY KEY(reviewID),
  CONSTRAINT fk_Reviews_userID FOREIGN KEY(userID)
  REFERENCES Users(userID)
  ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Reviews_trailID FOREIGN KEY (trailID)
  REFERENCES HikingTrails(trailID)
  ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE Comments(
  commentID INT AUTO_INCREMENT,
  userID INT,
  reviewID INT,
  comment TEXT,
  CONSTRAINT pk_Comments_commentID PRIMARY KEY(commentID),
  CONSTRAINT fk_Comments_userID FOREIGN KEY(userID)
  REFERENCES Users(userID)
  ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Comments_reviewID FOREIGN KEY(reviewID)
  REFERENCES Reviews(reviewID)
  ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Recommendations(
  recommendationID INT AUTO_INCREMENT,
  userID INT,
  trailID INT,
  CONSTRAINT pk_Recommendations_recommendationID PRIMARY KEY(recommendationID),
  CONSTRAINT fk_Recommendations_userID FOREIGN KEY(userID)
  REFERENCES Users(userID)
  ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_Recommendations_trailID FOREIGN KEY(trailID)
  REFERENCES HikingTrails(trailID)
  ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE HikingHistories(
  hikingHistoryID INT AUTO_INCREMENT,
  userID INT,
  trailID INT,
  travelTime DATE,
  CONSTRAINT pk_HikingHistories_hikingHistoryID PRIMARY KEY(hikingHistoryID),
  CONSTRAINT fk_HikingHistories_userID FOREIGN KEY(userID)
  REFERENCES Users(userID)
  ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_HikingHistories_trailID FOREIGN KEY(trailID)
  REFERENCES HikingTrails(trailID)
  ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE LikeReviews(
  likeReviewID INT AUTO_INCREMENT,
  userID INT,
  reviewID INT,
  CONSTRAINT pk_LikeReviews_likeReviewID PRIMARY KEY(likeReviewID),
  CONSTRAINT fk_LikeReviews_userID FOREIGN KEY(userID)
  REFERENCES Users(userID)
  ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_LikeReviews_reviewID FOREIGN KEY(reviewID)
  REFERENCES Reviews(reviewID)
  ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE LikeComments(
  likeCommentID INT AUTO_INCREMENT,
  userID INT,
  commentID INT,
  CONSTRAINT pk_LikeComments_likeCommentID PRIMARY KEY(likeCommentID),
  CONSTRAINT fk_LikeComments_userID FOREIGN KEY(userID)
  REFERENCES Users(userID)
  ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_LikeComments_commentID FOREIGN KEY(commentID)
  REFERENCES Comments(commentID)
  ON UPDATE CASCADE ON DELETE CASCADE
);
