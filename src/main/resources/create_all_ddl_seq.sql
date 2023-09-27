CREATE SEQUENCE clinic_user_seq START 1;
CREATE SEQUENCE clinic_role_seq START 1;
CREATE TABLE clinic_user (
                             user_id INT PRIMARY KEY DEFAULT nextval('clinic_user_seq'),
                             username VARCHAR(32) NOT NULL UNIQUE,
                             email VARCHAR(32) NOT NULL UNIQUE,
                             password VARCHAR(128) NOT NULL,
                             active BOOLEAN NOT NULL
);
CREATE TABLE clinic_role (
                             role_id INT PRIMARY KEY DEFAULT nextval('clinic_role_seq'),
                             role VARCHAR(50)
);
CREATE TABLE clinic_user_role (
                                  user_id INT,
                                  role_id INT,
                                  PRIMARY KEY (user_id,role_id),
                                  FOREIGN KEY (user_id) REFERENCES clinic_user(user_id),
                                  FOREIGN KEY (role_id) REFERENCES clinic_role(role_id)
);
CREATE SEQUENCE patients_seq START 1;
CREATE TABLE patients (
                          patient_id INT PRIMARY KEY DEFAULT nextval('patients_seq'),
                          name VARCHAR(25) NOT NULL,
                          surname VARCHAR(50) NOT NULL,
                          pesel VARCHAR(11) NOT NULL UNIQUE,
                          birth_date DATE NOT NULL,
                          address VARCHAR(25) NOT NULL,
                          phone VARCHAR(15) NOT NULL UNIQUE,
                          user_id int NOT NULL,
                          FOREIGN KEY (user_id) REFERENCES clinic_user(user_id)
);
CREATE SEQUENCE specialization_seq START 1;
CREATE TABLE specialization (
                                specialization_id INT PRIMARY KEY DEFAULT nextval('specialization_seq'),
                                specialization_name VARCHAR(100) NOT NULL
);
CREATE SEQUENCE doctors_seq START 1;
CREATE TABLE doctors (
                         doctor_id INT PRIMARY KEY DEFAULT nextval('doctors_seq'),
                         name VARCHAR(25) NOT NULL,
                         surname VARCHAR(50) NOT NULL,
                         pesel VARCHAR(11) NOT NULL UNIQUE,
                         phone VARCHAR(15) NOT NULL UNIQUE,
                         user_id int NOT NULL,
                         FOREIGN KEY (user_id) REFERENCES clinic_user(user_id)
);
CREATE TABLE doctor_specialization (

                                       doctor_id INT,
                                       specialization_id INT,
                                       PRIMARY KEY (doctor_id, specialization_id),
                                       FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id),
                                       FOREIGN KEY (specialization_id) REFERENCES specialization(specialization_id)
);
CREATE SEQUENCE office_seq START 1;
CREATE TABLE office (
                        office_id INT PRIMARY KEY DEFAULT nextval('office_seq'),
                        doctor_id int NOT NULL,
                        first_consultation_fee NUMERIC(10,2) NOT NULL,
                        followup_consultation_fee NUMERIC(10,2) NOT NULL,
                        FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id)
);
CREATE SEQUENCE office_doctor_availability_sequence START 1;
CREATE TABLE office_doctor_availability (
                                            office_availability_id INT PRIMARY KEY DEFAULT NEXTVAL('office_doctor_availability_sequence'),
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
CREATE SEQUENCE appointments_seq START 1;
CREATE TABLE appointments (
                              appointment_id INT PRIMARY KEY DEFAULT nextval('appointments_seq'),
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
CREATE SEQUENCE prescriptions_seq START 1;
CREATE TABLE prescriptions (
                               prescription_id INT PRIMARY KEY DEFAULT nextval('prescriptions_seq'),
                               prescription_date TIMESTAMP NOT NULL,
                               prescription_date_end TIMESTAMP,
                               prescription_available BOOLEAN NOT NULL
);
CREATE SEQUENCE medications_seq START 1;
CREATE TABLE medications (
                             medication_id INT PRIMARY KEY DEFAULT nextval('medications_seq'),
                             medication_name VARCHAR(50) NOT NULL,
                             dosage VARCHAR(25) NOT NULL,
                             frequency VARCHAR(25),
                             duration VARCHAR(50)
);
CREATE TABLE prescription_medications (
                                          prescription_id INT,
                                          medication_id INT,
                                          PRIMARY KEY (prescription_id, medication_id),
                                          FOREIGN KEY (prescription_id) REFERENCES prescriptions(prescription_id),
                                          FOREIGN KEY (medication_id) REFERENCES medications(medication_id)
);
CREATE SEQUENCE diseases_seq START 1;
CREATE TABLE diseases (
                          disease_id INT PRIMARY KEY DEFAULT nextval('diseases_seq'),
                          disease_name TEXT NOT NULL,
                          disease_description TEXT
);
CREATE SEQUENCE patient_card_seq START 1;
CREATE TABLE patient_card (
                              patient_card_id INT PRIMARY KEY DEFAULT nextval('patient_card_seq'),
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
                                 patient_card_id INT,
                                 disease_id INT,
                                 PRIMARY KEY (patient_card_id, disease_id),
                                 FOREIGN KEY (patient_card_id) REFERENCES patient_card(patient_card_id),
                                 FOREIGN KEY (disease_id) REFERENCES diseases(disease_id)
);