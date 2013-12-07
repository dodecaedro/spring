-- Schema: transfer

DROP SCHEMA IF EXISTS tansfer;

CREATE SCHEMA transfer
       AUTHORIZATION transferuser;



-- Sequence: transfer."s_account"

DROP SEQUENCE IF EXISTS transfer.s_account;

CREATE SEQUENCE transfer.s_account
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1
  CYCLE;
ALTER TABLE transfer.s_account
  OWNER TO transferuser;


-- Sequence: transfer."s_creditcard"

DROP SEQUENCE IF EXISTS transfer.s_creditcard;

CREATE SEQUENCE transfer.s_creditcard
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1
  CYCLE;
ALTER TABLE transfer.s_creditcard
  OWNER TO transferuser;


-- Sequence: transfer."s_customer"

DROP SEQUENCE IF EXISTS transfer.s_customer;

CREATE SEQUENCE transfer.s_customer
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 5
  CACHE 1
  CYCLE;
ALTER TABLE transfer.s_customer
  OWNER TO transferuser;



-- Table: transfer."customer"

DROP TABLE IF EXISTS transfer.customer;

CREATE TABLE transfer.customer
(
  "id" bigint NOT NULL DEFAULT nextval('transfer.s_customer'::regclass),
  "firstname" character varying(100) NOT NULL,
  "lastname" character varying(100) NOT NULL,
  "phonenumber" character varying(100),
  "address" character varying(100) NOT NULL,
  CONSTRAINT "customer_PKEY" PRIMARY KEY ("id")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE transfer.customer
  OWNER TO transferuser;


-- Table: transfer."account"

DROP TABLE IF EXISTS transfer.account;

CREATE TABLE transfer.account
(
  "id" bigint NOT NULL DEFAULT nextval('transfer.s_account'::regclass),
  "balance" bigint DEFAULT 0,
  "customer_id" bigint,
  CONSTRAINT "account_PKEY" PRIMARY KEY ("id"),
  CONSTRAINT "account_FKEY" FOREIGN KEY ("customer_id")
      REFERENCES transfer.customer ("id") MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE transfer.account
  OWNER TO transferuser;


-- Table: transfer."creditcard"

DROP TABLE IF EXISTS transfer.creditcard;

CREATE TABLE transfer.creditcard
(
  "id" bigint NOT NULL DEFAULT nextval('transfer.s_creditcard'::regclass),
  "issue_date" timestamp without time zone NOT NULL,
  "expiration_date" timestamp without time zone NOT NULL,
  "customer_id" bigint,
  CONSTRAINT "creditcard_PKEY" PRIMARY KEY ("id"),
  CONSTRAINT "creditcard_FKEY" FOREIGN KEY ("customer_id")
      REFERENCES transfer.customer ("id") MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE transfer.creditcard
  OWNER TO transferuser;
