DROP SCHEMA IF EXISTS admission;
CREATE SCHEMA admission;

CREATE TABLE admission.user (
  id INTEGER NOT NULL,
  name character varying (256) NOT NULL,
  user_type character varying(10) NOT NULL,
  CONSTRAINT pk_user PRIMARY KEY (id)
);
