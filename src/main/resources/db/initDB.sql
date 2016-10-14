DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS restaurants CASCADE;
DROP TABLE IF EXISTS lunches CASCADE;
DROP TABLE IF EXISTS dishes CASCADE;
DROP TABLE IF EXISTS votes CASCADE;
DROP TABLE IF EXISTS user_roles CASCADE;
DROP TABLE IF EXISTS lunch_dish CASCADE;
DROP SEQUENCE IF EXISTS restaurant_seq CASCADE;
DROP SEQUENCE IF EXISTS user_seq CASCADE;
DROP SEQUENCE IF EXISTS lunch_seq CASCADE;
DROP SEQUENCE IF EXISTS dish_seq CASCADE;
DROP SEQUENCE IF EXISTS vote_seq CASCADE;

CREATE SEQUENCE restaurant_seq START 100;
CREATE SEQUENCE user_seq START 100;
CREATE SEQUENCE lunch_seq START 100;
CREATE SEQUENCE dish_seq START 100;
CREATE SEQUENCE vote_seq START 100;

CREATE TABLE restaurants (
  id      INTEGER PRIMARY KEY DEFAULT nextval('restaurant_seq'),
  name    VARCHAR NOT NULL,
  address VARCHAR NOT NULL,
  phone   VARCHAR DEFAULT NULL
);

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('user_seq'),
  name       VARCHAR NOT NULL,
  email      VARCHAR NOT NULL,
  password   VARCHAR NOT NULL,
  registered TIMESTAMP DEFAULT now()
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE lunches
(
  id              INTEGER PRIMARY KEY DEFAULT nextval('lunch_seq'),
  name            VARCHAR NOT NULL,
  price           DECIMAL NOT NULL,
  restaurant_id   INTEGER NOT NULL,
  dateTime        TIMESTAMP DEFAULT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE dishes
(
  id              INTEGER PRIMARY KEY DEFAULT nextval('dish_seq'),
  name            VARCHAR NOT NULL,
  price           DECIMAL NOT NULL,
  restaurant_id   INTEGER NOT NULL ,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);

CREATE TABLE votes
(
  id              INTEGER PRIMARY KEY DEFAULT nextval('vote_seq'),
  vote_time            TIMESTAMP DEFAULT now(),
  user_id         INTEGER NOT NULL,
  restaurant_id        INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id)
);
CREATE UNIQUE INDEX vote_date ON votes (vote_time);

CREATE TABLE lunch_dish(
  lunch_id INTEGER NOT NULL,
  dish_id INTEGER NOT NULL,
  CONSTRAINT lunch_dish_idx UNIQUE (lunch_id, dish_id),
  FOREIGN KEY (lunch_id) REFERENCES lunches (id),
  FOREIGN KEY (dish_id) REFERENCES dishes (id)
);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


