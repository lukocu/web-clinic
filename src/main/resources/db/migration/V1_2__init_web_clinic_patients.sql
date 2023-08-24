CREATE TABLE patients (
                          patient_id serial PRIMARY KEY,
                          name VARCHAR(25) NOT NULL,
                          surname VARCHAR(50) NOT NULL,
                          pesel VARCHAR(11) NOT NULL UNIQUE,
                          birth_date DATE NOT NULL,
                          address VARCHAR(25) NOT NULL,
                          phone VARCHAR(15) NOT NULL UNIQUE,
                          user_id int NOT NULL,
                          FOREIGN KEY (user_id) REFERENCES clinic_user(user_id)
);