package pl.clinic.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pl.clinic.api.dto.OfficeDoctorAvailabilityDTO;
import pl.clinic.api.dto.mapper.OfficeDoctorAvailabilityMapper;
import pl.clinic.business.OfficeDoctorAvailabilityService;

import java.util.List;

@Controller
@AllArgsConstructor
public class AppointmentController {

    public static final String APPOINTMENT = "/appointment";
    public static final String APPOINTMENT_LIST = "/appointment/{officeId}";

    private OfficeDoctorAvailabilityService officeDoctorAvailabilityService;
    private OfficeDoctorAvailabilityMapper officeDoctorAvailabilityMapper;
    @GetMapping(value = APPOINTMENT_LIST)
    public String showAvailableList(@RequestParam("officeId") Integer officeId, Model model){
        List<OfficeDoctorAvailabilityDTO> availabilityDTOs = officeDoctorAvailabilityService.getAvailableHoursForOffice(officeId).stream()
                .map(a -> officeDoctorAvailabilityMapper.mapToDtoWithOfficeId(a))
                .toList();

        model.addAttribute("availableList",availabilityDTOs);

        return "appointment";
    }


    @GetMapping(value = APPOINTMENT)
    public String showVisitSite() {
        return "appointment";
    }

}
