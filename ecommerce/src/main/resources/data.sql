INSERT INTO INVENTORY (item_name, price) VALUES ('Microsoft Surface', 10.00);
INSERT INTO INVENTORY (item_name, price) VALUES ('Macbook Pro', 10.20);
INSERT INTO INVENTORY (item_name, price) VALUES ('Macbook Air', 10.30);

INSERT INTO SELLER (email, password, first_name, last_name, avatar, birth_date, gender, vendor_type, username)
    VALUES (
        'test@email.com',
        '$2a$10$r7HHOhgNeJ0WRMjMTX37AOWBRyuLFrvx2GY/mD4P19ck2oi8KUrjK',
        'Yanto',
        'Yeager',
        'abcd',
        2000-06-10,
        'L',
        1,
        'test@email.com'
    );