-- Students: Claire Cecil & Alya Nowilaty
-- Instructor: Dr. Jessica Lin
-- Emails: ccecil2@gmu.edu | anowilat@gmu.edu
-- Project - Phase 3 (TESTBED)
-- --------------------------------------------------------------------------------

INSERT INTO ADDRESS (city, state, zip) VALUES ('Long Beach', 'CA', 90802);
INSERT INTO ADDRESS (city, state, zip) VALUES ('Portland', 'OR', 97205);
INSERT INTO ADDRESS (city, state, zip) VALUES ('Chicago', 'IL', 60654);
INSERT INTO ADDRESS (city, state, zip) VALUES ('San Francisco', 'CA', 94103);

-- --------------------------------------------------------------------------------

INSERT INTO HOTEL (hotel_name, branch_ID, phone) VALUES ('Westin Hotel', 1, '5624363000');
INSERT INTO HOTEL (hotel_name, branch_ID, phone) VALUES ('Westin Hotel', 2, '3127441900');
INSERT INTO HOTEL (hotel_name, branch_ID, phone) VALUES ('Ritz Carlton Hotel', 1, '3122661000');
INSERT INTO HOTEL (hotel_name, branch_ID, phone) VALUES ('Ritz Carlton Hotel', 2, '4046590400');
INSERT INTO HOTEL (hotel_name, branch_ID, phone) VALUES ('Four Seasons Hotel', 1, '5615822800');

-- --------------------------------------------------------------------------------

INSERT INTO ROOM (hotel_name, branch_ID, type, capacity) VALUES ('Westin Hotel', 1, 'Single Suite', 2);
INSERT INTO ROOM (hotel_name, branch_ID, type, capacity) VALUES ('Westin Hotel', 1, 'Traditional Suite', 4);
INSERT INTO ROOM (hotel_name, branch_ID, type, capacity) VALUES ('Westin Hotel', 1, 'Presidential Suite', 6);
INSERT INTO ROOM (hotel_name, branch_ID, type, capacity) VALUES ('Westin Hotel', 2, 'Single Suite', 2);
INSERT INTO ROOM (hotel_name, branch_ID, type, capacity) VALUES ('Westin Hotel', 2, 'Traditional Suite', 4);
INSERT INTO ROOM (hotel_name, branch_ID, type, capacity) VALUES ('Westin Hotel', 2, 'Presidential Suite', 6);
INSERT INTO ROOM (hotel_name, branch_ID, type, capacity) VALUES ('Ritz Carlton Hotel', 1, 'Traditional Suite', 4);
INSERT INTO ROOM (hotel_name, branch_ID, type, capacity) VALUES ('Ritz Carlton Hotel', 1, 'Presidential Suite', 6);
INSERT INTO ROOM (hotel_name, branch_ID, type, capacity) VALUES ('Ritz Carlton Hotel', 2, 'Traditional Suite', 4);
INSERT INTO ROOM (hotel_name, branch_ID, type, capacity) VALUES ('Ritz Carlton Hotel', 2, 'Presidential Suite', 6);
INSERT INTO ROOM (hotel_name, branch_ID, type, capacity) VALUES ('Four Seasons Hotel', 1, 'Single Suite', 2);
INSERT INTO ROOM (hotel_name, branch_ID, type, capacity) VALUES ('Four Seasons Hotel', 1, 'Twin Suite', 4);

-- --------------------------------------------------------------------------------

INSERT INTO CUSTOMER (c_ID, first_name, last_name, age, gender) VALUES (1, 'Charles', 'Wang', 29, 'NA');
INSERT INTO CUSTOMER (c_ID, first_name, last_name, age, gender) VALUES (2, 'Jackie', 'Rowcliffe', 21, 'F');
INSERT INTO CUSTOMER (c_ID, first_name, last_name, age, gender) VALUES (3, 'Sacha', 'Scharnowske', 22, 'F');
INSERT INTO CUSTOMER (c_ID, first_name, last_name, age, gender) VALUES (4, 'Claire', 'Cecil', 22, 'F');
INSERT INTO CUSTOMER (c_ID, first_name, last_name, age, gender) VALUES (5, 'Alya', 'Nowilaty', 22, 'F');

-- --------------------------------------------------------------------------------

INSERT INTO HOTEL_ADDRESS (hotel_name, branch_ID, city, state, zip) VALUES ('Westin Hotel', 1, 'Long Beach', 'CA', 90802);
INSERT INTO HOTEL_ADDRESS (hotel_name, branch_ID, city, state, zip) VALUES ('Westin Hotel', 2, 'Portland', 'OR', 97205);
INSERT INTO HOTEL_ADDRESS (hotel_name, branch_ID, city, state, zip) VALUES ('Ritz Carlton Hotel', 1, 'Chicago', 'IL', 60654);
INSERT INTO HOTEL_ADDRESS (hotel_name, branch_ID, city, state, zip) VALUES ('Ritz Carlton Hotel', 2, 'Portland', 'OR', 97205);
INSERT INTO HOTEL_ADDRESS (hotel_name, branch_ID, city, state, zip) VALUES ('Four Seasons Hotel', 1, 'San Francisco', 'CA', 94103);

-- --------------------------------------------------------------------------------

INSERT INTO HOTEL_ROOMS (hotel_name, branch_ID, type, quantity) VALUES ('Westin Hotel', 1, 'Single Suite', 5);
INSERT INTO HOTEL_ROOMS (hotel_name, branch_ID, type, quantity) VALUES ('Westin Hotel', 1, 'Traditional Suite', 10);
INSERT INTO HOTEL_ROOMS (hotel_name, branch_ID, type, quantity) VALUES ('Westin Hotel', 1, 'Presidential Suite', 1);
INSERT INTO HOTEL_ROOMS (hotel_name, branch_ID, type, quantity) VALUES ('Westin Hotel', 2, 'Single Suite', 5);
INSERT INTO HOTEL_ROOMS (hotel_name, branch_ID, type, quantity) VALUES ('Westin Hotel', 2, 'Traditional Suite', 15);
INSERT INTO HOTEL_ROOMS (hotel_name, branch_ID, type, quantity) VALUES ('Westin Hotel', 2, 'Presidential Suite', 1);
INSERT INTO HOTEL_ROOMS (hotel_name, branch_ID, type, quantity) VALUES ('Ritz Carlton Hotel', 1, 'Traditional Suite', 20);
INSERT INTO HOTEL_ROOMS (hotel_name, branch_ID, type, quantity) VALUES ('Ritz Carlton Hotel', 1, 'Presidential Suite', 1);
INSERT INTO HOTEL_ROOMS (hotel_name, branch_ID, type, quantity) VALUES ('Ritz Carlton Hotel', 2, 'Traditional Suite', 25);
INSERT INTO HOTEL_ROOMS (hotel_name, branch_ID, type, quantity) VALUES ('Ritz Carlton Hotel', 2, 'Presidential Suite', 1);
INSERT INTO HOTEL_ROOMS (hotel_name, branch_ID, type, quantity) VALUES ('Four Seasons Hotel', 1, 'Single Suite', 10);
INSERT INTO HOTEL_ROOMS (hotel_name, branch_ID, type, quantity) VALUES ('Four Seasons Hotel', 1, 'Twin Suite', 10);

-- -------------------------------------------------------------------------------
