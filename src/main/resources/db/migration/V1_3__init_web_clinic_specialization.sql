CREATE SEQUENCE specialization_seq START 1;
CREATE TABLE specialization (
                                specialization_id INT PRIMARY KEY DEFAULT nextval('specialization_seq'),
                                specialization_name VARCHAR(100) NOT NULL
);