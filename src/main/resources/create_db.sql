
CREATE SEQUENCE app.id_ticket_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 1000000
  START 1
  CACHE 1;
ALTER TABLE app.id_ticket_seq
  OWNER TO postgres;

-- -------------------------------

CREATE TABLE app.tickets
(
  id integer NOT NULL,
  user_id integer,
  film_name character varying(256),
  category character varying(128),
  film_start_date timestamp with time zone,
  place_number integer,
  status character varying(128),
  CONSTRAINT ticket_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE app.tickets
  OWNER TO postgres;

-- ------------------------------

INSERT INTO app.tickets (id, user_id, film_name, category, film_start_date, place_number, status)
    VALUES (nextval('app.id_ticket_seq'), null, 'Terminator', 'STANDARD', CURRENT_TIMESTAMP, 1, 'FREE');
INSERT INTO app.tickets (id, user_id, film_name, category, film_start_date, place_number, status)
    VALUES (nextval('app.id_ticket_seq'), null, 'Terminator', 'STANDARD', CURRENT_TIMESTAMP, 2, 'FREE');
INSERT INTO app.tickets (id, user_id, film_name, category, film_start_date, place_number, status)
    VALUES (nextval('app.id_ticket_seq'), null, 'Terminator', 'STANDARD', CURRENT_TIMESTAMP, 3, 'FREE');
INSERT INTO app.tickets (id, user_id, film_name, category, film_start_date, place_number, status)
    VALUES (nextval('app.id_ticket_seq'), null, 'Terminator', 'STANDARD', CURRENT_TIMESTAMP, 4, 'FREE');
INSERT INTO app.tickets (id, user_id, film_name, category, film_start_date, place_number, status)
    VALUES (nextval('app.id_ticket_seq'), null, 'Terminator', 'STANDARD', CURRENT_TIMESTAMP, 5, 'FREE');
INSERT INTO app.tickets (id, user_id, film_name, category, film_start_date, place_number, status)
    VALUES (nextval('app.id_ticket_seq'), null, 'Terminator', 'STANDARD', CURRENT_TIMESTAMP, 6, 'FREE');
INSERT INTO app.tickets (id, user_id, film_name, category, film_start_date, place_number, status)
    VALUES (nextval('app.id_ticket_seq'), null, 'Terminator', 'STANDARD', CURRENT_TIMESTAMP, 7, 'FREE');
INSERT INTO app.tickets (id, user_id, film_name, category, film_start_date, place_number, status)
    VALUES (nextval('app.id_ticket_seq'), null, 'Terminator', 'STANDARD', CURRENT_TIMESTAMP, 8, 'FREE');
INSERT INTO app.tickets (id, user_id, film_name, category, film_start_date, place_number, status)
    VALUES (nextval('app.id_ticket_seq'), null, 'Terminator', 'STANDARD', CURRENT_TIMESTAMP, 9, 'FREE');
INSERT INTO app.tickets (id, user_id, film_name, category, film_start_date, place_number, status)
    VALUES (nextval('app.id_ticket_seq'), null, 'Terminator', 'STANDARD', CURRENT_TIMESTAMP, 10, 'FREE');

INSERT INTO app.tickets (id, user_id, film_name, category, film_start_date, place_number, status)
    VALUES (nextval('app.id_ticket_seq'), null, 'Independence Day', 'STANDARD', CURRENT_TIMESTAMP, 1, 'FREE');
INSERT INTO app.tickets (id, user_id, film_name, category, film_start_date, place_number, status)
    VALUES (nextval('app.id_ticket_seq'), null, 'Independence Day', 'STANDARD', CURRENT_TIMESTAMP, 2, 'FREE');
INSERT INTO app.tickets (id, user_id, film_name, category, film_start_date, place_number, status)
    VALUES (nextval('app.id_ticket_seq'), null, 'Independence Day', 'STANDARD', CURRENT_TIMESTAMP, 3, 'FREE');
INSERT INTO app.tickets (id, user_id, film_name, category, film_start_date, place_number, status)
    VALUES (nextval('app.id_ticket_seq'), null, 'Independence Day', 'STANDARD', CURRENT_TIMESTAMP, 4, 'FREE');
INSERT INTO app.tickets (id, user_id, film_name, category, film_start_date, place_number, status)
    VALUES (nextval('app.id_ticket_seq'), null, 'Independence Day', 'STANDARD', CURRENT_TIMESTAMP, 5, 'FREE');
INSERT INTO app.tickets (id, user_id, film_name, category, film_start_date, place_number, status)
    VALUES (nextval('app.id_ticket_seq'), null, 'Independence Day', 'STANDARD', CURRENT_TIMESTAMP, 6, 'FREE');
INSERT INTO app.tickets (id, user_id, film_name, category, film_start_date, place_number, status)
    VALUES (nextval('app.id_ticket_seq'), null, 'Independence Day', 'STANDARD', CURRENT_TIMESTAMP, 7, 'FREE');
INSERT INTO app.tickets (id, user_id, film_name, category, film_start_date, place_number, status)
    VALUES (nextval('app.id_ticket_seq'), null, 'Independence Day', 'STANDARD', CURRENT_TIMESTAMP, 8, 'FREE');
INSERT INTO app.tickets (id, user_id, film_name, category, film_start_date, place_number, status)
    VALUES (nextval('app.id_ticket_seq'), null, 'Independence Day', 'STANDARD', CURRENT_TIMESTAMP, 9, 'FREE');
INSERT INTO app.tickets (id, user_id, film_name, category, film_start_date, place_number, status)
    VALUES (nextval('app.id_ticket_seq'), null, 'Independence Day', 'STANDARD', CURRENT_TIMESTAMP, 10, 'FREE');