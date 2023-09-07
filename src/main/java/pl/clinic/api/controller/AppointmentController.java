package pl.clinic.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.clinic.api.dto.OfficeDoctorAvailabilityDTO;
import pl.clinic.api.dto.mapper.OfficeDoctorAvailabilityMapper;
import pl.clinic.business.OfficeDoctorAvailabilityService;

import java.util.List;

@Controller
@AllArgsConstructor
public class AppointmentController {

    public static final String APPOINTMENT = "/appointment";
    public static final String APPOINTMENT_LIST = "/appointment/{officeId}";

    public static final String APPOINTMENT_ADD = "{/appointment/{officeId}/add";

    private OfficeDoctorAvailabilityService officeDoctorAvailabilityService;
    private OfficeDoctorAvailabilityMapper officeDoctorAvailabilityMapper;

    @GetMapping(value = APPOINTMENT_LIST)
    public String showAvailableList(@RequestParam("officeId") Integer officeId, Model model) {
        List<OfficeDoctorAvailabilityDTO> availabilityDTOs = officeDoctorAvailabilityService.getAvailableHoursForOffice(officeId).stream()
                .map(a -> officeDoctorAvailabilityMapper.mapToDtoWithOfficeId(a))
                .toList();

        model.addAttribute("availableList", availabilityDTOs);

        return "appointment";
    }

    @PostMapping(value = APPOINTMENT_ADD)
    public String reserveAppointment(
            @PathVariable Integer officeId,
            @ModelAttribute("availability") OfficeDoctorAvailabilityDTO availability,
            RedirectAttributes redirectAttributes) {

        Integer officeAvailabilityId = availability.getOfficeAvailabilityId();

        // Przykład:
        officeDoctorAvailabilityService.reserveOfficeAvailability(officeAvailabilityId);

        redirectAttributes.addFlashAttribute("availability", availability);
        return "redirect:/success";
    }

    @GetMapping("/success")
    public String showSuccessPage(@ModelAttribute("availability") OfficeDoctorAvailabilityDTO availability, Model model) {

        model.addAttribute("availability", availability);
        return "success";
    }

    @GetMapping(value = APPOINTMENT)
    public String showVisitSite() {
        return "appointment";
    }

}
//TODO W APPOINTMENT POPRAW PRZYCISK I DODAJ TUTAJ @POST Noi obejrzyj bykowskiego