package pl.clinic.api.dto.mapper;

import org.junit.jupiter.api.Test;
import pl.clinic.api.dto.OfficeDoctorAvailabilityDTO;
import pl.clinic.domain.OfficeDoctorAvailability;
import pl.clinic.util.DTOFixtures;
import pl.clinic.util.DomainData;

import static org.junit.jupiter.api.Assertions.*;

public class OfficeDoctorAvailabilityMapperTest {

    @Test
    public void testMapToDtoWithOfficeId() {

        OfficeDoctorAvailability officeDoctorAvailability = DomainData.officeAvailabilityDoctor2_1();


        OfficeDoctorAvailabilityDTO result = OfficeDoctorAvailabilityMapper.INSTANCE.mapToDtoWithOfficeId(officeDoctorAvailability);


        assertEquals(officeDoctorAvailability.getOfficeAvailabilityId(), result.getOfficeAvailabilityId());
        assertEquals(officeDoctorAvailability.getDate(), result.getDate());
        assertEquals(officeDoctorAvailability.getStartTime(), result.getStartTime());
        assertEquals(officeDoctorAvailability.getEndTime(), result.getEndTime());
        assertEquals(officeDoctorAvailability.getAvailabilityStatus(), result.getAvailabilityStatus());
        assertEquals(officeDoctorAvailability.getOffice().getOfficeId(), result.getOfficeId());
    }

    @Test
    public void testMapToDto() {

        OfficeDoctorAvailability officeDoctorAvailability = DomainData.officeAvailabilityDoctor2_2();


        OfficeDoctorAvailabilityDTO result = OfficeDoctorAvailabilityMapper.INSTANCE.mapToDto(officeDoctorAvailability);


        assertEquals(officeDoctorAvailability.getOfficeAvailabilityId(), result.getOfficeAvailabilityId());
        assertEquals(officeDoctorAvailability.getDate(), result.getDate());
        assertEquals(officeDoctorAvailability.getStartTime(), result.getStartTime());
        assertEquals(officeDoctorAvailability.getEndTime(), result.getEndTime());
        assertEquals(officeDoctorAvailability.getAvailabilityStatus(), result.getAvailabilityStatus());
        assertEquals(officeDoctorAvailability.getOffice().getOfficeId(), result.getOfficeId());
    }

    @Test
    public void testMapFromDtoWithOffice() {

        OfficeDoctorAvailabilityDTO officeDoctorAvailabilityDTO = DTOFixtures.officeAvailabilityDoctor2_4();


        OfficeDoctorAvailability result = OfficeDoctorAvailabilityMapper.INSTANCE.mapFromDtoWithOffice(officeDoctorAvailabilityDTO);


        assertEquals(officeDoctorAvailabilityDTO.getDate(), result.getDate());
        assertEquals(officeDoctorAvailabilityDTO.getStartTime(), result.getStartTime());
        assertEquals(officeDoctorAvailabilityDTO.getEndTime(), result.getEndTime());
        assertEquals(officeDoctorAvailabilityDTO.getAvailabilityStatus(), result.getAvailabilityStatus());
        assertNotNull(result.getOffice());
        assertEquals(officeDoctorAvailabilityDTO.getOfficeId(), result.getOffice().getOfficeId());
    }
}
