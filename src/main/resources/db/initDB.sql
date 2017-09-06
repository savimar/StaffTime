DROP TABLE IF EXISTS status;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 1;

CREATE TABLE status
(
  id         BIGSERIAL PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR NOT NULL,
  time      TIMESTAMP NOT NULL
);