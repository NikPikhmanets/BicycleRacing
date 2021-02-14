CREATE TABLE users
(
    id           SERIAL  NOT NULL
        CONSTRAINT user_pkey PRIMARY KEY,
    username     VARCHAR NOT NULL,
    first_name   VARCHAR,
    last_name    VARCHAR,
    password     VARCHAR NOT NULL,
    avatar       VARCHAR,
    email        VARCHAR,
    phone_number VARCHAR,
    enabled      BOOLEAN,
    birthday     TIMESTAMP,
    created_at   TIMESTAMP
)