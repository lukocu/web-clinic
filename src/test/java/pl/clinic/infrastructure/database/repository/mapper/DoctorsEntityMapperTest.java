package pl.clinic.infrastructure.database.repository.mapper;

import org.junit.jupiter.api.Test;
import pl.clinic.domain.Doctors;
import pl.clinic.infrastructure.database.entity.DoctorsEntity;
import pl.clinic.infrastructure.database.entity.OfficeEntity;
import pl.clinic.infrastructure.database.entity.SpecializationEntity;
import pl.clinic.util.DomainData;
import pl.clinic.util.EntityFixtures;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class DoctorsEntityMapperTest {

    private final DoctorsEntityMapper mapper = DoctorsEntityMapper.INSTANCE;

    @Test
    public void testMapFromEntity() {

        DoctorsEntity entity = EntityFixtures.doctor1();

        entity.setSpecializations(Collections.emptySet());
        entity.setOffices(Collections.emptySet());


        Doctors doctor = mapper.mapFromEntity(entity);


        assertEquals(entity.getDoctorId(), doctor.getDoctorId());
        assertEquals(entity.getName(), doctor.getName());
        assertEquals(entity.getSurname(), doctor.getSurname());
        assertEquals(entity.getPesel(), doctor.getPesel());
        assertEquals(entity.getPhone(), doctor.getPhone());
        assertEquals(doctor.getSpecializations(), null);
        assertEquals(doctor.getOffices(), null);
        assertInstanceOf(Doctors.class, doctor);
    }

    @Test
    public void testMapToEntity() {

        Doctors doctor = DomainData.doctor1();

        DoctorsEntity entity = mapper.mapToEntityWithSpecializationAndOffices(doctor);


        boolean allSpecializationsAreInstanceOfSpecializationEntity = entity.getSpecializations()
                .stream()
                .allMatch(specialization -> specialization instanceof SpecializationEntity);

        boolean allAreInstanceOfOfficeEntity = entity.getOffices().stream()
                .allMatch(office -> office instanceof OfficeEntity);

        assertTrue(allAreInstanceOfOfficeEntity);
        assertEquals(doctor.getDoctorId(), entity.getDoctorId());
        assertEquals(doctor.getName(), entity.getName());
        assertEquals(doctor.getSurname(), entity.getSurname());
        assertEquals(doctor.getPesel(), entity.getPesel());
        assertEquals(doctor.getPhone(), entity.getPhone());
        assertTrue(allSpecializationsAreInstanceOfSpecializationEntity);

    }

    @Test
    public void testMapFromEntityWithSpecializationsAndOffices() {

        DoctorsEntity entity = EntityFixtures.doctor1();

        Doctors doctor = mapper.mapFromEntityWithSpecializationsAndOffices(entity);

        assertEquals(entity.getDoctorId(), doctor.getDoctorId());
        assertEquals(entity.getName(), doctor.getName());
        assertEquals(entity.getSurname(), doctor.getSurname());
        assertEquals(entity.getPesel(), doctor.getPesel());
        assertEquals(entity.getPhone(), doctor.getPhone());

        assertEquals(1, doctor.getSpecializations().size());

        assertEquals(1, doctor.getOffices().size());

        boolean allAreInstanceOfOfficeEntity = entity.getOffices().stream()
                .allMatch(office -> office instanceof OfficeEntity);


        assertTrue(allAreInstanceOfOfficeEntity);
    }
}
