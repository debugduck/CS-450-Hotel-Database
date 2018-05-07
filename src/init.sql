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
DROP TABLE Booking        CASCADE CONSTRAINTS;
DROP TABLE Information    CASCADE CONSTRAINTS;

-- --------------------------------------------------------------------------------
--                                    TABLES                                     --
-----------------------------------------------------------------------------------

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
--                                   TRIGGERS                                    --
-- --------------------------------------------------------------------------------

-- Trigger to check whether the reservation party size is correct:
-- | Assumes an entry has been added to Reservation w/ cost = 0

CREATE TRIGGER withinCapacity
  BEFORE INSERT ON Booking
  FOR EACH ROW

  DECLARE
    type_cap  INTEGER;
    party_s   INTEGER;
    overcap   EXCEPTION;

  BEGIN
    SELECT R.capacity INTO type_cap
    FROM Room R
    WHERE     R.hotel_name = :NEW.hotel_name
          AND R.branch_ID = :NEW.branch_ID
          AND R.type = :NEW.type;

    SELECT RV.party_size INTO party_s
    FROM Reservation RV
    WHERE     RV.c_ID = :NEW.c_ID
          AND RV.res_num = :NEW.res_num;

    IF party_s > type_cap THEN
      RAISE overcap;
    END IF;

  EXCEPTION
    WHEN overcap THEN
      Raise_application_error(-20001, 'ERROR: ROOM cannot accomodate PARTY SIZE.');
  END;
/

-- --------------------------------------------------------------------------------

-- Trigger to check whether the room type requested is available under the dates:

CREATE TRIGGER isAvailable
  BEFORE INSERT ON Booking
  FOR EACH ROW

  DECLARE
    avai_days INTEGER;
    no_room   EXCEPTION;

  BEGIN

    SELECT COUNT(*) INTO avai_days
    FROM Information I
    WHERE     I.date_from >= :NEW.check_in
          AND I.date_to <= :NEW.check_out
          AND I.hotel_name = :NEW.hotel_name
          AND I.branch_ID = :NEW.branch_ID
          AND I.type = :NEW.type
          AND I.num_avail = 0;

    IF avai_days = 0 THEN

      UPDATE Information
      SET num_avail = num_avail - 1
      WHERE     date_from >= :NEW.check_in
            AND date_to <= :NEW.check_out
            AND hotel_name = :NEW.hotel_name
            AND branch_ID = :NEW.branch_ID
            AND type = :NEW.type;

    ELSE
      RAISE no_room;
    END IF;

  EXCEPTION
    WHEN no_room THEN
      Raise_application_error(-20001, 'ERROR: No ROOMS of this TYPE available.');
  END;
/

-- --------------------------------------------------------------------------------

-- Trigger to calculate the TOTAL COST of stay at a HOTEL:
-- | Assumes the party size is correct
-- | Assumes there are rooms available

CREATE TRIGGER calculateCost
  AFTER INSERT ON Booking
  FOR EACH ROW

  DECLARE
    total   INTEGER;

  BEGIN
    SELECT SUM(I.price) INTO total
    FROM Information I
    WHERE     I.date_from >= :NEW.check_in
          AND I.date_to <= :NEW.check_out;

    UPDATE Reservation
    SET cost = total
    WHERE c_ID = :NEW.c_ID AND res_num = :NEW.res_num;

    DBMS_OUTPUT.PUT_LINE('Calculated COSTT for RESERVATION.');
  END;
/

-- --------------------------------------------------------------------------------

-- Trigger to return the availablility of room types when a booking is deleted:

CREATE TRIGGER nowAvailable
  AFTER DELETE ON Booking
  FOR EACH ROW

  BEGIN
    UPDATE Information
    SET num_avail = num_avail + 1
    WHERE     date_from >= :OLD.check_in
          AND date_to <= :OLD.check_out
          AND hotel_name = :OLD.hotel_name
          AND branch_ID = :OLD.branch_ID
          AND type = :OLD.type;
  END;
/

-- --------------------------------------------------------------------------------
