DROP TABLE dict;
DROP TABLE permission;
DROP TABLE person;
DROP TABLE product;
DROP TABLE resource;
DROP TABLE role;
DROP TABLE sequence;
DROP TABLE user_role;
DROP TABLE users;

CREATE TABLE dict(id BIGINT NOT NULL, code CHARACTER VARYING(30) NOT NULL, defunct_ind BOOLEAN, name CHARACTER VARYING(30) NOT NULL, parent_code CHARACTER VARYING(30), value CHARACTER VARYING(30), PRIMARY KEY (id))

CREATE TABLE permission ( id BIGINT NOT NULL, roleid BIGINT, permission CHARACTER VARYING(255), PRIMARY KEY (id) );

CREATE TABLE person ( id BIGINT NOT NULL, address CHARACTER VARYING(100), birthday TIMESTAMP(6) WITHOUT TIME ZONE, created_by CHARACTER VARYING(30), created_datetime TIMESTAMP(6) WITHOUT TIME ZONE, defunct_ind BOOLEAN, email CHARACTER VARYING(50), name CHARACTER VARYING(50) NOT NULL, nationality CHARACTER VARYING(50), phone CHARACTER VARYING(20), remarks CHARACTER VARYING(500), sex CHARACTER VARYING(10), updated_by CHARACTER VARYING(30), updated_datetime TIMESTAMP(6) WITHOUT TIME ZONE, vip SMALLINT, PRIMARY KEY (id) );

CREATE TABLE product ( id BIGINT NOT NULL, available BOOLEAN, category CHARACTER VARYING(255), code CHARACTER VARYING(255), created_by CHARACTER VARYING(30), created_datetime TIMESTAMP(6) WITHOUT TIME ZONE, defunct_ind BOOLEAN, describe CHARACTER VARYING(1000), name CHARACTER VARYING(255), price DOUBLE PRECISION, production_date TIMESTAMP(6) WITHOUT TIME ZONE, updated_by CHARACTER VARYING(30), updated_datetime TIMESTAMP(6) WITHOUT TIME ZONE, PRIMARY KEY (id) );

CREATE TABLE resource ( id BIGINT NOT NULL, is_leaf BOOLEAN, is_menu BOOLEAN, key_name CHARACTER VARYING(255) NOT NULL, level INTEGER, name CHARACTER VARYING(40), number CHARACTER VARYING(255), parent_id BIGINT, url CHARACTER VARYING(100), PRIMARY KEY (id) );

CREATE TABLE role ( id BIGINT NOT NULL, name CHARACTER VARYING(255), PRIMARY KEY (id) );

CREATE TABLE sequence ( seq_name CHARACTER VARYING(50) NOT NULL, seq_count NUMERIC(38,0), PRIMARY KEY (seq_name) );

CREATE TABLE user_role ( user_id BIGINT NOT NULL, role_id BIGINT NOT NULL, PRIMARY KEY (user_id, role_id) );

CREATE TABLE users ( id BIGINT NOT NULL, email CHARACTER VARYING(255), loginname CHARACTER VARYING(255), name CHARACTER VARYING(255), password CHARACTER VARYING(255), PRIMARY KEY (id) );
ALTER TABLE user_role ADD CONSTRAINT fk_user_role_role_id FOREIGN KEY (role_id) REFERENCES public.role (id);
ALTER TABLE user_role ADD CONSTRAINT fk_user_role_user_id FOREIGN KEY (user_id) REFERENCES public.users (id);