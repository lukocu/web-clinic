package pl.clinic.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.clinic.api.dto.*;
import pl.clinic.api.dto.mapper.*;
import pl.clinic.business.*;
import pl.clinic.domain.*;
import pl.clinic.security.IAuthenticationFacade;

import java.time.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class VisitController {

    public static final String VISIT = "/doctor_dashboard/visit/{officeAvailabilityId}";
    public static final String VISIT_ADD = "/doctor_dashboard/visit/{officeAvailabilityId}/add_patient_card";

    private PatientCardService patientCardService;
    private PatientsService patientService;
    private PatientsMapper patientsMapper;
    private DoctorMapper doctorMapper;
    private OfficeDoctorAvailabilityService officeDoctorAvailabilityService;
    private OfficeDoctorAvailabilityMapper officeDoctorAvailabilityMapper;
    private OfficeService officeService;
    private AppointmentsService appointmentsService;
    private IAuthenticationFacade authenticationFacade;
    private UserService userService;
    private DoctorsService doctorsService;
    private DiseasesMapper diseasesMapper;
    private MedicationsMapper medicationsMapper;
    private MedicationsService medicationsService;
    private PrescriptionsService prescriptionsService;

    //  private PrescriptionService prescriptionService;
    @GetMapping(value = VISIT)
    public String goToVisit(@PathVariable Integer officeAvailabilityId, Model model) {

        Authentication authentication = authenticationFacade.getAuthentication();

        if (authentication.getPrincipal() instanceof UserDetails userDetails) {

            var user = userService.findByUsername(userDetails.getUsername());
            Doctors doctor = user.getDoctors();

            OfficeDoctorAvailability visit = officeDoctorAvailabilityService
                    .getOfficeAvailability(officeAvailabilityId);

            Office office = officeService.getOffice(visit.getOffice().getOfficeId());

            Appointments currentAppointement = appointmentsService
                    .getCurrentAppointement(visit.getDate(), visit.getStartTime());

            PatientsDTO patientDTO =
                    PatientsMapper.INSTANCE.mapToDtoWithoutAppointment(currentAppointement.getPatient());

            OfficeDoctorAvailabilityDTO visitDTO
                    = officeDoctorAvailabilityMapper.mapToDtoWithOfficeId(visit);

            PrescriptionsDTO prescriptionsDTO = new PrescriptionsDTO();
            Set<DiseasesDTO> diseasesDTO = new HashSet<>();
            Set<MedicationsDTO> medicationsDTO = new HashSet<>();

            String diagnosisNote = "";

            model.addAttribute("officeDoctorAvailability", visitDTO);
            model.addAttribute("patient", patientDTO);
            model.addAttribute("medicationDTO", medicationsDTO);
            model.addAttribute("prescriptionsDTO", prescriptionsDTO);
            model.addAttribute("diseasesDTO", diseasesDTO);
            model.addAttribute("diagnosisNote", diagnosisNote);


            return "visit_details";
        }
        return "error";
    }

    @PostMapping(VISIT_ADD)
    public String addPatientCard(
            @PathVariable("officeAvailabilityId") Integer officeAvailabilityId,
            @RequestParam("diagnosisNote") String diagnosisNote,
            @RequestParam("prescriptionDate") String prescriptionDateString,
            @RequestParam("prescriptionDateEnd") String prescriptionDateEndString,
            @RequestParam("medicationsData") String medicationsData,
            @RequestParam("diseaseData") String diseaseData,
            @RequestParam("patientPesel") String patientPesel,
            Model model) throws JsonProcessingException {
        Authentication authentication = authenticationFacade.getAuthentication();


        OffsetDateTime prescriptionDate = OffsetDateTime.parse(prescriptionDateString + "Z");
        OffsetDateTime prescriptionDateEnd = OffsetDateTime.parse(prescriptionDateEndString + "Z");


        if (authentication.getPrincipal() instanceof UserDetails userDetails) {

            String username = userDetails.getUsername();
            User user = userService.findByUsername(username);
            Doctors doctor = doctorsService.findByUserId(user.getUserId());


            ObjectMapper objectMapper = new ObjectMapper();
            List<MedicationsDTO> medications = objectMapper.readValue(medicationsData, new TypeReference<List<MedicationsDTO>>() {
            });

            List<DiseasesDTO> diseases = objectMapper.readValue(diseaseData, new TypeReference<List<DiseasesDTO>>() {
            });

            Set<Medications> medicationsSet = medications.stream().map(MedicationsMapper.INSTANCE::mapFromDto)
                    .collect(Collectors.toSet());


            Set<Diseases> diseasesSet = diseases.stream().map(DiseasesMapper.INSTANCE::mapFromDtoWithoutPatientCard)
                    .collect(Collectors.toSet());


            Patients patient = patientService.searchPatient(patientPesel);

            OfficeDoctorAvailability officeAvailability =
                    officeDoctorAvailabilityService.getOfficeAvailability(officeAvailabilityId);


            OffsetDateTime currentTime
                    = OffsetDateTime.of(officeAvailability.getDate(),
                    officeAvailability.getEndTime(),
                    ZoneOffset.UTC);

            PatientCard patientCard = PatientCard.builder()
                    .diagnosisDate(currentTime)
                    .diagnosisNote(diagnosisNote)
                    .patient(patient)
                    .doctor(doctor)
                    .diseases(diseasesSet)
                    .prescription(Prescriptions.builder() //NIE POSAIDAJA ID
                            .prescriptionDate(prescriptionDate)
                            .prescriptionDateEnd(prescriptionDateEnd)
                            .prescriptionAvailable(true)
                            .medications(medicationsSet)
                            .build())
                    .build();
            patientCardService.addPatientCardEntry(patientCard);

            officeDoctorAvailabilityService.removeAvailability(officeAvailabilityId);

            return "doctor_dashboard";
        }
        return "error";
    }


}

