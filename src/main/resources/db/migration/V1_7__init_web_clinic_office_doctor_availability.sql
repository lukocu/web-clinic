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