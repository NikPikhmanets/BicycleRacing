CREATE TABLE sample_waypoint
(
  id        SERIAL NOT NULL
    CONSTRAINT sample_waypoint_pkey PRIMARY KEY,
  latitude  DOUBLE PRECISION,
  longitude DOUBLE PRECISION,
  date      TIMESTAMP,
  track_id  INTEGER REFERENCES track_sample (id)
);