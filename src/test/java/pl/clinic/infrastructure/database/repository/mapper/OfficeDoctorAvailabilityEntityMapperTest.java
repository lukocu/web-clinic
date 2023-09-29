package pl.clinic.infrastructure.database.repository.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.clinic.domain.OfficeDoctorAvailability;
import pl.clinic.infrastructure.database.entity.OfficeDoctorAvailabilityEntity;
import pl.clinic.util.DomainData;
import pl.clinic.util.EntityFixtures;

public class OfficeDoctorAvailabilityEntityMapperTest {

    private final OfficeDoctorAvailabilityEntityMapper mapper = Mappers.getMapper(OfficeDoctorAvailabilityEntityMapper.class);

    @Test
    public void testMapFromEntity() {

        OfficeDoctorAvailabilityEntity entity = EntityFixtures.officeAvailabilityDoctor2_2();

        OfficeDoctorAvailability availability = mapper.mapFromEntity(entity);


        assertNotNull(availability);
        assertEquals(entity.getOfficeAvailabilityId(), availability.getOfficeAvailabilityId());
        assertEquals(entity.getDate(), availability.getDate());
        assertEquals(entity.getStartTime(), availability.getStartTime());
        assertEquals(entity.getEndTime(), availability.getEndTime());
        assertEquals(entity.getAvailabilityStatus(), availability.getAvailabilityStatus());
    }

    @Test
    public void testMapToEntity() {

        OfficeDoctorAvailability availability = DomainData.officeAvailabilityDoctor1();



        OfficeDoctorAvailabilityEntity entity = mapper.mapToEntity(availability);


        assertNotNull(entity);
        assertEquals(availability.getOfficeAvailabilityId(), entity.getOfficeAvailabilityId());
        assertEquals(availability.getDate(), entity.getDate());
        assertEquals(availability.getStartTime(), entity.getStartTime());
        assertEquals(availability.getEndTime(), entity.getEndTime());
        assertEquals(availability.getAvailabilityStatus(), entity.getAvailabilityStatus());
    }

    @Test
    public void testMapToEntityWithOffice() {

        OfficeDoctorAvailability availability = DomainData.officeAvailabilityDoctor1();


        OfficeDoctorAvailabilityEntity entity = mapper.mapToEntityWithOffice(availability);


        assertNotNull(entity);
        assertEquals(availability.getOfficeAvailabilityId(), entity.getOfficeAvailabilityId());
        assertEquals(availability.getDate(), entity.getDate());
        assertEquals(availability.getStartTime(), entity.getStartTime());
        assertEquals(availability.getEndTime(), entity.getEndTime());
        assertEquals(availability.getAvailabilityStatus(), entity.getAvailabilityStatus());
        assertNotNull(entity.getOffice());
    }

    @Test
    public void testMapToEntityWithOfficeNoId() {

        OfficeDoctorAvailability availability = DomainData.officeAvailabilityDoctor1();
        OfficeDoctorAvailability officeDoctorAvailability = availability.withOfficeAvailabilityId(null);


        OfficeDoctorAvailabilityEntity entity = mapper.mapToEntityWithOfficeNoId(officeDoctorAvailability);


        assertNotNull(entity);
        assertNull(entity.getOfficeAvailabilityId());
        assertEquals(officeDoctorAvailability.getDate(), entity.getDate());
        assertEquals(officeDoctorAvailability.getStartTime(), entity.getStartTime());
        assertEquals(officeDoctorAvailability.getEndTime(), entity.getEndTime());
        assertEquals(officeDoctorAvailability.getAvailabilityStatus(), entity.getAvailabilityStatus());
        assertNotNull(entity.getOffice());
    }
}
