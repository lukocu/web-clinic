package pl.clinic.api.dto.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.clinic.api.dto.PatientsDTO;
import pl.clinic.api.dto.mapper.PatientsMapper;
import pl.clinic.domain.Patients;
import pl.clinic.util.DTOFixtures;
import pl.clinic.util.DomainData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatientsMapperTest {

    private final PatientsMapper patientsMapper =PatientsMapper.INSTANCE;

    @Test
    public void testMapFromDtoWithoutAppointment() {
        // Given
        PatientsDTO patientsDTO = DTOFixtures.patient1();

        // When
        Patients patients = patientsMapper.mapFromDtoWithoutAppointment(patientsDTO);

        // Then
        assertEquals(patientsDTO.getName(), patients.getName());
        assertEquals(patientsDTO.getSurname(), patients.getSurname());
        assertEquals(patientsDTO.getPesel(), patients.getPesel());
        assertEquals(patientsDTO.getBirthDate(), patients.getBirthDate());
        assertEquals(patientsDTO.getAddress(), patients.getAddress());
        assertEquals(patientsDTO.getPhone(), patients.getPhone());
    }

    @Test
    public void testMapToDtoWithoutAppointment() {
        // Given
        Patients patients = DomainData.patient3();

        // When
        PatientsDTO patientsDTO = patientsMapper.mapToDtoWithoutAppointment(patients);

        // Then
        assertEquals(patients.getName(), patientsDTO.getName());
        assertEquals(patients.getSurname(), patientsDTO.getSurname());
        assertEquals(patients.getPesel(), patientsDTO.getPesel());
        assertEquals(patients.getBirthDate(), patientsDTO.getBirthDate());
        assertEquals(patients.getAddress(), patientsDTO.getAddress());
        assertEquals(patients.getPhone(), patientsDTO.getPhone());
    }

    @Test
    public void testMapToDto() {
        // Given
        Patients patients = DomainData.patient2();

        // When
        PatientsDTO patientsDTO = patientsMapper.mapToDto(patients);

        // Then
        assertEquals(patients.getName(), patientsDTO.getName());
        assertEquals(patients.getSurname(), patientsDTO.getSurname());
        assertEquals(patients.getPesel(), patientsDTO.getPesel());
        assertEquals(patients.getBirthDate(), patientsDTO.getBirthDate());
        assertEquals(patients.getAddress(), patientsDTO.getAddress());
        assertEquals(patients.getPhone(), patientsDTO.getPhone());
    }
}
