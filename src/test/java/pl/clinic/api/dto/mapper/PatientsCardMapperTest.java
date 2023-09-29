package pl.clinic.api.dto.mapper;

import org.junit.jupiter.api.Test;
import pl.clinic.api.dto.PatientCardDTO;
import pl.clinic.domain.PatientCard;
import pl.clinic.util.DTOFixtures;
import pl.clinic.util.DomainData;

import static org.junit.jupiter.api.Assertions.*;

public class PatientsCardMapperTest {

    @Test
    public void testMapFromDto() {

        PatientCardDTO patientCardDTO = DTOFixtures.patientCard1();


        PatientCard result = PatientsCardMapper.INSTANCE.mapFromDto(patientCardDTO);


        assertEquals(patientCardDTO.getDiagnosisDate(), result.getDiagnosisDate());
        assertEquals(patientCardDTO.getDiagnosisNote(), result.getDiagnosisNote());
        assertNotNull(result.getDoctor());
        assertNotNull(result.getPatient());
        assertEquals(patientCardDTO.getDiseases().size(), result.getDiseases().size());
        assertNotNull(result.getPrescription());
    }

    @Test
    public void testMapToDtoWithDoc() {

        PatientCard patientCard = DomainData.patientCard2();


        PatientCardDTO result = PatientsCardMapper.INSTANCE.mapToDtoWithDoc(patientCard);


        assertEquals(patientCard.getDiagnosisDate(), result.getDiagnosisDate());
        assertEquals(patientCard.getDiagnosisNote(), result.getDiagnosisNote());
        assertNotNull(result.getDoctor());
        assertEquals(patientCard.getDiseases().size(), result.getDiseases().size());
        assertNotNull(result.getPrescription());
    }
}
