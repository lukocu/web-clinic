CREATE TABLE doctor_specialization (
                                       doctor_specialization_id serial PRIMARY KEY,
                                       doctor_id int NOT NULL,
                                       specialization_id int NOT NULL,
                                       FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id),
                                       FOREIGN KEY (specialization_id) REFERENCES specialization(specialization_id)
);