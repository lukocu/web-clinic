package pl.clinic.api.dto.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.clinic.api.dto.UserRegistrationDto;
import pl.clinic.api.dto.mapper.UserRegistrationMapper;
import pl.clinic.domain.Role;
import pl.clinic.domain.User;
import pl.clinic.util.DTOFixtures;
import pl.clinic.util.DomainData;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRegistrationMapperTest {

    private UserRegistrationMapper userRegistrationMapper;

    @BeforeEach
    public void setUp() {
        userRegistrationMapper = Mappers.getMapper(UserRegistrationMapper.class);
    }

    @Test
    public void testMapFromDto() {
        // Given
        UserRegistrationDto userRegistrationDto = DTOFixtures.user1();

        // When
        User user = userRegistrationMapper.mapFromDto(userRegistrationDto);

        // Then
        assertEquals(userRegistrationDto.getUsername(), user.getUsername());
        assertEquals(userRegistrationDto.getEmail(), user.getEmail());
        assertEquals(userRegistrationDto.getPassword(), user.getPassword());
        assertEquals(true, user.getActive());
        Set<String> roles = user.getRoles().stream().map(Role::getRole).collect(Collectors.toSet());
        assertEquals(userRegistrationDto.getRole(), roles.iterator().next());
    }

    @Test
    public void testMapToDto() {
        // Given
        User user = DomainData.user2();

        // When
        UserRegistrationDto userRegistrationDto = userRegistrationMapper.mapToDto(user);
        String roles = user.getRoles().stream()
                .map(Role::getRole)
                .collect(Collectors.joining(","));
        // Then
        assertEquals(user.getUsername(), userRegistrationDto.getUsername());
        assertEquals(user.getEmail(), userRegistrationDto.getEmail());
        assertEquals(user.getActive(), userRegistrationDto.getActive());
        assertEquals(roles, userRegistrationDto.getRole());


    }
}
