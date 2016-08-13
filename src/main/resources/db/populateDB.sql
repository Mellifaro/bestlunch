DELETE FROM restaurants;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO restaurants(name, address, popularity)
    VALUES ('Versal', 'Franka, 27', 1200),
           ('Marsel', 'Shevchenka, 35', 1500),
           ('Lion',   'Soborna, 41', 1218),
           ('Velur',  'Boguna, 54', 900);
