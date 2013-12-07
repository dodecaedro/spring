DROP SCHEMA IF EXISTS tansfer;

CREATE SCHEMA transfer
       AUTHORIZATION transferuser;   


-- Sequence: transfer."S_ACCOUNT"

DROP SEQUENCE IF EXISTS transfer."S_ACCOUNT";

CREATE SEQUENCE transfer."S_ACCOUNT"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1
  CYCLE;
ALTER TABLE transfer."S_ACCOUNT"
  OWNER TO transferuser;

-- Sequence: transfer."S_CREDITCARD"

DROP SEQUENCE IF EXISTS transfer."S_CREDITCARD";

CREATE SEQUENCE transfer."S_CREDITCARD"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1
  CYCLE;
ALTER TABLE transfer."S_CREDITCARD"
  OWNER TO transferuser;


-- Sequence: transfer."S_CUSTOMER"

DROP SEQUENCE IF EXISTS transfer."S_CUSTOMER";

CREATE SEQUENCE transfer."S_CUSTOMER"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1
  CYCLE;
ALTER TABLE transfer."S_CUSTOMER"
  OWNER TO transferuser;



-- Table: transfer."CUSTOMER"

DROP TABLE IF EXISTS transfer."CUSTOMER";

CREATE TABLE transfer."CUSTOMER"
(
  "ID" bigint NOT NULL DEFAULT nextval('transfer."S_CUSTOMER"'::regclass),
  "FIRSTNAME" character varying(100) NOT NULL,
  "LASTNAME" character varying(100) NOT NULL,
  "PHONENUMBER" character varying(100),
  "ADDRESS" character varying(100) NOT NULL,
  CONSTRAINT "CUSTOMER_PKEY" PRIMARY KEY ("ID")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE transfer."CUSTOMER"
  OWNER TO transferuser;



-- Table: transfer."ACCOUNT"

DROP TABLE IF EXISTS transfer."ACCOUNT";

CREATE TABLE transfer."ACCOUNT"
(
  "ID" bigint NOT NULL DEFAULT nextval('transfer."S_ACCOUNT"'::regclass),
  "BALANCE" bigint DEFAULT 0,
  "CUSTOMER_ID" bigint,
  CONSTRAINT "ACCOUNT_PKEY" PRIMARY KEY ("ID"),
  CONSTRAINT "ACCOUNT_FKEY" FOREIGN KEY ("ID")
      REFERENCES transfer."CUSTOMER" ("ID") MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE transfer."ACCOUNT"
  OWNER TO transferuser;



-- Table: transfer."CREDITCARD"

-- DROP TABLE transfer."CREDITCARD";

CREATE TABLE transfer."CREDITCARD"
(
  "ID" bigint NOT NULL DEFAULT nextval('transfer."S_CREDITCARD"'::regclass),
  "ISSUE_DATE" timestamp without time zone NOT NULL,
  "EXPIRATION_DATE" time without time zone NOT NULL,
  "CUSTOMER_ID" bigint,
  CONSTRAINT "CREDITCARD_PKEY" PRIMARY KEY ("ID"),
  CONSTRAINT "CREDITCARD_FKEY" FOREIGN KEY ("ID")
      REFERENCES transfer."CUSTOMER" ("ID") MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE transfer."CREDITCARD"
  OWNER TO transferuser;