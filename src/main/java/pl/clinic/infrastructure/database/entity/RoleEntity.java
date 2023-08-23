package pl.clinic.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "roleId")
@ToString(of = "role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clinic_role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "role")
    private String role;
}
