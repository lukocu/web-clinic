package pl.clinic.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.clinic.infrastructure.database.entity.UserEntity;
import pl.clinic.integration.configuration.PersistenceContainerTestConfiguration;
import pl.clinic.util.EntityFixtures;

import java.util.Optional;

import static org.junit.Assert.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserJpaRepositoryTest {

    private UserJpaRepository userJpaRepository;
    private RoleJpaRepository roleJpaRepository;

    @Test
    public void testFindByUsername() {

        UserEntity userEntity = EntityFixtures.doctorUser();
        String username = userEntity.getUsername();

        roleJpaRepository.saveAll(userEntity.getRoles());
        userJpaRepository.save(userEntity);


        Optional<UserEntity> foundUserOptional = userJpaRepository.findByUsername(username);


        assertTrue(foundUserOptional.isPresent());


        UserEntity foundUser = foundUserOptional.get();

        assertEquals(username, foundUser.getUsername());
    }

    @Test
    public void testFindByUsernameWhenNotFound() {
        String nonExistentUsername = "nonexistentuser";
        Optional<UserEntity> foundUserOptional = userJpaRepository.findByUsername(nonExistentUsername);

        assertFalse(foundUserOptional.isPresent());
    }


}
