DROP TABLE IF EXISTS restaurants;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE restaurants (
  id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name    VARCHAR NOT NULL,
  address VARCHAR NOT NULL,
  popularity INTEGER
);