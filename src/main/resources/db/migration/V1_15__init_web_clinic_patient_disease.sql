CREATE TABLE patient_disease (
                                 patient_card_id INT,
                                 disease_id INT,
                                 PRIMARY KEY (patient_card_id, disease_id),
                                 FOREIGN KEY (patient_card_id) REFERENCES patient_card(patient_card_id),
                                 FOREIGN KEY (disease_id) REFERENCES diseases(disease_id)
);