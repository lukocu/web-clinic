package pl.clinic.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import pl.clinic.api.dto.AppointmentsDTO;
import pl.clinic.api.dto.PatientsDTO;
import pl.clinic.api.dto.mapper.AppointmentsMapper;
import pl.clinic.api.dto.mapper.PatientsMapper;
import pl.clinic.business.AppointmentsService;
import pl.clinic.business.UserService;
import pl.clinic.domain.User;
import pl.clinic.security.IAuthenticationFacade;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    public static final String PATIENT = "/patient_dashboard";
    public static final String PATIENT_CANCEL = "/patient_dashboard/cancel-visit/{appointmentId}";
    public static final String APPOINTMENT_HISTORY = "/patient_dashboard/appointment_history";

    private UserService userService;
    private IAuthenticationFacade authenticationFacade;
    private AppointmentsService appointmentsService;
    private PatientsMapper patientsMapper;
    private AppointmentsMapper appointmentsMapper;

    @GetMapping(value = PATIENT)
    public String getPatientPage(Model model) {

        Authentication authentication = authenticationFacade.getAuthentication();


        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {

            String username = userDetails.getUsername();


            User user = userService.findByUsernameForPatient(username);


            if (user.getPatient() != null) {
                PatientsDTO patientsDTO = patientsMapper.mapToDtoWithoutAppointment(user.getPatient());

                model.addAttribute("patient", patientsDTO);

                List<AppointmentsDTO> appointments = appointmentsService.findAppointmentsByPatientId(user.getPatient().getPatientId())
                        .stream()
                        .map(appointment -> appointmentsMapper.mapToDto(appointment))
                        .toList();


                model.addAttribute("appointments", appointments);


                return "patient_dashboard";

            } else {

                return "redirect:/error";
            }
        } else {

            return "redirect:/login";
        }
    }

    @PutMapping(PATIENT_CANCEL)
    public String cancelVisit(@PathVariable Integer appointmentId) {

       appointmentsService.appointmentCanceled(appointmentId);

        return "redirect:/patient_dashboard"; //
    }

    @GetMapping(APPOINTMENT_HISTORY)
    public String showAppointmentHistory(Model model) {
        Authentication authentication = authenticationFacade.getAuthentication();


        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {

            String username = userDetails.getUsername();


            User user = userService.findByUsernameForPatient(username);


            if (user.getPatient() != null) {

                List<AppointmentsDTO> completedAndCanceledAppointments =
                        appointmentsService.getCompletedAndCanceledAppointments(user.getPatient().getPatientId()).stream()
                                .map(appointments -> appointmentsMapper.mapToDto(appointments))
                                .toList();


                model.addAttribute("completedAndCanceledAppointments", completedAndCanceledAppointments);


                return "appointment_history";

            } else {

                return "redirect:/error";
            }
        } else {

            return "redirect:/login";
        }

    }
}
