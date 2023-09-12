package pl.clinic.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class VisitController {

    public static final String VISIT="web-clinic/doctor_dashboard/visit/{officeAvailabilityId}";
    @GetMapping(value = VISIT)
    public String goToVisit(@PathVariable Integer officeAvailabilityId, Model model) {




        model.addAttribute("officeAvailabilityId", officeAvailabilityId);

        // Zwracasz widok, który będzie renderowany po kliknięciu przycisku
        return "visit_details"; // Zastąp "visit_details" nazwą swojego widoku
    }
}
