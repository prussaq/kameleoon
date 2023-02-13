insert into users (id, name, email, password, date)
values (1, 'bob', 'bob@mail.com', 'pass', now()),
       (2, 'tom', 'tom@mail.com', 'pass', now());

insert into quotes (id, content, date, user_id, vote_id)
values (1, 'bob''s first quote', now(), 1, 1),
       (2, 'tom''s first quote', now(), 2, 2),
       (3, 'tom''s second quote', now(), 2, 3),
       (4, 'tom''s n quote', now(), 2, 4),
       (5, 'tom''s n quote', now(), 2, 5),
       (6, 'tom''s n quote', now(), 2, 6),
       (7, 'tom''s n quote', now(), 2, 7),
       (8, 'tom''s n quote', now(), 2, 8),
       (9, 'tom''s n quote', now(), 2, 9),
       (10, 'tom''s n quote', now(), 2, 10),
       (11, 'tom''s n quote', now(), 2, 11),
       (12, 'tom''s n quote', now(), 2, 12),
       (13, 'tom''s n quote', now(), 2, 13);

insert into votes (id, upvote, downvote)
values (1, 5, 0),
       (2, 9, 5),
       (3, 0, 0),
       (4, 1, 5),
       (5, 3, 9),
       (6, 15, 5),
       (7, 44, 2),
       (8, 6, 15),
       (9, 0, 33),
       (10, 7, 22),
       (11, 1, 2),
       (12, 90, 55),
       (13, 9, 5);