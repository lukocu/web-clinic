package pl.clinic.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.clinic.business.dao.MedicationsRepository;
import pl.clinic.domain.Medications;
import pl.clinic.util.DomainFixtures;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class MedicationsServiceTest {
    @InjectMocks
    private MedicationsService medicationsService;

    @Mock
    private MedicationsRepository medicationsRepository;

    @Test
    public void SaveNewMedicationsTest() {
        // Given
        Set<Medications> medicationsSet = new HashSet<>();
        Medications medication1 = DomainFixtures.createSampleMedication1();
        Medications medication2 = DomainFixtures.createSampleMedication2();
        medicationsSet.add(medication1);
        medicationsSet.add(medication2);

        when(medicationsRepository.findByName(medication1.getMedicationName())).thenReturn(Optional.empty());
        when(medicationsRepository.findByName(medication2.getMedicationName())).thenReturn(Optional.empty());

        // When
        medicationsService.save(medicationsSet);

        // Then
        verify(medicationsRepository, times(2)).save(any(Medications.class));
    }

    @Test
    public void SaveExistingMedicationsTest() {
        // Given
        Set<Medications> medicationsSet = new HashSet<>();
        Medications medication1 = DomainFixtures.createSampleMedication1();
        Medications medication2 = DomainFixtures.createSampleMedication2();
        medicationsSet.add(medication1);
        medicationsSet.add(medication2);


        when(medicationsRepository.findByName(medication1.getMedicationName())).thenReturn(Optional.of(medication1));
        when(medicationsRepository.findByName(medication2.getMedicationName())).thenReturn(Optional.of(medication2));

        // When
        medicationsService.save(medicationsSet);

        // Then
        verify(medicationsRepository, never()).save(any(Medications.class));
    }

    @Test
    public void SaveMixedMedicationsTest() {
        // Given
        Set<Medications> medicationsSet = new HashSet<>();
        Medications medication1 = DomainFixtures.createSampleMedication1();;
        Medications medication2 = DomainFixtures.createSampleMedication2();;
        medicationsSet.add(medication1);
        medicationsSet.add(medication2);


        when(medicationsRepository.findByName(medication1.getMedicationName())).thenReturn(Optional.of(medication1));
        when(medicationsRepository.findByName(medication2.getMedicationName())).thenReturn(Optional.empty());

        // When
        medicationsService.save(medicationsSet);

        // Then
        verify(medicationsRepository, times(1)).save(any(Medications.class));
    }
}
