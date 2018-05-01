-- Students: Claire Cecil & Alya Nowilaty
-- Instructor: Dr. Jessica Lin
-- Emails: ccecil2@gmu.edu | anowilat@gmu.edu
-- Project - Phase 3
-- --------------------------------------------------------------------------------

-- Drop existing tables:
DROP TABLE Address        CASCADE CONSTRAINTS;
DROP TABLE Hotel          CASCADE CONSTRAINTS;
DROP TABLE Room           CASCADE CONSTRAINTS;
DROP TABLE Customer       CASCADE CONSTRAINTS;
DROP TABLE Reservation    CASCADE CONSTRAINTS;
DROP TABLE DateList       CASCADE CONSTRAINTS;
DROP TABLE Hotel_Address  CASCADE CONSTRAINTS;
DROP TABLE Hotel_Rooms    CASCADE CONSTRAINTS;
DROP TABLE Reserved       CASCADE CONSTRAINTS;
DROP TABLE Booking        CASCADE CONSTRAINTS;
DROP TABLE Information    CASCADE CONSTRAINTS;

-- --------------------------------------------------------------------------------
-- Create Tables:

CREATE TABLE Address(

  city    VARCHAR(20) NOT NULL,
  state   CHAR(2)     NOT NULL,
  zip     INTEGER     NOT NULL,
  PRIMARY KEY(city, state, zip));

CREATE TABLE Hotel(

  hotel_name  VARCHAR(50) NOT NULL,
  branch_ID   INTEGER     NOT NULL,
  phone       CHAR(10),
  PRIMARY KEY (hotel_name, branch_ID));

CREATE TABLE Room(

  hotel_name  VARCHAR(50),
  branch_ID   INTEGER,
  type        VARCHAR(20) NOT NULL,
  capacity    INTEGER,
  PRIMARY KEY (hotel_name, branch_ID, type),
  FOREIGN KEY (hotel_name, branch_ID) REFERENCES Hotel (hotel_name, branch_ID)
    ON DELETE CASCADE);

CREATE TABLE Customer(

    c_ID        INTEGER NOT NULL,
    first_name  VARCHAR(20),
    last_name   VARCHAR(20),
    age         INTEGER CHECK (age > 18),
    gender      VARCHAR(2),
    PRIMARY KEY (c_ID));

CREATE TABLE Reservation(

    c_ID        INTEGER,
    res_num     INTEGER NOT NULL,
    party_size  INTEGER NOT NULL,
    cost        INTEGER,
    PRIMARY KEY (c_ID, res_num));

CREATE TABLE DateList(

    date_from   DATE NOT NULL,
    date_to     DATE NOT NULL,
    PRIMARY KEY (date_from, date_to));

CREATE TABLE Hotel_Address(

    hotel_name  VARCHAR(50),
    branch_ID   INTEGER,
    city        VARCHAR(20),
    state       CHAR(2),
    zip         INTEGER,
    PRIMARY KEY (hotel_name, branch_ID),
    FOREIGN KEY (hotel_name, branch_ID) REFERENCES Hotel (hotel_name, branch_ID)
      ON DELETE CASCADE,
    FOREIGN KEY (city, state, zip) REFERENCES Address (city, state, zip)
      ON DELETE CASCADE);

CREATE TABLE Hotel_Rooms(

    hotel_name  VARCHAR(50),
    branch_ID   INTEGER,
    type        VARCHAR(20),
    quantity    INTEGER NOT NULL,
    PRIMARY KEY (hotel_name, branch_ID, type),
    FOREIGN KEY (hotel_name, branch_ID, type) REFERENCES Room (hotel_name, branch_ID, type)
      ON DELETE CASCADE);

CREATE TABLE Reserved(

    c_ID     INTEGER,
    res_num  INTEGER,
    PRIMARY KEY (c_ID, res_num),
    FOREIGN KEY (c_ID, res_num) REFERENCES Reservation (c_ID, res_num)
      ON DELETE CASCADE);

CREATE TABLE Booking(

    c_ID       INTEGER,
    res_num    INTEGER,
    check_in   DATE NOT NULL,
    check_out  DATE NOT NULL,
    hotel_name VARCHAR(50),
    branch_ID  INTEGER,
    type       VARCHAR(20),
    PRIMARY KEY (c_ID, res_num),
    FOREIGN KEY (c_ID, res_num) REFERENCES Reservation (c_ID, res_num)
      ON DELETE CASCADE,
    FOREIGN KEY (hotel_name, branch_ID, type) REFERENCES Room (hotel_name, branch_ID, type)
      ON DELETE CASCADE);

CREATE TABLE Information(

    hotel_name  VARCHAR(50),
    branch_ID   INTEGER,
    type        VARCHAR(20),
    date_from   DATE,
    date_to     DATE,
    num_avail   INTEGER NOT NULL,
    price       INTEGER NOT NULL,
    PRIMARY KEY (hotel_name, branch_ID, type, date_from, date_to),
    FOREIGN KEY (hotel_name, branch_ID, type) REFERENCES Room (hotel_name, branch_ID, type)
      ON DELETE CASCADE,
    FOREIGN KEY (date_from, date_to) REFERENCES DateList (date_from, date_to)
      ON DELETE CASCADE);

-- --------------------------------------------------------------------------------
