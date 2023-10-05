package pl.clinic.business.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;
import pl.clinic.domain.Doctors;
import pl.clinic.infrastructure.database.entity.DoctorsEntity;
import pl.clinic.infrastructure.database.repository.jpa.DoctorsJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.DoctorsEntityMapper;
import pl.clinic.util.DomainData;
import pl.clinic.util.EntityFixtures;

@ExtendWith(MockitoExtension.class)
public class DoctorsRepositoryTest {

    @Mock
    private DoctorsJpaRepository doctorsJpaRepository;

    @Mock
    private DoctorsEntityMapper doctorsEntityMapper;

    @InjectMocks
    private DoctorsRepository doctorsRepository;

    @Test
    public void testFindDoctorsAndOffice() {
        // given
        Doctors doctors = DomainData.doctor1();
        DoctorsEntity doctorsEntity = EntityFixtures.doctor1();

        List<DoctorsEntity> doctorsEntities = Collections.singletonList(doctorsEntity);
        when(doctorsJpaRepository.findDoctorsAndOffice()).thenReturn(doctorsEntities);


        when(doctorsEntityMapper.mapFromEntityWithSpecializationsAndOffices(any(DoctorsEntity.class))).thenReturn(doctors);

        // when
        List<Doctors> result = doctorsRepository.findDoctorsAndOffice();

        // then
        assertEquals(1, result.size());
        assertEquals(doctors, result.get(0));
        Mockito.verify(doctorsEntityMapper).mapFromEntityWithSpecializationsAndOffices(doctorsEntity);
    }

    @Test
    public void testFindByUserId() {
        // given
        Integer userId = 1;
        Doctors doctors = DomainData.doctor1();
        DoctorsEntity doctorsEntity = EntityFixtures.doctor1();

        when(doctorsJpaRepository.findByUserId(userId)).thenReturn(Optional.of(doctorsEntity));


        when(doctorsEntityMapper.mapFromEntityWithFields(any(DoctorsEntity.class))).thenReturn(doctors);

        // when
        Optional<Doctors> result = doctorsRepository.findByUserId(userId);

        // then
        assertTrue(result.isPresent());
        assertEquals(doctors, result.get());
        Mockito.verify(doctorsEntityMapper).mapFromEntityWithFields(doctorsEntity);
    }
}
