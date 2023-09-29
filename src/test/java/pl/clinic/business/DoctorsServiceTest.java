package pl.clinic.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.clinic.business.dao.DoctorsRepository;
import pl.clinic.domain.Doctors;
import pl.clinic.domain.exception.NotFoundException;
import pl.clinic.util.services.DomainFixtures;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class DoctorsServiceTest {

    @InjectMocks
    private DoctorsService doctorsService;
    @Mock
    private DoctorsRepository doctorsRepository;


    @Test
    public void GetDoctorsAndOfficeTest() {
        // given
        Doctors doctor1 = DomainFixtures.doctor1()
                .withOffices(Set.of(DomainFixtures.office1()));

        Doctors doctor2 = DomainFixtures.doctor2()
                .withOffices(Set.of(DomainFixtures.office2()));

        List<Doctors> expectedDoctors = List.of(doctor1, doctor2);


        when(doctorsRepository.findDoctorsAndOffice()).thenReturn(expectedDoctors);

        // when
        List<Doctors> doctors = doctorsService.getDoctorsAndOffice();

        // then
        assertEquals(expectedDoctors, doctors);
        verify(doctorsRepository, times(1)).findDoctorsAndOffice();
    }

    @Test
    public void FindByUserIdTest() {
        // given
        Doctors expectedDoctor = DomainFixtures.doctor1()
                .withUser(DomainFixtures.doctorUser());


        when(doctorsRepository.findByUserId(expectedDoctor.getUser().getUserId())).thenReturn(Optional.of(expectedDoctor));

    //when
        Doctors doctor = doctorsService.findByUserId(expectedDoctor.getUser().getUserId());

        // then
        assertEquals(expectedDoctor, doctor);


        verify(doctorsRepository, times(1)).findByUserId(expectedDoctor.getUser().getUserId());
    }

    @Test
    public void FindByUserIdNotFoundTest() {
        //given
        Integer userId = 1;

        when(doctorsRepository.findByUserId(userId)).thenReturn(Optional.empty());

    //when, then
        assertThrows(NotFoundException.class, () -> {
            doctorsService.findByUserId(userId);
        });
        verify(doctorsRepository, times(1)).findByUserId(userId);
    }
}
