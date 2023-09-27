package pl.clinic.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "doctorId")
@ToString(of = {"doctorId", "name", "pesel", "surname", "phone"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctors")
public class DoctorsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctors_seq")
    @SequenceGenerator(name = "doctors_seq", sequenceName = "doctors_seq", allocationSize = 1)
    @Column(name = "doctor_id")
    private Integer doctorId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "pesel")
    private String pesel;

    @Column(name = "phone")
    private String phone;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "doctors")
    private Set<SpecializationEntity> specializations;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", unique = true)
    private UserEntity user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor", cascade = CascadeType.ALL)
    private Set<OfficeEntity> offices;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor")
    private Set<PatientCardEntity> patientCards;
}