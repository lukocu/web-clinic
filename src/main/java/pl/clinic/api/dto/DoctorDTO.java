package pl.clinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

    private String name;
    private String surname;
    private String phone;
    private String specializationNames;
    private String officeId; // office number in clinic
    private BigDecimal firstConsultationFee;
    private BigDecimal followupConsultationFee;


}
