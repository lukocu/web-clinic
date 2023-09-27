CREATE SEQUENCE prescriptions_seq START 1;
CREATE TABLE prescriptions (
                               prescription_id INT PRIMARY KEY DEFAULT nextval('prescriptions_seq'),
                               prescription_date TIMESTAMP NOT NULL,
                               prescription_date_end TIMESTAMP,
                               prescription_available BOOLEAN NOT NULL
);