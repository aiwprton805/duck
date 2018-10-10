CREATE TABLE @{jdbc.schema}.roles
(
    id smallint NOT NULL,
    name character varying(10) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE @{jdbc.schema}.roles
    OWNER to @{jdbc.username};
