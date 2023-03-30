DROP TABLE IF EXISTS "articles";
DROP SEQUENCE IF EXISTS articles_id_seq;
CREATE SEQUENCE articles_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 8 CACHE 1;

CREATE TABLE "public"."articles" (
    "id" integer DEFAULT nextval('articles_id_seq') NOT NULL,
    "external_id" text NOT NULL,
    "name" text NOT NULL,
    "unit" text NOT NULL,
    "amount" integer NOT NULL,
    "description" text NOT NULL,
    "buying_price" integer NOT NULL,
    "selling_price" integer NOT NULL,
    CONSTRAINT "articles_pkey" PRIMARY KEY ("id"),
    UNIQUE ("external_id")
) WITH (oids = false);
