-- Przykładowe dane dla tabeli clinic_role
INSERT INTO clinic_role (role_id, role)
VALUES (1, 'ADMIN'),
       (2, 'DOCTOR'),
       (3, 'PATIENT');

-- Przykładowe dane dla tabeli clinic_user
INSERT INTO clinic_user (user_id, username, email, password, active)
VALUES (1, 'admin123', 'admin@example.com', 'hashed_password', true),
       (2, 'doctor456', 'doctor@example.com', 'hashed_password', true),
       (3, 'doctor2', 'doctor2@example.com', 'hashed_password', true),
       (4, 'patient789', 'patient@example.com', 'hashed_password', true),
       (5, 'patient11', 'patient11@example.com', 'hashed_password', true);

-- Przykładowe dane dla tabeli clinic_user_role
INSERT INTO clinic_user_role (clinic_user_role_id, user_id, role_id)
VALUES (1, 1, 1), -- admin ma rolę ADMIN
       (2, 2, 2), -- doctor ma rolę DOCTOR
       (3, 3, 3);
-- patient ma rolę PATIENT

-- Przykładowe dane dla tabeli specialization
INSERT INTO specialization (specialization_id, specialization_name)
VALUES (1, 'Cardiology'),
       (2, 'Dermatology'),
       (3, 'Pediatrics');

-- Przykładowe dane dla tabeli doctors
INSERT INTO doctors (doctor_id, name, surname, phone, user_id)
VALUES (1, 'John', 'Smith', '123456789', 2), -- Doctor with ID 2 from clinic_user
       (2, 'Emily', 'Johnson', '987654321', 3);-- Doctor with ID 2 from clinic_user

-- Przykładowe dane dla tabeli doctor_specialization
INSERT INTO doctor_specialization (doctor_specialization_id, doctor_id, specialization_id)
VALUES (1, 1, 1), -- Doctor 1 is specialized in Cardiology
       (2, 2, 2); -- Doctor 2 is specialized in Dermatology

-- Przykładowe dane dla tabeli office
INSERT INTO office (office_id, doctor_id, first_consultation_fee, followup_consultation_fee)
VALUES (1, 1, 100.00, 50.00), -- Office of Doctor 1
       (2, 2, 150.00, 75.00); -- Office of Doctor 2

-- Przykładowe dane dla tabeli appointment_status
INSERT INTO appointment_status (appointment_status_id, status)
VALUES (1, 'Scheduled'),
       (2, 'Completed'),
       (3, 'Canceled');

-- Przykładowe dane dla tabeli patients
INSERT INTO patients (patient_id, name, surname, birth_date, address, phone, user_id)
VALUES (1, 'Alice', 'Smith', '1990-05-15', '123 Main St', '555-1234', 4), -- Patient with ID 4 from clinic_user
       (2, 'Bob', 'Johnson', '1985-10-20', '456 Elm St', '555-5678', 5);-- Patient with ID 5 from clinic_user

-- Przykładowe dane dla tabeli medications
INSERT INTO medications (medication_id, medication_name, dosage, frequency, duration)
VALUES (1, 'Aspirin', '500 mg', 'Once a day', '1 day'),
       (2, 'Ibuprofen', '200 mg', 'Every 6 hours', '3 days');

-- Przykładowe dane dla tabeli diseases
INSERT INTO diseases (disease_id, disease_name, disease_description)
VALUES (1, 'Flu', 'Respiratory illness caused by influenza virus'),
       (2, 'Hypertension', 'High blood pressure condition');

-- Przykładowe dane dla tabeli patient_disease
INSERT INTO prescriptions (prescription_id, medication_id, prescription_date, prescription_date_end,
                           prescription_available)
VALUES (1, 1, '2023-08-01', '2023-08-07', true), -- Prescription for Aspirin
       (2, 2, '2023-08-03', '2023-08-10', true); -- Prescription for Ibuprofen

-- Przykładowe dane dla tabeli prescriptions
INSERT INTO patient_card (patient_card_id, diagnosis_date, diagnosis_note, patient_id, doctor_id,
                          prescription_id)
VALUES (1, '2023-08-05', 'Diagnosed with Flu', 1, 1, 1), -- Alice diagnosed by Doctor 1 with Flu
       (2, '2023-08-07', 'Diagnosed with Hypertension', 2, 2, 2); -- Bob diagnosed by Doctor 2 with Hypertension

-- Przykładowe dane dla tabeli patient_card
INSERT INTO patient_disease (patient_disease_id, patient_card_id, disease_id)
VALUES (1, 1, 1), -- Alice has Flu
       (2, 2, 2); -- Bob has Hypertension

-- Przykładowe dane dla tabeli office_doctor_availability
INSERT INTO office_doctor_availability (office_availability_id, office_id, date, start_time, end_time,
                                        availability_status)
VALUES (1, 1, '2023-08-15', '08:00:00', '12:00:00', true), -- Office 1 availability for Doctor 1
       (2, 2, '2023-08-17', '14:00:00', '18:00:00', true); -- Office 2 availability for Doctor 2


-- Przykładowe dane dla tabeli appointments
INSERT INTO appointments (appointment_id, patient_id, office_id, probable_start_time, actual_end_time,
                          appointment_status_id, appointment_taken_date)
VALUES (1, 1, 1, '2023-08-20 10:00:00', NULL, 1, '2023-08-15'),
       (2, 2, 2, '2023-08-22 15:30:00', NULL, 1, '2023-08-18');




