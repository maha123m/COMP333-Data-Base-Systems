drop database drivingschoolp;
CREATE DATABASE drivingschoolP;

USE drivingschoolP;

create table trainer (
 trainerID int primary key ,
 trainerFirstName varchar(32), #composit
 trainerLastName varchar(32),  #composit
  numberOfVehiclesOwns int,
  cityAddress varchar(32),#composit
  streetAddress varchar(32),#composit
   phone1 int not null unique,
   phone2 int

   );
   
   

   
   create table trainerAvalibility (# multivalue
   trainerID int , #composite
   avalableDay varchar(32), #composite
     avalableTime varchar(32), #composite
   primary key(trainerID,avalableDay,avalableTime),
   FOREIGN KEY(trainerID) REFERENCES trainer(trainerID)
   );
   
   create table trainerQulification (# multivalue
   qulificationID int,
   trainerID int,
   qulificationDescreption varchar (100),
   primary key(qulificationID,trainerID),
   FOREIGN KEY(trainerID) REFERENCES trainer(trainerID)
   );
   
create table vehicale (
 plateNumber int primary key ,
 trainerID int ,
  vehicaleModel varchar(32),  
  productionYear int,
  
  
  transmissionType varchar(32), # automate,gear
  
  insuranteDate DATE  ,
  licenseDate date,
  FOREIGN KEY(trainerID) REFERENCES trainer(trainerID)
   );
   
   create table session (
   sessionId int primary key ,
   studentID int not Null,
   plateNumber int not Null,
   trainerID int not Null  ,
  sessionCost int not Null ,
  sessioncompleted int,
  sessionDay varchar(32), 
  sessionTime varchar(32), 
  sessionDate Date, 
  sessionStatus varchar (32),#completed,cancled
  FOREIGN KEY(plateNumber) REFERENCES vehicale (plateNumber),
  FOREIGN KEY(trainerID) REFERENCES trainer(trainerID),
  FOREIGN KEY(studentID) REFERENCES students(studentID)
   );
   
 
     create table students (
   studentId int primary key ,
   studentFirstName varchar(32), #composit
   studentmiddleName varchar(32), #composit
   studentLastName varchar(32),  #composit
   cityAddress varchar(32),#composit
   streetAddress varchar(32),#composit
   phone1 int not null unique,
   phone2 int,
  wallet int,#how much money he has payed 
  gender varchar(32), 
  birthdate Date
   );
   
    create table student_trainer (
   studentId int ,
   trainerId int ,
  primary key(studentId, trainerId),
  foreign key(studentID) REFERENCES students(studentID),
  FOREIGN KEY(trainerID) REFERENCES trainer(trainerID)
   );
   
   create table license (
   licenseId int primary key ,
   studentId int not null,
   licensetype varchar(32),
  foreign key(studentID) REFERENCES students(studentID)
   );
   
   
   -- Creating the Payment table
create table payment (
  Pid integer NOT NULL PRIMARY KEY,
  StudentId integer REFERENCES Student(student_id),
  amount integer,
  Pstatus varchar(30),
  Pmethood varchar(30),
  Pdate date
);



-- Creating the Test table
create table test1 (
  Tid integer NOT NULL PRIMARY KEY,
  StudentId integer REFERENCES Student(student_id),
  Tresult varchar(30),
  Pdate date
);

CREATE TABLE userAccounts (
    idUserAccounts INT PRIMARY KEY,
    Firstname VARCHAR(50),
    Lastname VARCHAR(50),
    Username VARCHAR(50),
    passowrd VARCHAR(50)
);


-- Inserting data into the trainer table
INSERT INTO trainer (trainerID, trainerFirstName, trainerLastName, numberOfVehiclesOwns, cityAddress, streetAddress, phone1, phone2)
VALUES 
  (1, 'John', 'Doe', 3, 'City A', 'Street 1', 0598200651, NULL),
  (2, 'Alice', 'Smith', 2, 'City B', 'Street 2', 0598200652, 0598200654),
  (3, 'Michael', 'Johnson', 1, 'City C', 'Street 3', 0598200655, NULL),
  (4, 'Emily', 'Brown', 2, 'City D', 'Street 4', 0598200656, 0598200657),
  (5, 'David', 'Taylor', 3, 'City E', 'Street 5', 0598200658, NULL);
  
  
  -- Inserting data into the trainerAvalibility table
INSERT INTO trainerAvalibility (trainerID, avalableDay, avalableTime)
VALUES 
  (1, 'Monday', '10:00 AM - 12:00 PM'),
  (2, 'Tuesday', '2:00 PM - 4:00 PM'),
  (3, 'Wednesday', '8:00 AM - 10:00 AM'),
  (4, 'Thursday', '4:00 PM - 6:00 PM'),
  (5, 'Friday', '12:00 PM - 2:00 PM');
  
  -- Inserting data into the trainerQulification table
INSERT INTO trainerQulification (qulificationID, trainerID, qulificationDescreption)
VALUES 
  (1, 1, 'Certified'),
  (2, 2, 'Defensive'),
  (3, 3, 'Motorcycle'),
  (4, 4, 'Commercial'),
  (5, 5, 'Off-Road');
  
  -- Inserting data into the vehicale table
INSERT INTO vehicale (plateNumber, trainerID, vehicaleModel, productionYear, transmissionType,  insuranteDate, licenseDate)
VALUES 
  (123, 1, 'Toyota Camry', 2020,  'Automatic', '2023-06-01', '2023-06-01'),
  (456, 2, 'Honda Civic', 2018, 'Automatic',  '2023-06-02', '2023-06-02'),
  (789, 3, 'Ford Mustang', 2019,  'Automatic','2023-06-03', '2023-06-03'),
  (101, 4, 'Chevrolet Silverado', 2021,  'Automatic',  '2023-06-04', '2023-06-04'),
  (121, 5, 'BMW X5', 2022,  'Automatic',  '2023-06-05', '2023-06-05');

-- Inserting data into the session table
INSERT INTO session (sessionId, studentId, plateNumber, trainerID, sessionCost, sessioncompleted, sessionDay, sessionTime, sessionDate, sessionStatus)
VALUES 
  (1, 1001, 123, 1, 50, 0, 'Monday', '10:00 AM - 12:00 PM', '2023-06-05', 'Scheduled'),
  (2, 1002, 456, 2, 60, 0, 'Tuesday', '2:00 PM - 4:00 PM', '2023-06-06', 'Scheduled'),
  (3, 1003, 789, 3, 55, 1, 'Wednesday', '8:00 AM - 10:00 AM', '2023-06-07', 'Completed'),
  (4, 1004, 101, 4, 65, 1, 'Thursday', '4:00 PM - 6:00 PM', '2023-06-08', 'Completed'),
  (5, 1005, 121, 5, 70, 0, 'Friday', '12:00 PM - 2:00 PM', '2023-06-09', 'Scheduled');

-- Inserting data into the students table
INSERT INTO students (studentId, studentFirstName, studentmiddleName, studentLastName, cityAddress, streetAddress, phone1, phone2, wallet, gender, birthdate)
VALUES 
  (1001, 'Jane', 'M', 'Smith', 'City B', 'Street 2',  0597874305, NULL, 500, 'Female', '2000-01-01'),
  (1002, 'Robert', 'J', 'Johnson', 'City C', 'Street 3', 0597874306, 0597874307, 750, 'Male', '1999-02-02'),
  (1003, 'Sarah', 'L', 'Williams', 'City D', 'Street 4', 0597874308, NULL, 250, 'Female', '2001-03-03'),
  (1004, 'Michael', 'A', 'Brown', 'City E', 'Street 5', 0597874309, 0597874315, 400, 'Male', '2002-04-04'),
  (1005, 'Emily', 'K', 'Taylor', 'City F', 'Street 6', 0597374305, NULL, 600, 'Female', '1998-05-05'),
  (1006, 'Emily', 'K', 'Taylor', 'City F', 'Street 6', 456789, NULL, 600, 'Female', '1998-05-05');
  

-- Inserting data into the student_trainer table
INSERT INTO student_trainer (studentId, trainerId)
VALUES 
  (1001, 1),
  (1002, 2),
  (1003, 3),
  (1004, 4),
  (1005, 5);

-- Inserting data into the license table
INSERT INTO license (licenseId,studentId, licensetype)
VALUES 
   (1, 1001, 'Car'),
  (2, 1002, 'Motorcycle'),
  (3, 1003, 'Car'),
  (4, 1004, 'Car'),
  (5, 1005, 'Car');
  
-- Inserting data into the Payment table
INSERT INTO Payment (Pid, StudentId, amount, Pstatus,Pmethood, Pdate)
VALUES 
  (1, 1001, 100, 'Paid','cash', '2023-06-01'),
  (2, 1002, 120, 'Paid','cash', '2023-06-02'),
  (3, 1003, 80, 'Pending','cash', '2023-06-03'),
  (4, 1004, 90, 'Paid','cash', '2023-06-04'),
  (5, 1005, 110, 'Pending','cash', '2023-06-05');

-- Inserting data into the Test table
INSERT INTO test1 (Tid, StudentId, Tresult, Pdate)
VALUES 
  (1, 1001, 'Pass', '2023-06-05'),
  (2, 1002, 'Fail', '2023-06-06'),
  (3, 1003, 'Pass', '2023-06-07'),
  (4, 1004, 'Pass', '2023-06-08'),
  (5, 1005, 'Fail', '2023-06-09'),
  (6, 1006, 'Fail', '2023-06-09');
  
  
  
  INSERT INTO userAccounts (idUserAccounts, Firstname, Lastname, Username, passowrd) VALUES 
    (1, 'John', 'Doe', 'johndoe', '1234'),
    (2, 'Jane', 'Smith', 'janesmith', '0000'),
    (3, 'Michael', 'Johnson', 'michaelj', '1l1l'),
    (4, 'Sarah', 'Williams', 'sarahw', '4321');



-- Retrieving data from the trainer table
SELECT * FROM trainer;

-- Retrieving data from the trainerAvalibility table
SELECT * FROM trainerAvalibility;

-- Retrieving data from the trainerQulification table
SELECT * FROM trainerQulification;

-- Retrieving data from the vehicale table
SELECT * FROM vehicale;

-- Retrieving data from the session table
SELECT * FROM session;

-- Retrieving data from the students table
SELECT * FROM students;

-- Retrieving data from the student_trainer table
SELECT * FROM student_trainer;

-- Retrieving data from the license table
SELECT * FROM license;


-- Retrieving data from the Payment table
SELECT * FROM Payment;

-- Retrieving data from the Test table
SELECT * FROM test1;

SELECT * FROM userAccounts;



show tables