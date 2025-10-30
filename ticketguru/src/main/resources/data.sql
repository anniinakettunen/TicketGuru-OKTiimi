-- Roles
INSERT INTO role (role_name)
VALUES ('MYYJÄ')
ON CONFLICT (role_name) DO NOTHING;

INSERT INTO role (role_name)
VALUES ('ADMIN')
ON CONFLICT (role_name) DO NOTHING;

-- Users
INSERT INTO app_user (username, password_hash, firstname, lastname, email, phone, role_id)
VALUES ('oskari', '$2a$06$A4cFHZ0VdAfln850o4HbqOOMFG6qekLyT/Z4RafkW25qYgFjyhyVm', 'Oskari', 'Uninen', 'oskari.uninen@gmail.com', '12345678',
        (SELECT id FROM role WHERE role_name='MYYJÄ'))
ON CONFLICT (username) DO NOTHING;

INSERT INTO app_user (username, password_hash, firstname, lastname, email, phone, role_id)
VALUES ('jaska', '$2a$06$53ukiDugdY0RoSVT/Uoc2u3hCzIIXtiwXTa4mw5iKZt0jGpGig5oG', 'Jaska', 'Jokunen', 'jaska.jokunen@hotmail.com', '87654321',
        (SELECT id FROM role WHERE role_name='ADMIN'))
ON CONFLICT (username) DO NOTHING;

-- Events
INSERT INTO event (name, location, city, event_date, description, capacity)
VALUES ('Rock Night', 'Arena', 'Helsinki', '2026-10-10', 'Live rock music', 500)
ON CONFLICT (name, event_date) DO NOTHING;

INSERT INTO event (name, location, city, event_date, description, capacity)
VALUES ('Jazz Sunday', 'Jazz Club', 'Tampere', '2026-11-02', 'Smooth jazz evening', 150)
ON CONFLICT (name, event_date) DO NOTHING;

INSERT INTO event (name, location, city, event_date, description, capacity)
VALUES ('Tech Expo', 'Messukeskus', 'Espoo', '2026-12-20', 'Technology and innovation fair', 1000)
ON CONFLICT (name, event_date) DO NOTHING;

-- Ticket types
INSERT INTO ticket_type (name, price)
VALUES ('Adult', 30.0)
ON CONFLICT (name) DO NOTHING;

INSERT INTO ticket_type (name, price)
VALUES ('Child', 15.0)
ON CONFLICT (name) DO NOTHING;

INSERT INTO ticket_type (name, price)
VALUES ('Senior', 20.0)
ON CONFLICT (name) DO NOTHING;

-- Ticket sales
INSERT INTO ticket_sale (user_id, date_time, price)
VALUES ((SELECT id FROM app_user WHERE username='oskari'), NOW(), 60.0)
ON CONFLICT DO NOTHING;

INSERT INTO ticket_sale (user_id, date_time, price)
VALUES ((SELECT id FROM app_user WHERE username='jaska'), NOW(), 45.0)
ON CONFLICT DO NOTHING;

-- Tickets
INSERT INTO ticket (ticket_code, ticket_type_id, event_id, ticket_sale_id)
VALUES (1001,
        (SELECT id FROM ticket_type WHERE name='Adult'),
        (SELECT id FROM event WHERE name='Rock Night' AND event_date='2026-10-10'),
        (SELECT id FROM ticket_sale WHERE user_id=(SELECT id FROM app_user WHERE username='oskari') ORDER BY date_time DESC LIMIT 1))
ON CONFLICT (ticket_code) DO NOTHING;

INSERT INTO ticket (ticket_code, ticket_type_id, event_id, ticket_sale_id)
VALUES (1002,
        (SELECT id FROM ticket_type WHERE name='Adult'),
        (SELECT id FROM event WHERE name='Rock Night' AND event_date='2026-10-10'),
        (SELECT id FROM ticket_sale WHERE user_id=(SELECT id FROM app_user WHERE username='oskari') ORDER BY date_time DESC LIMIT 1))
ON CONFLICT (ticket_code) DO NOTHING;

INSERT INTO ticket (ticket_code, ticket_type_id, event_id, ticket_sale_id)
VALUES (2001,
        (SELECT id FROM ticket_type WHERE name='Adult'),
        (SELECT id FROM event WHERE name='Jazz Sunday' AND event_date='2026-11-02'),
        (SELECT id FROM ticket_sale WHERE user_id=(SELECT id FROM app_user WHERE username='jaska') ORDER BY date_time DESC LIMIT 1))
ON CONFLICT (ticket_code) DO NOTHING;

INSERT INTO ticket (ticket_code, ticket_type_id, event_id, ticket_sale_id)
VALUES (2002,
        (SELECT id FROM ticket_type WHERE name='Child'),
        (SELECT id FROM event WHERE name='Jazz Sunday' AND event_date='2026-11-02'),
        (SELECT id FROM ticket_sale WHERE user_id=(SELECT id FROM app_user WHERE username='jaska') ORDER BY date_time DESC LIMIT 1))
ON CONFLICT (ticket_code) DO NOTHING;
