-- Przykładowe dane dla tabeli clinic_role
INSERT INTO clinic_role (role_id, role)
VALUES (1, 'ADMIN'),
       (2, 'DOCTOR'),
       (3, 'PATIENT');

-- Przykładowe dane dla tabeli clinic_user
INSERT INTO clinic_user (user_id, username, email, password, active)
VALUES (1, 'admin123', 'admin@example.com', '$2a$12$FCAlDbUksXTRONN7F8R/nuItbH4EGIpiVrCYiWDW8Tp3WhDBl/rxO', true),
       (2, 'doctor1', 'doctor1@example.com', '$2a$12$FCAlDbUksXTRONN7F8R/nuItbH4EGIpiVrCYiWDW8Tp3WhDBl/rxO', true),
       (3, 'doctor2', 'doctor2@example.com', '$2a$12$FCAlDbUksXTRONN7F8R/nuItbH4EGIpiVrCYiWDW8Tp3WhDBl/rxO', true),
       (4, 'patient1', 'patient1@example.com', '$2a$12$FCAlDbUksXTRONN7F8R/nuItbH4EGIpiVrCYiWDW8Tp3WhDBl/rxO', true),
       (5, 'patient2', 'patient2@example.com', '$2a$12$FCAlDbUksXTRONN7F8R/nuItbH4EGIpiVrCYiWDW8Tp3WhDBl/rxO', true);

-- Przykładowe dane dla tabeli clinic_user_role
INSERT INTO clinic_user_role (user_id, role_id)
VALUES (1, 1), -- admin ma rolę ADMIN
       (2, 2), -- doctor ma rolę DOCTOR
       (3, 2), -- doctor ma rolę DOCTOR
       (4, 3), -- patient ma rolę PATIENT
       (5, 3);
-- patient ma rolę PATIENT

-- Przykładowe dane dla tabeli specialization
INSERT INTO specialization (specialization_id, specialization_name)
VALUES (1, 'Cardiology'),
       (2, 'Dermatology'),
       (3, 'Pediatrics');

-- Przykładowe dane dla tabeli doctors
INSERT INTO doctors (doctor_id, name, surname, pesel, phone, user_id)
VALUES (1, 'John', 'Smith', '88092556231', '123456789', 2), -- Doctor with ID 2 from clinic_user
       (2, 'Emily', 'Johnson', '78051523148', '987654321', 3);
-- Doctor with ID 2 from clinic_user

-- Przykładowe dane dla tabeli doctor_specialization
INSERT INTO doctor_specialization (doctor_id, specialization_id)
VALUES (1, 1), -- Doctor 1 is specialized in Cardiology
       (2, 2);
-- Doctor 2 is specialized in Dermatology

-- Przykładowe dane dla tabeli office
INSERT INTO office (office_id, doctor_id, first_consultation_fee, followup_consultation_fee)
VALUES (1, 1, 100.00, 50.00), -- Office of Doctor 1
       (2, 2, 150.00, 75.00);
-- Office of Doctor 2


-- Przykładowe dane dla tabeli patients
INSERT INTO patients (patient_id, name, surname, pesel, birth_date, address, phone, user_id)
VALUES (1, 'Alice', 'Smith', '95011257943', '1990-05-15', '123 Main St', '555-1234',4), -- Patient with ID 4 from clinic_user
       (2, 'Bob', 'Johnson', '99022878014', '1985-10-20', '456 Elm St', '555-5678', 5);
-- Patient with ID 5 from clinic_user


-- Przykładowe dane dla tabeli appointment_status
INSERT INTO appointment_status (appointment_status_id, status)
VALUES (1, 'Scheduled'),
       (2, 'Completed'),
       (3, 'Canceled');


-- Przykładowe dane dla tabeli office_doctor_availability
INSERT INTO office_doctor_availability (office_availability_id, office_id, date, start_time, end_time,
                                        availability_status)
VALUES (1, 1, '2023-08-15', '08:00:00', '12:00:00', true),  -- Office 1 availability for Doctor 1
      -- Office 2 availability for Doctor 2
       (3, 2, '2023-08-17', '16:00:00', '17:00:00', true),  -- Office 2 availability for Doctor 2
       (4, 2, '2023-08-17', '17:00:00', '18:00:00', true);  -- Office 2 availability for Doctor 2

-- Office 2 availability for Doctor 2




