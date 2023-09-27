CREATE SEQUENCE office_seq START 1;
CREATE TABLE office (
                        office_id INT PRIMARY KEY DEFAULT nextval('office_seq'),
                        doctor_id int NOT NULL,
                        first_consultation_fee NUMERIC(10,2) NOT NULL,
                        followup_consultation_fee NUMERIC(10,2) NOT NULL,
                        FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id)
);