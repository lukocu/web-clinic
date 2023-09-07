package pl.clinic.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.clinic.business.dao.AppointmentStatusRepository;
import pl.clinic.domain.AppointmentStatus;
import pl.clinic.domain.Status;

@Service
@AllArgsConstructor
public class AppointmentStatusService {

    private AppointmentStatusRepository appointmentStatusRepository;

    @Transactional
    public AppointmentStatus createAppointmentStatus() {

        AppointmentStatus newStatus = AppointmentStatus.builder()
                .status(Status.Scheduled)
                .build();

        return newStatus;
    }

}
