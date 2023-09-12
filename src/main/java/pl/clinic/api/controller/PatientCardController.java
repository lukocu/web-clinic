package pl.clinic.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.clinic.security.IAuthenticationFacade;

@Controller
@AllArgsConstructor
public class PatientCardController {

    private IAuthenticationFacade authenticationFacade;
    @GetMapping("/patient_card")
    public String showPatientCard(Model model) {
        Authentication authentication = authenticationFacade.getAuthentication();


        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {

            String username = userDetails.getUsername();

            model.addAttribute("patient", userDetails);
            return "patient_card";

        } else {
            return "redirect:/login";
        }
    }
}