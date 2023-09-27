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