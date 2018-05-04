CREATE TABLE blood_types (
  id_blood_type  SERIAL NOT NULL PRIMARY KEY,
  description    varchar(10) NOT NULL UNIQUE
);

CREATE TABLE education_level (
    id_education_level SERIAL NOT NULL PRIMARY KEY,
    description character varying(255) NOT NULL UNIQUE
);

CREATE TABLE health_care_org (
    id_health_care_org SERIAL NOT NULL PRIMARY KEY,
    description character varying(255) NOT NULL UNIQUE
);

CREATE TABLE identification_types (
    id_identification_type SERIAL NOT NULL PRIMARY KEY,
    description character varying(255) UNIQUE
);

CREATE TABLE localities (
    id_locality SERIAL NOT NULL PRIMARY KEY,
    description character varying(255) UNIQUE
);

CREATE TABLE roles (
    id_role SERIAL NOT NULL PRIMARY KEY,
    description character varying(255) NOT NULL UNIQUE
);

CREATE TABLE parent_types (
    id_parent_type SERIAL NOT NULL PRIMARY KEY,
    description character varying(255) NOT NULL UNIQUE
);

CREATE TABLE persons (
    id_person SERIAL NOT NULL PRIMARY KEY,
    name character varying(255) NOT NULL,
    surname character varying(255) NOT NULL,
    id_identification_type integer NOT NULL REFERENCES IDENTIFICATION_TYPES,
    identification_number NOT NULL bigint,
    birth_date NOT NULL date,
    join_date NOT NULL date,
    id_role integer NOT NULL REFERENCES roles
);


CREATE TABLE person_details (
    id_person integer NOT NULL REFERENCES PERSONS UNIQUE,
    height NOT NULL integer,
    weight NOT NULL nteger,
    id_blood_type integer NOT NULL REFERENCES blood_types,
    id_health_care_org integer NOT NULL REFERENCES health_care_org,
    id_education_level integer NOT NULL REFERENCES education_level,
    school_name character varying(500),
    id_locality integer NOT NULL REFERENCES localities,
    neighborhood character varying(500),
    streetadress1 NOT NULL integer,
    streetaddress2 integer,
    phone1 NOT NULL character varying(50),
    phone2 character varying(50)
);

--ALTER TABLE person_details ADD CONSTRAINT pk_persons PRIMARY KEY (id_person);

--ALTER TABLE person_details DELETE CONSTRAINT uk_persons;


--drop table person_details;

--drop table persons;

CREATE TABLE relatives (
    id_relative SERIAL NOT NULL PRIMARY KEY,
    fullname character varying(200) NOT NULL,
    id_identification_type integer NOT NULL REFERENCES identification_types,
    identification_number bigint NOT NULL UNIQUE,
    streetaddress1 character varying(500) NOT NULL,
    phone1 character varying(50),
    cellphone character varying(50) NOT NULL,
    ocupation text
);



CREATE TABLE person_relatives (
    id_relative integer NOT NULL REFERENCES relatives,
    id_person integer NOT NULL REFERENCES persons,
    id_parent_type integer NOT NULL REFERENCES parent_types,
	CONSTRAINT r_p_pt_pk
    PRIMARY KEY (id_relative,id_person,id_parent_type)
);

---------------------------------------------------------------- 
--INSERTS
----------------------------------------------------------------

INSERT INTO roles (role_description) VALUES ('Administrator');
INSERT INTO roles (role_description) VALUES ('Colaborador');
INSERT INTO roles (role_description) VALUES ('Entrenador');
INSERT INTO roles (role_description) VALUES ('Estudiante');
INSERT INTO roles (role_description) VALUES ('Personal administrativo');


CREATE TABLE train_groups (
    id_group SERIAL NOT NULL PRIMARY KEY,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE train_group_hours (
    id_group integer NOT NULL REFERENCES train_groups,
    day integer NOT NULL,
    h0 boolean NOT NULL DEFAULT FALSE,
    h1 boolean NOT NULL DEFAULT FALSE,
    h2 boolean NOT NULL DEFAULT FALSE,
    h3 boolean NOT NULL DEFAULT FALSE,
    h4 boolean NOT NULL DEFAULT FALSE,
    h5 boolean NOT NULL DEFAULT FALSE,
    h6 boolean NOT NULL DEFAULT FALSE,
    h7 boolean NOT NULL DEFAULT FALSE,
    h8 boolean NOT NULL DEFAULT FALSE,
    h9 boolean NOT NULL DEFAULT FALSE,
    h10 boolean NOT NULL DEFAULT FALSE,
    h11 boolean NOT NULL DEFAULT FALSE,
    h12 boolean NOT NULL DEFAULT FALSE,
    h13 boolean NOT NULL DEFAULT FALSE,
    h14 boolean NOT NULL DEFAULT FALSE,
    h15 boolean NOT NULL DEFAULT FALSE,
    h16 boolean NOT NULL DEFAULT FALSE,
    h17 boolean NOT NULL DEFAULT FALSE,
    h18 boolean NOT NULL DEFAULT FALSE,
    h19 boolean NOT NULL DEFAULT FALSE,
    h20 boolean NOT NULL DEFAULT FALSE,
    h21 boolean NOT NULL DEFAULT FALSE,
    h22 boolean NOT NULL DEFAULT FALSE,
    h23 boolean NOT NULL DEFAULT FALSE,
    CONSTRAINT t_g_h_pk PRIMARY KEY (id_group, day)
);

CREATE TABLE person_train_groups (
    id_person integer NOT NULL REFERENCES persons (id_person),
    id_group integer NOT NULL REFERENCES train_groups (id_group),
    CONSTRAINT p_t_g_pk PRIMARY KEY (id_person, id_group)
);