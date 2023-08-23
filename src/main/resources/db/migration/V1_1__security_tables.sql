CREATE TABLE clinic_user (
                             user_id serial PRIMARY KEY,
                             username VARCHAR(32) NOT NULL UNIQUE,
                             email VARCHAR(32) NOT NULL UNIQUE,
                             password VARCHAR(128) NOT NULL,
                             active BOOLEAN NOT NULL
);
CREATE TABLE clinic_role (
                             role_id serial PRIMARY KEY,
                             role VARCHAR(50)
);
CREATE TABLE clinic_user_role (
                                  clinic_user_role_id serial PRIMARY KEY,
                                  user_id int NOT NULL,
                                  role_id int NOT NULL,
                                  FOREIGN KEY (user_id) REFERENCES clinic_user(user_id),
                                  FOREIGN KEY (role_id) REFERENCES clinic_role(role_id)
);