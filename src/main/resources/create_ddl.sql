CREATE TABLE clinic_user (
                             user_id serial PRIMARY KEY,
                             username VARCHAR(32) NOT NULL UNIQUE,
                             email VARCHAR(32) NOT NULL UNIQUE,
                             password VARCHAR(128) NOT NULL,
                             active BOOLEAN NOT NULL
);
CREATE TABLE clinic_role (
                             role_id serial PRIMARY KEY,
                             role VARCHAR(50)
);
CREATE TABLE clinic_user_role (
                                  clinic_user_role_id serial PRIMARY KEY,
                                  user_id int NOT NULL,
                                  role_id int NOT NULL,
                                  FOREIGN KEY (user_id) REFERENCES clinic_user(user_id),
                                  FOREIGN KEY (role_id) REFERENCES clinic_role(role_id)
);
CREATE TABLE patients (
                          patient_id serial PRIMARY KEY,
                          name VARCHAR(25) NOT NULL,
                          surname VARCHAR(50) NOT NULL,
                          birth_date DATE NOT NULL,
                          address VARCHAR(25) NOT NULL,
                          phone VARCHAR(15) NOT NULL UNIQUE,
                          user_id int NOT NULL,
                          FOREIGN KEY (user_id) REFERENCES clinic_user(user_id)
);
CREATE TABLE specialization (
                                specialization_id serial PRIMARY KEY,
                                specialization_name VARCHAR(100) NOT NULL
);

CREATE TABLE doctors (
                         doctor_id serial PRIMARY KEY,
                         name VARCHAR(25) NOT NULL,
                         surname VARCHAR(50) NOT NULL,
                         phone VARCHAR(15) NOT NULL UNIQUE,
                         user_id int NOT NULL,
                         FOREIGN KEY (user_id) REFERENCES clinic_user(user_id)
);

CREATE TABLE doctor_specialization (
                                       doctor_specialization_id serial PRIMARY KEY,
                                       doctor_id int NOT NULL,
                                       specialization_id int NOT NULL,
                                       FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id),
                                       FOREIGN KEY (specialization_id) REFERENCES specialization(specialization_id)
);

CREATE TABLE office (
                        office_id serial PRIMARY KEY,
                        doctor_id int NOT NULL,
                        first_consultation_fee NUMERIC(10,2) NOT NULL,
                        followup_consultation_fee NUMERIC(10,2) NOT NULL,
                        FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id)
);
CREATE TABLE office_doctor_availability (
                                            office_availability_id serial PRIMARY KEY,
                                            office_id int NOT NULL,
                                            date DATE NOT NULL,
                                            start_time TIME NOT NULL,
                                            end_time TIME NOT NULL,
                                            availability_status BOOLEAN,
                                            FOREIGN KEY (office_id) REFERENCES office(office_id)
);
CREATE TABLE appointment_status (
                                    appointment_status_id serial PRIMARY KEY,
                                    status VARCHAR(15) NOT NULL
);

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
CREATE TABLE medications (
                             medication_id serial PRIMARY KEY,
                             medication_name VARCHAR(50) NOT NULL,
                             dosage VARCHAR(25) NOT NULL,
                             frequency VARCHAR(25),
                             duration INTERVAL
);
CREATE TABLE prescriptions (
                               prescription_id serial PRIMARY KEY,
                               medication_id int NOT NULL,
                               prescription_date TIMESTAMP NOT NULL,
                               prescription_date_end TIMESTAMP,
                               prescription_available BOOLEAN NOT NULL,
                               FOREIGN KEY (medication_id) REFERENCES medications(medication_id)
);
CREATE TABLE diseases (
                          disease_id serial PRIMARY KEY,
                          disease_name TEXT NOT NULL,
                          disease_description TEXT
);

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

CREATE TABLE patient_disease (
                                 patient_disease_id serial PRIMARY KEY,
                                 patient_card_id int NOT NULL,
                                 disease_id int NOT NULL,
                                 FOREIGN KEY (patient_card_id) REFERENCES patient_card(patient_card_id),
                                 FOREIGN KEY (disease_id) REFERENCES diseases(disease_id)
);
