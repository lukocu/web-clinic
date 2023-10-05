package pl.clinic.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.clinic.business.dao.DoctorsRepository;
import pl.clinic.business.dao.OfficeDoctorAvailabilityRepository;
import pl.clinic.business.dao.OfficeRepository;
import pl.clinic.domain.Doctors;
import pl.clinic.domain.Office;
import pl.clinic.domain.exception.NotFoundException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class OfficeService {

    private OfficeRepository officeRepository;

    @Transactional
    public Office getOffice(Integer officeId) {
        return officeRepository.findById(officeId)
                .orElseThrow(() -> new NotFoundException("Office not found"));

    }


    @Transactional
    public List<Office> getOffices(Integer doctorId) {
        return officeRepository.findByDoctorId(doctorId);
    }



}