package pl.clinic.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.clinic.business.dao.PatientsRepository;
import pl.clinic.domain.Patients;
import pl.clinic.domain.exception.NotFoundException;
import pl.clinic.util.services.DomainFixtures;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatientsServiceTest {

    @Mock
    private PatientsRepository patientsRepository;

    @InjectMocks
    private PatientsService patientsService;

    private final Patients patient1 = DomainFixtures.patient1();
    private final Patients patient2 = DomainFixtures.patient2();

    @Test
    public void testSearchPatientFound() {
        // Given
        String patientPesel = patient1.getPesel();

        when(patientsRepository.findPatientByPesel(patientPesel)).thenReturn(Optional.of(patient1));

        // When
        Patients result = patientsService.searchPatient(patientPesel);

        // Then
        assertNotNull(result);
        assertEquals(patient1, result);
    }

    @Test
    public void testSearchPatientNotFound() {
        // Given
        String patientPesel = "1234567890";

        when(patientsRepository.findPatientByPesel(patientPesel)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(NotFoundException.class, () -> patientsService.searchPatient(patientPesel));
    }

    @Test
    public void testGetPatientFound() {
        // Given
        int patientId = patient2.getPatientId();

        when(patientsRepository.findById(patientId)).thenReturn(Optional.of(patient2));

        // When
        Patients result = patientsService.getPatient(patientId);

        // Then
        assertNotNull(result);
        assertEquals(patient2, result);
    }

    @Test
    public void testGetPatientNotFound() {
        // Given
        int patientId = 3; // This patient ID doesn't exist in our test data

        when(patientsRepository.findById(patientId)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(NotFoundException.class, () -> patientsService.getPatient(patientId));
    }
}
