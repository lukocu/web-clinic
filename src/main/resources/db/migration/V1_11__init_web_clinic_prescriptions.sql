CREATE TABLE prescriptions (
                               prescription_id serial PRIMARY KEY,
                               medication_id int NOT NULL,
                               prescription_date TIMESTAMP NOT NULL,
                               prescrpition_date_end TIMESTAMP,
                               prescription_available BOOLEAN NOT NULL,
                               FOREIGN KEY (medication_id) REFERENCES medications(medication_id)
);