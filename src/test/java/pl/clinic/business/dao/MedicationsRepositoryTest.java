package pl.clinic.business.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.jupiter.MockitoExtension;
import pl.clinic.business.dao.MedicationsRepository;
import pl.clinic.domain.Medications;
import pl.clinic.infrastructure.database.entity.MedicationsEntity;
import pl.clinic.infrastructure.database.repository.jpa.MedicationsJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.MedicationsEntityMapper;
import pl.clinic.util.DomainData;
import pl.clinic.util.EntityFixtures;

@ExtendWith(MockitoExtension.class)
public class MedicationsRepositoryTest {

    @Mock
    private MedicationsJpaRepository medicationsJpaRepository;

    @Mock
    private MedicationsEntityMapper medicationsEntityMapper;

    @InjectMocks
    private MedicationsRepository medicationsRepository;

    @Test
    public void testSave() {
        // given
        Medications medication = DomainData.ibuprofenMedications();


        // when
        medicationsRepository.save(medication);

        // then
        verify(medicationsJpaRepository).save(medicationsEntityMapper.mapToEntity(medication));
    }

    @Test
    public void testFindByName() {
        // given
        String medicationName = "Example Medication";
        Medications medication = DomainData.aspirinMedications();
        MedicationsEntity medicationEntity = EntityFixtures.aspirinMedicationsEntity();

        when(medicationsJpaRepository.findByMedicationName(medicationName)).thenReturn(Optional.of(medicationEntity));


        when(medicationsEntityMapper.mapFromEntity(any(MedicationsEntity.class))).thenReturn(medication);

        // when
        Optional<Medications> result = medicationsRepository.findByName(medicationName);

        // then
        assertTrue(result.isPresent());
        assertEquals(medication, result.get());
        Mockito.verify(medicationsEntityMapper).mapFromEntity(medicationEntity);
    }
}
