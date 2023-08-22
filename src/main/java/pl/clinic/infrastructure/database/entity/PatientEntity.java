package pl.clinic.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
@Getter
@Setter
@EqualsAndHashCode(of = "patientId")
@ToString(of = {})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    private String firstName;
    private String lastName;
    private OffsetDateTime birthDate;
    private String address;
    private String phoneNumber;
    private String emailAddress;
    private String otherPatientData;
}