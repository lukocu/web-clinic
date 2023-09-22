CREATE TABLE office_doctor_availability (
                                            office_availability_id SERIAL PRIMARY KEY,
                                            office_id int NOT NULL,
                                            date DATE NOT NULL,
                                            start_time TIME NOT NULL,
                                            end_time TIME NOT NULL,
                                            availability_status BOOLEAN,
                                            FOREIGN KEY (office_id) REFERENCES office(office_id)
);