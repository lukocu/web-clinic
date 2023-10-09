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
import org.mockito.MockitoAnnotations;

import org.mockito.junit.jupiter.MockitoExtension;
import pl.clinic.business.dao.OfficeRepository;
import pl.clinic.domain.Office;
import pl.clinic.infrastructure.database.entity.OfficeEntity;
import pl.clinic.infrastructure.database.repository.jpa.OfficeJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.OfficeEntityMapper;
import pl.clinic.util.DomainData;
import pl.clinic.util.EntityFixtures;

@ExtendWith(MockitoExtension.class)
public class OfficeRepositoryTest {

    @Mock
    private OfficeJpaRepository officeJpaRepository;

    @Mock
    private OfficeEntityMapper officeEntityMapper;

    @InjectMocks
    private OfficeRepository officeRepository;



    @Test
    public void testFindByDoctorId() {
        // given
        Integer doctorId = 1;
        Office office = DomainData.officeForDoctor1();
        OfficeEntity officeEntity = EntityFixtures.officeForDoctor1();

        when(officeJpaRepository.findAllByDoctorDoctorId(doctorId))
                .thenReturn(Collections.singletonList(officeEntity));


        when(officeEntityMapper.mapFromEntityWithoutDoctor(officeEntity))
                .thenReturn(office);

        // when
        List<Office> result = officeRepository.findByDoctorId(doctorId);

        // then
        assertEquals(1, result.size());
        assertEquals(office, result.get(0));
        Mockito.verify(officeEntityMapper).mapFromEntityWithoutDoctor(officeEntity);
    }
}
