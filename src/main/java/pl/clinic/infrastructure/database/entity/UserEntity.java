package pl.clinic.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "userId")
@ToString(of = {"userId", "username", "email", "password", "active"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clinic_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "clinic_user_seq", allocationSize = 1)
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


    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(
            name = "clinic_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private PatientsEntity patient;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private DoctorsEntity doctors;
}
