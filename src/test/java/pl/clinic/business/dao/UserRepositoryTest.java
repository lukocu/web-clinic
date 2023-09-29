package pl.clinic.business.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.jupiter.MockitoExtension;
import pl.clinic.business.dao.UserRepository;
import pl.clinic.domain.User;
import pl.clinic.infrastructure.database.entity.UserEntity;
import pl.clinic.infrastructure.database.repository.jpa.UserJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.UserEntityMapper;
import pl.clinic.util.DomainData;
import pl.clinic.util.EntityFixtures;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserJpaRepository userJpaRepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    @InjectMocks
    private UserRepository userRepository;

    @Test
    public void testFindAll() {
        // given
        User user = DomainData.user1();
        UserEntity userEntity = EntityFixtures.userEntity1();
        when(userEntityMapper.mapFromEntity(userEntity))
                .thenReturn(user);
        when(userJpaRepository.findAll())
                .thenReturn(Collections.singletonList(userEntity));

        // when
        List<User> result = userRepository.findAll();

        // then
        assertEquals(1, result.size());
        assertEquals(user, result.get(0));
        verify(userEntityMapper).mapFromEntity(userEntity);
    }

    @Test
    public void testFindByUsernameForPatient() {
        // given
        String username = "patient1";
        User user = DomainData.user5();
        UserEntity userEntity = EntityFixtures.userEntity5();
        when(userEntityMapper.mapFromEntityForPatient(userEntity))
                .thenReturn(user);
        when(userJpaRepository.findByUsername(username))
                .thenReturn(Optional.of(userEntity));

        // when
        Optional<User> result = userRepository.findByUsernameForPatient(username);

        // then
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userEntityMapper).mapFromEntityForPatient(userEntity);
    }

    @Test
    public void testFindByUsernameDoctor() {
        // given
        String username = "doctor1";
        User user = DomainData.user2();
        UserEntity userEntity = EntityFixtures.userEntity2();
        when(userEntityMapper.mapFromEntityForDoctor(userEntity))
                .thenReturn(user);
        when(userJpaRepository.findByUsername(username))
                .thenReturn(Optional.of(userEntity));

        // when
        Optional<User> result = userRepository.findByUsernameDoctor(username);

        // then
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userEntityMapper).mapFromEntityForDoctor(userEntity);
    }
}
