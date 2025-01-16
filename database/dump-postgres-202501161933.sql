--
-- PostgreSQL database cluster dump
--

-- Started on 2025-01-16 19:33:38

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE pedevelop;
ALTER ROLE pedevelop WITH SUPERUSER INHERIT NOCREATEROLE NOCREATEDB LOGIN NOREPLICATION NOBYPASSRLS;
CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS;

--
-- User Configurations
--








--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

-- Started on 2025-01-16 19:33:39

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

-- Completed on 2025-01-16 19:33:39

--
-- PostgreSQL database dump complete
--

--
-- Database "company_db" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

-- Started on 2025-01-16 19:33:39

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4840 (class 1262 OID 16388)
-- Name: company_db; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE company_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';


ALTER DATABASE company_db OWNER TO postgres;

\connect company_db

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4841 (class 0 OID 0)
-- Dependencies: 4840
-- Name: DATABASE company_db; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON DATABASE company_db TO pedevelop;


-- Completed on 2025-01-16 19:33:39

--
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

\connect postgres

--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

-- Started on 2025-01-16 19:33:39

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2 (class 3079 OID 16389)
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- TOC entry 4896 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 219 (class 1259 OID 16401)
-- Name: tb_employee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_employee (
    empid integer NOT NULL,
    userid character varying(255),
    empname character varying(255),
    email character varying(255),
    password character varying(255)
);


ALTER TABLE public.tb_employee OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16400)
-- Name: tb_employee_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_employee_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tb_employee_id_seq OWNER TO postgres;

--
-- TOC entry 4908 (class 0 OID 0)
-- Dependencies: 218
-- Name: tb_employee_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_employee_id_seq OWNED BY public.tb_employee.empid;


--
-- TOC entry 221 (class 1259 OID 16410)
-- Name: tb_project; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_project (
    id integer NOT NULL,
    projectname character varying(255) NOT NULL,
    startdate date,
    enddate date,
    status character varying(50)
);


ALTER TABLE public.tb_project OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16409)
-- Name: tb_project_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_project_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tb_project_id_seq OWNER TO postgres;

--
-- TOC entry 4911 (class 0 OID 0)
-- Dependencies: 220
-- Name: tb_project_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_project_id_seq OWNED BY public.tb_project.id;


--
-- TOC entry 223 (class 1259 OID 16417)
-- Name: tb_task; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_task (
    id integer NOT NULL,
    taskname character varying(255) NOT NULL,
    taskempid integer NOT NULL,
    projectid integer NOT NULL,
    startdate date,
    enddate date,
    actualstartdate date,
    actualenddate date,
    progress integer,
    esttime numeric(10,2) NOT NULL,
    note text
);


ALTER TABLE public.tb_task OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16416)
-- Name: tb_task_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_task_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tb_task_id_seq OWNER TO postgres;

--
-- TOC entry 4914 (class 0 OID 0)
-- Dependencies: 222
-- Name: tb_task_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_task_id_seq OWNED BY public.tb_task.id;


--
-- TOC entry 225 (class 1259 OID 16436)
-- Name: tb_workingtime; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_workingtime (
    id integer NOT NULL,
    taskid integer NOT NULL,
    wktdate date NOT NULL,
    wkttime numeric(10,2)
);


ALTER TABLE public.tb_workingtime OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16435)
-- Name: tb_workingtime_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_workingtime_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tb_workingtime_id_seq OWNER TO postgres;

--
-- TOC entry 4917 (class 0 OID 0)
-- Dependencies: 224
-- Name: tb_workingtime_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_workingtime_id_seq OWNED BY public.tb_workingtime.id;


--
-- TOC entry 4723 (class 2604 OID 16404)
-- Name: tb_employee empid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_employee ALTER COLUMN empid SET DEFAULT nextval('public.tb_employee_id_seq'::regclass);


--
-- TOC entry 4724 (class 2604 OID 16413)
-- Name: tb_project id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_project ALTER COLUMN id SET DEFAULT nextval('public.tb_project_id_seq'::regclass);


--
-- TOC entry 4725 (class 2604 OID 16420)
-- Name: tb_task id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_task ALTER COLUMN id SET DEFAULT nextval('public.tb_task_id_seq'::regclass);


--
-- TOC entry 4726 (class 2604 OID 16439)
-- Name: tb_workingtime id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_workingtime ALTER COLUMN id SET DEFAULT nextval('public.tb_workingtime_id_seq'::regclass);


--
-- TOC entry 4884 (class 0 OID 16401)
-- Dependencies: 219
-- Data for Name: tb_employee; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_employee (empid, userid, empname, email, password) FROM stdin;
5	hong	hong nguyen2	hong@mail.com	12345
1	cuong	cuong bui	cuong@gmail.com	123
4	phung	phung nguyen	phung@gmail.com	123
6	tommy	tommy nguyen-2	tommy2@gmail.com	1234
\.


--
-- TOC entry 4886 (class 0 OID 16410)
-- Dependencies: 221
-- Data for Name: tb_project; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_project (id, projectname, startdate, enddate, status) FROM stdin;
1	projectA	2025-01-01	2025-01-02	\N
2	project B	2025-02-01	2025-02-20	\N
3	project C	2025-01-10	2025-01-20	\N
4	project D	2025-01-10	2025-01-25	\N
5	projject E	2025-01-10	2025-01-15	\N
6	projject F	2025-01-10	2025-01-30	\N
7	projject G	2025-01-10	2025-01-20	\N
\.


--
-- TOC entry 4888 (class 0 OID 16417)
-- Dependencies: 223
-- Data for Name: tb_task; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_task (id, taskname, taskempid, projectid, startdate, enddate, actualstartdate, actualenddate, progress, esttime, note) FROM stdin;
4	Task04	1	2	2025-02-01	2025-02-05	\N	\N	0	10.00	abc
1	task01	1	1	2025-01-01	2025-01-10	\N	\N	0	8.00	abc
2	task02	1	1	2025-01-01	2025-01-10	\N	\N	0	20.00	note1
3	task03	1	2	2025-01-09	2025-01-10	\N	\N	0	20.00	
5	task05	1	1	\N	\N	\N	\N	0	10.00	
6	task_projectB_t01	4	2	2025-01-10	2025-01-15	\N	\N	0	20.00	high task
7	task07	1	4	2025-01-10	2025-01-16	\N	\N	0	20.00	higt risk
8	task08	5	1	2025-01-01	2025-01-02	\N	\N	0	20.00	high rissk
9	task 09	1	6	2025-01-10	2025-01-14	\N	\N	0	20.00	higt risk
10	task 10	1	2	2025-01-10	2025-01-15	\N	\N	0	20.00	higt risk task
\.


--
-- TOC entry 4890 (class 0 OID 16436)
-- Dependencies: 225
-- Data for Name: tb_workingtime; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_workingtime (id, taskid, wktdate, wkttime) FROM stdin;
1	1	2025-01-03	7.00
2	1	2025-01-04	8.00
3	1	2025-01-14	6.00
4	2	2025-01-14	2.00
5	1	2025-01-15	6.50
9	2	2025-01-15	4.00
7	2	2025-01-16	2.00
8	3	2025-01-16	1.50
10	3	2025-01-15	1.00
11	5	2025-01-16	1.50
6	1	2025-01-16	3.00
12	7	2025-01-16	2.80
\.


--
-- TOC entry 4919 (class 0 OID 0)
-- Dependencies: 218
-- Name: tb_employee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_employee_id_seq', 10, true);


--
-- TOC entry 4920 (class 0 OID 0)
-- Dependencies: 220
-- Name: tb_project_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_project_id_seq', 7, true);


--
-- TOC entry 4921 (class 0 OID 0)
-- Dependencies: 222
-- Name: tb_task_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_task_id_seq', 10, true);


--
-- TOC entry 4922 (class 0 OID 0)
-- Dependencies: 224
-- Name: tb_workingtime_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_workingtime_id_seq', 12, true);


--
-- TOC entry 4728 (class 2606 OID 16408)
-- Name: tb_employee tb_employee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_employee
    ADD CONSTRAINT tb_employee_pkey PRIMARY KEY (empid);


--
-- TOC entry 4730 (class 2606 OID 16415)
-- Name: tb_project tb_project_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_project
    ADD CONSTRAINT tb_project_pkey PRIMARY KEY (id);


--
-- TOC entry 4732 (class 2606 OID 16424)
-- Name: tb_task tb_task_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_task
    ADD CONSTRAINT tb_task_pkey PRIMARY KEY (id);


--
-- TOC entry 4734 (class 2606 OID 16441)
-- Name: tb_workingtime tb_workingtime_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_workingtime
    ADD CONSTRAINT tb_workingtime_pkey PRIMARY KEY (id);


--
-- TOC entry 4735 (class 2606 OID 16430)
-- Name: tb_task fk_project; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_task
    ADD CONSTRAINT fk_project FOREIGN KEY (projectid) REFERENCES public.tb_project(id);


--
-- TOC entry 4737 (class 2606 OID 16442)
-- Name: tb_workingtime fk_task; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_workingtime
    ADD CONSTRAINT fk_task FOREIGN KEY (taskid) REFERENCES public.tb_task(id);


--
-- TOC entry 4736 (class 2606 OID 16425)
-- Name: tb_task fk_taskemp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_task
    ADD CONSTRAINT fk_taskemp FOREIGN KEY (taskempid) REFERENCES public.tb_employee(empid);


--
-- TOC entry 4897 (class 0 OID 0)
-- Dependencies: 231
-- Name: FUNCTION uuid_generate_v1(); Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON FUNCTION public.uuid_generate_v1() TO pedevelop;


--
-- TOC entry 4898 (class 0 OID 0)
-- Dependencies: 232
-- Name: FUNCTION uuid_generate_v1mc(); Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON FUNCTION public.uuid_generate_v1mc() TO pedevelop;


--
-- TOC entry 4899 (class 0 OID 0)
-- Dependencies: 233
-- Name: FUNCTION uuid_generate_v3(namespace uuid, name text); Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON FUNCTION public.uuid_generate_v3(namespace uuid, name text) TO pedevelop;


--
-- TOC entry 4900 (class 0 OID 0)
-- Dependencies: 234
-- Name: FUNCTION uuid_generate_v4(); Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON FUNCTION public.uuid_generate_v4() TO pedevelop;


--
-- TOC entry 4901 (class 0 OID 0)
-- Dependencies: 235
-- Name: FUNCTION uuid_generate_v5(namespace uuid, name text); Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON FUNCTION public.uuid_generate_v5(namespace uuid, name text) TO pedevelop;


--
-- TOC entry 4902 (class 0 OID 0)
-- Dependencies: 226
-- Name: FUNCTION uuid_nil(); Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON FUNCTION public.uuid_nil() TO pedevelop;


--
-- TOC entry 4903 (class 0 OID 0)
-- Dependencies: 227
-- Name: FUNCTION uuid_ns_dns(); Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON FUNCTION public.uuid_ns_dns() TO pedevelop;


--
-- TOC entry 4904 (class 0 OID 0)
-- Dependencies: 229
-- Name: FUNCTION uuid_ns_oid(); Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON FUNCTION public.uuid_ns_oid() TO pedevelop;


--
-- TOC entry 4905 (class 0 OID 0)
-- Dependencies: 228
-- Name: FUNCTION uuid_ns_url(); Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON FUNCTION public.uuid_ns_url() TO pedevelop;


--
-- TOC entry 4906 (class 0 OID 0)
-- Dependencies: 230
-- Name: FUNCTION uuid_ns_x500(); Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON FUNCTION public.uuid_ns_x500() TO pedevelop;


--
-- TOC entry 4907 (class 0 OID 0)
-- Dependencies: 219
-- Name: TABLE tb_employee; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.tb_employee TO pedevelop;


--
-- TOC entry 4909 (class 0 OID 0)
-- Dependencies: 218
-- Name: SEQUENCE tb_employee_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.tb_employee_id_seq TO pedevelop;


--
-- TOC entry 4910 (class 0 OID 0)
-- Dependencies: 221
-- Name: TABLE tb_project; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.tb_project TO pedevelop;


--
-- TOC entry 4912 (class 0 OID 0)
-- Dependencies: 220
-- Name: SEQUENCE tb_project_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.tb_project_id_seq TO pedevelop;


--
-- TOC entry 4913 (class 0 OID 0)
-- Dependencies: 223
-- Name: TABLE tb_task; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.tb_task TO pedevelop;


--
-- TOC entry 4915 (class 0 OID 0)
-- Dependencies: 222
-- Name: SEQUENCE tb_task_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.tb_task_id_seq TO pedevelop;


--
-- TOC entry 4916 (class 0 OID 0)
-- Dependencies: 225
-- Name: TABLE tb_workingtime; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.tb_workingtime TO pedevelop;


--
-- TOC entry 4918 (class 0 OID 0)
-- Dependencies: 224
-- Name: SEQUENCE tb_workingtime_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.tb_workingtime_id_seq TO pedevelop;


--
-- TOC entry 2071 (class 826 OID 16451)
-- Name: DEFAULT PRIVILEGES FOR SEQUENCES; Type: DEFAULT ACL; Schema: public; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public GRANT ALL ON SEQUENCES TO pedevelop;


--
-- TOC entry 2070 (class 826 OID 16450)
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: public; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public GRANT ALL ON TABLES TO pedevelop;


-- Completed on 2025-01-16 19:33:40

--
-- PostgreSQL database dump complete
--

-- Completed on 2025-01-16 19:33:40

--
-- PostgreSQL database cluster dump complete
--

