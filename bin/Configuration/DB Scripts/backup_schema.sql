--
-- PostgreSQL database dump
--

-- Dumped from database version 10.3
-- Dumped by pg_dump version 10.3

-- Started on 2018-04-10 21:51:01

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2941 (class 1262 OID 16393)
-- Name: CELTAS; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "CELTAS" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';


ALTER DATABASE "CELTAS" OWNER TO postgres;

\connect "CELTAS"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2942 (class 0 OID 0)
-- Dependencies: 2941
-- Name: DATABASE "CELTAS"; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE "CELTAS" IS 'Database 1 for Celtas Project';


--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2944 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 16400)
-- Name: blood_types; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.blood_types (
    id_blood_type integer DEFAULT nextval(('public.blood_types_id_blood_type_seq'::text)::regclass) NOT NULL,
    description character varying(10) NOT NULL
);


ALTER TABLE public.blood_types OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16561)
-- Name: blood_types_id_blood_type_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.blood_types_id_blood_type_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.blood_types_id_blood_type_seq OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16424)
-- Name: education_level; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.education_level (
    id_education_level integer DEFAULT nextval(('public.education_level_id_education_level_seq'::text)::regclass) NOT NULL,
    description character varying(255) NOT NULL
);


ALTER TABLE public.education_level OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16610)
-- Name: education_level_id_education_level_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.education_level_id_education_level_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.education_level_id_education_level_seq OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 16418)
-- Name: health_care_org; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.health_care_org (
    id_health_care_org integer DEFAULT nextval(('public.health_care_org_id_health_care_org_seq'::text)::regclass) NOT NULL,
    description character varying(255) NOT NULL
);


ALTER TABLE public.health_care_org OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16592)
-- Name: health_care_org_id_health_care_org_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.health_care_org_id_health_care_org_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.health_care_org_id_health_care_org_seq OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16412)
-- Name: identification_types; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.identification_types (
    id_identification_type integer DEFAULT nextval(('public.tipo_documento_id_identification_type_seq'::text)::regclass) NOT NULL,
    description character varying(255)
);


ALTER TABLE public.identification_types OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16514)
-- Name: localities; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.localities (
    id_locality integer DEFAULT nextval(('public.localities_id_locality_seq'::text)::regclass) NOT NULL,
    description character varying(255)
);


ALTER TABLE public.localities OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 16681)
-- Name: localities_id_locality_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.localities_id_locality_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.localities_id_locality_seq OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16688)
-- Name: parent_types; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.parent_types (
    id_parent_type integer NOT NULL,
    description character varying(255) NOT NULL
);


ALTER TABLE public.parent_types OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16686)
-- Name: parent_types_id_parent_type_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.parent_types_id_parent_type_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.parent_types_id_parent_type_seq OWNER TO postgres;

--
-- TOC entry 2945 (class 0 OID 0)
-- Dependencies: 214
-- Name: parent_types_id_parent_type_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.parent_types_id_parent_type_seq OWNED BY public.parent_types.id_parent_type;


--
-- TOC entry 210 (class 1259 OID 16636)
-- Name: person_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person_details (
    id_person integer,
    height integer,
    weight integer,
    id_blood_type integer,
    id_health_care_org integer,
    id_education_level integer,
    school_name character varying(500),
    id_locality integer,
    neighborhood character varying(500),
    streetadress1 integer,
    streetaddress2 integer,
    phone1 character varying(50),
    phone2 character varying(50)
);


ALTER TABLE public.person_details OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16694)
-- Name: person_relatives; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person_relatives (
    id_relative integer NOT NULL,
    id_person integer NOT NULL,
    id_parent_type integer NOT NULL
);


ALTER TABLE public.person_relatives OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16771)
-- Name: person_train_groups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person_train_groups (
    id_person integer NOT NULL,
    id_group integer NOT NULL
);


ALTER TABLE public.person_train_groups OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16665)
-- Name: person_train_groups_id_person_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.person_train_groups_id_person_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.person_train_groups_id_person_seq OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 16394)
-- Name: persons; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.persons (
    id_person integer NOT NULL,
    name character varying(255),
    surname character varying(255),
    id_identification_type integer,
    identification_number integer,
    birth_date date,
    join_date date,
    id_role integer
);


ALTER TABLE public.persons OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16548)
-- Name: persons_id_person_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.persons_id_person_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.persons_id_person_seq OWNER TO postgres;

--
-- TOC entry 2946 (class 0 OID 0)
-- Dependencies: 204
-- Name: persons_id_person_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.persons_id_person_seq OWNED BY public.persons.id_person;


--
-- TOC entry 202 (class 1259 OID 16460)
-- Name: relatives; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.relatives (
    id_relative integer DEFAULT nextval(('public.relatives_id_relative_seq'::text)::regclass) NOT NULL,
    fullname character varying(200) NOT NULL,
    id_identification_type integer,
    identification_number integer,
    streetaddress1 character varying(500),
    phone1 character varying(50),
    cellphone character varying(50),
    ocupation text
);


ALTER TABLE public.relatives OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16644)
-- Name: relatives_id_relative_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.relatives_id_relative_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.relatives_id_relative_seq OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 16448)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id_role integer DEFAULT nextval(('public.role_id_role_seq'::text)::regclass) NOT NULL,
    description character varying(255) NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16626)
-- Name: role_id_role_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.role_id_role_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.role_id_role_seq OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16574)
-- Name: tipo_documento_id_identification_type_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_documento_id_identification_type_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.tipo_documento_id_identification_type_seq OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16706)
-- Name: train_group_hours; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.train_group_hours (
    id_group integer NOT NULL,
    day integer NOT NULL,
    h0 boolean NOT NULL,
    h1 integer NOT NULL,
    h2 boolean NOT NULL,
    h3 boolean NOT NULL,
    h4 boolean NOT NULL,
    h5 boolean NOT NULL,
    h6 boolean NOT NULL,
    h7 boolean NOT NULL,
    h8 boolean NOT NULL,
    h9 boolean NOT NULL,
    h10 boolean NOT NULL,
    h11 boolean NOT NULL,
    h12 boolean NOT NULL,
    h13 boolean NOT NULL,
    h14 boolean NOT NULL,
    h15 boolean NOT NULL,
    h16 boolean NOT NULL,
    h17 boolean NOT NULL,
    h18 boolean NOT NULL,
    h19 boolean NOT NULL,
    h20 boolean NOT NULL,
    h21 boolean NOT NULL,
    h22 boolean NOT NULL,
    h23 boolean NOT NULL
);


ALTER TABLE public.train_group_hours OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16699)
-- Name: train_groups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.train_groups (
    id_group integer NOT NULL,
    description character varying(255) NOT NULL
);


ALTER TABLE public.train_groups OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16697)
-- Name: train_groups_id_group_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.train_groups_id_group_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.train_groups_id_group_seq OWNER TO postgres;

--
-- TOC entry 2947 (class 0 OID 0)
-- Dependencies: 217
-- Name: train_groups_id_group_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.train_groups_id_group_seq OWNED BY public.train_groups.id_group;


--
-- TOC entry 2753 (class 2604 OID 16691)
-- Name: parent_types id_parent_type; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.parent_types ALTER COLUMN id_parent_type SET DEFAULT nextval('public.parent_types_id_parent_type_seq'::regclass);


--
-- TOC entry 2745 (class 2604 OID 16550)
-- Name: persons id_person; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persons ALTER COLUMN id_person SET DEFAULT nextval('public.persons_id_person_seq'::regclass);


--
-- TOC entry 2754 (class 2604 OID 16702)
-- Name: train_groups id_group; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.train_groups ALTER COLUMN id_group SET DEFAULT nextval('public.train_groups_id_group_seq'::regclass);


--
-- TOC entry 2912 (class 0 OID 16400)
-- Dependencies: 197
-- Data for Name: blood_types; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.blood_types (id_blood_type, description) FROM stdin;
1	O-
\.


--
-- TOC entry 2915 (class 0 OID 16424)
-- Dependencies: 200
-- Data for Name: education_level; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.education_level (id_education_level, description) FROM stdin;
\.


--
-- TOC entry 2914 (class 0 OID 16418)
-- Dependencies: 199
-- Data for Name: health_care_org; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.health_care_org (id_health_care_org, description) FROM stdin;
\.


--
-- TOC entry 2913 (class 0 OID 16412)
-- Dependencies: 198
-- Data for Name: identification_types; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.identification_types (id_identification_type, description) FROM stdin;
\.


--
-- TOC entry 2918 (class 0 OID 16514)
-- Dependencies: 203
-- Data for Name: localities; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.localities (id_locality, description) FROM stdin;
\.


--
-- TOC entry 2930 (class 0 OID 16688)
-- Dependencies: 215
-- Data for Name: parent_types; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.parent_types (id_parent_type, description) FROM stdin;
\.


--
-- TOC entry 2925 (class 0 OID 16636)
-- Dependencies: 210
-- Data for Name: person_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.person_details (id_person, height, weight, id_blood_type, id_health_care_org, id_education_level, school_name, id_locality, neighborhood, streetadress1, streetaddress2, phone1, phone2) FROM stdin;
\.


--
-- TOC entry 2931 (class 0 OID 16694)
-- Dependencies: 216
-- Data for Name: person_relatives; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.person_relatives (id_relative, id_person, id_parent_type) FROM stdin;
\.


--
-- TOC entry 2935 (class 0 OID 16771)
-- Dependencies: 220
-- Data for Name: person_train_groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.person_train_groups (id_person, id_group) FROM stdin;
\.


--
-- TOC entry 2911 (class 0 OID 16394)
-- Dependencies: 196
-- Data for Name: persons; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.persons (id_person, name, surname, id_identification_type, identification_number, birth_date, join_date, id_role) FROM stdin;
\.


--
-- TOC entry 2917 (class 0 OID 16460)
-- Dependencies: 202
-- Data for Name: relatives; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.relatives (id_relative, fullname, id_identification_type, identification_number, streetaddress1, phone1, cellphone, ocupation) FROM stdin;
\.


--
-- TOC entry 2916 (class 0 OID 16448)
-- Dependencies: 201
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role (id_role, description) FROM stdin;
\.


--
-- TOC entry 2934 (class 0 OID 16706)
-- Dependencies: 219
-- Data for Name: train_group_hours; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.train_group_hours (id_group, day, h0, h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, h11, h12, h13, h14, h15, h16, h17, h18, h19, h20, h21, h22, h23) FROM stdin;
\.


--
-- TOC entry 2933 (class 0 OID 16699)
-- Dependencies: 218
-- Data for Name: train_groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.train_groups (id_group, description) FROM stdin;
\.


--
-- TOC entry 2948 (class 0 OID 0)
-- Dependencies: 205
-- Name: blood_types_id_blood_type_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.blood_types_id_blood_type_seq', 2, true);


--
-- TOC entry 2949 (class 0 OID 0)
-- Dependencies: 208
-- Name: education_level_id_education_level_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.education_level_id_education_level_seq', 1, false);


--
-- TOC entry 2950 (class 0 OID 0)
-- Dependencies: 207
-- Name: health_care_org_id_health_care_org_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.health_care_org_id_health_care_org_seq', 1, false);


--
-- TOC entry 2951 (class 0 OID 0)
-- Dependencies: 213
-- Name: localities_id_locality_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.localities_id_locality_seq', 1, false);


--
-- TOC entry 2952 (class 0 OID 0)
-- Dependencies: 214
-- Name: parent_types_id_parent_type_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.parent_types_id_parent_type_seq', 1, false);


--
-- TOC entry 2953 (class 0 OID 0)
-- Dependencies: 212
-- Name: person_train_groups_id_person_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.person_train_groups_id_person_seq', 1, false);


--
-- TOC entry 2954 (class 0 OID 0)
-- Dependencies: 204
-- Name: persons_id_person_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.persons_id_person_seq', 1, false);


--
-- TOC entry 2955 (class 0 OID 0)
-- Dependencies: 211
-- Name: relatives_id_relative_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.relatives_id_relative_seq', 1, false);


--
-- TOC entry 2956 (class 0 OID 0)
-- Dependencies: 209
-- Name: role_id_role_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.role_id_role_seq', 1, false);


--
-- TOC entry 2957 (class 0 OID 0)
-- Dependencies: 206
-- Name: tipo_documento_id_identification_type_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_documento_id_identification_type_seq', 1, false);


--
-- TOC entry 2958 (class 0 OID 0)
-- Dependencies: 217
-- Name: train_groups_id_group_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.train_groups_id_group_seq', 1, false);


--
-- TOC entry 2764 (class 2606 OID 16613)
-- Name: education_level education_level_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.education_level
    ADD CONSTRAINT education_level_pkey PRIMARY KEY (id_education_level);


--
-- TOC entry 2758 (class 2606 OID 16564)
-- Name: blood_types grupo_sanguineo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.blood_types
    ADD CONSTRAINT grupo_sanguineo_pkey PRIMARY KEY (id_blood_type);


--
-- TOC entry 2762 (class 2606 OID 16595)
-- Name: health_care_org health_care_org_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.health_care_org
    ADD CONSTRAINT health_care_org_pkey PRIMARY KEY (id_health_care_org);


--
-- TOC entry 2770 (class 2606 OID 16684)
-- Name: localities localidades_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.localities
    ADD CONSTRAINT localidades_pkey PRIMARY KEY (id_locality);


--
-- TOC entry 2772 (class 2606 OID 16693)
-- Name: parent_types parent_types_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.parent_types
    ADD CONSTRAINT parent_types_pkey PRIMARY KEY (id_parent_type);


--
-- TOC entry 2756 (class 2606 OID 16552)
-- Name: persons persons_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persons
    ADD CONSTRAINT persons_pkey PRIMARY KEY (id_person);


--
-- TOC entry 2768 (class 2606 OID 16647)
-- Name: relatives relatives_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relatives
    ADD CONSTRAINT relatives_pkey PRIMARY KEY (id_relative);


--
-- TOC entry 2766 (class 2606 OID 16629)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id_role);


--
-- TOC entry 2760 (class 2606 OID 16577)
-- Name: identification_types tipo_documento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.identification_types
    ADD CONSTRAINT tipo_documento_pkey PRIMARY KEY (id_identification_type);


--
-- TOC entry 2776 (class 2606 OID 16710)
-- Name: train_group_hours train_group_hours_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.train_group_hours
    ADD CONSTRAINT train_group_hours_pkey PRIMARY KEY (id_group);


--
-- TOC entry 2774 (class 2606 OID 16704)
-- Name: train_groups train_groups_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.train_groups
    ADD CONSTRAINT train_groups_pkey PRIMARY KEY (id_group);


--
-- TOC entry 2780 (class 2606 OID 16721)
-- Name: person_details fk_id_blood_type; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_details
    ADD CONSTRAINT fk_id_blood_type FOREIGN KEY (id_blood_type) REFERENCES public.blood_types(id_blood_type) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2782 (class 2606 OID 16731)
-- Name: person_details fk_id_education_level; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_details
    ADD CONSTRAINT fk_id_education_level FOREIGN KEY (id_education_level) REFERENCES public.education_level(id_education_level) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2789 (class 2606 OID 16779)
-- Name: person_train_groups fk_id_group; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_train_groups
    ADD CONSTRAINT fk_id_group FOREIGN KEY (id_group) REFERENCES public.train_groups(id_group) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2781 (class 2606 OID 16726)
-- Name: person_details fk_id_health_care_org; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_details
    ADD CONSTRAINT fk_id_health_care_org FOREIGN KEY (id_health_care_org) REFERENCES public.health_care_org(id_health_care_org) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2777 (class 2606 OID 16711)
-- Name: persons fk_id_identification_type; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persons
    ADD CONSTRAINT fk_id_identification_type FOREIGN KEY (id_identification_type) REFERENCES public.identification_types(id_identification_type) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2779 (class 2606 OID 16746)
-- Name: relatives fk_id_identification_type; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relatives
    ADD CONSTRAINT fk_id_identification_type FOREIGN KEY (id_identification_type) REFERENCES public.identification_types(id_identification_type) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2783 (class 2606 OID 16736)
-- Name: person_details fk_id_locality; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_details
    ADD CONSTRAINT fk_id_locality FOREIGN KEY (id_locality) REFERENCES public.localities(id_locality) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2787 (class 2606 OID 16761)
-- Name: person_relatives fk_id_parent_type; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_relatives
    ADD CONSTRAINT fk_id_parent_type FOREIGN KEY (id_parent_type) REFERENCES public.parent_types(id_parent_type) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2784 (class 2606 OID 16741)
-- Name: person_details fk_id_person; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_details
    ADD CONSTRAINT fk_id_person FOREIGN KEY (id_person) REFERENCES public.persons(id_person) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2786 (class 2606 OID 16756)
-- Name: person_relatives fk_id_person; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_relatives
    ADD CONSTRAINT fk_id_person FOREIGN KEY (id_person) REFERENCES public.persons(id_person) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2788 (class 2606 OID 16774)
-- Name: person_train_groups fk_id_person; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_train_groups
    ADD CONSTRAINT fk_id_person FOREIGN KEY (id_person) REFERENCES public.persons(id_person) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2785 (class 2606 OID 16751)
-- Name: person_relatives fk_id_relative; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_relatives
    ADD CONSTRAINT fk_id_relative FOREIGN KEY (id_relative) REFERENCES public.relatives(id_relative) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2778 (class 2606 OID 16716)
-- Name: persons fk_id_role; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persons
    ADD CONSTRAINT fk_id_role FOREIGN KEY (id_role) REFERENCES public.role(id_role) ON UPDATE RESTRICT ON DELETE RESTRICT;


-- Completed on 2018-04-10 21:51:03

--
-- PostgreSQL database dump complete
--

