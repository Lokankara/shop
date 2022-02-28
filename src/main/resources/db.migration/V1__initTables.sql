CREATE TABLE products (
    id serial PRIMARY KEY,
    name VARCHAR (50) NOT NULL,
    description VARCHAR (150) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    created TIMESTAMP NOT NULL
);

CREATE TABLE users (
    user_id serial PRIMARY KEY,
    username VARCHAR (50) NOT NULL,
    password VARCHAR (50) NOT NULL
);

