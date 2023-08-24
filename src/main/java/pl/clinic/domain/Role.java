package pl.clinic.domain;

import lombok.*;

@With
@Value
@EqualsAndHashCode(of = "roleId")
@ToString(of = {"roleId", "role"})
@Builder
public class Role {

    Integer roleId;

    String role;
}
