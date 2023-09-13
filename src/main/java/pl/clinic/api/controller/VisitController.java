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
import pl.clinic.api.dto.mapper.DoctorMapper;
import pl.clinic.api.dto.mapper.OfficeDoctorAvailabilityMapper;
import pl.clinic.api.dto.mapper.PatientsMapper;
import pl.clinic.business.*;
import pl.clinic.domain.*;
import pl.clinic.security.IAuthenticationFacade;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            @RequestParam("prescriptionAvailable") String prescriptionAvailableString,
            @RequestParam("medicationsData") String medicationsData,
            @RequestParam("diseaseData") String diseaseData,
            @RequestParam("patientPesel") String patientPesel,
            Model model) throws JsonProcessingException {
        Authentication authentication = authenticationFacade.getAuthentication();


        OffsetDateTime prescriptionDate = OffsetDateTime.parse(prescriptionDateString + "Z");
        OffsetDateTime prescriptionDateEnd = OffsetDateTime.parse(prescriptionDateEndString + "Z");
        OffsetDateTime prescriptionAvailable = OffsetDateTime.parse(prescriptionAvailableString + "Z");


        if (authentication.getPrincipal() instanceof UserDetails userDetails) {

            String username = userDetails.getUsername();
            User user = userService.findByUsername(username);
            Doctors doctor = doctorsService.findByUserId(user.getUserId());
            DoctorDTO doctorDTO = doctorMapper.mapToDtoSpecAndOffices(doctor);

            ObjectMapper objectMapper = new ObjectMapper();
            List<MedicationsDTO> medications = objectMapper.readValue(medicationsData, new TypeReference<List<MedicationsDTO>>() {
            });

            List<DiseasesDTO> diseases = objectMapper.readValue(diseaseData, new TypeReference<List<DiseasesDTO>>() {
            });



            PatientsDTO patientsDTO =
                    patientsMapper.mapToDtoWithoutAppointment(patientService.searchPatient(patientPesel));

            OfficeDoctorAvailabilityDTO officeDoctorAvailabilityDTO
                    = officeDoctorAvailabilityMapper.
                    mapToDto(officeDoctorAvailabilityService.getOfficeAvailability(officeAvailabilityId));

            OffsetDateTime currentTime
                    = OffsetDateTime.of(officeDoctorAvailabilityDTO.getDate(),
                    officeDoctorAvailabilityDTO.getEndTime(),
                    ZoneOffset.UTC);

            PatientCardDTO patientCardDTO = PatientCardDTO.builder()
                    .diagnosisDate(currentTime)
                    .diagnosisNote(diagnosisNote)
                    .patient(patientsDTO)
                    .doctor(doctorDTO)
                    .diseases(new HashSet<>(diseases))
                    .prescription(PrescriptionsDTO.builder()
                            .prescriptionDate(prescriptionDate)
                            .prescriptionDateEnd(prescriptionDateEnd)
                            .prescriptionAvailable(prescriptionAvailable)
                            .medications(new HashSet<>(medications))
                            .build())
                    .build();

            patientCardService.saveCard(patientCardDTO);

            return "visit_details";
        }
        return "error";
    }


}

