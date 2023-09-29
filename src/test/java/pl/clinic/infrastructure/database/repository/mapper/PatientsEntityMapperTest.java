package pl.clinic.infrastructure.database.repository.mapper;

import org.junit.jupiter.api.Test;
import pl.clinic.domain.Patients;
import pl.clinic.infrastructure.database.entity.PatientsEntity;
import pl.clinic.util.DomainData;
import pl.clinic.util.EntityFixtures;

import static org.junit.jupiter.api.Assertions.*;

public class PatientsEntityMapperTest {

    @Test
    public void testMapFromEntity() {

        PatientsEntity entity = EntityFixtures.patient1();


        Patients result = PatientsEntityMapper.INSTANCE.mapFromEntity(entity);


        assertEquals(entity.getPatientId(), result.getPatientId());
        assertEquals(entity.getName(), result.getName());
        assertEquals(entity.getSurname(), result.getSurname());
        assertEquals(entity.getPesel(), result.getPesel());
        assertEquals(entity.getBirthDate(), result.getBirthDate());
        assertEquals(entity.getAddress(), result.getAddress());
        assertEquals(entity.getPhone(), result.getPhone());
    }

    @Test
    public void testMapFromEntityWithUser() {

        PatientsEntity entity = EntityFixtures.patient2();


        Patients result = PatientsEntityMapper.INSTANCE.mapFromEntityWithUser(entity);


        assertEquals(entity.getPatientId(), result.getPatientId());
        assertEquals(entity.getName(), result.getName());
        assertEquals(entity.getSurname(), result.getSurname());
        assertEquals(entity.getPesel(), result.getPesel());
        assertEquals(entity.getBirthDate(), result.getBirthDate());
        assertEquals(entity.getAddress(), result.getAddress());
        assertEquals(entity.getPhone(), result.getPhone());
        assertNotNull(result.getUser());
    }

    @Test
    public void testMapToEntity() {

        Patients patients = DomainData.patient1();


        PatientsEntity result = PatientsEntityMapper.INSTANCE.mapToEntity(patients);


        assertEquals(patients.getPatientId(), result.getPatientId());
        assertEquals(patients.getName(), result.getName());
        assertEquals(patients.getSurname(), result.getSurname());
        assertEquals(patients.getPesel(), result.getPesel());
        assertEquals(patients.getBirthDate(), result.getBirthDate());
        assertEquals(patients.getAddress(), result.getAddress());
        assertEquals(patients.getPhone(), result.getPhone());
    }

    @Test
    public void testMapToEntityWithUser() {

        Patients patients = DomainData.patient2();


        PatientsEntity result = PatientsEntityMapper.INSTANCE.mapToEntityWithUser(patients);


        assertEquals(patients.getName(), result.getName());
        assertEquals(patients.getSurname(), result.getSurname());
        assertEquals(patients.getPesel(), result.getPesel());
        assertEquals(patients.getBirthDate(), result.getBirthDate());
        assertEquals(patients.getAddress(), result.getAddress());
        assertEquals(patients.getPhone(), result.getPhone());
        assertNotNull(result.getUser());
    }
}
