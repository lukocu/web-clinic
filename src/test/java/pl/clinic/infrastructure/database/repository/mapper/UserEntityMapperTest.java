package pl.clinic.infrastructure.database.repository.mapper;

import org.junit.jupiter.api.Test;
import pl.clinic.domain.User;
import pl.clinic.infrastructure.database.entity.UserEntity;
import pl.clinic.util.DomainData;
import pl.clinic.util.EntityFixtures;

import static org.junit.jupiter.api.Assertions.*;

public class UserEntityMapperTest {

    @Test
    public void testMapToEntityWithNewPatient() {


        User user = DomainData.user3().withPatient(DomainData.patient1());

        UserEntity result = UserEntityMapper.INSTANCE.mapToEntityWithNewPatient(user);


        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getPassword(), result.getPassword());
        assertEquals(user.getActive(), result.getActive());
        assertNotNull(result.getPatient());
        assertNull(result.getDoctors());
        assertNotNull(result.getRoles());
    }

    @Test
    public void testMapFromEntity() {

        UserEntity entity = EntityFixtures.userEntity2();
        entity.setDoctors(null);

        User result = UserEntityMapper.INSTANCE.mapFromEntity(entity);


        assertEquals(entity.getUserId(), result.getUserId());
        assertEquals(entity.getUsername(), result.getUsername());
        assertEquals(entity.getEmail(), result.getEmail());
        assertEquals(entity.getPassword(), result.getPassword());
        assertEquals(entity.getActive(), result.getActive());
        assertNotNull(result.getRoles());
        assertNull(result.getDoctors());
        assertNull(result.getPatient());
    }

    @Test
    public void testMapFromEntityForDoctor() {

        UserEntity entity = EntityFixtures.userEntity3();
        entity.setDoctors(EntityFixtures.doctor1());

        User result = UserEntityMapper.INSTANCE.mapFromEntityForDoctor(entity);


        assertEquals(entity.getUserId(), result.getUserId());
        assertEquals(entity.getUsername(), result.getUsername());
        assertEquals(entity.getEmail(), result.getEmail());
        assertEquals(entity.getPassword(), result.getPassword());
        assertEquals(entity.getActive(), result.getActive());
        assertNotNull(result.getRoles());
        assertNotNull(result.getDoctors());
        assertNull(result.getPatient());
    }

    @Test
    public void testMapFromEntityForPatient() {

        UserEntity entity = EntityFixtures.userEntity3();
        entity.setPatient(EntityFixtures.patient1());

        User result = UserEntityMapper.INSTANCE.mapFromEntityForPatient(entity);


        assertEquals(entity.getUserId(), result.getUserId());
        assertEquals(entity.getUsername(), result.getUsername());
        assertEquals(entity.getEmail(), result.getEmail());
        assertEquals(entity.getPassword(), result.getPassword());
        assertEquals(entity.getActive(), result.getActive());
        assertNotNull(result.getRoles());
        assertNull(result.getDoctors());
        assertNotNull(result.getPatient());
    }

    @Test
    public void testMapToEntityForPatient() {

        User user = DomainData.user5();

        UserEntity result = UserEntityMapper.INSTANCE.MapToEntityForPatient(user);

        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getPassword(), result.getPassword());
        assertEquals(user.getActive(), result.getActive());
        assertNull(result.getDoctors());
        assertNull(result.getPatient());
        assertNotNull(result.getRoles());
    }
}
