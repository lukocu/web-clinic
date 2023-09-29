import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.jupiter.MockitoExtension;
import pl.clinic.business.dao.PrescriptionsRepository;
import pl.clinic.domain.Prescriptions;
import pl.clinic.infrastructure.database.entity.PrescriptionsEntity;
import pl.clinic.infrastructure.database.repository.jpa.PrescriptionsJpaRepository;
import pl.clinic.infrastructure.database.repository.mapper.PrescriptionsEntityMapper;
import pl.clinic.util.DomainData;
import pl.clinic.util.EntityFixtures;

@ExtendWith(MockitoExtension.class)
public class PrescriptionsRepositoryTest {

    @Mock
    private PrescriptionsJpaRepository prescriptionsJpaRepository;

    @Mock
    private PrescriptionsEntityMapper prescriptionsEntityMapper;

    @InjectMocks
    private PrescriptionsRepository prescriptionsRepository;

    @Test
    public void testSave() {
        // given
        Prescriptions prescription = DomainData.prescription1();
        PrescriptionsEntity prescriptionsEntity = EntityFixtures.prescription1();
        when(prescriptionsEntityMapper.mapToEntity(prescription))
                .thenReturn(prescriptionsEntity);

        // when
        prescriptionsRepository.save(prescription);

        // then
        verify(prescriptionsJpaRepository).save(prescriptionsEntity);
        verify(prescriptionsEntityMapper).mapToEntity(prescription);
    }
}
