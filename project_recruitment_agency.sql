-- Java project : recruitment agency
DROP DATABASE IF EXISTS recruitment_agency;
CREATE DATABASE recruitment_agency;
USE recruitment_agency;

-- creating the tables in our database

CREATE TABLE Employer (
    name varchar(20) PRIMARY KEY NOT NULL,
    member boolean,
    password varchar(15),
    totalPosts int
);

CREATE TABLE Job_seeker (
    lastName varchar (20),
    firstName varchar (20),
    email varchar(30) PRIMARY KEY NOT NULL,
    profile varchar (15),
    birthDate Date, 
    phoneNumber double,
    password varchar(15)
);

CREATE TABLE Job_offer (
    title varchar(30),
    employer varchar(20),
    candidateProfile varchar(15),
    duration varchar(15),
    salary double,
    startDate Date,
    location varchar(20),
    candidates varchar(500),
    foreign key (employer) references Employer(name),
    offerId INT PRIMARY KEY NOT NULL AUTO_INCREMENT
);

CREATE TABLE Agency (
    name varchar(20) PRIMARY KEY NOT NULL,
    password varchar(15)
);

-- insert values into our tables

INSERT INTO Agency VALUES ('Indeed', 'iamindeed');
INSERT INTO Agency VALUES ('Hello', 'iamhello');
INSERT INTO Agency VALUES ('Work agency', 'iamwork');
INSERT INTO Agency VALUES ('New day', 'iamnewday');
INSERT INTO Agency VALUES ('Employement', 'iamemployement');
INSERT INTO Agency VALUES ('My job', 'iammyjob');

INSERT INTO Employer VALUES ('Coca Cola', 1, 'iamcoca', 22);
INSERT INTO Employer VALUES ('ECE', 0, 'iamece', 4);
INSERT INTO Employer VALUES ('Ferrari', 1, 'iamferrari', 36);
INSERT INTO Employer VALUES ('Pepsi', 1, 'iampepsi', 28);
INSERT INTO Employer VALUES ('SpaceX', 1, 'iamspacex', 23);
INSERT INTO Employer VALUES ('Williams', 0, 'iamwilliams', 18);

INSERT INTO Job_seeker VALUES ('Auvrignon', 'Charles', 'charles.auvrignon@gmail.com', 'selfemployed', '2000/08/18', 0635487512, 'charles1');
INSERT INTO Job_seeker VALUES ('Auvfort', 'Vincent', 'vincent.aufort@gmail.com', 'student', '2000/07/25', 0642411840, 'vincent1');
INSERT INTO Job_seeker VALUES ('Audouy', 'Laure', 'laure.audouy@gmail.com', 'student', '2000/02/15', 0787192621, 'laure1');
INSERT INTO Job_seeker VALUES ('Trump', 'Donald', 'donald.trump@gmail.com', 'unemployed', '1957/04/28', 0634298364, 'donald1');
INSERT INTO Job_seeker VALUES ('Biden', 'Joe', 'joe.biden@gmail.com', 'employed', '1959/05/20', 069840374, 'joe1');
INSERT INTO Job_seeker VALUES ('Jean', 'Eric', 'eric.jean@gmail.com', 'employed', '1970/10/10', 0783649204, 'eric1');

INSERT INTO Job_offer (title, employer, candidateProfile,duration,salary,startDate,location,candidates) VALUES ('president', 'Coca Cola', 'employed', 'short-term', 10000, '2021/01/20', 'Washington', 'joe.biden@gmail.com');
INSERT INTO Job_offer (title, employer, candidateProfile,duration,salary,startDate,location,candidates) VALUES ('2 months internship', 'Williams', 'student', 'internship', 1500, '2021/02/15', 'Wantage', 'laure.audouy@gmail.com');
INSERT INTO Job_offer (title, employer, candidateProfile,duration,salary,startDate,location,candidates) VALUES ('5 weeks internship', 'SpaceX', 'unemployed', 'internship', 1200, '2021/10/14', 'Cap Canaveral', 'donald.trump@gmail.com');
INSERT INTO Job_offer (title, employer, candidateProfile,duration,salary,startDate,location,candidates) VALUES ('quality engineer', 'Ferrari', 'student', 'internship', 2400, '2021/03/23', 'Milan', 'vincent.aufort@gmail.com');
INSERT INTO Job_offer (title, employer, candidateProfile,duration,salary,startDate,location,candidates) VALUES ('manager', 'Pepsi', 'employed', 'long-term', 2500, '2021/07/15', 'Miami', 'eric.jean@gmail.com');
INSERT INTO Job_offer (title, employer, candidateProfile,duration,salary,startDate,location,candidates) VALUES ('3 months internship', 'ECE', 'selfemployed', 'internship', 0, '2021/01/10', 'Paris', 'charles.auvrignon@gmail.com');
