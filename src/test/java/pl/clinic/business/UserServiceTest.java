package pl.clinic.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.clinic.business.dao.UserRepository;
import pl.clinic.domain.User;
import pl.clinic.domain.exception.NotFoundException;
import pl.clinic.util.DomainFixtures;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private final User doctorUser = DomainFixtures.doctorUser();
    private final User patientUser = DomainFixtures.patientUser();



    @Test
    public void testFindAllUsers() {
        // Given
        List<User> userList = Arrays.asList(doctorUser, patientUser);

        when(userRepository.findAll()).thenReturn(userList);

        // When
        List<User> result = userService.findAllUsers();

        // Then
        assertNotNull(result);
        assertEquals(userList.size(), result.size());
        assertTrue(result.contains(doctorUser));
        assertTrue(result.contains(patientUser));
    }

    @Test
    public void testFindByUsernameDoctor() {
        // Given
        String username = doctorUser.getUsername();

        when(userRepository.findByUsernameDoctor(username)).thenReturn(Optional.of(doctorUser));

        // When
        User result = userService.findByUsernameDoctor(username);

        // Then
        assertNotNull(result);
        assertEquals(doctorUser, result);
    }

    @Test
    public void testFindByUsernameDoctorNotFound() {
        // Given
        String username = "nonexistent_doctor";

        when(userRepository.findByUsernameDoctor(username)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(NotFoundException.class, () -> userService.findByUsernameDoctor(username));
    }

    @Test
    public void testFindByUsername() {
        // Given
        String username = patientUser.getUsername();

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(patientUser));

        // When
        User result = userService.findByUsername(username);

        // Then
        assertNotNull(result);
        assertEquals(patientUser, result);
    }

    @Test
    public void testFindByUsernameNotFound() {
        // Given
        String username = "nonexistent_user";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(NotFoundException.class, () -> userService.findByUsername(username));
    }
}
