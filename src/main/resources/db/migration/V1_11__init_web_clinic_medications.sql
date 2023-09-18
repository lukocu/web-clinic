CREATE TABLE medications (
                             medication_id serial PRIMARY KEY,
                             medication_name VARCHAR(50) NOT NULL UNIQUE,
                             dosage VARCHAR(25) NOT NULL,
                             frequency VARCHAR(25),
                             duration VARCHAR(50),
);