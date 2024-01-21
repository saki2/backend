--admin
--pass: admin123
insert into USERS(address, blocked, email, first_name, last_name, password, phone_number, picture, role) values ('Bulevar oslobodjenja 12 Novi Sad', false, 'admin@gmail.com', 'Admin', 'Admin','$2a$10$SNwqKTpejKgVD9kMunSvYuVPgF/rs9oq5TEkWMwRUb75hb2CVMUa6', '0664587545', 'picture', 'ADMIN')
insert into ADMINS(id) values (1)

--guests
--pass: marko123
insert into USERS(address, blocked, email, first_name, last_name, password, phone_number, picture, role) values ( 'Bulevar oslobodjenja 127, Novi Sad', false, 'marko.markovic@gmail.com', 'Marko', 'Markovic', '$2a$10$pW7xhKHkW/n6UAr1zlrK8OG9lZgio/Ix/K3dltB512sCDRL8h9ZI2', '06652685', 'picture', 'GUEST')
insert into GUEST(id) values (2)
insert into GUEST_FAVORITE_ACCOMMODATIONS(guest_id, favorite_accommodations) values (2,1)
insert into GUEST_FAVORITE_ACCOMMODATIONS(guest_id, favorite_accommodations) values (2,2)

--pass: petar123
insert into USERS(address, blocked, email, first_name, last_name, password, phone_number, picture, role) values ( 'Bulevar oslobodjenja 2, Novi Sad', false, 'petar.petrovic@gmail.com', 'Petar', 'Petrovic', '$2a$10$cQnYiXJL0XHGoX27SCRLjOhoHRzKWlzkNP5sEXjStrFdtjAntCHH6', '066874521', 'picture', 'GUEST')
insert into GUEST(id) values (3)


--hosts
--pass: andrea123
insert into USERS(address, blocked, email, first_name, last_name, password, phone_number, picture, role) values ( 'Bulevar oslobodjenja 78, Novi Sad', false, 'andrea.katzenberger@gmail.com', 'Andrea', 'Katzenberger', '$2a$10$PelwlV29qdhWztr21ChMBeYYQUkWMuJhJhYqLKBKQiTa7he8qaIN6', '0641254658', 'picture', 'HOST')
insert into HOST(id) values (4)

--pass: mirko123
insert into USERS(address, blocked, email, first_name, last_name, password, phone_number, picture, role) values ( 'Bulevar oslobodjenja 7, Novi Sad', false, 'mirko.mirkovic@gmail.com', 'Mirko', 'Mirkovic', '$2a$10$bm0/QkZmhQEQVI8LwFV9QeescIjZjdJSHre/xK2lJw020xCHJxV0m', '0645879458', 'picture', 'HOST')
insert into HOST(id) values (5)
--

--accommodations
-- INSERT INTO accommodation (name, description, location, type, wifi, kitchen, air_conditioner, parking, availability, payment, price, booking_method, min_guest, max_guest, status, host_id) VALUES ('Apartman Slavica 1', 'Spacious and comfortable apartment located in a quiet part of the city.', 'Bulevar Kralja Petra 1, Novi Sad', 'ROOM', true, true, true, true, null, 'PerAccommodation', 100, 'NON-AUTOMATIC', 2, 'PENDING', 4);
--
-- INSERT INTO accommodation (name, description, location, type, wifi, kitchen, air_conditioner, parking, availability, payment, price, booking_method, min_guest, max_guest, status, host_id) VALUES ('Apartman Slavica 2', 'Spacious and comfortable apartment located in a quiet part of the city.', 'Bulevar Kralja Petra 1, Novi Sad', 'ROOM', true, true, true, true, ARRAY['2023-12-15':: date], 'PerAccommodation', 100, 'NON-AUTOMATIC', 2,2 'PENDING', 4);
--
-- INSERT INTO accommodation (name, description, location, type, wifi, kitchen, air_conditioner, parking, availability, payment, price, booking_method, min_guest, max_guest, status, host_id) VALUES ('Apartman Slavica 3', 'Spacious and comfortable apartment located in a quiet part of the city.', 'Bulevar Kralja Petra 1, Beograd', 'ROOM', true, true, true, true, ARRAY['2023-12-16':: date], 'PerAccommodation', 100, 'NON-AUTOMATIC', 2,5, 'ACCEPTED', 4);
--
-- INSERT INTO accommodation (name, description, location, type, wifi, kitchen, air_conditioner, parking, availability, payment, price, booking_method, min_guest, max_guest, status, host_id) VALUES ('Apartman Slavica 4', 'Spacious and comfortable apartment located in a quiet part of the city.', 'Bulevar Kralja Petra 1, Beograd', 'ROOM', true, true, true, true, ARRAY['2023-12-16':: date], 'PerAccommodation', 100, 'NON-AUTOMATIC', 2,3, 'ACCEPTED', 4);
--
-- INSERT INTO accommodation (name, description, location, type, wifi, kitchen, air_conditioner, parking, availability, payment, price, booking_method, min_guest, max_guest, status, host_id) VALUES ('Studio 11', 'Spacious and comfortable studio located in a quiet part of the city.', 'Jevrejska 14, Novi Sad',                     'STUDIO', true, true, true, true, ARRAY['2023-12-16':: date], 'PerPerson', 80, 'AUTOMATIC', 2,5, 'PENDING', 5);
--
-- INSERT INTO accommodation (name, description, location, type, wifi, kitchen, air_conditioner, parking, availability, payment, price, booking_method, min_guest, max_guest, status, host_id) VALUES ('Studio 22', 'Spacious and comfortable studio located in a quiet part of the city.', 'Jevrejska 15, Novi Sad',                      'STUDIO', true, true, true, true, ARRAY['2023-12-16':: date], 'PerPerson', 80, 'AUTOMATIC', 2,6, 'ACCEPTED', 5);
