CREATE TABLE events
(
    id              SERIAL    NOT NULL
        CONSTRAINT event_pkey PRIMARY KEY,
    type            VARCHAR   NOT NULL,
    title           VARCHAR   NOT NULL,
    simple_track_id INT,
    time_start      TIMESTAMP NOT NULL,
    time_limit      TIME,
    distance        INT,
    content         VARCHAR,
    created_at      TIMESTAMP,
    edited_at       TIMESTAMP
);