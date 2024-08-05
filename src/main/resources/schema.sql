-- DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    user_name text NOT NULL UNIQUE,
    name text NOT NULL,
    password text
);
