package pl.clinic.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.clinic.api.dto.OfficeDTO;
import pl.clinic.api.dto.OfficeDoctorAvailabilityDTO;
import pl.clinic.api.dto.mapper.OfficeDoctorAvailabilityMapper;
import pl.clinic.api.dto.mapper.OfficeMapper;
import pl.clinic.business.DoctorsService;
import pl.clinic.business.OfficeDoctorAvailabilityService;
import pl.clinic.business.OfficeService;
import pl.clinic.business.UserService;
import pl.clinic.domain.Doctors;
import pl.clinic.domain.Office;
import pl.clinic.domain.OfficeDoctorAvailability;
import pl.clinic.domain.User;
import pl.clinic.security.AuthenticationFacade;
import pl.clinic.util.DTOFixtures;
import pl.clinic.util.DomainData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AvailabilityController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AvailabilityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OfficeDoctorAvailabilityService officeDoctorAvailabilityService;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthenticationFacade authenticationFacade;

    @MockBean
    private DoctorsService doctorsService;

    @MockBean
    private OfficeService officeService;

    @MockBean
    private OfficeMapper officeMapper;

    @MockBean
    private OfficeDoctorAvailabilityMapper officeDoctorAvailabilityMapper;

    @Test
    public void testShowAddAvailabilityForm() throws Exception {
        Doctors doctor = DomainData.doctor2();
        User user = DomainData.doctor2().getUser();

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user, "password", Collections.singleton(new SimpleGrantedAuthority("DOCTOR"))
        );

        when(authenticationFacade.getAuthentication()).thenReturn(authentication);
        when(userService.findByUsernameDoctor("doctor2")).thenReturn(doctor.getUser());
        when(doctorsService.findByUserId(doctor.getUser().getUserId())).thenReturn(doctor);
        when(officeService.getOffices(any())).thenReturn((List<Office>) doctor.getOffices());
        when(officeMapper.mapToDto(any(Office.class))).thenReturn(new OfficeDTO());

        mockMvc.perform(MockMvcRequestBuilders.get(AvailabilityController.ADD)
                        .with(authentication(authentication)))
                .andExpect(status().isOk())
                .andDo(print());


    }
    @Test
    @WithMockUser(username = "doctor2", roles = "DOCTOR")
    public void testAddAvailability() throws Exception {

        OfficeDoctorAvailabilityDTO availabilityDTO = DTOFixtures.officeAvailabilityDoctor1();
        OfficeDoctorAvailability availability = DomainData.officeAvailabilityDoctor1();



        when(officeDoctorAvailabilityService.isValid(availabilityDTO)).thenReturn(null);
        when(officeDoctorAvailabilityMapper.mapFromDtoWithOffice(availabilityDTO)).thenReturn(availability);

        mockMvc.perform(MockMvcRequestBuilders.post(AvailabilityController.ADD_POST)
                        .flashAttr("availabilityDTO", availabilityDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/doctor_dashboard/add_availability"))
                .andDo(print());


        verify(officeDoctorAvailabilityService, times(1)).isValid(availabilityDTO);
        verify(officeDoctorAvailabilityMapper, times(1)).mapFromDtoWithOffice(availabilityDTO);
    }


    @Test
    @WithMockUser(username = "doctor2", roles = "DOCTOR")
    public void testShowRemoveAvailabilityPage() throws Exception {

        Doctors doctor = DomainData.doctor2();
        User user = DomainData.doctor2().getUser();
        List<OfficeDoctorAvailability> availabilities = new ArrayList<>();

        when(authenticationFacade.getAuthentication()).thenReturn(new UsernamePasswordAuthenticationToken(
                user, "password", Collections.singleton(new SimpleGrantedAuthority("DOCTOR"))
        ));
        when(userService.findByUsernameForPatient("doctor2")).thenReturn(doctor.getUser());
        when(doctorsService.findByUserId(doctor.getUser().getUserId())).thenReturn(doctor);
        when(officeDoctorAvailabilityService.getAvailableHoursForDoctor(doctor.getName(), doctor.getSurname())).thenReturn(availabilities);
        when(officeDoctorAvailabilityMapper.mapToDtoWithOfficeId(any(OfficeDoctorAvailability.class))).thenReturn(new OfficeDoctorAvailabilityDTO());

        // Wykonanie żądania
        mockMvc.perform(MockMvcRequestBuilders.get(AvailabilityController.REMOVE))
                .andExpect(status().isOk())
                .andDo(print());


    }

    @Test
    @WithMockUser(username = "doctor2", roles = "DOCTOR")
    public void testRemoveAvailability() throws Exception {

        Integer officeAvailabilityId = 1;


        mockMvc.perform(delete(AvailabilityController.REMOVE_DELETE, officeAvailabilityId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/doctor_dashboard/remove_availability"))
                .andDo(print());


        verify(officeDoctorAvailabilityService, times(1)).removeAvailability(officeAvailabilityId);
    }




}
