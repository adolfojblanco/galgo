INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_BUSINESS');
INSERT INTO roles (name) VALUES ('ROLE_DRIVER');
INSERT INTO roles (name) VALUES ('ROLE_USER');

INSERT INTO restaurant_types (enabled, name) VALUES (1, 'Arabe');
INSERT INTO restaurant_types (enabled, name) VALUES (1, 'Americana');
INSERT INTO restaurant_types (enabled, name) VALUES (1, 'Mexicana');

INSERT INTO categories (enabled, category_name) VALUES (1, "Hamburguesas");
INSERT INTO categories (enabled, category_name) VALUES (1, "Perritos");

INSERT INTO users (enabled, username, email, first_name, last_name, password) VALUES (1, "adolfob", "ajblanco156@gmail.com", "Adolfo", "Blanco", "$2a$12$YSHspdDG55.a9E5fJUyYdum2Ldu2xNnYYvO/r68sGKxWJ603cwrKi");
INSERT INTO users (enabled, username, email, first_name, last_name, password) VALUES (1, "alexblanco", "ajblanco@gmail.com", "Adolfo", "Blanco", "$2a$12$YSHspdDG55.a9E5fJUyYdum2Ldu2xNnYYvO/r68sGKxWJ603cwrKi");

INSERT INTO users_roles (role_id, user_id) VALUES (1, 1);
INSERT INTO users_roles (role_id, user_id) VALUES (2, 2);

INSERT INTO addresses (enabled, address_name, area, building_number, city, country, door_number, floor_number, latitude, longitude, postal_code, street) VALUES (1, "Principal", "Arteixo", "1", "Arteixo", "España", "1", "0", "1", "0", "15100", "Travesia Meicende");

INSERT INTO restaurants (enabled, address_id, restaurant_type_id, user_id, restaurant_name, local_phone, mobile_phone) VALUES (1, 1, 1, 2, "Villano", "981765567", "876987567");




