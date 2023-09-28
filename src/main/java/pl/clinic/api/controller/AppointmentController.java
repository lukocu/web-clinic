package pl.clinic.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.clinic.api.dto.OfficeDoctorAvailabilityDTO;
import pl.clinic.api.dto.mapper.OfficeDoctorAvailabilityMapper;
import pl.clinic.business.OfficeDoctorAvailabilityService;
import pl.clinic.business.UserService;
import pl.clinic.security.IAuthenticationFacade;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@AllArgsConstructor
public class AppointmentController {

    public static final String APPOINTMENT = "/appointment";
    public static final String APPOINTMENT_LIST = "/appointment/{officeId}";

    public static final String APPOINTMENT_BOOK = "/appointment/{officeId}/book";

    private OfficeDoctorAvailabilityService officeDoctorAvailabilityService;
    private OfficeDoctorAvailabilityMapper officeDoctorAvailabilityMapper;
    private UserService userService;

    private IAuthenticationFacade authenticationFacade;

    @GetMapping(value = APPOINTMENT_LIST)
    public String showAvailableList(@RequestParam("officeId") Integer officeId, Model model) {
        List<OfficeDoctorAvailabilityDTO> availabilityDTOs = officeDoctorAvailabilityService.getAvailableHoursForOffice(officeId).stream()
                .map(a -> officeDoctorAvailabilityMapper.mapToDtoWithOfficeId(a))
                .toList();

        model.addAttribute("availableList", availabilityDTOs);

        return "appointment";
    }

    @PostMapping(value = APPOINTMENT_BOOK)
    public String reserveAppointment(
            @PathVariable Integer officeId,
            @RequestParam("officeAvailabilityId") Integer officeAvailabilityId,
            @RequestParam("date") LocalDate date,
            @RequestParam("startTime") LocalTime startTime,
            @RequestParam("endTime") LocalTime endTime,
            @RequestParam("availabilityStatus") Boolean availabilityStatus,
            @ModelAttribute("availability") OfficeDoctorAvailabilityDTO availability,
            RedirectAttributes redirectAttributes) {

        Authentication authentication = authenticationFacade.getAuthentication();


        if (authentication.getPrincipal() instanceof UserDetails userDetails) {

            var user = userService.findByUsernameForPatient(userDetails.getUsername());


            officeDoctorAvailabilityService.reservedAppointment(officeAvailabilityId, user.getPatient());

        }
        redirectAttributes.addFlashAttribute("availability", availability);
        return "redirect:/appointment/success";

    }

    @GetMapping("/appointment/success")
    public String showSuccessPage(@ModelAttribute("availability") OfficeDoctorAvailabilityDTO availability, Model model) {

        model.addAttribute("availability", availability);
        return "success";
    }

    @GetMapping(value = APPOINTMENT)
    public String showVisitSite() {
        return "appointment";
    }

}
