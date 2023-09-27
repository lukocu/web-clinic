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