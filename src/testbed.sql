-- Students: Claire Cecil & Alya Nowilaty
-- Instructor: Dr. Jessica Lin
-- Emails: ccecil2@gmu.edu | anowilat@gmu.edu
-- Project - Phase 3 (TESTBED)
-- --------------------------------------------------------------------------------

INSERT INTO ADDRESS (city, state, zip)
  VALUES ('Long Beach', 'CA', 90802),
         ('Portland', 'OR', 97205),
         ('Chicago', 'IL', 60654),
         ('Atlanta', 'GA', 30303),
         ('Palm Beach', 'FL', 33480),
         ('New York City', 'NY', 10022);

-- --------------------------------------------------------------------------------

INSERT INTO HOTEL (hotel_name, branch_ID, phone)
  VALUES ('Westin Hotel', 1, '5624363000'),
         ('Westin Hotel', 2, '2077755411'),
         ('Westin Hotel', 3, '3127441900'),
         ('Ritz Carlton Hotel', 1, '3122661000'),
         ('Ritz Carlton Hotel', 2, '4046590400'),
         ('Four Seasons Hotel', 1, '5615822800'),
         ('Four Seasons Hotel', 2, '2127585700');

-- --------------------------------------------------------------------------------

INSERT INTO ROOM (hotel_name, branch_ID, type, capacity)
  VALUES ('Westin Hotel', 1, 'Single Suite', 2),
         ('Westin Hotel', 1, 'Traditional Suite', 4),
         ('Westin Hotel', 1, 'Presidential Suite', 6),
         ('Westin Hotel', 2, 'Single Suite', 2),
         ('Westin Hotel', 2, 'Traditional Suite', 4),
         ('Westin Hotel', 2, 'Presidential Suite', 6),
         ('Westin Hotel', 3, 'Single Suite', 2),
         ('Westin Hotel', 3, 'Traditional Suite', 4),
         ('Westin Hotel', 3, 'Presidential Suite', 6),
         ('Ritz Carlton Hotel', 1, 'Traditional Suite', 4),
         ('Ritz Carlton Hotel', 1, 'Presidential Suite', 6),
         ('Ritz Carlton Hotel', 2, 'Traditional Suite', 4),
         ('Ritz Carlton Hotel', 2, 'Presidential Suite', 6),
         ('Four Seasons Hotel', 1, 'Single Suite', 2),
         ('Four Seasons Hotel', 1, 'Twin Suite', 4),
         ('Four Seasons Hotel', 2, 'Single Suite', 2),
         ('Four Seasons Hotel', 2, 'Twin Suite', 4),
         ('Four Seasons Hotel', 2, 'Presidential Suite', 6);

-- --------------------------------------------------------------------------------

INSERT INTO CUSTOMER (c_ID, first_name, last_name, age, gender)
  VALUES (1, 'Charles', 'Wang', 29, 'NA'),
         (2, 'Jackie', 'Rowcliffe', 21, 'F'),
         (3, 'Sacha', 'Scharnowske', 22, 'F'),
         (4, 'Claire', 'Cecil', 22, 'F'),
         (5, 'Alya', 'Nowilaty', 22, 'F');

-- --------------------------------------------------------------------------------
-- Limit it to the 12 months of year (for simplicity)

INSERT INTO DATELIST (date_from, date_to)
  VALUES (to_date('2018-01-01', 'YYYY-MM-DD'), to_date('2018-01-31', 'YYYY-MM-DD')),
         (to_date('2018-02-01', 'YYYY-MM-DD'), to_date('2018-02-28', 'YYYY-MM-DD')),
         (to_date('2018-03-01', 'YYYY-MM-DD'), to_date('2018-03-31', 'YYYY-MM-DD')),
         (to_date('2018-04-01', 'YYYY-MM-DD'), to_date('2018-04-30', 'YYYY-MM-DD')),
         (to_date('2018-05-01', 'YYYY-MM-DD'), to_date('2018-05-31', 'YYYY-MM-DD')),
         (to_date('2018-06-01', 'YYYY-MM-DD'), to_date('2018-06-30', 'YYYY-MM-DD')),
         (to_date('2018-07-01', 'YYYY-MM-DD'), to_date('2018-07-31', 'YYYY-MM-DD')),
         (to_date('2018-08-01', 'YYYY-MM-DD'), to_date('2018-08-31', 'YYYY-MM-DD')),
         (to_date('2018-09-01', 'YYYY-MM-DD'), to_date('2018-05-30', 'YYYY-MM-DD')),
         (to_date('2018-10-01', 'YYYY-MM-DD'), to_date('2018-10-31', 'YYYY-MM-DD')),
         (to_date('2018-11-01', 'YYYY-MM-DD'), to_date('2018-11-30', 'YYYY-MM-DD')),
         (to_date('2018-12-01', 'YYYY-MM-DD'), to_date('2018-12-31', 'YYYY-MM-DD'));

-- --------------------------------------------------------------------------------

INSERT INTO HOTEL_ADDRESS (hotel_name, branch_ID, city, state, zip)
  VALUES ('Westin Hotel', 1, 'Long Beach', 'CA', 90802),
         ('Westin Hotel', 2, 'Portland', 'OR', 97205),
         ('Westin Hotel', 3, 'Chicago', 'IL', 60654),
         ('Ritz Carlton Hotel', 1, 'Chicago', 'IL', 60654),
         ('Ritz Carlton Hotel', 2, 'Atlanta', 'GA', 30303),
         ('Four Seasons Hotel', 1, 'Palm Beach', 'FL', 33480),
         ('Four Seasons Hotel', 2, 'New York City', 'NY', 10022);

-- --------------------------------------------------------------------------------

INSERT INTO HOTEL_ROOMS (hotel_name, branch_ID, type, quantity)
  VALUES ('Westin Hotel', 1, 'Single Suite', 5),
         ('Westin Hotel', 1, 'Traditional Suite', 10),
         ('Westin Hotel', 1, 'Presidential Suite', 1),
         ('Westin Hotel', 2, 'Single Suite', 5),
         ('Westin Hotel', 2, 'Traditional Suite', 15),
         ('Westin Hotel', 2, 'Presidential Suite', 1),
         ('Westin Hotel', 3, 'Single Suite', 10),
         ('Westin Hotel', 3, 'Traditional Suite', 5),
         ('Westin Hotel', 3, 'Presidential Suite', 1),
         ('Ritz Carlton Hotel', 1, 'Traditional Suite', 20),
         ('Ritz Carlton Hotel', 1, 'Presidential Suite', 1),
         ('Ritz Carlton Hotel', 2, 'Traditional Suite', 25),
         ('Ritz Carlton Hotel', 2, 'Presidential Suite', 1),
         ('Four Seasons Hotel', 1, 'Single Suite', 10),
         ('Four Seasons Hotel', 1, 'Twin Suite', 10),
         ('Four Seasons Hotel', 2, 'Single Suite', 20),
         ('Four Seasons Hotel', 2, 'Twin Suite', 10),
         ('Four Seasons Hotel', 2, 'Presidential Suite', 1);

-- --------------------------------------------------------------------------------

INSERT INTO INFORMATION (hotel_name, branch_ID, type, date_from, date_to, num_avail, price)
  VALUES ('Westin Hotel', 1, 'Single Suite', to_date('2018-01-01', 'YYYY-MM-DD'), to_date('2018-01-31','YYYY-MM-DD'), 5, 200),
         ('Westin Hotel', 1, 'Single Suite', to_date('2018-02-01', 'YYYY-MM-DD'), to_date('2018-02-28','YYYY-MM-DD'), 5, 200),
         ('Westin Hotel', 1, 'Single Suite', to_date('2018-03-01', 'YYYY-MM-DD'), to_date('2018-03-31','YYYY-MM-DD'), 5, 200),
         ('Westin Hotel', 1, 'Single Suite', to_date('2018-04-01', 'YYYY-MM-DD'), to_date('2018-04-30','YYYY-MM-DD'), 5, 200),
         ('Westin Hotel', 1, 'Single Suite', to_date('2018-05-01', 'YYYY-MM-DD'), to_date('2018-05-31','YYYY-MM-DD'), 5, 200),
         ('Westin Hotel', 1, 'Single Suite', to_date('2018-06-01', 'YYYY-MM-DD'), to_date('2018-06-30','YYYY-MM-DD'), 5, 200),
         ('Westin Hotel', 1, 'Single Suite', to_date('2018-07-01', 'YYYY-MM-DD'), to_date('2018-07-31','YYYY-MM-DD'), 5, 200),
         ('Westin Hotel', 1, 'Single Suite', to_date('2018-08-01', 'YYYY-MM-DD'), to_date('2018-08-31','YYYY-MM-DD'), 5, 200),
         ('Westin Hotel', 1, 'Single Suite', to_date('2018-09-01', 'YYYY-MM-DD'), to_date('2018-09-30','YYYY-MM-DD'), 5, 200),
         ('Westin Hotel', 1, 'Single Suite', to_date('2018-10-01', 'YYYY-MM-DD'), to_date('2018-10-30','YYYY-MM-DD'), 5, 200),
         ('Westin Hotel', 1, 'Single Suite', to_date('2018-11-01', 'YYYY-MM-DD'), to_date('2018-11-30','YYYY-MM-DD'), 5, 200),
         ('Westin Hotel', 1, 'Single Suite', to_date('2018-12-01', 'YYYY-MM-DD'), to_date('2018-12-31','YYYY-MM-DD'), 5, 200),
         ('Westin Hotel', 1, 'Traditional Suite', to_date('2018-01-01', 'YYYY-MM-DD'), to_date('2018-01-31','YYYY-MM-DD'), 10, 400),
         ('Westin Hotel', 1, 'Traditional Suite', to_date('2018-02-01', 'YYYY-MM-DD'), to_date('2018-02-28','YYYY-MM-DD'), 10, 400),
         ('Westin Hotel', 1, 'Traditional Suite', to_date('2018-03-01', 'YYYY-MM-DD'), to_date('2018-03-31','YYYY-MM-DD'), 10, 400),
         ('Westin Hotel', 1, 'Traditional Suite', to_date('2018-04-01', 'YYYY-MM-DD'), to_date('2018-04-30','YYYY-MM-DD'), 10, 400),
         ('Westin Hotel', 1, 'Traditional Suite', to_date('2018-05-01', 'YYYY-MM-DD'), to_date('2018-05-31','YYYY-MM-DD'), 10, 400),
         ('Westin Hotel', 1, 'Traditional Suite', to_date('2018-06-01', 'YYYY-MM-DD'), to_date('2018-06-30','YYYY-MM-DD'), 10, 400),
         ('Westin Hotel', 1, 'Traditional Suite', to_date('2018-07-01', 'YYYY-MM-DD'), to_date('2018-07-31','YYYY-MM-DD'), 10, 400),
         ('Westin Hotel', 1, 'Traditional Suite', to_date('2018-08-01', 'YYYY-MM-DD'), to_date('2018-08-31','YYYY-MM-DD'), 10, 400),
         ('Westin Hotel', 1, 'Traditional Suite', to_date('2018-09-01', 'YYYY-MM-DD'), to_date('2018-09-30','YYYY-MM-DD'), 10, 400),
         ('Westin Hotel', 1, 'Traditional Suite', to_date('2018-10-01', 'YYYY-MM-DD'), to_date('2018-10-30','YYYY-MM-DD'), 10, 400),
         ('Westin Hotel', 1, 'Traditional Suite', to_date('2018-11-01', 'YYYY-MM-DD'), to_date('2018-11-30','YYYY-MM-DD'), 10, 400),
         ('Westin Hotel', 1, 'Traditional Suite', to_date('2018-12-01', 'YYYY-MM-DD'), to_date('2018-12-31','YYYY-MM-DD'), 10, 400),
         ('Westin Hotel', 1, 'Presidential Suite', to_date('2018-01-01', 'YYYY-MM-DD'), to_date('2018-01-31','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 1, 'Presidential Suite', to_date('2018-02-01', 'YYYY-MM-DD'), to_date('2018-02-28','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 1, 'Presidential Suite', to_date('2018-03-01', 'YYYY-MM-DD'), to_date('2018-03-31','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 1, 'Presidential Suite', to_date('2018-04-01', 'YYYY-MM-DD'), to_date('2018-04-30','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 1, 'Presidential Suite', to_date('2018-05-01', 'YYYY-MM-DD'), to_date('2018-05-31','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 1, 'Presidential Suite', to_date('2018-06-01', 'YYYY-MM-DD'), to_date('2018-06-30','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 1, 'Presidential Suite', to_date('2018-07-01', 'YYYY-MM-DD'), to_date('2018-07-31','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 1, 'Presidential Suite', to_date('2018-08-01', 'YYYY-MM-DD'), to_date('2018-08-31','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 1, 'Presidential Suite', to_date('2018-09-01', 'YYYY-MM-DD'), to_date('2018-09-30','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 1, 'Presidential Suite', to_date('2018-10-01', 'YYYY-MM-DD'), to_date('2018-10-30','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 1, 'Presidential Suite', to_date('2018-11-01', 'YYYY-MM-DD'), to_date('2018-11-30','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 1, 'Presidential Suite', to_date('2018-12-01', 'YYYY-MM-DD'), to_date('2018-12-31','YYYY-MM-DD'), 1, 1000);

INSERT INTO INFORMATION (hotel_name, branch_ID, type, date_from, date_to, num_avail, price)
  VALUES ('Westin Hotel', 2, 'Single Suite', to_date('2018-01-01', 'YYYY-MM-DD'), to_date('2018-01-31','YYYY-MM-DD'), 5, 200),
         ('Westin Hotel', 2, 'Single Suite', to_date('2018-02-01', 'YYYY-MM-DD'), to_date('2018-02-28','YYYY-MM-DD'), 5, 200),
         ('Westin Hotel', 2, 'Single Suite', to_date('2018-03-01', 'YYYY-MM-DD'), to_date('2018-03-31','YYYY-MM-DD'), 5, 200),
         ('Westin Hotel', 2, 'Single Suite', to_date('2018-04-01', 'YYYY-MM-DD'), to_date('2018-04-30','YYYY-MM-DD'), 5, 200),
         ('Westin Hotel', 2, 'Single Suite', to_date('2018-05-01', 'YYYY-MM-DD'), to_date('2018-05-31','YYYY-MM-DD'), 5, 200),
         ('Westin Hotel', 2, 'Single Suite', to_date('2018-06-01', 'YYYY-MM-DD'), to_date('2018-06-30','YYYY-MM-DD'), 5, 200),
         ('Westin Hotel', 2, 'Single Suite', to_date('2018-07-01', 'YYYY-MM-DD'), to_date('2018-07-31','YYYY-MM-DD'), 5, 200),
         ('Westin Hotel', 2, 'Single Suite', to_date('2018-08-01', 'YYYY-MM-DD'), to_date('2018-08-31','YYYY-MM-DD'), 5, 200),
         ('Westin Hotel', 2, 'Single Suite', to_date('2018-09-01', 'YYYY-MM-DD'), to_date('2018-09-30','YYYY-MM-DD'), 5, 200),
         ('Westin Hotel', 2, 'Single Suite', to_date('2018-10-01', 'YYYY-MM-DD'), to_date('2018-10-30','YYYY-MM-DD'), 5, 200),
         ('Westin Hotel', 2, 'Single Suite', to_date('2018-11-01', 'YYYY-MM-DD'), to_date('2018-11-30','YYYY-MM-DD'), 5, 200),
         ('Westin Hotel', 2, 'Single Suite', to_date('2018-12-01', 'YYYY-MM-DD'), to_date('2018-12-31','YYYY-MM-DD'), 5, 200),
         ('Westin Hotel', 2, 'Traditional Suite', to_date('2018-01-01', 'YYYY-MM-DD'), to_date('2018-01-31','YYYY-MM-DD'), 15, 400),
         ('Westin Hotel', 2, 'Traditional Suite', to_date('2018-02-01', 'YYYY-MM-DD'), to_date('2018-02-28','YYYY-MM-DD'), 15, 400),
         ('Westin Hotel', 2, 'Traditional Suite', to_date('2018-03-01', 'YYYY-MM-DD'), to_date('2018-03-31','YYYY-MM-DD'), 15, 400),
         ('Westin Hotel', 2, 'Traditional Suite', to_date('2018-04-01', 'YYYY-MM-DD'), to_date('2018-04-30','YYYY-MM-DD'), 15, 400),
         ('Westin Hotel', 2, 'Traditional Suite', to_date('2018-05-01', 'YYYY-MM-DD'), to_date('2018-05-31','YYYY-MM-DD'), 15, 400),
         ('Westin Hotel', 2, 'Traditional Suite', to_date('2018-06-01', 'YYYY-MM-DD'), to_date('2018-06-30','YYYY-MM-DD'), 15, 400),
         ('Westin Hotel', 2, 'Traditional Suite', to_date('2018-07-01', 'YYYY-MM-DD'), to_date('2018-07-31','YYYY-MM-DD'), 15, 400),
         ('Westin Hotel', 2, 'Traditional Suite', to_date('2018-08-01', 'YYYY-MM-DD'), to_date('2018-08-31','YYYY-MM-DD'), 15, 400),
         ('Westin Hotel', 2, 'Traditional Suite', to_date('2018-09-01', 'YYYY-MM-DD'), to_date('2018-09-30','YYYY-MM-DD'), 15, 400),
         ('Westin Hotel', 2, 'Traditional Suite', to_date('2018-10-01', 'YYYY-MM-DD'), to_date('2018-10-30','YYYY-MM-DD'), 15, 400),
         ('Westin Hotel', 2, 'Traditional Suite', to_date('2018-11-01', 'YYYY-MM-DD'), to_date('2018-11-30','YYYY-MM-DD'), 15, 400),
         ('Westin Hotel', 2, 'Traditional Suite', to_date('2018-12-01', 'YYYY-MM-DD'), to_date('2018-12-31','YYYY-MM-DD'), 15, 400),
         ('Westin Hotel', 2, 'Presidential Suite', to_date('2018-01-01', 'YYYY-MM-DD'), to_date('2018-01-31','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 2, 'Presidential Suite', to_date('2018-02-01', 'YYYY-MM-DD'), to_date('2018-02-28','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 2, 'Presidential Suite', to_date('2018-03-01', 'YYYY-MM-DD'), to_date('2018-03-31','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 2, 'Presidential Suite', to_date('2018-04-01', 'YYYY-MM-DD'), to_date('2018-04-30','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 2, 'Presidential Suite', to_date('2018-05-01', 'YYYY-MM-DD'), to_date('2018-05-31','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 2, 'Presidential Suite', to_date('2018-06-01', 'YYYY-MM-DD'), to_date('2018-06-30','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 2, 'Presidential Suite', to_date('2018-07-01', 'YYYY-MM-DD'), to_date('2018-07-31','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 2, 'Presidential Suite', to_date('2018-08-01', 'YYYY-MM-DD'), to_date('2018-08-31','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 2, 'Presidential Suite', to_date('2018-09-01', 'YYYY-MM-DD'), to_date('2018-09-30','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 2, 'Presidential Suite', to_date('2018-10-01', 'YYYY-MM-DD'), to_date('2018-10-30','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 2, 'Presidential Suite', to_date('2018-11-01', 'YYYY-MM-DD'), to_date('2018-11-30','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 2, 'Presidential Suite', to_date('2018-12-01', 'YYYY-MM-DD'), to_date('2018-12-31','YYYY-MM-DD'), 1, 1000);

INSERT INTO INFORMATION (hotel_name, branch_ID, type, date_from, date_to, num_avail, price)
  VALUES ('Westin Hotel', 3, 'Single Suite', to_date('2018-01-01', 'YYYY-MM-DD'), to_date('2018-01-31','YYYY-MM-DD'), 10, 200),
         ('Westin Hotel', 3, 'Single Suite', to_date('2018-02-01', 'YYYY-MM-DD'), to_date('2018-02-28','YYYY-MM-DD'), 10, 200),
         ('Westin Hotel', 3, 'Single Suite', to_date('2018-03-01', 'YYYY-MM-DD'), to_date('2018-03-31','YYYY-MM-DD'), 10, 200),
         ('Westin Hotel', 3, 'Single Suite', to_date('2018-04-01', 'YYYY-MM-DD'), to_date('2018-04-30','YYYY-MM-DD'), 10, 200),
         ('Westin Hotel', 3, 'Single Suite', to_date('2018-05-01', 'YYYY-MM-DD'), to_date('2018-05-31','YYYY-MM-DD'), 10, 200),
         ('Westin Hotel', 3, 'Single Suite', to_date('2018-06-01', 'YYYY-MM-DD'), to_date('2018-06-30','YYYY-MM-DD'), 10, 200),
         ('Westin Hotel', 3, 'Single Suite', to_date('2018-07-01', 'YYYY-MM-DD'), to_date('2018-07-31','YYYY-MM-DD'), 10, 200),
         ('Westin Hotel', 3, 'Single Suite', to_date('2018-08-01', 'YYYY-MM-DD'), to_date('2018-08-31','YYYY-MM-DD'), 10, 200),
         ('Westin Hotel', 3, 'Single Suite', to_date('2018-09-01', 'YYYY-MM-DD'), to_date('2018-09-30','YYYY-MM-DD'), 10, 200),
         ('Westin Hotel', 3, 'Single Suite', to_date('2018-10-01', 'YYYY-MM-DD'), to_date('2018-10-30','YYYY-MM-DD'), 10, 200),
         ('Westin Hotel', 3, 'Single Suite', to_date('2018-11-01', 'YYYY-MM-DD'), to_date('2018-11-30','YYYY-MM-DD'), 10, 200),
         ('Westin Hotel', 3, 'Single Suite', to_date('2018-12-01', 'YYYY-MM-DD'), to_date('2018-12-31','YYYY-MM-DD'), 10, 200),
         ('Westin Hotel', 3, 'Traditional Suite', to_date('2018-01-01', 'YYYY-MM-DD'), to_date('2018-01-31','YYYY-MM-DD'), 5, 400),
         ('Westin Hotel', 3, 'Traditional Suite', to_date('2018-02-01', 'YYYY-MM-DD'), to_date('2018-02-28','YYYY-MM-DD'), 5, 400),
         ('Westin Hotel', 3, 'Traditional Suite', to_date('2018-03-01', 'YYYY-MM-DD'), to_date('2018-03-31','YYYY-MM-DD'), 5, 400),
         ('Westin Hotel', 3, 'Traditional Suite', to_date('2018-04-01', 'YYYY-MM-DD'), to_date('2018-04-30','YYYY-MM-DD'), 5, 400),
         ('Westin Hotel', 3, 'Traditional Suite', to_date('2018-05-01', 'YYYY-MM-DD'), to_date('2018-05-31','YYYY-MM-DD'), 5, 400),
         ('Westin Hotel', 3, 'Traditional Suite', to_date('2018-06-01', 'YYYY-MM-DD'), to_date('2018-06-30','YYYY-MM-DD'), 5, 400),
         ('Westin Hotel', 3, 'Traditional Suite', to_date('2018-07-01', 'YYYY-MM-DD'), to_date('2018-07-31','YYYY-MM-DD'), 5, 400),
         ('Westin Hotel', 3, 'Traditional Suite', to_date('2018-08-01', 'YYYY-MM-DD'), to_date('2018-08-31','YYYY-MM-DD'), 5, 400),
         ('Westin Hotel', 3, 'Traditional Suite', to_date('2018-09-01', 'YYYY-MM-DD'), to_date('2018-09-30','YYYY-MM-DD'), 5, 400),
         ('Westin Hotel', 3, 'Traditional Suite', to_date('2018-10-01', 'YYYY-MM-DD'), to_date('2018-10-30','YYYY-MM-DD'), 5, 400),
         ('Westin Hotel', 3, 'Traditional Suite', to_date('2018-11-01', 'YYYY-MM-DD'), to_date('2018-11-30','YYYY-MM-DD'), 5, 400),
         ('Westin Hotel', 3, 'Traditional Suite', to_date('2018-12-01', 'YYYY-MM-DD'), to_date('2018-12-31','YYYY-MM-DD'), 5, 400),
         ('Westin Hotel', 3, 'Presidential Suite', to_date('2018-01-01', 'YYYY-MM-DD'), to_date('2018-01-31','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 3, 'Presidential Suite', to_date('2018-02-01', 'YYYY-MM-DD'), to_date('2018-02-28','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 3, 'Presidential Suite', to_date('2018-03-01', 'YYYY-MM-DD'), to_date('2018-03-31','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 3, 'Presidential Suite', to_date('2018-04-01', 'YYYY-MM-DD'), to_date('2018-04-30','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 3, 'Presidential Suite', to_date('2018-05-01', 'YYYY-MM-DD'), to_date('2018-05-31','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 3, 'Presidential Suite', to_date('2018-06-01', 'YYYY-MM-DD'), to_date('2018-06-30','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 3, 'Presidential Suite', to_date('2018-07-01', 'YYYY-MM-DD'), to_date('2018-07-31','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 3, 'Presidential Suite', to_date('2018-08-01', 'YYYY-MM-DD'), to_date('2018-08-31','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 3, 'Presidential Suite', to_date('2018-09-01', 'YYYY-MM-DD'), to_date('2018-09-30','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 3, 'Presidential Suite', to_date('2018-10-01', 'YYYY-MM-DD'), to_date('2018-10-30','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 3, 'Presidential Suite', to_date('2018-11-01', 'YYYY-MM-DD'), to_date('2018-11-30','YYYY-MM-DD'), 1, 1000),
         ('Westin Hotel', 3, 'Presidential Suite', to_date('2018-12-01', 'YYYY-MM-DD'), to_date('2018-12-31','YYYY-MM-DD'), 1, 1000);

-- -----------------------------------------------------------

INSERT INTO INFORMATION (hotel_name, branch_ID, type, date_from, date_to, num_avail, price)
  VALUES ('Ritz Carlton Hotel', 1, 'Traditional Suite', to_date('2018-01-01', 'YYYY-MM-DD'), to_date('2018-01-31','YYYY-MM-DD'), 20, 800),
         ('Ritz Carlton Hotel', 1, 'Traditional Suite', to_date('2018-02-01', 'YYYY-MM-DD'), to_date('2018-02-28','YYYY-MM-DD'), 20, 800),
         ('Ritz Carlton Hotel', 1, 'Traditional Suite', to_date('2018-03-01', 'YYYY-MM-DD'), to_date('2018-03-31','YYYY-MM-DD'), 20, 800),
         ('Ritz Carlton Hotel', 1, 'Traditional Suite', to_date('2018-04-01', 'YYYY-MM-DD'), to_date('2018-04-30','YYYY-MM-DD'), 20, 800),
         ('Ritz Carlton Hotel', 1, 'Traditional Suite', to_date('2018-05-01', 'YYYY-MM-DD'), to_date('2018-05-31','YYYY-MM-DD'), 20, 800),
         ('Ritz Carlton Hotel', 1, 'Traditional Suite', to_date('2018-06-01', 'YYYY-MM-DD'), to_date('2018-06-30','YYYY-MM-DD'), 20, 800),
         ('Ritz Carlton Hotel', 1, 'Traditional Suite', to_date('2018-07-01', 'YYYY-MM-DD'), to_date('2018-07-31','YYYY-MM-DD'), 20, 800),
         ('Ritz Carlton Hotel', 1, 'Traditional Suite', to_date('2018-08-01', 'YYYY-MM-DD'), to_date('2018-08-31','YYYY-MM-DD'), 20, 800),
         ('Ritz Carlton Hotel', 1, 'Traditional Suite', to_date('2018-09-01', 'YYYY-MM-DD'), to_date('2018-09-30','YYYY-MM-DD'), 20, 800),
         ('Ritz Carlton Hotel', 1, 'Traditional Suite', to_date('2018-10-01', 'YYYY-MM-DD'), to_date('2018-10-30','YYYY-MM-DD'), 20, 800),
         ('Ritz Carlton Hotel', 1, 'Traditional Suite', to_date('2018-11-01', 'YYYY-MM-DD'), to_date('2018-11-30','YYYY-MM-DD'), 20, 800),
         ('Ritz Carlton Hotel', 1, 'Traditional Suite', to_date('2018-12-01', 'YYYY-MM-DD'), to_date('2018-12-31','YYYY-MM-DD'), 20, 800),
         ('Ritz Carlton Hotel', 1, 'Presidential Suite', to_date('2018-01-01', 'YYYY-MM-DD'), to_date('2018-01-31','YYYY-MM-DD'), 1, 2000),
         ('Ritz Carlton Hotel', 1, 'Presidential Suite', to_date('2018-02-01', 'YYYY-MM-DD'), to_date('2018-02-28','YYYY-MM-DD'), 1, 2000),
         ('Ritz Carlton Hotel', 1, 'Presidential Suite', to_date('2018-03-01', 'YYYY-MM-DD'), to_date('2018-03-31','YYYY-MM-DD'), 1, 2000),
         ('Ritz Carlton Hotel', 1, 'Presidential Suite', to_date('2018-04-01', 'YYYY-MM-DD'), to_date('2018-04-30','YYYY-MM-DD'), 1, 2000),
         ('Ritz Carlton Hotel', 1, 'Presidential Suite', to_date('2018-05-01', 'YYYY-MM-DD'), to_date('2018-05-31','YYYY-MM-DD'), 1, 2000),
         ('Ritz Carlton Hotel', 1, 'Presidential Suite', to_date('2018-06-01', 'YYYY-MM-DD'), to_date('2018-06-30','YYYY-MM-DD'), 1, 2000),
         ('Ritz Carlton Hotel', 1, 'Presidential Suite', to_date('2018-07-01', 'YYYY-MM-DD'), to_date('2018-07-31','YYYY-MM-DD'), 1, 2000),
         ('Ritz Carlton Hotel', 1, 'Presidential Suite', to_date('2018-08-01', 'YYYY-MM-DD'), to_date('2018-08-31','YYYY-MM-DD'), 1, 2000),
         ('Ritz Carlton Hotel', 1, 'Presidential Suite', to_date('2018-09-01', 'YYYY-MM-DD'), to_date('2018-09-30','YYYY-MM-DD'), 1, 2000),
         ('Ritz Carlton Hotel', 1, 'Presidential Suite', to_date('2018-10-01', 'YYYY-MM-DD'), to_date('2018-10-30','YYYY-MM-DD'), 1, 2000),
         ('Ritz Carlton Hotel', 1, 'Presidential Suite', to_date('2018-11-01', 'YYYY-MM-DD'), to_date('2018-11-30','YYYY-MM-DD'), 1, 2000),
         ('Ritz Carlton Hotel', 1, 'Presidential Suite', to_date('2018-12-01', 'YYYY-MM-DD'), to_date('2018-12-31','YYYY-MM-DD'), 1, 2000);

INSERT INTO INFORMATION (hotel_name, branch_ID, type, date_from, date_to, num_avail, price)
  VALUES ('Ritz Carlton Hotel', 2, 'Traditional Suite', to_date('2018-01-01', 'YYYY-MM-DD'), to_date('2018-01-31','YYYY-MM-DD'), 25, 1000),
         ('Ritz Carlton Hotel', 2, 'Traditional Suite', to_date('2018-02-01', 'YYYY-MM-DD'), to_date('2018-02-28','YYYY-MM-DD'), 25, 1000),
         ('Ritz Carlton Hotel', 2, 'Traditional Suite', to_date('2018-03-01', 'YYYY-MM-DD'), to_date('2018-03-31','YYYY-MM-DD'), 25, 1000),
         ('Ritz Carlton Hotel', 2, 'Traditional Suite', to_date('2018-04-01', 'YYYY-MM-DD'), to_date('2018-04-30','YYYY-MM-DD'), 25, 1000),
         ('Ritz Carlton Hotel', 2, 'Traditional Suite', to_date('2018-05-01', 'YYYY-MM-DD'), to_date('2018-05-31','YYYY-MM-DD'), 25, 1000),
         ('Ritz Carlton Hotel', 2, 'Traditional Suite', to_date('2018-06-01', 'YYYY-MM-DD'), to_date('2018-06-30','YYYY-MM-DD'), 25, 1000),
         ('Ritz Carlton Hotel', 2, 'Traditional Suite', to_date('2018-07-01', 'YYYY-MM-DD'), to_date('2018-07-31','YYYY-MM-DD'), 25, 1000),
         ('Ritz Carlton Hotel', 2, 'Traditional Suite', to_date('2018-08-01', 'YYYY-MM-DD'), to_date('2018-08-31','YYYY-MM-DD'), 25, 1000),
         ('Ritz Carlton Hotel', 2, 'Traditional Suite', to_date('2018-09-01', 'YYYY-MM-DD'), to_date('2018-09-30','YYYY-MM-DD'), 25, 1000),
         ('Ritz Carlton Hotel', 2, 'Traditional Suite', to_date('2018-10-01', 'YYYY-MM-DD'), to_date('2018-10-30','YYYY-MM-DD'), 25, 1000),
         ('Ritz Carlton Hotel', 2, 'Traditional Suite', to_date('2018-11-01', 'YYYY-MM-DD'), to_date('2018-11-30','YYYY-MM-DD'), 25, 1000),
         ('Ritz Carlton Hotel', 2, 'Traditional Suite', to_date('2018-12-01', 'YYYY-MM-DD'), to_date('2018-12-31','YYYY-MM-DD'), 25, 1000),
         ('Ritz Carlton Hotel', 2, 'Presidential Suite', to_date('2018-01-01', 'YYYY-MM-DD'), to_date('2018-01-31','YYYY-MM-DD'), 1, 2000),
         ('Ritz Carlton Hotel', 2, 'Presidential Suite', to_date('2018-02-01', 'YYYY-MM-DD'), to_date('2018-02-28','YYYY-MM-DD'), 1, 2000),
         ('Ritz Carlton Hotel', 2, 'Presidential Suite', to_date('2018-03-01', 'YYYY-MM-DD'), to_date('2018-03-31','YYYY-MM-DD'), 1, 2000),
         ('Ritz Carlton Hotel', 2, 'Presidential Suite', to_date('2018-04-01', 'YYYY-MM-DD'), to_date('2018-04-30','YYYY-MM-DD'), 1, 2000),
         ('Ritz Carlton Hotel', 2, 'Presidential Suite', to_date('2018-05-01', 'YYYY-MM-DD'), to_date('2018-05-31','YYYY-MM-DD'), 1, 2000),
         ('Ritz Carlton Hotel', 2, 'Presidential Suite', to_date('2018-06-01', 'YYYY-MM-DD'), to_date('2018-06-30','YYYY-MM-DD'), 1, 2000),
         ('Ritz Carlton Hotel', 2, 'Presidential Suite', to_date('2018-07-01', 'YYYY-MM-DD'), to_date('2018-07-31','YYYY-MM-DD'), 1, 2000),
         ('Ritz Carlton Hotel', 2, 'Presidential Suite', to_date('2018-08-01', 'YYYY-MM-DD'), to_date('2018-08-31','YYYY-MM-DD'), 1, 2000),
         ('Ritz Carlton Hotel', 2, 'Presidential Suite', to_date('2018-09-01', 'YYYY-MM-DD'), to_date('2018-09-30','YYYY-MM-DD'), 1, 2000),
         ('Ritz Carlton Hotel', 2, 'Presidential Suite', to_date('2018-10-01', 'YYYY-MM-DD'), to_date('2018-10-30','YYYY-MM-DD'), 1, 2000),
         ('Ritz Carlton Hotel', 2, 'Presidential Suite', to_date('2018-11-01', 'YYYY-MM-DD'), to_date('2018-11-30','YYYY-MM-DD'), 1, 2000),
         ('Ritz Carlton Hotel', 2, 'Presidential Suite', to_date('2018-12-01', 'YYYY-MM-DD'), to_date('2018-12-31','YYYY-MM-DD'), 1, 2000);

-- -----------------------------------------------------------

INSERT INTO INFORMATION (hotel_name, branch_ID, type, date_from, date_to, num_avail, price)
  VALUES ('Four Seasons Hotel', 1, 'Traditional Suite', to_date('2018-01-01', 'YYYY-MM-DD'), to_date('2018-01-31','YYYY-MM-DD'), 10, 350),
         ('Four Seasons Hotel', 1, 'Traditional Suite', to_date('2018-02-01', 'YYYY-MM-DD'), to_date('2018-02-28','YYYY-MM-DD'), 10, 350),
         ('Four Seasons Hotel', 1, 'Traditional Suite', to_date('2018-03-01', 'YYYY-MM-DD'), to_date('2018-03-31','YYYY-MM-DD'), 10, 350),
         ('Four Seasons Hotel', 1, 'Traditional Suite', to_date('2018-04-01', 'YYYY-MM-DD'), to_date('2018-04-30','YYYY-MM-DD'), 10, 350),
         ('Four Seasons Hotel', 1, 'Traditional Suite', to_date('2018-05-01', 'YYYY-MM-DD'), to_date('2018-05-31','YYYY-MM-DD'), 10, 350),
         ('Four Seasons Hotel', 1, 'Traditional Suite', to_date('2018-06-01', 'YYYY-MM-DD'), to_date('2018-06-30','YYYY-MM-DD'), 10, 350),
         ('Four Seasons Hotel', 1, 'Traditional Suite', to_date('2018-07-01', 'YYYY-MM-DD'), to_date('2018-07-31','YYYY-MM-DD'), 10, 350),
         ('Four Seasons Hotel', 1, 'Traditional Suite', to_date('2018-08-01', 'YYYY-MM-DD'), to_date('2018-08-31','YYYY-MM-DD'), 10, 350),
         ('Four Seasons Hotel', 1, 'Traditional Suite', to_date('2018-09-01', 'YYYY-MM-DD'), to_date('2018-09-30','YYYY-MM-DD'), 10, 350),
         ('Four Seasons Hotel', 1, 'Traditional Suite', to_date('2018-10-01', 'YYYY-MM-DD'), to_date('2018-10-30','YYYY-MM-DD'), 10, 350),
         ('Four Seasons Hotel', 1, 'Traditional Suite', to_date('2018-11-01', 'YYYY-MM-DD'), to_date('2018-11-30','YYYY-MM-DD'), 10, 350),
         ('Four Seasons Hotel', 1, 'Traditional Suite', to_date('2018-12-01', 'YYYY-MM-DD'), to_date('2018-12-31','YYYY-MM-DD'), 10, 350),
         ('Four Seasons Hotel', 1, 'Twin Suite', to_date('2018-01-01', 'YYYY-MM-DD'), to_date('2018-01-31','YYYY-MM-DD'), 10, 250),
         ('Four Seasons Hotel', 1, 'Twin Suite', to_date('2018-02-01', 'YYYY-MM-DD'), to_date('2018-02-28','YYYY-MM-DD'), 10, 250),
         ('Four Seasons Hotel', 1, 'Twin Suite', to_date('2018-03-01', 'YYYY-MM-DD'), to_date('2018-03-31','YYYY-MM-DD'), 10, 250),
         ('Four Seasons Hotel', 1, 'Twin Suite', to_date('2018-04-01', 'YYYY-MM-DD'), to_date('2018-04-30','YYYY-MM-DD'), 10, 250),
         ('Four Seasons Hotel', 1, 'Twin Suite', to_date('2018-05-01', 'YYYY-MM-DD'), to_date('2018-05-31','YYYY-MM-DD'), 10, 250),
         ('Four Seasons Hotel', 1, 'Twin Suite', to_date('2018-06-01', 'YYYY-MM-DD'), to_date('2018-06-30','YYYY-MM-DD'), 10, 250),
         ('Four Seasons Hotel', 1, 'Twin Suite', to_date('2018-07-01', 'YYYY-MM-DD'), to_date('2018-07-31','YYYY-MM-DD'), 10, 250),
         ('Four Seasons Hotel', 1, 'Twin Suite', to_date('2018-08-01', 'YYYY-MM-DD'), to_date('2018-08-31','YYYY-MM-DD'), 10, 250),
         ('Four Seasons Hotel', 1, 'Twin Suite', to_date('2018-09-01', 'YYYY-MM-DD'), to_date('2018-09-30','YYYY-MM-DD'), 10, 250),
         ('Four Seasons Hotel', 1, 'Twin Suite', to_date('2018-10-01', 'YYYY-MM-DD'), to_date('2018-10-30','YYYY-MM-DD'), 10, 250),
         ('Four Seasons Hotel', 1, 'Twin Suite', to_date('2018-11-01', 'YYYY-MM-DD'), to_date('2018-11-30','YYYY-MM-DD'), 10, 250),
         ('Four Seasons Hotel', 1, 'Twin Suite', to_date('2018-12-01', 'YYYY-MM-DD'), to_date('2018-12-31','YYYY-MM-DD'), 10, 250);

INSERT INTO INFORMATION (hotel_name, branch_ID, type, date_from, date_to, num_avail, price)
  VALUES ('Four Seasons Hotel', 2, 'Single Suite', to_date('2018-01-01', 'YYYY-MM-DD'), to_date('2018-01-31','YYYY-MM-DD'), 20, 100),
         ('Four Seasons Hotel', 2, 'Single Suite', to_date('2018-02-01', 'YYYY-MM-DD'), to_date('2018-02-28','YYYY-MM-DD'), 20, 100),
         ('Four Seasons Hotel', 2, 'Single Suite', to_date('2018-03-01', 'YYYY-MM-DD'), to_date('2018-03-31','YYYY-MM-DD'), 20, 100),
         ('Four Seasons Hotel', 2, 'Single Suite', to_date('2018-04-01', 'YYYY-MM-DD'), to_date('2018-04-30','YYYY-MM-DD'), 20, 100),
         ('Four Seasons Hotel', 2, 'Single Suite', to_date('2018-05-01', 'YYYY-MM-DD'), to_date('2018-05-31','YYYY-MM-DD'), 20, 100),
         ('Four Seasons Hotel', 2, 'Single Suite', to_date('2018-06-01', 'YYYY-MM-DD'), to_date('2018-06-30','YYYY-MM-DD'), 20, 100),
         ('Four Seasons Hotel', 2, 'Single Suite', to_date('2018-07-01', 'YYYY-MM-DD'), to_date('2018-07-31','YYYY-MM-DD'), 20, 100),
         ('Four Seasons Hotel', 2, 'Single Suite', to_date('2018-08-01', 'YYYY-MM-DD'), to_date('2018-08-31','YYYY-MM-DD'), 20, 100),
         ('Four Seasons Hotel', 2, 'Single Suite', to_date('2018-09-01', 'YYYY-MM-DD'), to_date('2018-09-30','YYYY-MM-DD'), 20, 100),
         ('Four Seasons Hotel', 2, 'Single Suite', to_date('2018-10-01', 'YYYY-MM-DD'), to_date('2018-10-30','YYYY-MM-DD'), 20, 100),
         ('Four Seasons Hotel', 2, 'Single Suite', to_date('2018-11-01', 'YYYY-MM-DD'), to_date('2018-11-30','YYYY-MM-DD'), 20, 100),
         ('Four Seasons Hotel', 2, 'Single Suite', to_date('2018-12-01', 'YYYY-MM-DD'), to_date('2018-12-31','YYYY-MM-DD'), 20, 100),
         ('Four Seasons Hotel', 2, 'Twin Suite', to_date('2018-01-01', 'YYYY-MM-DD'), to_date('2018-01-31','YYYY-MM-DD'), 10, 200),
         ('Four Seasons Hotel', 2, 'Twin Suite', to_date('2018-02-01', 'YYYY-MM-DD'), to_date('2018-02-28','YYYY-MM-DD'), 10, 200),
         ('Four Seasons Hotel', 2, 'Twin Suite', to_date('2018-03-01', 'YYYY-MM-DD'), to_date('2018-03-31','YYYY-MM-DD'), 10, 200),
         ('Four Seasons Hotel', 2, 'Twin Suite', to_date('2018-04-01', 'YYYY-MM-DD'), to_date('2018-04-30','YYYY-MM-DD'), 10, 200),
         ('Four Seasons Hotel', 2, 'Twin Suite', to_date('2018-05-01', 'YYYY-MM-DD'), to_date('2018-05-31','YYYY-MM-DD'), 10, 200),
         ('Four Seasons Hotel', 2, 'Twin Suite', to_date('2018-06-01', 'YYYY-MM-DD'), to_date('2018-06-30','YYYY-MM-DD'), 10, 200),
         ('Four Seasons Hotel', 2, 'Twin Suite', to_date('2018-07-01', 'YYYY-MM-DD'), to_date('2018-07-31','YYYY-MM-DD'), 10, 200),
         ('Four Seasons Hotel', 2, 'Twin Suite', to_date('2018-08-01', 'YYYY-MM-DD'), to_date('2018-08-31','YYYY-MM-DD'), 10, 200),
         ('Four Seasons Hotel', 2, 'Twin Suite', to_date('2018-09-01', 'YYYY-MM-DD'), to_date('2018-09-30','YYYY-MM-DD'), 10, 200),
         ('Four Seasons Hotel', 2, 'Twin Suite', to_date('2018-10-01', 'YYYY-MM-DD'), to_date('2018-10-30','YYYY-MM-DD'), 10, 200),
         ('Four Seasons Hotel', 2, 'Twin Suite', to_date('2018-11-01', 'YYYY-MM-DD'), to_date('2018-11-30','YYYY-MM-DD'), 10, 200),
         ('Four Seasons Hotel', 2, 'Twin Suite', to_date('2018-12-01', 'YYYY-MM-DD'), to_date('2018-12-31','YYYY-MM-DD'), 10, 200),
         ('Four Seasons Hotel', 2, 'Presidential Suite', to_date('2018-01-01', 'YYYY-MM-DD'), to_date('2018-01-31','YYYY-MM-DD'), 1, 600),
         ('Four Seasons Hotel', 2, 'Presidential Suite', to_date('2018-02-01', 'YYYY-MM-DD'), to_date('2018-02-28','YYYY-MM-DD'), 1, 600),
         ('Four Seasons Hotel', 2, 'Presidential Suite', to_date('2018-03-01', 'YYYY-MM-DD'), to_date('2018-03-31','YYYY-MM-DD'), 1, 600),
         ('Four Seasons Hotel', 2, 'Presidential Suite', to_date('2018-04-01', 'YYYY-MM-DD'), to_date('2018-04-30','YYYY-MM-DD'), 1, 600),
         ('Four Seasons Hotel', 2, 'Presidential Suite', to_date('2018-05-01', 'YYYY-MM-DD'), to_date('2018-05-31','YYYY-MM-DD'), 1, 600),
         ('Four Seasons Hotel', 2, 'Presidential Suite', to_date('2018-06-01', 'YYYY-MM-DD'), to_date('2018-06-30','YYYY-MM-DD'), 1, 600),
         ('Four Seasons Hotel', 2, 'Presidential Suite', to_date('2018-07-01', 'YYYY-MM-DD'), to_date('2018-07-31','YYYY-MM-DD'), 1, 600),
         ('Four Seasons Hotel', 2, 'Presidential Suite', to_date('2018-08-01', 'YYYY-MM-DD'), to_date('2018-08-31','YYYY-MM-DD'), 1, 600),
         ('Four Seasons Hotel', 2, 'Presidential Suite', to_date('2018-09-01', 'YYYY-MM-DD'), to_date('2018-09-30','YYYY-MM-DD'), 1, 600),
         ('Four Seasons Hotel', 2, 'Presidential Suite', to_date('2018-10-01', 'YYYY-MM-DD'), to_date('2018-10-30','YYYY-MM-DD'), 1, 600),
         ('Four Seasons Hotel', 2, 'Presidential Suite', to_date('2018-11-01', 'YYYY-MM-DD'), to_date('2018-11-30','YYYY-MM-DD'), 1, 600),
         ('Four Seasons Hotel', 2, 'Presidential Suite', to_date('2018-12-01', 'YYYY-MM-DD'), to_date('2018-12-31','YYYY-MM-DD'), 1, 600);

-- -----------------------------------------------------------
