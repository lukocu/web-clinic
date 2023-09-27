CREATE SEQUENCE diseases_seq START 1;
CREATE TABLE diseases (
                          disease_id INT PRIMARY KEY DEFAULT nextval('diseases_seq'),
                          disease_name TEXT NOT NULL,
                          disease_description TEXT
);