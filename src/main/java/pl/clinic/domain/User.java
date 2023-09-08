package pl.clinic.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@EqualsAndHashCode(of = "userId")
@ToString(of = {"userId", "username", "email", "password", "active"})
@Builder
public class User {

    Integer userId;

    String username;

    String email;

    String password;

    Boolean active;

    Set<Role> roles;

    Patients patient;

    Doctors doctors;
}
