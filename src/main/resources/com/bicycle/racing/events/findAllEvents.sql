SELECT e.id,
       e.type,
       e.title,
       e.simple_track_id,
       e.time_limit,
       e.time_start,
       e.distance,
       e.content,
       e.created_at,
       e.edited_at
FROM events AS e;