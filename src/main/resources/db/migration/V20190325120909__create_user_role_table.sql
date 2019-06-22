CREATE TABLE user_roles
(
  id      SERIAL      NOT NULL
    CONSTRAINT user_roles_pkey PRIMARY KEY,
  user_id INTEGER     NOT NULL REFERENCES users (id),
  role    VARCHAR(50) NOT NULL
);