CREATE TABLE event_registr
(
  id       SERIAL  NOT NULL
    CONSTRAINT event_registr_pkey PRIMARY KEY,
  event_id INTEGER NOT NULL REFERENCES event (id),
  username VARCHAR(100) REFERENCES users (username)
);