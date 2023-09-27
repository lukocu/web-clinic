CREATE SEQUENCE medications_seq START 1;
CREATE TABLE medications (
                             medication_id INT PRIMARY KEY DEFAULT nextval('medications_seq'),
                             medication_name VARCHAR(50) NOT NULL,
                             dosage VARCHAR(25) NOT NULL,
                             frequency VARCHAR(25),
                             duration VARCHAR(50)
);