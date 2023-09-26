package pl.clinic.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.clinic.business.dao.OfficeRepository;
import pl.clinic.domain.Office;
import pl.clinic.domain.exception.NotFoundException;
import pl.clinic.util.DomainFixtures;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OfficeServiceTest {

    @Mock
    private OfficeRepository officeRepository;

    @InjectMocks
    private OfficeService officeService;

    @Test
    public void testGetOfficeFound() {
        // Given
        Integer officeId = 1;
        Office mockOffice = DomainFixtures.office1();

        when(officeRepository.findById(officeId)).thenReturn(Optional.of(mockOffice));

        // When
        Office result = officeService.getOffice(officeId);

        // Then
        assertEquals(mockOffice, result);
    }

    @Test
    public void testGetOfficeNotFound() {
        // Given
        Integer officeId = 2;
        when(officeRepository.findById(officeId)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(NotFoundException.class, () -> officeService.getOffice(officeId));
    }


}
