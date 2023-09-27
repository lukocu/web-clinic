package pl.clinic.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "roleId")
@ToString(of = {"roleId", "role"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clinic_role")
public class RoleEntity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clinic_role_seq")
    @SequenceGenerator(name = "clinic_role_seq", sequenceName = "clinic_role_seq",allocationSize = 1)
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "role")
    private String role;
}
