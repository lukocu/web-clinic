package pl.clinic.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = {"name","surname","phone"})
@ToString(of = {})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctors")
public class DoctorsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private String doctorId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone")
    private String phone;

    @ManyToMany(mappedBy = "doctors")
    private Set<SpecializationEntity> specializations;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", unique = true)
    private UserEntity user;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "doctor",cascade = CascadeType.ALL)
    private Set<OfficeEntity> offices;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "doctor")
    private Set<PatientCardEntity> patientCards;
}