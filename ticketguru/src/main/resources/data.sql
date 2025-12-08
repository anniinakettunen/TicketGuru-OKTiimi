-- =========================================
-- Drop tables if they exist (fresh start)
-- =========================================
-- DROP TABLE IF EXISTS ticket CASCADE;
-- DROP TABLE IF EXISTS ticket_sale CASCADE;
-- DROP TABLE IF EXISTS ticket_type CASCADE;
-- DROP TABLE IF EXISTS event CASCADE;
-- DROP TABLE IF EXISTS app_user CASCADE;
-- DROP TABLE IF EXISTS role CASCADE;

-- =========================================
-- Roles table
-- =========================================
-- CREATE TABLE role (
--     role_id SERIAL PRIMARY KEY,
--     role_name VARCHAR(50) UNIQUE NOT NULL,
--     notes TEXT
-- );

-- =========================================
-- Users table
-- =========================================
-- CREATE TABLE app_user (
--     id SERIAL PRIMARY KEY,
--     username VARCHAR(50) UNIQUE NOT NULL,
--     password_hash VARCHAR(255) NOT NULL,
--     firstname VARCHAR(50) NOT NULL,
--     lastname VARCHAR(50) NOT NULL,
--     email VARCHAR(100) NOT NULL,
--     phone VARCHAR(20),
--     role_id INT NOT NULL REFERENCES role(role_id)
-- );

-- =========================================
-- Events table
-- =========================================
-- CREATE TABLE event (
--     event_id SERIAL PRIMARY KEY,
--     event_name VARCHAR(100) NOT NULL,
--     event_location VARCHAR(100) NOT NULL,
--     event_city VARCHAR(50) NOT NULL,
--     event_date DATE NOT NULL,
--     event_description TEXT,
--     max_number_of_tickets INT NOT NULL,
--     CONSTRAINT unique_event UNIQUE(event_name, event_date)
-- );

-- =========================================
-- Ticket types table
-- =========================================
-- CREATE TABLE ticket_type (
--     ticket_type_id SERIAL PRIMARY KEY,
--     ticket_name VARCHAR(50) UNIQUE NOT NULL,
--     price NUMERIC(10,2) NOT NULL
-- );

-- =========================================
-- Ticket sales table
-- =========================================
-- CREATE TABLE ticket_sale (
--     sale_id SERIAL PRIMARY KEY,
--     user_id INT NOT NULL REFERENCES app_user(id),
--     date_time TIMESTAMP NOT NULL,
--     price NUMERIC(10,2) NOT NULL
-- );

-- =========================================
-- Tickets table
-- =========================================
-- CREATE TABLE ticket (
--     ticket_id SERIAL PRIMARY KEY,
--     ticket_code INT UNIQUE NOT NULL,
--     ticket_type_id INT NOT NULL REFERENCES ticket_type(ticket_type_id),
--     event_id INT NOT NULL REFERENCES event(event_id),
--     sale_id INT REFERENCES ticket_sale(sale_id),
--     used BOOLEAN DEFAULT FALSE
-- );

-- =========================================
-- Insert initial roles
-- =========================================
-- =========================================
-- Insert roles
-- =========================================
INSERT INTO role (role_name) VALUES 
('MYYJÄ'),
('ADMIN')
ON CONFLICT (role_name) DO NOTHING;

-- =========================================
-- Insert users
-- =========================================
INSERT INTO app_user (username, password_hash, firstname, lastname, email, phone, role_id)
VALUES 
('oskari','$2a$06$A4cFHZ0VdAfln850o4HbqOOMFG6qekLyT/Z4RafkW25qYgFjyhyVm','Oskari','Uninen','oskari.uninen@gmail.com','12345678',(SELECT role_id FROM role WHERE role_name='MYYJÄ')),
('jaska','$2a$06$53ukiDugdY0RoSVT/Uoc2u3hCzIIXtiwXTa4mw5iKZt0jGpGig5oG','Jaska','Jokunen','jaska.jokunen@hotmail.com','87654321',(SELECT role_id FROM role WHERE role_name='ADMIN'))
ON CONFLICT (username) DO NOTHING;

-- =========================================
-- Insert events
-- =========================================
INSERT INTO event (event_name, event_location, event_city, event_date, event_description, max_number_of_tickets)
VALUES 
('Rock Night', 'Arena', 'Helsinki', '2026-10-10', 'Live rock music', 500),
('Jazz Sunday', 'Jazz Club', 'Tampere', '2026-11-02', 'Smooth jazz evening', 150),
('Tech Expo', 'Messukeskus', 'Espoo', '2026-12-20', 'Technology and innovation fair', 1000),
('Rap Festial', 'Myyrmanni', 'Vantaa', '2026-09-15', 'Rapping show in the lounge', 750),
('Pop Explosion', 'Hartwall Arena', 'Helsinki', '2026-08-20', 'Top pop artists performing live', 1200),
('Classical Evening', 'Finnish National Opera', 'Helsinki', '2026-09-05', 'Orchestra and solo performances', 300),
('EDM Beats', 'Ratinan Stadion', 'Tampere', '2026-07-15', 'Electronic music festival with DJs', 2000),
('Shakespeare in Action', 'Espoo City Theatre', 'Espoo', '2026-11-18', 'Classic Shakespeare play performed live', 250),
('Disney on Ice', 'Veikkaus Arena', 'Helsinki', '2026-12-05', 'Magical Disney on Ice show for all ages', 3000)
ON CONFLICT (event_name, event_date) DO NOTHING;

-- =========================================
-- Insert ticket types
-- =========================================
INSERT INTO ticket_type (ticket_name, price)
VALUES 
('Adult', 30.0),
('Child', 15.0),
('Senior', 20.0),
('Student', 25.0)
ON CONFLICT (ticket_name) DO NOTHING;

-- =========================================
-- Insert ticket sales
-- =========================================
INSERT INTO ticket_sale (user_id, date_time, price)
VALUES 
((SELECT id FROM app_user WHERE username='oskari'), NOW(), 60.0),  -- Sale 1
((SELECT id FROM app_user WHERE username='jaska'), NOW(), 45.0),   -- Sale 2
((SELECT id FROM app_user WHERE username='oskari'), NOW(), 90.0),  -- Sale 3
((SELECT id FROM app_user WHERE username='jaska'), NOW(), 35.0)    -- Sale 4
ON CONFLICT DO NOTHING;

-- =========================================
-- Insert tickets
-- =========================================
INSERT INTO ticket (ticket_code, ticket_type_id, event_id, sale_id, used)
VALUES 
-- Sale 1 (oskari)
(1001, (SELECT ticket_type_id FROM ticket_type WHERE ticket_name='Adult'),
       (SELECT event_id FROM event WHERE event_name='Rock Night'),
       (SELECT sale_id FROM ticket_sale WHERE user_id=(SELECT id FROM app_user WHERE username='oskari') ORDER BY date_time DESC LIMIT 4 OFFSET 3),
       false),
(1002, (SELECT ticket_type_id FROM ticket_type WHERE ticket_name='Adult'),
       (SELECT event_id FROM event WHERE event_name='Rock Night'),
       (SELECT sale_id FROM ticket_sale WHERE user_id=(SELECT id FROM app_user WHERE username='oskari') ORDER BY date_time DESC LIMIT 4 OFFSET 3),
       false),

-- Sale 2 (jaska)
(2001, (SELECT ticket_type_id FROM ticket_type WHERE ticket_name='Adult'),
       (SELECT event_id FROM event WHERE event_name='Jazz Sunday'),
       (SELECT sale_id FROM ticket_sale WHERE user_id=(SELECT id FROM app_user WHERE username='jaska') ORDER BY date_time DESC LIMIT 4 OFFSET 2),
       false),
(2002, (SELECT ticket_type_id FROM ticket_type WHERE ticket_name='Child'),
       (SELECT event_id FROM event WHERE event_name='Jazz Sunday'),
       (SELECT sale_id FROM ticket_sale WHERE user_id=(SELECT id FROM app_user WHERE username='jaska') ORDER BY date_time DESC LIMIT 4 OFFSET 2),
       false),

-- Sale 3 (oskari)
(1003, (SELECT ticket_type_id FROM ticket_type WHERE ticket_name='Adult'),
       (SELECT event_id FROM event WHERE event_name='Tech Expo'),
       (SELECT sale_id FROM ticket_sale WHERE user_id=(SELECT id FROM app_user WHERE username='oskari') ORDER BY date_time DESC LIMIT 4 OFFSET 1),
       false),
(1004, (SELECT ticket_type_id FROM ticket_type WHERE ticket_name='Adult'),
       (SELECT event_id FROM event WHERE event_name='Tech Expo'),
       (SELECT sale_id FROM ticket_sale WHERE user_id=(SELECT id FROM app_user WHERE username='oskari') ORDER BY date_time DESC LIMIT 4 OFFSET 1),
       false),
(1005, (SELECT ticket_type_id FROM ticket_type WHERE ticket_name='Adult'),
       (SELECT event_id FROM event WHERE event_name='Tech Expo'),
       (SELECT sale_id FROM ticket_sale WHERE user_id=(SELECT id FROM app_user WHERE username='oskari') ORDER BY date_time DESC LIMIT 4 OFFSET 1),
       false),

-- Sale 4 (jaska)
(2003, (SELECT ticket_type_id FROM ticket_type WHERE ticket_name='Adult'),
       (SELECT event_id FROM event WHERE event_name='Rap Festial'),
       (SELECT sale_id FROM ticket_sale WHERE user_id=(SELECT id FROM app_user WHERE username='jaska') ORDER BY date_time DESC LIMIT 4 OFFSET 0),
       false),
(2004, (SELECT ticket_type_id FROM ticket_type WHERE ticket_name='Student'),
       (SELECT event_id FROM event WHERE event_name='Rap Festial'),
       (SELECT sale_id FROM ticket_sale WHERE user_id=(SELECT id FROM app_user WHERE username='jaska') ORDER BY date_time DESC LIMIT 4 OFFSET 0),
       false)
ON CONFLICT (ticket_code) DO NOTHING;
