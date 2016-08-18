DELETE FROM users;
DELETE FROM restaurants;
DELETE FROM lunches;
DELETE FROM dishes;
DELETE FROM votes;
DELETE FROM user_roles;
DELETE FROM lunch_dish;

ALTER SEQUENCE restaurant_seq RESTART WITH 100;
ALTER SEQUENCE user_seq RESTART WITH 100;
ALTER SEQUENCE lunch_seq RESTART WITH 100;
ALTER SEQUENCE dish_seq RESTART WITH 100;
ALTER SEQUENCE vote_seq RESTART WITH 100;

INSERT INTO restaurants(name, address)
    VALUES ('Versal', 'Franka, 27'),
           ('Marsel', 'Shevchenka, 35'),
           ('Lion',   'Soborna, 41'),
           ('Velur',  'Boguna, 54');

INSERT INTO users (name, email, password)
    VALUES ('Admin', 'admin@gmail.com', 'admin'),
           ('User',  'user@gmail.com',  'user');

INSERT INTO user_roles (role, user_id)
    VALUES ('ROLE_USER', 100),
           ('ROLE_ADMIN', 101);

INSERT INTO lunches (name, price, restaurant_id)
      VALUES ('Breakfast1', 15.25, 100),
             ('Breakfast2', 18.35, 100),
             ('Dinner1', 9.25, 100),
             ('Dinner2', 10.00, 101);

INSERT INTO dishes (name, price)
      VALUES  ('Eggs', 15.25),
              ('Tea', 23.13),
              ('Soup', 9.25),
              ('Bread', 5.35),
              ('Cake', 9.25),
              ('Ice-cream', 3.50),
              ('Porridge', 11.25),
              ('Pasta', 10.00);

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
      VALUES  (101, 101);

