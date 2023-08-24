package pl.clinic.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@With
@Value
@EqualsAndHashCode(of = "officeId")
@ToString(of = {"firstConsultationFee", "followupConsultationFee"})
@Builder
public class Office {

    Integer officeId;

    BigDecimal firstConsultationFee;

    BigDecimal followupConsultationFee;

    Doctors doctor;

    Set<OfficeDoctorAvailability> officeDoctorAvailabilities;

    Set<Appointments> appointments;

}