
INSERT INTO roles (role_name) 
VALUES 
('HR'), 
('ADMIN'), 
('EMPLOYEE');

INSERT INTO users (email, user_name, password, first_name, last_name, department, tech_stack, profile_photo_url, active, wallet_balance, last_login) 
VALUES 
('john.doe@example.com', 'shreyas', 'hashedpassword1', 'John', 'Doe', 'Engineering', 'Java, Spring Boot', 'https://example.com/photo1.jpg', TRUE, 500, CURRENT_TIMESTAMP),
('jane.doe@example.com', 'janedoe', 'hashedpassword2', 'Jane', 'Doe', 'Marketing', 'SEO, Digital Marketing', 'https://example.com/photo2.jpg', TRUE, 300, CURRENT_TIMESTAMP),
('alice.smith@example.com', 'alicesmith', 'hashedpassword3', 'Alice', 'Smith', 'Sales', 'CRM, Sales Strategy', 'https://example.com/photo3.jpg', FALSE, 0, CURRENT_TIMESTAMP);


INSERT INTO modules (module_name) 
VALUES 
('Training'),
('Employee Recognition'),
('Feedback System'),
('Task Management');


INSERT INTO user_roles (user_id, role_id) 
VALUES 
((select id from users where user_name='shreyas'), (select id from roles where role_name='ADMIN')), -- John Doe assigned as ADMIN
((select id from users where user_name='janedoe'), (select id from roles where role_name='HR')), -- Jane Doe assigned as HR
((select id from users where user_name='alicesmith'), (select id from roles where role_name='EMPLOYEE')); -- Alice Smith assigned as EMPLOYEE

INSERT INTO wallet_transaction (sender_id, receiver_id, amount, module_id, type, description, date, specific_id) 
VALUES 
((select id from users where user_name='shreyas'), (select id from users where user_name='janedoe'), 100, (select id from modules where module_name='Training'), 'CREDIT', 'Training completion reward', CURRENT_TIMESTAMP, NULL),
((select id from users where user_name='janedoe'), (select id from users where user_name='shreyas'), 50, (select id from modules where module_name='Training'), 'DEBIT', 'Employee recognition reward', CURRENT_TIMESTAMP, NULL),
((select id from users where user_name='alicesmith'), (select id from users where user_name='shreyas'), 150, (select id from modules where module_name='Training'), 'CREDIT', 'Task completion reward', CURRENT_TIMESTAMP, NULL);

