-- Przykładowe dane dla tabeli clinic_role
INSERT INTO clinic_role (role_id, role)
VALUES (nextval('clinic_role_seq'), 'ADMIN'),
       (nextval('clinic_role_seq'), 'DOCTOR'),
       (nextval('clinic_role_seq'), 'PATIENT');

-- Przykładowe dane dla tabeli clinic_user
INSERT INTO clinic_user (user_id, username, email, password, active)
VALUES (nextval('clinic_user_seq'), 'admin123', 'admin@example.com', '$2a$12$FCAlDbUksXTRONN7F8R/nuItbH4EGIpiVrCYiWDW8Tp3WhDBl/rxO', true),
       (nextval('clinic_user_seq'), 'doctor1', 'doctor1@example.com', '$2a$12$FCAlDbUksXTRONN7F8R/nuItbH4EGIpiVrCYiWDW8Tp3WhDBl/rxO', true),
       (nextval('clinic_user_seq'), 'doctor2', 'doctor2@example.com', '$2a$12$FCAlDbUksXTRONN7F8R/nuItbH4EGIpiVrCYiWDW8Tp3WhDBl/rxO', true),
       (nextval('clinic_user_seq'), 'patient1', 'patient1@example.com', '$2a$12$FCAlDbUksXTRONN7F8R/nuItbH4EGIpiVrCYiWDW8Tp3WhDBl/rxO', true),
       (nextval('clinic_user_seq'), 'patient2', 'patient2@example.com', '$2a$12$FCAlDbUksXTRONN7F8R/nuItbH4EGIpiVrCYiWDW8Tp3WhDBl/rxO', true),
       (nextval('clinic_user_seq'), 'patient3', 'patient3@example.com', '$2a$12$FCAlDbUksXTRONN7F8R/nuItbH4EGIpiVrCYiWDW8Tp3WhDBl/rxO', true),
       (nextval('clinic_user_seq'), 'patient4', 'patient4@example.com', '$2a$12$FCAlDbUksXTRONN7F8R/nuItbH4EGIpiVrCYiWDW8Tp3WhDBl/rxO', true);

-- Przykładowe dane dla tabeli clinic_user_role
INSERT INTO clinic_user_role (user_id, role_id)
VALUES (1, 1), -- admin ma rolę ADMIN
       (2, 2), -- doctor ma rolę DOCTOR
       (3, 2), -- doctor ma rolę DOCTOR
       (4, 3), -- patient ma rolę PATIENT
       (5, 3), -- patient ma rolę PATIENT
       (6, 3), -- patient ma rolę PATIENT
       (7, 3); -- patient ma rolę PATIENT

-- Przykładowe dane dla tabeli specialization
INSERT INTO specialization (specialization_id, specialization_name)
VALUES (nextval('specialization_seq'), 'Cardiology'),
       (nextval('specialization_seq'), 'Dermatology'),
       (nextval('specialization_seq'), 'Pediatrics');

-- Przykładowe dane dla tabeli doctors
INSERT INTO doctors (doctor_id, name, surname, pesel, phone, user_id)
VALUES (nextval('doctors_seq'), 'John', 'Smith', '88092556231', '123456789', 2), -- Doctor with ID 2 from clinic_user
       (nextval('doctors_seq'), 'Emily', 'Johnson', '78051523148', '987654321', 3);
-- Doctor with ID 2 from clinic_user

-- Przykładowe dane dla tabeli doctor_specialization
INSERT INTO doctor_specialization (doctor_id, specialization_id)
VALUES (1, 1), -- Doctor 1 is specialized in Cardiology
       (2, 2);
-- Doctor 2 is specialized in Dermatology

-- Przykładowe dane dla tabeli office
INSERT INTO office (office_id, doctor_id, first_consultation_fee, followup_consultation_fee)
VALUES (nextval('office_seq'), 1, 100.00, 50.00), -- Office of Doctor 1
       (nextval('office_seq'), 2, 150.00, 75.00);
-- Office of Doctor 2


-- Przykładowe dane dla tabeli patients
INSERT INTO patients (patient_id, name, surname, pesel, birth_date, address, phone, user_id)
VALUES (nextval('patients_seq'), 'Alice', 'Smith', '95011257943', '1990-05-15', '123 Main St', '555-1234',4), -- Patient with ID 4 from clinic_user
       (nextval('patients_seq'), 'Bob', 'Johnson', '99022878014', '1985-10-20', '456 Elm St', '555-5678', 5),-- Patient with ID 5 from clinic_user
       (nextval('patients_seq'), 'Jake', 'John', '99027978014', '1987-12-23', '456 Dln St', '555-567-258', 6),-- Patient with ID 6 from clinic_user
       (nextval('patients_seq'), 'Blake', 'Mood', '95022878014', '1995-12-24', '456 Main St', '555-567-128', 7);-- Patient with ID 7 from clinic_user


-- Przykładowe dane dla tabeli appointment_status
INSERT INTO appointment_status (appointment_status_id, status)
VALUES (1, 'Scheduled'),
       (2, 'Completed'),
       (3, 'Canceled');


-- Przykładowe dane dla tabeli office_doctor_availability
INSERT INTO office_doctor_availability (office_availability_id, office_id, date, start_time, end_time,
                                        availability_status)
VALUES (nextval('office_doctor_availability_sequence'), 1, '2023-08-15', '08:00:00', '12:00:00', true),  -- Office 1 availability for Doctor 1
       (nextval('office_doctor_availability_sequence'), 2, '2023-08-17', '14:00:00', '15:00:00', false), -- Office 2 availability for Doctor 2
       (nextval('office_doctor_availability_sequence'), 2, '2023-08-17', '16:00:00', '17:00:00', true),  -- Office 2 availability for Doctor 2
       (nextval('office_doctor_availability_sequence'), 2, '2023-08-17', '17:00:00', '18:00:00', true),  -- Office 2 availability for Doctor 2
       (nextval('office_doctor_availability_sequence'), 2, '2023-08-22', '15:00:00', '16:00:00', false);-- Office 2 availability for Doctor 2

-- Przykładowe dane dla tabeli appointments
INSERT INTO appointments (appointment_id, patient_id, office_id, probable_start_time, actual_end_time,
                          appointment_status_id, appointment_taken_date)
VALUES (nextval('appointments_seq'), 1, 2, '2023-08-17 14:00:00', NULL, 1, '2023-08-17'),
       (nextval('appointments_seq'), 2, 2, '2023-08-22 15:00:00', NULL, 1, '2023-08-18'),
       (nextval('appointments_seq'), 1, 2, '2023-08-17 14:00:00', NULL, 3, '2023-08-17'),
       (nextval('appointments_seq'), 2, 2, '2023-08-17 17:00:00', NULL, 3, '2023-08-18'),
       (nextval('appointments_seq'), 2, 2, '2023-06-15 10:00:00', '2023-06-15 11:00:00', 2, '2023-06-10'),
       (nextval('appointments_seq'), 2, 2, '2023-06-16 10:00:00', '2023-06-16 11:00:00', 2, '2023-06-10'),
       (nextval('appointments_seq'), 3, 2, '2023-06-16 10:00:00', '2023-06-16 11:00:00', 3, '2023-06-11'),
       (nextval('appointments_seq'), 3, 2, '2023-06-17 10:00:00', '2023-06-16 11:00:00', 3, '2023-06-12'),
       (nextval('appointments_seq'), 4, 2, '2023-06-16 11:00:00', '2023-06-16 11:00:00', 2, '2023-06-13'),
       (nextval('appointments_seq'), 4, 2, '2023-06-24 12:00:00', '2023-06-16 11:00:00', 2, '2023-06-23');

-- Dane dla tabeli medications
INSERT INTO medications (medication_id, medication_name, dosage, frequency, duration)
VALUES (nextval('medications_seq'), 'Aspirin', '500mg', 'Once a day', '7 days'),
       (nextval('medications_seq'), 'Ibuprofen', '200mg', 'Twice a day', '10 days'),
       (nextval('medications_seq'), 'Antibiotic', '250mg', 'Three times a day', '14 days');

-- Dane dla tabeli prescriptions
INSERT INTO prescriptions (prescription_id, prescription_date, prescription_date_end, prescription_available)
VALUES (nextval('prescriptions_seq'), '2023-09-26 08:00:00', '2023-10-05 08:00:00', true),
       (nextval('prescriptions_seq'), '2023-09-27 10:30:00', '2023-10-10 10:30:00', true),
       (nextval('prescriptions_seq'), '2023-06-16 11:00:00', '2023-08-16 11:00:00', true),
       (nextval('prescriptions_seq'), '2023-06-24 12:00:00', '2023-08-24 12:00:00', true);


-- Dane dla tabeli prescription_medications (łączenie recept z lekami)
INSERT INTO prescription_medications (prescription_id, medication_id)
VALUES (1, 1), -- Przypisuje lek "Aspirin" do recepty 1
       (1, 2), -- Przypisuje lek "Ibuprofen" do recepty 1
       (2, 2), -- Przypisuje lek "Ibuprofen" do recepty 2
       (2, 3), -- Przypisuje lek "Antibiotic" do recepty 2
       (3, 3), -- Przypisuje lek "Antibiotic" do recepty 2
       (4, 3); -- Przypisuje lek "Antibiotic" do recepty 2

-- Dane dla tabeli diseases
INSERT INTO diseases (disease_id, disease_name, disease_description)
VALUES (nextval('diseases_seq'), 'Flu', 'Upper respiratory tract infection'),
       (nextval('diseases_seq'), 'Headache', 'Frequent headaches and migraines'),
       (nextval('diseases_seq'), 'Fever', 'Condition of fever due to infection'),
       (nextval('diseases_seq'), 'Tonsillitis', 'in the most common sense, acute inflammation of the palatine tonsils');

INSERT INTO patient_card (patient_card_id, diagnosis_date, diagnosis_note, patient_id, doctor_id, prescription_id)
VALUES (nextval('patient_card_seq'), '2023-06-15 10:00:00', 'Przykładowa diagnoza 1', 1, 2, 1),
       (nextval('patient_card_seq'), '2023-06-16 10:00:00', 'Przykładowa diagnoza 2', 2, 2, 2),
       (nextval('patient_card_seq'), '2023-06-16 11:00:00', 'Przykładowa diagnoza 3', 4, 2, 3),
       (nextval('patient_card_seq'), '2023-06-24 12:00:00', 'Przykładowa diagnoza 4', 4, 2, 4);

-- Dane dla tabeli patient_disease (łączenie pacjentów z chorobami)
INSERT INTO patient_disease (patient_card_id, disease_id)
VALUES (1, 1), -- Pacjent karta  1 ma flu
       (1, 2), -- Pacjent karta  1 ma Headache
       (2, 3), -- Pacjent karta  2 ma fever
       (3, 4), -- Pacjent karta  3 ma Tonsillitis
       (4, 4); -- Pacjent karta  4 ma Tonsillitis











