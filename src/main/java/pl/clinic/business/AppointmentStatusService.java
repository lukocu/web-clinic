package pl.clinic.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.clinic.business.dao.AppointmentStatusRepository;
import pl.clinic.domain.AppointmentStatus;
import pl.clinic.domain.Status;
import pl.clinic.domain.exception.NotFoundException;

@Service
@AllArgsConstructor
public class AppointmentStatusService {

    private AppointmentStatusRepository appointmentStatusRepository;


    @Transactional
    public AppointmentStatus findByStatus(Status status) {
      return  appointmentStatusRepository.findByStatus(status)
              .orElseThrow(() -> new NotFoundException("Status not found"));
    }
}
