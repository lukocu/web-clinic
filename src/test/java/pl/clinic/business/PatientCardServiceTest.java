package pl.clinic.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.clinic.business.dao.PatientCardRepository;
import pl.clinic.domain.PatientCard;
import pl.clinic.util.services.DomainFixtures;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientCardServiceTest {

    @Mock
    private PatientCardRepository patientCardRepository;

    @Mock
    private PrescriptionsService prescriptionsService;

    @InjectMocks
    private PatientCardService patientCardService;

    @Test
    public void testAddPatientCardEntry() {
        // Given
        PatientCard patientCard = DomainFixtures.patientCard1();

        // When
        patientCardService.addPatientCardEntry(patientCard);

        // Then
        verify(patientCardRepository, times(1)).save(patientCard);
    }

    @Test
    public void testGetPatientCards() {

        Integer patientId = 1;


        PatientCard patientCard1 = DomainFixtures.patientCard1();
        PatientCard patientCard2 = DomainFixtures.patientCard2();


        List<PatientCard> patientCards = new ArrayList<>();
        patientCards.add(patientCard1);
        patientCards.add(patientCard2);
        when(patientCardRepository.findPatientCardByPatientId(patientId)).thenReturn(patientCards);

        // When
        List<PatientCard> result = patientCardService.getPatientCards(patientId);

        // Then
        verify(patientCardRepository, times(1)).findPatientCardByPatientId(patientId);


        Assertions.assertEquals(2, result.size());
    }

}


