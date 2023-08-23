CREATE TABLE appointments (
                              appointment_id serial PRIMARY KEY,
                              patient_id int NOT NULL,
                              office_id int NOT NULL,
                              probable_start_time TIMESTAMP NOT NULL,
                              actual_end_time TIMESTAMP,
                              appointment_status_id int NOT NULL,
                              appointment_taken_date DATE,
                              FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
                              FOREIGN KEY (office_id) REFERENCES office(office_id),
                              FOREIGN KEY (appointment_status_id) REFERENCES appointment_status(appointment_status_id)
);