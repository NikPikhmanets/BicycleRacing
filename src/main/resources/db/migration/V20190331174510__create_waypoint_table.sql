CREATE TABLE waypoint
(
  id        SERIAL NOT NULL
    CONSTRAINT waypoint_pkey PRIMARY KEY,
  latitude  DOUBLE PRECISION,
  longitude DOUBLE PRECISION,
  date      TIMESTAMP,
  track_id  INTEGER REFERENCES track (id)
);