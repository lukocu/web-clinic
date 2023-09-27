CREATE TABLE doctor_specialization (

                                       doctor_id INT,
                                       specialization_id INT,
                                       PRIMARY KEY (doctor_id, specialization_id),
                                       FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id),
                                       FOREIGN KEY (specialization_id) REFERENCES specialization(specialization_id)
);

