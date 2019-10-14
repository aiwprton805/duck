CREATE TABLE @{jdbc.schema}.persistent_logins
(
    username character varying(30) NOT NULL,
    series character varying(64) NOT NULL,
    token character varying(64) NOT NULL,
    last_used timestamp with time zone NOT NULL,
    PRIMARY KEY (series),
    FOREIGN KEY (username)
        REFERENCES @{jdbc.schema}.users (name) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
);

ALTER TABLE @{jdbc.schema}.persistent_logins
    OWNER to @{jdbc.username};
COMMENT ON TABLE @{jdbc.schema}.persistent_logins
    IS 'This table is used by Spring Security remember me option.';
