CREATE TABLE patient_card (
                              patient_card_id serial PRIMARY KEY,
                              diagnosis_date TIMESTAMP,
                              diagnosis_note TEXT,
                              patient_id int NOT NULL,
                              doctor_id int NOT NULL,
                              prescription_id INT,
                              FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
                              FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id),
                              FOREIGN KEY (prescription_id) REFERENCES prescriptions(prescription_id)
);