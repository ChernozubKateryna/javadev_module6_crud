INSERT INTO worker (name, birthday, level, salary) VALUES
('Stephan', '1970-01-02', 'Senior', 15000),
('Galyna', '1991-12-05', 'Middle', 3000),
('Taras', '1995-11-29', 'Junior', 1500),
('Maria', '1988-04-17', 'Senior', 8000),
('Ivan', '1992-06-14', 'Middle', 5000),
('Daryna', '1999-06-08', 'Trainee', 700),
('Oleksandr', '2002-10-10', 'Trainee', 500),
('Tamara', '1994-03-12', 'Middle', 4500),
('Dmytro', '1995-01-10', 'Senior', 8800),
('Kateryna', '1997-09-09', 'Trainee', 600);

INSERT INTO client (name) VALUES
('Palaie'),
('It`s a Good Trip'),
('BLOGMAYSTER'),
('Sh.t I know life'),
('Sergey Nemchinskiy');

INSERT INTO project (client_id, start_date, finish_date) VALUES
(5, '2021-09-01', '2023-10-31'),
(5, '2022-11-16', '2022-12-17'),
(1, '2019-02-14', '2022-11-11'),
(3, '2020-06-04', '2021-01-05'),
(2, '2019-04-10', '2022-09-27'),
(4, '2020-07-07', '2022-12-15'),
(2, '2022-06-01', '2023-02-28'),
(3, '2015-12-12', '2022-09-11'),
(1, '2021-10-24', '2024-12-31'),
(3, '2018-04-09', '2022-12-19');

INSERT INTO project_worker (project_id, worker_id) VALUES
(1, 1),
(1, 2),
(1, 5),
(1, 7),
(1, 10),
(2, 9),
(3, 4),
(3, 6),
(4, 3),
(4, 4),
(4, 5),
(5, 1),
(5, 4),
(5, 9),
(6, 1),
(6, 9),
(6, 10),
(6, 7),
(7, 5),
(8, 1),
(8, 2),
(8, 3),
(8, 4),
(8, 5),
(9, 6),
(9, 7),
(10, 8),
(10, 9),
(10, 10);