 drop database drivingschool;
 
create database drivingschool; 
USE drivingschool;

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
   FOREIGN KEY(trainerID) REFERENCES trainer(trainerID) ON UPDATE CASCADE ON DELETE CASCADE
 
   );
   
   create table trainerQulification (# multivalue
   qulificationID int,
   trainerID int,
   qulificationDescreption varchar (100),
   primary key(qulificationID,trainerID),
   FOREIGN KEY(trainerID) REFERENCES trainer(trainerID) ON UPDATE CASCADE ON DELETE CASCADE
   );
   

   
CREATE TABLE vehicle (
  plateNumber INT PRIMARY KEY,
  trainerID INT DEFAULT -1,
  vehicleModel VARCHAR(32),
  productionYear INT,
  mileage INT,
  engineSize INT,
  transmissionType VARCHAR(32),
  fuelType VARCHAR(32),
  insuranceDate DATE,
  licenseDate DATE,
  FOREIGN KEY (trainerID) REFERENCES trainer(trainerID) ON UPDATE CASCADE 
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
  FOREIGN KEY(plateNumber) REFERENCES vehicle (plateNumber) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY(trainerID) REFERENCES trainer(trainerID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY(studentID) REFERENCES students(studentID) ON UPDATE CASCADE ON DELETE CASCADE
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
  foreign key(studentID) REFERENCES students(studentID)ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY(trainerID) REFERENCES trainer(trainerID)ON UPDATE CASCADE ON DELETE CASCADE
   );
   
   create table license (
   studentId int not null,
   licenseId int primary key ,
   licensetype varchar(32),

  foreign key(studentID) REFERENCES students(studentID) ON UPDATE CASCADE ON DELETE CASCADE
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
create table Test (
  Tid integer NOT NULL PRIMARY KEY,

  Tresult varchar(30),
  Pdate date,
    StudentId integer REFERENCES Student(student_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE userAccounts (
    idUserAccounts INT PRIMARY KEY,
    Firstname VARCHAR(50),
    Lastname VARCHAR(50),
    Username VARCHAR(50),
    passowrd VARCHAR(50),
     
   trainerID int   ,
     studentId integer REFERENCES Student(student_id) ON UPDATE CASCADE ON DELETE CASCADE,
      FOREIGN KEY(trainerID) REFERENCES trainer(trainerID)ON UPDATE CASCADE ON DELETE CASCADE,
    class VARCHAR(50)
);

CREATE TABLE studentAssignment (
    studentID INT PRIMARY KEY,
    newTrainerFirstName VARCHAR(50),
    newTrainerLastName VARCHAR(50)
);

create table studentsession(
studentID int , 
trainerID int ,
avalableDay varchar(32), #composite
avalableTime varchar(32), #composite
 primary key(studentId,trainerID,avalableDay,avalableTime),
FOREIGN KEY(studentID) REFERENCES students(studentID) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY(trainerID) REFERENCES trainer(trainerID) ON UPDATE CASCADE ON DELETE CASCADE
);



-- Inserting data into the trainer table
INSERT INTO trainer (trainerID, trainerFirstName, trainerLastName, numberOfVehiclesOwns, cityAddress, streetAddress, phone1, phone2)
VALUES 
  (1, 'John', 'Doe', 3, 'City A', 'Street 1', 0598200651, NULL),
  (2, 'Alice', 'Smith', 2, 'City B', 'Street 2', 0598200652, 0598200654),
  (3, 'Michael', 'Johnson', 1, 'City C', 'Street 3', 0598200655, NULL),
  (4, 'Emily', 'Brown', 2, 'City D', 'Street 4', 0598200656, 0598200657),
  (5, 'David', 'Taylor', 3, 'City E', 'Street 5', 0598200658, NULL);
  
  
  -- Inserting data into the trainer table
INSERT INTO trainer (trainerID, trainerFirstName, trainerLastName, numberOfVehiclesOwns, cityAddress, streetAddress, phone1, phone2)
VALUES 

  (2, 'Alice', 'Smith', 2, 'City B', 'Street 2', 0598200652, 0598200654);
  
  
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
delete From  vehicle 
where vehicle.plateNumber =123 ;
delete From  vehicle 
where vehicle.plateNumber =110 ;
delete From  vehicle 
where vehicle.plateNumber =101 ;
delete From  vehicle 
where vehicle.plateNumber =789 ;
delete From  vehicle 
where vehicle.plateNumber =456 ;
INSERT INTO vehicle (plateNumber, trainerID, vehicleModel, productionYear, mileage, engineSize, transmissionType, fuelType, insuranceDate, licenseDate)
VALUES 
  (123, 1, 'Toyota Camry', 2020, 50000, 2000, 'Automatic', 'Petrol', '2023-06-01', '2023-06-01'),
  (456, 2, 'Honda Civic', 2018, 40000, 1800, 'Automatic', 'Petrol', '2023-06-02', '2023-06-02'),
  (789, 3, 'Ford Mustang', 2019, 60000, 2500, 'Automatic', 'Petrol', '2023-06-03', '2023-06-03'),

  (101, 4, 'Chevrolet Silverado', 2021, 30000, 3500, 'Automatic', 'Diesel', '2023-06-04', '2023-06-04'),
    (110, 5, 'Chevrolet Silverado', 2021, 30000, 3500, 'Automatic', 'Diesel', '2023-06-04', '2023-06-04');
    
    DELETE FROM vehicle WHERE plateNumber = 123;
 
 ALTER TABLE trainer ALTER COLUMN trainerID SET DEFAULT -1;

-- Inserting data into the session table
INSERT INTO session (sessionId, studentID, plateNumber, trainerID, sessionCost, sessioncompleted, sessionDay, sessionTime, sessionDate, sessionStatus)
VALUES 
  (1, 1001, 1000, 5, 50, 0, 'Monday', '10:00 AM - 12:00 PM', '2023-06-05', 'Scheduled'),
  (5, 1004, 2000, 6, 65, 1, 'Thursday', '4:00 PM - 6:00 PM', '2023-06-08', 'Completed');

-- Inserting data into the students table
INSERT INTO students (studentId, studentFirstName, studentmiddleName, studentLastName, cityAddress, streetAddress, phone1, phone2, wallet, gender, birthdate)
VALUES 
  (1001, 'Jane', 'M', 'Smith', 'City B', 'Street 2',  0597874305, NULL, 500, 'Female', '2000-01-01'),
  (1002, 'Robert', 'J', 'Johnson', 'City C', 'Street 3', 0597874306, 0597874307, 750, 'Male', '1999-02-02'),
  (1003, 'Sarah', 'L', 'Williams', 'City D', 'Street 4', 0597874308, NULL, 250, 'Female', '2001-03-03'),
  (1004, 'Michael', 'A', 'Brown', 'City E', 'Street 5', 0597874309, 0597874315, 400, 'Male', '2002-04-04'),
  (1005, 'Emily', 'K', 'Taylor', 'City F', 'Street 6', 0597374305, NULL, 600, 'Female', '1998-05-05');
  
  
   INSERT INTO students (studentId, studentFirstName, studentmiddleName, studentLastName, cityAddress, streetAddress, phone1, phone2, wallet, gender, birthdate)
VALUES 
(200, 'Jana', 'A', 'Herzallah', 'City F', 'Street 6', 0597994305, NULL, 600, 'Female', '1998-05-05');
  
  
  INSERT INTO students (studentId, studentFirstName, studentmiddleName, studentLastName, cityAddress, streetAddress, phone1, phone2, wallet, gender, birthdate)
VALUES 
  (1006, 'a', 'M', 'Smith', 'City B', 'Street 2',  1, NULL, 500, 'Female', '2000-01-01'),
  (1007, 'b', 'J', 'Johnson', 'City C', 'Street 3', 2, 12, 750, 'Male', '1999-02-02'),
  (1008, 'c', 'L', 'Williams', 'City D', 'Street 4', 3, NULL, 250, 'Female', '2001-03-03'),
  (1009, 'd', 'A', 'Brown', 'City E', 'Street 5', 4, 13, 400, 'Male', '2002-04-04'),
  (1010, 'e', 'K', 'Taylor', 'City F', 'Street 6', 5, NULL, 600, 'Female', '1998-05-05');

-- Inserting data into the student_trainer table
INSERT INTO student_trainer (studentId, trainerId)
VALUES 
  (1001, 1),
  (1002, 2),
  (1003, 3),
  (1004, 4),
  (1005, 5);
  
  
  delete From  student_trainer 
where student_trainer .studentId =1005 ;

  delete From  student_trainer 
where student_trainer .studentId =1006 ;

  delete From  student_trainer 
where student_trainer .studentId =1007 ;

  delete From  student_trainer 
where student_trainer .studentId =1008 ;

  delete From  student_trainer 
where student_trainer .studentId =1009 ;

  INSERT INTO student_trainer (studentId, trainerId)
VALUES 
  (1006, 6),
  (1007, 6),
  (1008, 7),
  (1009, 7);


-- Inserting data into the license table
INSERT INTO license (studentId, licenseId, licensetype)
VALUES 
  (1001, 1, 'Car'),
  (1002, 2, 'Motorcycle'),
  (1003, 3, 'Car'),
  (1004, 4, 'Car'),
  (1005, 5, 'Car');
  
-- Inserting data into the Payment table
INSERT INTO payment (Pid, StudentId, amount, Pstatus, Pmethood, Pdate)
VALUES 
  (1, 1001, 100, 'Paid', 'Cash', '2023-06-01'),
  (2, 1002, 120, 'Paid', 'Cash', '2023-06-02'),
  (3, 1003, 80, 'Pending', 'Credit Card', '2023-06-03'),
  (4, 1004, 90, 'Paid', 'Cash', '2023-06-04'),
  (5, 1005, 110, 'Pending', 'Credit Card', '2023-06-05');
  -- Inserting data into the Payment table
INSERT INTO payment (Pid, StudentId, amount, Pstatus, Pmethood, Pdate)
VALUES 
  (6, 1002, 100, 'Pending', 'Cash', '2023-06-01');

-- Inserting data into the Test table
INSERT INTO Test (Tid, StudentId, Tresult, Pdate)
VALUES 
  (1, 1001, 'Pass', '2023-06-05'),
  (2, 1002, 'Fail', '2023-06-06'),
  (3, 1003, 'Pass', '2023-06-07'),
  (4, 1004, 'Pass', '2023-06-08'),
  (5, 1005, 'Fail', '2023-06-09');
  
-- Insert data into the new table with the "class" column included
INSERT INTO userAccounts (idUserAccounts, Firstname, Lastname, Username, passowrd, studentID, trainerID, class)
VALUES 
    (1, 'John', 'Doe', 'johndoe', '1234', NULL, NULL, 'administrator'),
    (2, 'Jane', 'Smith', 'janesmith', '0000', 1001, NULL, 'student'),
     (5, 'Jane', 'Smith', 'janesmith', '0000', 1002, NULL, 'student'),
          (6, 'Jane', 'Smith', 'janesmith', '0000', 1003, NULL, 'student'),
    (3, 'Michael', 'Johnson', 'michaelj', '1l1l', NULL, 3, 'trainer'),
       (7, 'Michael', 'Johnson', 'michaelj', '1l1l', NULL, 5, 'trainer'),

    (4, 'Sarah', 'Williams', 'sarahw', '4321', NULL, NULL, 'administrator');
    
    INSERT INTO userAccounts (idUserAccounts, Firstname, Lastname, Username, passowrd, studentID, trainerID, class)
VALUES 
    (10, 'John', 'Doe', 'yaz', '1234', NULL, 5, 'trainer');
    
    -- Insert data into the new table with the "class" column included
INSERT INTO userAccounts (idUserAccounts, Firstname, Lastname, Username, passowrd, studentID, trainerID, class)
VALUES 
    (7, 'John', 'Doe', 'lana', '1234', 1005, NULL, 'student');
      -- Insert data into the new table with the "class" column included
INSERT INTO userAccounts (idUserAccounts, Firstname, Lastname, Username, passowrd, studentID, trainerID, class)
VALUES 
    (9, 'John', 'Doe', 'lana', '0000', 1013, NULL, 'student');
     -- Insert data into the new table with the "class" column included
INSERT INTO userAccounts (idUserAccounts, Firstname, Lastname, Username, passowrd, studentID, trainerID, class)
VALUES 
    (8, 'J', 'Doe', 'dana', '1234', 1010, NULL, 'student');
    
        
    INSERT INTO userAccounts (idUserAccounts, Firstname, Lastname, Username, passowrd, studentID, trainerID, class)
VALUES 
    (3, 'Jana', 'Herzallah', 'janaHerz', '1234', 200, NULL, 'student');
        INSERT INTO student_trainer (studentId, trainerId)
VALUES 
  (200, 2);
  
    INSERT INTO trainerAvalibility (trainerID, avalableDay, avalableTime)
VALUES 
   (2,'Monday','3:00 PM - 5:00 PM'),
  (2,'Sunday','10:00 PM - 12:00 PM');
  
    INSERT INTO trainerAvalibility (trainerID, avalableDay, avalableTime)
VALUES 
   (5,'Monday','3:00 PM - 5:00 PM'),
  (6,'Sunday','10:00 PM - 12:00 PM');


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
SELECT * FROM Test;

SELECT * FROM userAccounts;



show tables;
