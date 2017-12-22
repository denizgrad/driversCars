/**
 * CREATE Script for init of DB
 */

-- Create 3 OFFLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username, car) values (1, now(), false, 'OFFLINE',
'driver01pw', 'driver01', null);

insert into driver (id, date_created, deleted, online_status, password, username, car) values (2, now(), false, 'OFFLINE',
'driver02pw', 'driver02', null);

insert into driver (id, date_created, deleted, online_status, password, username, car) values (3, now(), false, 'OFFLINE',
'driver03pw', 'driver03', null);


-- Create 3 ONLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username, car) values (4, now(), false, 'ONLINE',
'driver04pw', 'driver04', null);

insert into driver (id, date_created, deleted, online_status, password, username, car) values (5, now(), false, 'ONLINE',
'driver05pw', 'driver05', null);

insert into driver (id, date_created, deleted, online_status, password, username, car) values (6, now(), false, 'ONLINE',
'driver06pw', 'driver06', null);

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username, car)
values
 (7,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'OFFLINE',
'driver07pw', 'driver07', null);

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username, car)
values
 (8,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'ONLINE',
'driver08pw', 'driver08', null);


 insert into manufacturer(id, date_created, name) VALUES(11,  now(), 'Lada');

insert into car(id, convertible, date_created, engine_type, license_plate, rating, seat_count, manufacturer, driver)
values(9, false, now(),  'BENZIN', 'PLAKA', 1.0, 4, 11, null);

insert into car(id, convertible, date_created, engine_type, license_plate, rating, seat_count, manufacturer, driver)
values(10, false, now(),  'DIZEL', 'PLAKA1', 1.0, 4, 11, null);
