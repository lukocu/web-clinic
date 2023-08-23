CREATE TABLE office (
                        office_id serial PRIMARY KEY,
                        doctor_id int NOT NULL,
                        first_consultation_fee NUMERIC(10,2) NOT NULL,
                        followup_consultation_fee NUMERIC(10,2) NOT NULL,
                        FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id)
);