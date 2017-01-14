DELETE FROM votes;
DELETE FROM lunch_dish;
DELETE FROM dishes;
DELETE FROM lunches;
DELETE FROM users;
DELETE FROM restaurants;
DELETE FROM user_roles;


ALTER SEQUENCE restaurant_seq RESTART WITH 100;
ALTER SEQUENCE user_seq RESTART WITH 100;
ALTER SEQUENCE lunch_seq RESTART WITH 100;
ALTER SEQUENCE dish_seq RESTART WITH 100;
ALTER SEQUENCE vote_seq RESTART WITH 100;

INSERT INTO restaurants(name, address, phone)
    VALUES ('Versal', 'Franka, 27', '+380635254123'),
           ('Marsel', 'Shevchenka, 35', NULL ),
           ('Lion',   'Soborna, 41', '+380635254000'),
           ('Velur',  'Boguna, 54', '+380635254101');

INSERT INTO users (name, email, password)
    VALUES ('Admin', 'admin@gmail.com', 'admin'),
           ('User',  'user@gmail.com',  'user');

INSERT INTO user_roles (role, user_id)
    VALUES ('USER', 101),
           ('ADMIN', 100);

INSERT INTO lunches (name, price, restaurant_id, datetime)
      VALUES ('Breakfast1', 15.25, 100, '2016-12-23 10:00:00'),
             ('Breakfast2', 18.35, 101, '2016-12-23 11:00:00'),
             ('Dinner1', 9.25, 102, '2016-12-23 12:30:00'),
             ('Dinner2', 10.00, 103, '2016-12-23 13:00:00');

INSERT INTO dishes (name, price, restaurant_id)
      VALUES  ('Eggs', 15.25, 100),
              ('Tea', 23.13, 100),
              ('Soup', 9.25, 101),
              ('Bread', 5.35, 101),
              ('Cake', 9.25, 102),
              ('Ice-cream', 3.50, 102),
              ('Porridge', 11.25, 103),
              ('Pasta', 10.00, 103);

INSERT INTO lunch_dish (lunch_id, dish_id)
      VALUES  (100, 100),
              (100, 101),
              (101, 102),
              (101, 103),
              (102, 104),
              (102, 105),
              (103, 106),
              (103, 107);

INSERT INTO votes (user_id, restaurant_id)
      VALUES  (100, 101),
              (101, 103);


