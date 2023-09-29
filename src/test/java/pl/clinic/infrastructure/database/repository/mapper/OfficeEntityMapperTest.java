package pl.clinic.infrastructure.database.repository.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.clinic.domain.Doctors;
import pl.clinic.domain.Office;
import pl.clinic.infrastructure.database.entity.OfficeEntity;
import pl.clinic.util.DomainData;
import pl.clinic.util.EntityFixtures;

import java.math.BigDecimal;

public class OfficeEntityMapperTest {

    private final OfficeEntityMapper mapper = Mappers.getMapper(OfficeEntityMapper.class);

    @Test
    public void testMapFromEntity() {

        OfficeEntity entity = EntityFixtures.officeForDoctor2();



        Office office = mapper.mapFromEntity(entity);


        assertNotNull(office);
        assertEquals(entity.getOfficeId(), office.getOfficeId());
        assertEquals(entity.getFirstConsultationFee(), office.getFirstConsultationFee());
        assertEquals(entity.getFollowupConsultationFee(), office.getFollowupConsultationFee());
    }

    @Test
    public void testMapToEntity() {

        Office office = Office.builder()
                .officeId(100)
                .firstConsultationFee(BigDecimal.valueOf(150.00))
                .followupConsultationFee(BigDecimal.valueOf(100.00))
                .build();



        OfficeEntity entity = mapper.mapToEntity(office);


        assertNotNull(entity);
        assertEquals(office.getOfficeId(), entity.getOfficeId());
        assertEquals(office.getFirstConsultationFee(), entity.getFirstConsultationFee());
        assertEquals(office.getFollowupConsultationFee(), entity.getFollowupConsultationFee());
    }

    @Test
    public void testMapFromEntityWithoutAppointments() {

        OfficeEntity entity = EntityFixtures.officeForDoctor1();



        Office office = mapper.mapFromEntityWithoutAppointments(entity);


        assertNotNull(office);
        assertEquals(entity.getOfficeId(), office.getOfficeId());
        assertEquals(entity.getFirstConsultationFee(), office.getFirstConsultationFee());
        assertEquals(entity.getFollowupConsultationFee(), office.getFollowupConsultationFee());
        assertNull(office.getDoctor());
        assertNotNull(office.getOfficeDoctorAvailabilities());
        assertEquals(1, office.getOfficeDoctorAvailabilities().size());
    }

    @Test
    public void testMapFromEntityWithoutDoctor() {

        OfficeEntity entity = EntityFixtures.officeForDoctor1();



        Office office = mapper.mapFromEntityWithoutDoctor(entity);


        assertNotNull(office);
        assertEquals(entity.getOfficeId(), office.getOfficeId());
        assertEquals(entity.getFirstConsultationFee(), office.getFirstConsultationFee());
        assertEquals(entity.getFollowupConsultationFee(), office.getFollowupConsultationFee());
        assertNull(office.getDoctor());
        assertNotNull(office.getOfficeDoctorAvailabilities());
        assertEquals(1, office.getOfficeDoctorAvailabilities().size());
    }
}
