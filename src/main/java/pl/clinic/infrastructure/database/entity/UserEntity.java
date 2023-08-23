package pl.clinic.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "userId")
@ToString(of = {"username","email","password","active"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clinic_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private Boolean active;


    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "clinic_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;

    @OneToOne(mappedBy = "user")
    private PatientsEntity patient;

    @OneToOne(mappedBy = "user")
    private DoctorsEntity doctors;
}
