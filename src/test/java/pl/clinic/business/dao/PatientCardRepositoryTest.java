package pl.clinic.business.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.jupiter.MockitoExtension;
import pl.clinic.business.dao.PatientCardRepository;
import pl.clinic.domain.PatientCard;
import pl.clinic.infrastructure.database.entity.PatientCardEntity;
import pl.clinic.infrastructure.database.repository.jpa.PatientCardJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.PatientCardEntityMapper;
import pl.clinic.util.DomainData;
import pl.clinic.util.EntityFixtures;

@ExtendWith(MockitoExtension.class)
public class PatientCardRepositoryTest {

    @Mock
    private PatientCardJpaRepository patientCardJpaRepository;

    @Mock
    private PatientCardEntityMapper patientCardEntityMapper;

    @InjectMocks
    private PatientCardRepository patientCardRepository;

    @Test
    public void testSave() {
        // given
        PatientCard patientCard = DomainData.patientCard1();


        // when
        patientCardRepository.save(patientCard);

        // then
        verify(patientCardJpaRepository).save(patientCardEntityMapper.mapToEntityWithFields(patientCard));
    }

    @Test
    public void testFindPatientCardByPatientId() {
        // given
        Integer patientId = 1;
        PatientCard patientCard = DomainData.patientCard1();
        PatientCardEntity patientCardEntity = EntityFixtures.patientCardEntity1();

        when(patientCardJpaRepository.findByPatientIdWithDetails(patientId))
                .thenReturn(Collections.singletonList(patientCardEntity));


        when(patientCardEntityMapper.mapFromEntityWithFields(patientCardEntity))
                .thenReturn(patientCard);

        // when
        List<PatientCard> result = patientCardRepository.findPatientCardByPatientId(patientId);

        // then
        assertEquals(1, result.size());
        assertEquals(patientCard, result.get(0));
        Mockito.verify(patientCardEntityMapper).mapFromEntityWithFields(patientCardEntity);
    }
}
