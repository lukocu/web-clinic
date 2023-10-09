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
import pl.clinic.business.dao.PatientsRepository;
import pl.clinic.domain.Patients;
import pl.clinic.infrastructure.database.entity.PatientsEntity;
import pl.clinic.infrastructure.database.repository.jpa.PatientsJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.PatientsEntityMapper;
import pl.clinic.util.DomainData;
import pl.clinic.util.EntityFixtures;

@ExtendWith(MockitoExtension.class)
public class PatientsRepositoryTest {

    @Mock
    private PatientsJpaRepository patientsJpaRepository;

    @Mock
    private PatientsEntityMapper patientsEntityMapper;

    @InjectMocks
    private PatientsRepository patientsRepository;

    @Test
    public void testFindPatientByPesel() {
        // given
        String pesel = "95011257943";
        Patients patient = DomainData.patient1();
        PatientsEntity patientEntity = EntityFixtures.patient1();

        when(patientsJpaRepository.findByPesel(pesel))
                .thenReturn(Optional.of(patientEntity));


        when(patientsEntityMapper.mapFromEntity(patientEntity))
                .thenReturn(patient);

        // when
        Optional<Patients> result = patientsRepository.findPatientByPesel(pesel);

        // then
        assertTrue(result.isPresent());
        assertEquals(patient, result.get());
        Mockito.verify(patientsEntityMapper).mapFromEntity(patientEntity);
    }

    @Test
    public void testSave() {
        // given
        Patients patient = DomainData.patient1();
        PatientsEntity patientEntity = EntityFixtures.patient1();

        when(patientsEntityMapper.mapToEntityWithUser(patient))
                .thenReturn(patientEntity);

        when(patientsJpaRepository.save(patientEntity))
                .thenReturn(patientEntity);

        when(patientsEntityMapper.mapFromEntityWithUser(patientEntity))
                .thenReturn(patient);

        // when
        Patients savedPatient = patientsRepository.save(patient);

        // then
        assertEquals(patient, savedPatient);
        Mockito.verify(patientsEntityMapper).mapToEntityWithUser(patient);
        Mockito.verify(patientsJpaRepository).save(patientEntity);
        Mockito.verify(patientsEntityMapper).mapFromEntityWithUser(patientEntity);
    }


}
