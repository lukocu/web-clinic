CREATE TABLE prescriptions (
                               prescription_id serial PRIMARY KEY,
                               prescription_date TIMESTAMP NOT NULL,
                               prescription_date_end TIMESTAMP,
                               prescription_available BOOLEAN NOT NULL
);