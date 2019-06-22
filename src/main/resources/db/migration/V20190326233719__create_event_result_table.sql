CREATE TABLE event_result
(
  id       SERIAL NOT NULL
    CONSTRAINT event_result_pkey PRIMARY KEY,
  event_id INTEGER REFERENCES event (id),
  username VARCHAR(100) REFERENCES users (username),
  time     TIME,
  status   boolean
);