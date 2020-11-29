INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

-- password is encode by BCrypt
-- mateusz: mati123
-- admin: admin
INSERT INTO users(username, email, password, enable, role_id, first_name, last_name, address, phone_number) VALUES
('mateusz', 'mateusz75319@gmail.com', '$2a$10$Ya/QJWvpliz0VusAm1bqpuPaV93ba/1P1vRfiM0VUh4azSR88REC6', true, 1, 'Mateusz', 'Kowalski', 'Bydgoszcz', '+48111555666'),
('admin', 'admin@admin.pl', '$2a$10$JfKYMW2Q4Cm.F5IIp9i3duSavHEYOWj28I6E4QVHEzNzX6Hf1NDcm', true , 2, 'Admin', null, null, '+48333222111');
