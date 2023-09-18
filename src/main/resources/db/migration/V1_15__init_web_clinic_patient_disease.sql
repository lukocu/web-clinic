CREATE TABLE patient_disease (
                                 patient_disease_id serial PRIMARY KEY,
                                 patient_card_id int NOT NULL,
                                 disease_id int NOT NULL,
                                 FOREIGN KEY (patient_card_id) REFERENCES patient_card(patient_card_id),
                                 FOREIGN KEY (disease_id) REFERENCES diseases(disease_id)
);