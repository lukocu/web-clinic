package pl.clinic.api.dto.mapper;

import org.junit.jupiter.api.Test;
import pl.clinic.api.dto.DoctorDTO;
import pl.clinic.domain.Doctors;
import pl.clinic.util.DTOFixtures;
import pl.clinic.util.DomainData;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class DoctorMapperTest {

    @Test
    public void testMapToDtoSpecAndOffices() {

        Doctors doctor = DomainData.doctor1();

        DoctorDTO result = DoctorMapper.INSTANCE.mapToDtoSpecAndOffices(doctor);


        assertEquals(doctor.getName(), result.getName());
        assertEquals(doctor.getSurname(), result.getSurname());
        assertEquals(doctor.getPhone(), result.getPhone());
        assertEquals(doctor.getPesel(), result.getPesel());
        assertEquals(doctor.getSpecializations().size(), result.getSpecializationNames().size());
        assertEquals(doctor.getOffices().size(), result.getOffices().size());
    }

    @Test
    public void testMapToDto() {

        Doctors doctor = DomainData.doctor2();


        DoctorDTO result = DoctorMapper.INSTANCE.mapToDto(doctor);

        assertEquals(doctor.getName(), result.getName());
        assertEquals(doctor.getSurname(), result.getSurname());
        assertEquals(doctor.getPhone(), result.getPhone());
        assertEquals(doctor.getPesel(), result.getPesel());
        assertNull(result.getSpecializationNames());
        assertNull(result.getOffices());
    }

    @Test
    public void testMapFromDtoForPatientCard() {

        DoctorDTO doctorDTO = DTOFixtures.doctor1();


        Doctors result = DoctorMapper.INSTANCE.mapFromDtoForPatientCard(doctorDTO);


        assertEquals(doctorDTO.getName(), result.getName());
        assertEquals(doctorDTO.getSurname(), result.getSurname());
        assertEquals(doctorDTO.getPhone(), result.getPhone());
        assertEquals(doctorDTO.getPesel(), result.getPesel());
        assertNull(result.getSpecializations());
        assertNull(result.getOffices());
    }

    @Test
    public void testMapFromDtoSpec() {

        DoctorDTO doctorDTO = DTOFixtures.doctor2();


        Doctors result = DoctorMapper.INSTANCE.mapFromDtoSpec(doctorDTO);


        assertEquals(doctorDTO.getName(), result.getName());
        assertEquals(doctorDTO.getSurname(), result.getSurname());
        assertEquals(doctorDTO.getPhone(), result.getPhone());
        assertEquals(doctorDTO.getPesel(), result.getPesel());
        assertNotNull(result.getSpecializations());
        assertNull(result.getOffices());
    }


}
