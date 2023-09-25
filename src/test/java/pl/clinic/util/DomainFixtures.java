package pl.clinic.util;

import lombok.experimental.UtilityClass;
import pl.clinic.domain.*;

import java.math.BigDecimal;
import java.time.*;
import java.util.Set;

@UtilityClass
public class DomainFixtures {

    public static Patients patient1() {
        return Patients.builder()
                .patientId(1)
                .name("John")
                .surname("Mago")
                .pesel("95011257943")
                .birthDate(LocalDate.of(1990, 6, 15))
                .address("123 Main St")
                .phone(String.valueOf(111 - 222 - 333))
                .build();
    }
    public static Patients patient2() {
        return Patients.builder()
                .patientId(2)
                .name("Jane")
                .surname("Doe")
                .pesel("96052312345")
                .birthDate(LocalDate.of(1996, 5, 23))
                .address("456 Elm St")
                .phone("444-555-666")
                .build();
    }
    public static Appointments appointment1(){
      return   Appointments.builder()
                .appointmentId(1)
                .probableStartTime(OffsetDateTime.now())
                .actualEndTime(OffsetDateTime.now().plusMinutes(30))
                .appointmentTakenDate(LocalDate.now())
                .appointmentStatus(AppointmentStatus.builder()
                        .status(Status.Scheduled)
                        .build())
                .build();
    }
    public static Appointments appointment2(){
      return   Appointments.builder()
                .appointmentId(2)
                .probableStartTime(OffsetDateTime.now().plusDays(30))
                .actualEndTime(OffsetDateTime.now())
                .appointmentTakenDate(LocalDate.now())
                .appointmentStatus(AppointmentStatus.builder()
                        .status(Status.Scheduled)
                        .build())
                .build();
    }
    public static Appointments appointment3(){
      return   Appointments.builder()
                .appointmentId(3)
                .probableStartTime(OffsetDateTime.now().plusHours(10))
                .actualEndTime(OffsetDateTime.now())
                .appointmentTakenDate(LocalDate.now())
                .appointmentStatus(AppointmentStatus.builder()
                        .status(Status.Scheduled)
                        .build())
                .build();
    }
    public static OfficeDoctorAvailability availability1() {
        return OfficeDoctorAvailability.builder()
                .officeAvailabilityId(1)
                .date(LocalDate.of(2023,9,25))
                .startTime(LocalTime.of(10,0))
                .endTime(LocalTime.of(11,0))
                .availabilityStatus(true)
                .build();
    }
    public static OfficeDoctorAvailability availability2() {
        return OfficeDoctorAvailability.builder()
                .officeAvailabilityId(2)
                .date(LocalDate.of(2023,9,30))
                .startTime(LocalTime.of(11,0))
                .endTime(LocalTime.of(12,0))
                .availabilityStatus(true)
                .build();
    }


    public static Office office1() {
        return Office.builder()
                .officeId(1)
                .firstConsultationFee(new BigDecimal("100.00"))
                .followupConsultationFee(new BigDecimal("80.00"))
                .doctor(doctor1())
                .officeDoctorAvailabilities(Set.of(availability1()))
                .appointments(Set.of(appointment1(), appointment2()))
                .build();
    }
    public static Office office2() {
        return Office.builder()
                .officeId(2)
                .firstConsultationFee(new BigDecimal("150.00"))
                .followupConsultationFee(new BigDecimal("100.00"))
                .doctor(doctor2())
                .officeDoctorAvailabilities(Set.of(availability1()))
                .appointments(Set.of(appointment3()))
                .build();
    }
    public static Doctors doctor1(){
        return Doctors.builder()
                .doctorId(1)
                .name("John")
                .surname("Smith")
                .phone("123-456-789")
                .pesel("84051212345") // Przykładowy PESEL
                .build();
    }
    public static Doctors doctor2(){
        return Doctors.builder()
                .doctorId(2)
                .name("Stan")
                .surname("Abgil")
                .phone("145-463-934")
                .pesel("560251146745") // Przykładowy PESEL
                .build();
    }

    public static User doctorUser() {
        return User.builder()
                .userId(1)
                .username("doctor1")
                .email("doctor@example.com")
                .password("test")
                .active(true)
                .roles(Set.of(Role.builder()
                        .roleId(2)
                        .role("DOCTOR")
                        .build()))
                .patient(null)
                .doctors(doctor1())
                .build();
    }
}
