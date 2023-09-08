package pl.clinic.domain;

import lombok.*;

import java.util.Set;

@Data
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


}
