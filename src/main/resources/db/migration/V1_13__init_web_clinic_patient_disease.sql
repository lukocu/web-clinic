CREATE TABLE patient_disease (
                                 patient_disease_id serial PRIMARY KEY,
                                 patient_id int NOT NULL,
                                 disease_id int NOT NULL,
                                 FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
                                 FOREIGN KEY (disease_id) REFERENCES diseases(disease_id)
);