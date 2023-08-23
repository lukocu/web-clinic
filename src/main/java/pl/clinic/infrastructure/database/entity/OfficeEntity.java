package pl.clinic.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "officeId")
@ToString(of = {"firstConsultationFee", "followupConsultationFee"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "office")
public class OfficeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "office_id")
    private Integer officeId;

    @Column(name = "first_consultation_fee")
    private BigDecimal firstConsultationFee;

    @Column(name = "followup_consultation_fee")
    private BigDecimal followupConsultationFee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private DoctorsEntity doctor;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "office", cascade = CascadeType.ALL)
    Set<OfficeDoctorAvailabilityEntity> officeDoctorAvailabilities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "office")
    Set<AppointmentsEntity> appointments;

}