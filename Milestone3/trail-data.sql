USE Trail;

insert into HikingTrails(trailName, county, length, trailSystem, surface, canBicycle, canCarDrive)
values("trail01", "King", 1, "national", "ASPHALT", true, true),
("trail02", "King", 2, "national", "ASPHALT", true, true),
("trail03", "King", 3, "national", "ASPHALT", false, false),
("trail04", "King", 4, "national", "ASPHALT", true, true),
("trail05", "King", 5, "national", "ASPHALT", true, true),
("trail06", "King", 6, "national", "ASPHALT", true, true),
("trail07", "King", 7, "national", "ASPHALT", false, false),
("trail08", "King", 8, "national", "ASPHALT", true, true),
("trail09", "King", 9, "national", "ASPHALT", true, true),
("trail10", "King", 10, "national", "ASPHALT", true, true),
("trail11", "King", 11, "national", "ASPHALT", true, true),
("trail12", "King", 12, "national", "ASPHALT", true, true),
("trail13", "King", 13, "national", "ASPHALT", true, true),
("trail14", "King", 14, "national", "ASPHALT", true, true),
("trail15", "King", 15, "national", "ASPHALT", true, true);

insert into Users(firstName, lastName, gender, hikingLevel)
values("A", "a", "Male", "Beginner"),
("B", "b", "Male", "Intermediate"),
("C", "c", "Male", "Advanced"),
("D", "d", "Male", "Advanced"),
("E", "e", "Male", "Professional"),
("F", "f", "Female", "Beginner"),
("G", "g", "Female", "Intermediate"),
("H", "h", "Female", "Advanced"),
("I", "i", "Female", "Advanced"),
("J", "j", "Female", "Professional"),
("K", "k", "Female", "Professional");

insert into Reviews(userId, trailId, review)
values(1, 1, "good trail"),
(2, 5, "will come again"),
(3, 6, "nice trail"),
(4, 7, "better trail"),
(5, 8, "best trail"),
(6, 9, "ultra trail");


insert into LikeReviews(userId, reviewId)
values(7, 1),
(7, 2),
(7, 3),
(7, 4),
(7, 5),
(7, 6),
(8, 1),
(8, 2),
(8, 3),
(8, 4),
(8, 5),
(8, 6),
(9, 1),
(9, 2),
(9, 3),
(9, 4),
(9, 5);

insert into HikingHistories(userId, trailId)
values(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(1, 13),
(1, 14),
(1, 15),
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(3, 5),
(3, 6),
(3, 7),
(4, 8),
(4, 9),
(4, 10),
(4, 1),
(4, 2),
(5, 3),
(10, 1), 
(11, 1),
(8, 2), 
(9, 3);

insert into Recommendations(userId, trailId)
values(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(1, 13),
(1, 14),
(1, 15),
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(3, 5),
(3, 1),
(3, 2),
(4, 3),
(4, 4),
(4, 5),
(4, 1),
(4, 2),
(5, 3);

insert into Friendship(userId1, userId2)
values(1, 2),
(1, 3),
(1, 4),
(1, 5);








