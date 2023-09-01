package pl.clinic.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import pl.clinic.business.AppointmentsService;
import pl.clinic.business.OfficeService;

@Controller
@RequiredArgsConstructor
public class AppointmentController {
    public static final String APPOINTMENT = "/appointment";

    private AppointmentsService appointmentsService;
    private OfficeService officeService;
}
