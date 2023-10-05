package pl.clinic.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.clinic.business.dao.PrescriptionsRepository;
import pl.clinic.domain.Prescriptions;
import pl.clinic.util.DomainData;

import java.time.OffsetDateTime;

import static org.mockito.Mockito.*;

public class PrescriptionsServiceTest {

    private PrescriptionsService prescriptionsService;

    @Mock
    private PrescriptionsRepository prescriptionsRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        prescriptionsService = new PrescriptionsService(prescriptionsRepository);
    }

    @Test
    public void testSavePrescription() {

        Prescriptions prescription = DomainData.prescription2();


        prescriptionsService.save(prescription);


        verify(prescriptionsRepository).save(prescription);
    }


}
