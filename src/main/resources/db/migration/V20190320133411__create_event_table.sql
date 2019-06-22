CREATE TABLE event
(
  id              SERIAL       NOT NULL
    CONSTRAINT event_pkey PRIMARY KEY,
  type            VARCHAR(100) NOT NULL,
  title           VARCHAR(100) NOT NULL,
  time_start      TIMESTAMP    NOT NULL,
  time_limit      TIME,
  content         VARCHAR(10000)
);