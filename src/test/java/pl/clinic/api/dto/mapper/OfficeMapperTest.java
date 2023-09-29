package pl.clinic.api.dto.mapper;

import org.junit.jupiter.api.Test;
import pl.clinic.api.dto.OfficeDTO;
import pl.clinic.domain.Office;
import pl.clinic.util.DomainData;

import static org.junit.jupiter.api.Assertions.*;

public class OfficeMapperTest {

    @Test
    public void testMapToDto() {

        Office office = DomainData.officeForDoctor1();


        OfficeDTO result = OfficeMapper.INSTANCE.mapToDto(office);


        assertEquals(office.getOfficeId(), result.getOfficeId());
        assertEquals(office.getFirstConsultationFee(), result.getFirstConsultationFee());
        assertEquals(office.getFollowupConsultationFee(), result.getFollowupConsultationFee());
        assertNull(result.getDoctor());
    }

    @Test
    public void testMapToDtoForDoctor() {

        Office office = DomainData.officeForDoctor2();


        OfficeDTO result = OfficeMapper.INSTANCE.mapToDtoForDoctor(office);


        assertEquals(office.getOfficeId(), result.getOfficeId());
        assertEquals(office.getFirstConsultationFee(), result.getFirstConsultationFee());
        assertEquals(office.getFollowupConsultationFee(), result.getFollowupConsultationFee());
        assertNull(result.getDoctor());
    }

    @Test
    public void testMapToDtoWithDoctor() {

        Office office = DomainData.officeForDoctor1();
        Office officeWithDoctor = office.withDoctor(DomainData.doctor1().withOffices(null));

        OfficeDTO result = OfficeMapper.INSTANCE.mapToDtoWithDoctor(officeWithDoctor);


        assertEquals(office.getOfficeId(), result.getOfficeId());
        assertEquals(office.getFirstConsultationFee(), result.getFirstConsultationFee());
        assertEquals(office.getFollowupConsultationFee(), result.getFollowupConsultationFee());
        assertNotNull(result.getDoctor());
    }
}
