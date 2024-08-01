DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    user_name text NOT NULL UNIQUE,
    name text NOT NULL,
    password text
);
