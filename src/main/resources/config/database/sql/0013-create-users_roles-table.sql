CREATE TABLE @{jdbc.schema}.users_roles
(
    user_id integer NOT NULL,
    role_id smallint NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES @{jdbc.schema}.users (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (role_id)
        REFERENCES @{jdbc.schema}.roles (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
);

ALTER TABLE @{jdbc.schema}.users_roles
    OWNER to @{jdbc.username};
