DELETE FROM status;

ALTER SEQUENCE global_seq RESTART WITH 1;

INSERT INTO status (time, name ) VALUES
  ('2017-08-30 10:00:00', 'На работе'),
  ('2017-08-30 13:00:00', 'Отсутсвует'),
  ('2015-08-30 14:00:00', 'На работе'),
  ('2015-08-30 15:00:00', 'Отсутсвует'),
  ('2015-08-30 15:30:00', 'На работе');
