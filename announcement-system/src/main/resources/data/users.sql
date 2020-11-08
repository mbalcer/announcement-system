INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

-- password is encode by BCrypt
-- password for mateusz: mati123
-- password for admin: admin
INSERT INTO users(username, email, password, enable, role_id) VALUES
('mateusz', 'mati@wp.pl', '$2a$10$Ya/QJWvpliz0VusAm1bqpuPaV93ba/1P1vRfiM0VUh4azSR88REC6', true, 1),
('admin', 'admin@admin.pl', '$2a$10$JfKYMW2Q4Cm.F5IIp9i3duSavHEYOWj28I6E4QVHEzNzX6Hf1NDcm', true , 2);
