-- db/init/init.sql


DROP TABLE IF EXISTS employee_skills;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS skill;


CREATE TABLE skill
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);


INSERT INTO skill (name)
VALUES ('Skill: 1'),
       ('Skill: 2'),
       ('Skill: 3'),
       ('Skill: 4'),
       ('Skill: 5');


CREATE TABLE employee
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(100) NOT NULL,
    email           VARCHAR(100) NOT NULL,
    password        VARCHAR(100) NOT NULL,
    type            VARCHAR(20)  NOT NULL,
    employment_date DATE         NOT NULL
);

INSERT INTO employee (email, employment_date, name, password, type)
VALUES ('emp1@yahoo.com', '2023-06-25', 'Employee: 1', 'emp1', 'ROLE_ADMIN'),
       ('emp2@yahoo.com', '2023-06-25', 'Employee: 2', 'emp2', 'ROLE_REGULAR'),
       ('emp3@yahoo.com', '2022-06-25', 'Employee: 3', 'emp3', 'ROLE_CONSULTANT'),
       ('emp4@yahoo.com', '2022-06-25', 'Employee: 4', 'emp4', 'ROLE_ADMIN'),
       ('emp5@yahoo.com', '2021-06-25', 'Employee: 5', 'emp5', 'ROLE_REGULAR'),
       ('emp6@yahoo.com', '2024-05-27', 'Employee: 6', 'emp6', 'ROLE_CONSULTANT'),
       ('emp7@yahoo.com', '2020-06-25', 'Employee: 7', 'emp7', 'ROLE_ADMIN'),
       ('emp8@yahoo.com', '2020-06-25', 'Employee: 8', 'emp8', 'ROLE_REGULAR'),
       ('emp9@yahoo.com', '2019-06-25', 'Employee: 9', 'emp9', 'ROLE_CONSULTANT'),
       ('emp10@yahoo.com', '2019-06-25', 'Employee: 10', 'emp10', 'ROLE_ADMIN'),
       ('emp11@yahoo.com', '2024-05-27', 'Employee: 11', 'emp11', 'ROLE_CONSULTANT');

CREATE TABLE employee_skills
(
    employee_id INT NOT NULL,
    skills_id    INT NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee (id),
    FOREIGN KEY (skills_id) REFERENCES skill (id)
);

INSERT INTO employee_skills (employee_id, skills_id)
VALUES (2, 2),
       (2, 4),
       (4, 4),
       (4, 1),
       (5, 5),
       (5, 2),
       (7, 2),
       (7, 4),
       (8, 3),
       (8, 5),
       (9, 4),
       (9, 1),
       (10, 5),
       (10, 2),
       (6, 2),
       (3, 3),
       (3, 5),
       (11, 2),
       (11, 3),
       (11, 4),
       (1,4),
       (1, 1),
       (1, 3);
