--1 step: adding workers:
INSERT INTO worker (NAME, BIRTHDAY, LEVEL, SALARY) VALUES
    ('Ray Misterio', '1990-05-15', 'Trainee', 800),
    ('Jane Sanders', '1985-12-20', 'Junior', 1200),
    ('Jonatan Jostar', '1980-07-10', 'Middle', 3000),
    ('Mika Bell', '1978-03-25', 'Senior', 6000),
    ('Arthur Morgan', '1993-09-05', 'Trainee', 900),
    ('Sanji Winsmoke', '1987-11-18', 'Junior', 1500),
    ('Teylor Love', '1982-06-30', 'Middle', 3500),
    ('Tony Soprano', '1975-08-12', 'Senior', 7000),
    ('James Bond', '1995-04-02', 'Trainee', 950),
    ('Sophia Marcedes', '1989-10-08', 'Junior', 1700);

--2 step: adding clients:
INSERT INTO client (NAME) VALUES
    ('Microsoft'),
    ('Apple'),
    ('IBM'),
    ('SoftServe'),
    ('Google');

--3 step: adding projects:
INSERT INTO project (CLIENT_ID, START_DATE, END_DATE) VALUES
        ((SELECT ID FROM client WHERE NAME = 'Microsoft'), '2023-01-01', '2023-05-15'),
        ((SELECT ID FROM client WHERE NAME = 'Microsoft'), '2023-02-15', '2024-04-20'),
        ((SELECT ID FROM client WHERE NAME = 'Apple'), '2023-03-20', '2023-09-25'),
        ((SELECT ID FROM client WHERE NAME = 'Apple'), '2023-04-10', '2023-11-30'),
        ((SELECT ID FROM client WHERE NAME = 'IBM'), '2023-05-01', '2023-08-10'),
        ((SELECT ID FROM client WHERE NAME = 'IBM'), '2023-06-15', '2024-02-28'),
        ((SELECT ID FROM client WHERE NAME = 'SoftServe'), '2023-07-20', '2023-12-05'),
        ((SELECT ID FROM client WHERE NAME = 'SoftServe'), '2023-08-10', '2024-01-15'),
        ((SELECT ID FROM client WHERE NAME = 'Google'), '2023-09-05', '2023-11-10'),
        ((SELECT ID FROM client WHERE NAME = 'Google'), '2023-10-15', '2024-03-20');

--4 step: adding workers to projects:
INSERT INTO project_worker (PROJECT_ID, WORKER_ID)
VALUES
    (1, 1), (1, 2), (1, 3), (1, 4),
    (2, 2), (2, 3), (2, 5), (2, 6), (2, 7),
    (3, 4), (3, 6), (3, 8), (3, 10),
    (4, 1), (4, 3), (4, 5),
    (5, 2), (5, 4), (5, 7), (5, 9),
    (6, 3), (6, 5), (6, 6),
    (7, 1), (7, 4), (7, 8),
    (8, 2), (8, 3), (8, 5), (8, 7),
    (9, 1), (9, 2), (9, 4), (9, 6),
    (10, 3), (10, 5), (10, 7), (10, 9);