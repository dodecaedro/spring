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
  START 5
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
  START 5
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

-- Sequence: transfer."s_customer"

DROP SEQUENCE IF EXISTS transfer.s_transfer;

CREATE SEQUENCE transfer.s_transfer
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 3
  CACHE 1
  CYCLE;
ALTER TABLE transfer.s_transfer
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
  "balance" numeric(19,2) DEFAULT 0,
  "customer_id" bigint UNIQUE,
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
  "issue_date" timestamp with time zone NOT NULL,
  "expiration_date" timestamp with time zone NOT NULL,
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


-- Table: transfer."transfer"

DROP TABLE IF EXISTS transfer.transfer;

CREATE TABLE transfer.transfer
(
  "id" bigint NOT NULL DEFAULT nextval('transfer.s_transfer'::regclass),
  "transfer_date" timestamp with time zone NOT NULL,
  "account_origin_id" bigint,
  "account_target_id" bigint,
  "amount" numeric(19,2),
  CONSTRAINT "transfer_PKEY" PRIMARY KEY ("id"),
  CONSTRAINT "account_origin_FKEY" FOREIGN KEY ("account_origin_id")
      REFERENCES transfer.account ("id") MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT "account_target_FKEY" FOREIGN KEY ("account_target_id")
      REFERENCES transfer.account ("id") MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE SET NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE transfer.creditcard
  OWNER TO transferuser;
