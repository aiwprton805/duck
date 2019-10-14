CREATE TABLE @{jdbc.schema}.users
(
    id serial NOT NULL,
    name character varying(30) NOT NULL,
    password text NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE @{jdbc.schema}.users
    OWNER to @{jdbc.username};
