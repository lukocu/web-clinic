package pl.clinic.api.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.clinic.api.dto.mapper.OfficeDoctorAvailabilityMapper;
import pl.clinic.business.AppointmentsService;
import pl.clinic.business.OfficeDoctorAvailabilityService;
import pl.clinic.business.OfficeService;

@Controller
@AllArgsConstructor
public class AppointmentController {
    public static final String APPOINTMENT = "/appointment";

    private AppointmentsService appointmentsService;
    private OfficeService officeService;
   private OfficeDoctorAvailabilityService officeDoctorAvailabilityService;
   private OfficeDoctorAvailabilityMapper officeDoctorAvailabilityMapper;
    @GetMapping(APPOINTMENT+"/check/{officeNumber}")
    public String listOfAvailablePage(@PathVariable("officeNumber") Integer officeNumber, Model model){

        var officeAvailable = officeDoctorAvailabilityService.getAvailableHoursForOffice(officeNumber).stream()
                .map(officeDoctorAvailabilityMapper::mapToDtoWithOfficeId)
                .toList();

        model.addAttribute("officeAvailableDTO",officeAvailable);

        model.addAttribute("showAvailabilityTable", true);

        return "appointment";
    }
}
//TODO