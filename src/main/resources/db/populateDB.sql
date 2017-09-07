DELETE FROM status;

ALTER SEQUENCE global_seq RESTART WITH 1;

INSERT INTO status (time, name ) VALUES
  ('2017-09-08 10:00:00', 'На работе'),
  ('2017-09-08 13:00:00', 'Отсутсвует'),
  ('2017-09-08 14:00:00', 'На работе'),
  ('2017-09-08 15:00:00', 'Отсутсвует'),
  ('2017-09-08 15:30:00', 'На работе');
