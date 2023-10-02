package pl.clinic.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.clinic.api.dto.DoctorDTO;
import pl.clinic.api.dto.OfficeDoctorAvailabilityDTO;
import pl.clinic.api.dto.mapper.DoctorMapper;
import pl.clinic.api.dto.mapper.OfficeDoctorAvailabilityMapper;
import pl.clinic.business.DoctorsService;
import pl.clinic.business.OfficeDoctorAvailabilityService;
import pl.clinic.business.UserService;
import pl.clinic.domain.Doctors;
import pl.clinic.domain.OfficeDoctorAvailability;
import pl.clinic.domain.User;
import pl.clinic.security.IAuthenticationFacade;
import pl.clinic.util.DTOFixtures;
import pl.clinic.util.DomainData;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(DoctorController.class)
public class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorsService doctorsService;

    @MockBean
    private DoctorMapper doctorMapper;

    @MockBean
    private IAuthenticationFacade authenticationFacade;

    @MockBean
    private UserService userService;

    @MockBean
    private OfficeDoctorAvailabilityMapper officeDoctorAvailabilityMapper;

    @MockBean
    private OfficeDoctorAvailabilityService officeDoctorAvailabilityService;

    @Test
    @WithMockUser(username = "testUser", roles = "DOCTOR")
    public void testGetDoctorDashboard() throws Exception {

        DoctorDTO doctorDTO = DTOFixtures.doctor2();
        List<OfficeDoctorAvailability> unavailableOfficeHours = List.of(DomainData.officeAvailabilityDoctor2_1(),
                DomainData.officeAvailabilityDoctor1(),
                DomainData.officeAvailabilityDoctor2_4());


        when(authenticationFacade.getAuthentication()).thenReturn(mock(Authentication.class));
        when(userService.findByUsernameDoctor("testUser")).thenReturn(mock(User.class));
        when(doctorsService.findByUserId(any())).thenReturn(mock(Doctors.class));
        when(doctorMapper.mapToDtoSpecAndOffices(any())).thenReturn(doctorDTO);
        when(officeDoctorAvailabilityService.getUnavailableOfficeHoursForDoctor(anyInt())).thenReturn(unavailableOfficeHours);


        mockMvc.perform(get(DoctorController.DOCTOR_DASHBOAR))
                .andExpect(status().isOk())
                .andDo(print());


        verify(authenticationFacade, times(1)).getAuthentication();


    }

    @Test
    public void testGetDoctorDashboardUnauthorized() throws Exception {

        mockMvc.perform(get(DoctorController.DOCTOR_DASHBOAR))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "testUser", roles = "DOCTOR")
    public void testGetDoctorList() throws Exception {

        List<Doctors> doctors = List.of(DomainData.doctor1(),DomainData.doctor2());


        when(doctorsService.getDoctorsAndOffice()).thenReturn(doctors);
        when(doctorMapper.mapToDtoSpecAndOffices(any())).thenReturn(new DoctorDTO());


        mockMvc.perform(get(DoctorController.DOCTOR_LIST))
                .andExpect(status().isOk())
                .andExpect(view().name("doctor_list"))
                .andExpect(model().attributeExists("doctorsAndOffice"))
                .andDo(print());


        verify(doctorsService, times(1)).getDoctorsAndOffice();
    }
}
