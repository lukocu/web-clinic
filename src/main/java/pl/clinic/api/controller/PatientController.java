package pl.clinic.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.clinic.api.dto.AppointmentsDTO;
import pl.clinic.api.dto.DoctorDTO;
import pl.clinic.api.dto.PatientsDTO;
import pl.clinic.api.dto.mapper.AppointmentsMapper;
import pl.clinic.api.dto.mapper.PatientsMapper;
import pl.clinic.business.AppointmentsService;
import pl.clinic.business.PatientsService;
import pl.clinic.business.UserService;
import pl.clinic.domain.Appointments;
import pl.clinic.domain.Patients;
import pl.clinic.domain.User;
import pl.clinic.security.IAuthenticationFacade;

import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor
public class PatientController {

    public static final String PATIENT ="/patient_dashboard";
    private UserService userService;
    private IAuthenticationFacade authenticationFacade;
    private PatientsService patientsService;
    private AppointmentsService appointmentsService;
    private PatientsMapper patientsMapper;
    private AppointmentsMapper appointmentsMapper;
    @GetMapping(value = PATIENT)
    public String getPatientPage(Model model) {

        Authentication authentication = authenticationFacade.getAuthentication();


        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {

            String username = userDetails.getUsername();


            User user = userService.findByUsername(username);


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

}
