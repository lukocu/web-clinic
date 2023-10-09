package pl.clinic.util;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.clinic.api.dto.*;
import pl.clinic.domain.*;

import java.math.BigDecimal;
import java.time.*;
import java.util.Set;

public class DTOFixtures {
    public static UserRegistrationDto user1() {
        return UserRegistrationDto.builder()
                .username("admin123")
                .email("admin@example.com")
                .password("test")
                .role("ADMIN")
                .active(true)
                .build();
    }

    public static UserRegistrationDto user2() {
        return UserRegistrationDto.builder()
                .username("doctor1")
                .email("doctor1@example.com")
                .password("test")
                .role(doctorRole().getRole())
                .active(true)
                .build();
    }

    public static UserRegistrationDto user3() {
        return UserRegistrationDto.builder()
                .username("doctor2")
                .email("doctor2@example.com")
                .password("test")
                .role(doctorRole().getRole())
                .active(true)
                .build();
    }

    public static UserRegistrationDto user4() {
        return UserRegistrationDto.builder()
                .username("patient1")
                .email("patient1@example.com")
                .password("test")
                .role(patientRole().getRole())
                .active(true)
                .build();
    }

    public static UserRegistrationDto user5() {
        return UserRegistrationDto.builder()
                .username("patient2")
                .email("patient2@example.com")
                .password("test")
                .active(true)
                .role(patientRole().getRole())
                .build();
    }

    public static UserRegistrationDto user6() {
        return UserRegistrationDto.builder()
                .username("patient3")
                .email("patient3@example.com")
                .password("test")
                .role(patientRole().getRole())
                .active(true)
                .build();
    }

    public static UserRegistrationDto user7() {
        return UserRegistrationDto.builder()
                .username("patient4")
                .email("patient4@example.com")
                .password("test")
                .role(patientRole().getRole())
                .active(true)
                .build();
    }


    public static Role adminRole() {
        return Role.builder()
                .role("ADMIN")
                .build();
    }

    public static Role doctorRole() {
        return Role.builder()
                .role("DOCTOR")
                .build();
    }

    public static Role patientRole() {
        return Role.builder()
                .role("PATIENT")
                .build();
    }

    public static SpecializationDTO cardiologySpecialization() {
        return SpecializationDTO.builder()
                .specializationId(1)
                .specializationName("Cardiology")
                .build();
    }

    public static SpecializationDTO dermatologySpecialization() {
        return SpecializationDTO.builder()
                .specializationId(2)
                .specializationName("Dermatology")
                .build();
    }

    public static SpecializationDTO pediatricsSpecialization() {
        return SpecializationDTO.builder()
                .specializationId(3)
                .specializationName("Pediatrics")
                .build();
    }

    public static DoctorDTO doctor1() {
        return DoctorDTO.builder()
                .doctorId(1)
                .name("John")
                .surname("Smith")
                .pesel("88092556231")
                .phone("123456789")
                .offices(Set.of(officeForDoctor1()))
                .specializationNames(Set.of(pediatricsSpecialization()))
                .build();
    }

    public static DoctorDTO newDoctor() {
        return DoctorDTO.builder()
                .doctorId(10)
                .name("Wick")
                .surname("Lazy")
                .pesel("88095555231")
                .phone("122226789")
                .offices(Set.of(newOfficeForDoctor3()))
                .specializationNames(Set.of(pediatricsSpecialization()))
                .build();
    }

    public static DoctorDTO doctor2() {
        return DoctorDTO.builder()
                .name("Emily")
                .surname("Johnson")
                .pesel("78051523148")
                .phone("987654321")
                .specializationNames(Set.of(dermatologySpecialization(), cardiologySpecialization()))
                .build();
    }

    public static OfficeDTO officeForDoctor1() {
        return OfficeDTO.builder()
                .officeId(1)
                .firstConsultationFee(BigDecimal.valueOf(100.00))
                .followupConsultationFee(BigDecimal.valueOf(50.00))
                .build();
    }

    public static OfficeDTO newOfficeForDoctor3() {
        return OfficeDTO.builder()
                .officeId(3)
                .firstConsultationFee(BigDecimal.valueOf(100.00))
                .followupConsultationFee(BigDecimal.valueOf(50.00))
                .build();
    }

    public static OfficeDTO officeForDoctor2() {
        return OfficeDTO.builder()
                .officeId(2)
                .doctor(doctor2())
                .firstConsultationFee(BigDecimal.valueOf(150.00))
                .followupConsultationFee(BigDecimal.valueOf(75.00))
                .build();
    }

    public static PatientsDTO patient1() {
        return PatientsDTO.builder()
                .patientId(1)
                .name("Alice")
                .surname("Smith")
                .pesel("95011257943")
                .birthDate(LocalDate.of(1990, 5, 15))
                .address("123 Main St")
                .phone("555-1234")
                .build();
    }

    public static PatientsDTO patient2() {
        return PatientsDTO.builder()
                .patientId(2)
                .name("Bob")
                .surname("Johnson")
                .pesel("99022878014")
                .birthDate(LocalDate.of(1985, 10, 20))
                .address("456 Elm St")
                .phone("555-5678")
                .build();
    }

    public static PatientsDTO patient3() {
        return PatientsDTO.builder()
                .patientId(3)
                .name("Jake")
                .surname("John")
                .pesel("99027978014")
                .birthDate(LocalDate.of(1987, 12, 23))
                .address("456 Dln St")
                .phone("555-567-258")
                .build();
    }

    public static PatientsDTO patient4() {
        return PatientsDTO.builder()
                .patientId(4)
                .name("Blake")
                .surname("Mood")
                .pesel("95022878014")
                .birthDate(LocalDate.of(1995, 12, 24))
                .address("456 Main St")
                .phone("555-567-128")
                .build();
    }

    public static PatientUserDTO newPatient() {
        return PatientUserDTO.builder()
                .username("newPatient2")
                .email("newPatient2@example.pl")
                .password("test")
                .confirmPassword("test")
                .role("PATIENT")
                .active(true)
                .name("newName2")
                .surname("newSurname2")
                .pesel("88773355226")
                .birthDate(LocalDate.of(1999, 8, 21))
                .address("newPatient2")
                .phone("000999888")
                .build();
    }

    public static OfficeDoctorAvailabilityDTO officeAvailabilityDoctor1() {
        return OfficeDoctorAvailabilityDTO.builder()
                .officeAvailabilityId(1)
                .date(LocalDate.of(2023, 8, 15))
                .startTime(LocalTime.of(8, 0, 0))
                .endTime(LocalTime.of(12, 0, 0))
                .availabilityStatus(true)
                .build();
    }

    public static OfficeDoctorAvailabilityDTO officeAvailabilityDoctor2_1() {
        return OfficeDoctorAvailabilityDTO.builder()
                .officeAvailabilityId(2)
                .date(LocalDate.of(2023, 8, 17))
                .startTime(LocalTime.of(14, 0, 0))
                .endTime(LocalTime.of(15, 0, 0))
                .availabilityStatus(false)
                .build();
    }

    public static OfficeDoctorAvailabilityDTO officeAvailabilityDoctor2_2() {
        return OfficeDoctorAvailabilityDTO.builder()
                .officeAvailabilityId(3)
                .date(LocalDate.of(2023, 8, 17))
                .startTime(LocalTime.of(16, 0, 0))
                .endTime(LocalTime.of(17, 0, 0))
                .availabilityStatus(true)
                .build();
    }

    public static OfficeDoctorAvailabilityDTO officeAvailabilityDoctor2_3() {
        return OfficeDoctorAvailabilityDTO.builder()
                .officeAvailabilityId(4)
                .date(LocalDate.of(2023, 8, 17))
                .startTime(LocalTime.of(17, 0, 0))
                .endTime(LocalTime.of(18, 0, 0))
                .availabilityStatus(true)
                .build();
    }

    public static OfficeDoctorAvailabilityDTO officeAvailabilityDoctor2_4() {
        return OfficeDoctorAvailabilityDTO.builder()
                .officeAvailabilityId(5)
                .officeId(2)
                .date(LocalDate.of(2023, 8, 22))
                .startTime(LocalTime.of(15, 0, 0))
                .endTime(LocalTime.of(16, 0, 0))
                .availabilityStatus(false)
                .build();
    }

    public static AppointmentStatusDTO scheduledStatus() {
        return AppointmentStatusDTO.builder()
                .appointmentStatusId(1)
                .status("Scheduled")
                .build();
    }

    public static AppointmentStatusDTO completedStatus() {
        return AppointmentStatusDTO.builder()
                .appointmentStatusId(2)
                .status("Completed")
                .build();
    }

    public static AppointmentStatusDTO canceledStatus() {
        return AppointmentStatusDTO.builder()
                .appointmentStatusId(3)
                .status("Canceled")
                .build();
    }

    public static AppointmentsDTO appointment1() {
        return AppointmentsDTO.builder()
                .patient(patient1())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-08-17T14:00:00"), ZoneOffset.UTC))
                .appointmentStatus(scheduledStatus())
                .appointmentTakenDate(LocalDate.parse("2023-08-17"))
                .build();
    }

    public static AppointmentsDTO appointment2() {
        return AppointmentsDTO.builder()
                .patient(patient2())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-08-22T15:00:00"), ZoneOffset.UTC))
                .appointmentStatus(scheduledStatus())
                .appointmentTakenDate(LocalDate.parse("2023-08-18"))
                .build();
    }

    public static AppointmentsDTO appointment3() {
        return AppointmentsDTO.builder()
                .patient(patient1())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-08-17T14:00:00"), ZoneOffset.UTC))
                .appointmentStatus(canceledStatus())
                .appointmentTakenDate(LocalDate.parse("2023-08-17"))
                .build();
    }

    public static AppointmentsDTO appointment4() {
        return AppointmentsDTO.builder()
                .patient(patient2())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-08-17T17:00:00"), ZoneOffset.UTC))
                .appointmentStatus(canceledStatus())
                .appointmentTakenDate(LocalDate.parse("2023-08-18"))
                .build();
    }

    public static AppointmentsDTO appointment5() {
        return AppointmentsDTO.builder()
                .patient(patient2())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-15T10:00:00"), ZoneOffset.UTC))
                .actualEndTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-15T11:00:00"), ZoneOffset.UTC))
                .appointmentStatus(completedStatus())
                .appointmentTakenDate(LocalDate.parse("2023-06-10"))
                .build();
    }

    public static AppointmentsDTO appointment6() {
        return AppointmentsDTO.builder()
                .patient(patient2())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T10:00:00"), ZoneOffset.UTC))
                .actualEndTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T11:00:00"), ZoneOffset.UTC))
                .appointmentStatus(completedStatus())
                .appointmentTakenDate(LocalDate.parse("2023-06-10"))
                .build();
    }

    public static AppointmentsDTO appointment7() {
        return AppointmentsDTO.builder()
                .patient(patient3())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T10:00:00"), ZoneOffset.UTC))
                .actualEndTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T11:00:00"), ZoneOffset.UTC))
                .appointmentStatus(canceledStatus())
                .appointmentTakenDate(LocalDate.parse("2023-06-11"))
                .build();
    }

    public static AppointmentsDTO appointment8() {
        return AppointmentsDTO.builder()
                .patient(patient3())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-17T10:00:00"), ZoneOffset.UTC))
                .actualEndTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T11:00:00"), ZoneOffset.UTC))
                .appointmentStatus(canceledStatus())
                .appointmentTakenDate(LocalDate.parse("2023-06-12"))
                .build();
    }

    public static AppointmentsDTO appointment9() {
        return AppointmentsDTO.builder()
                .patient(patient4())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T11:00:00"), ZoneOffset.UTC))
                .actualEndTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T11:00:00"), ZoneOffset.UTC))
                .appointmentStatus(completedStatus())
                .appointmentTakenDate(LocalDate.parse("2023-06-13"))
                .build();
    }

    public static AppointmentsDTO appointment10() {
        return AppointmentsDTO.builder()
                .patient(patient4())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-24T12:00:00"), ZoneOffset.UTC))
                .actualEndTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T11:00:00"), ZoneOffset.UTC))
                .appointmentStatus(completedStatus())
                .appointmentTakenDate(LocalDate.parse("2023-06-23"))
                .build();
    }

    public static MedicationsDTO aspirinMedications() {
        return MedicationsDTO.builder()
                .medicationName("Aspirin")
                .dosage("500mg")
                .frequency("Once a day")
                .duration("7 days")
                .build();
    }

    public static MedicationsDTO ibuprofenMedications() {
        return MedicationsDTO.builder()
                .medicationName("Ibuprofen")
                .dosage("200mg")
                .frequency("Twice a day")
                .duration("10 days")
                .build();
    }

    public static MedicationsDTO antibioticMedications() {
        return MedicationsDTO.builder()
                .medicationName("Antibiotic")
                .dosage("250mg")
                .frequency("Three times a day")
                .duration("14 days")
                .build();
    }

    public static PrescriptionsDTO prescription1() {
        return PrescriptionsDTO.builder()
                .prescriptionDate(OffsetDateTime.of(
                        LocalDateTime.of(2023, 9, 26, 8, 0), ZoneOffset.UTC))
                .prescriptionDateEnd(OffsetDateTime.of(
                        LocalDateTime.of(2023, 10, 5, 8, 0), ZoneOffset.UTC))
                .prescriptionAvailable(true)
                .medications(Set.of(aspirinMedications()))
                .build();
    }

    public static PrescriptionsDTO prescription2() {
        return PrescriptionsDTO.builder()
                .prescriptionDate(OffsetDateTime.of(
                        LocalDateTime.of(2023, 9, 27, 10, 30), ZoneOffset.UTC))
                .prescriptionDateEnd(OffsetDateTime.of(
                        LocalDateTime.of(2023, 10, 10, 10, 30), ZoneOffset.UTC))
                .prescriptionAvailable(true)
                .medications(Set.of(ibuprofenMedications(), antibioticMedications()))
                .build();
    }

    public static PrescriptionsDTO prescription3() {
        return PrescriptionsDTO.builder()
                .prescriptionDate(OffsetDateTime.of(LocalDateTime.of(
                        2023, 6, 16, 11, 0), ZoneOffset.UTC))
                .prescriptionDateEnd(OffsetDateTime.of(LocalDateTime.of(
                        2023, 8, 16, 11, 0), ZoneOffset.UTC))
                .prescriptionAvailable(true)
                .medications(Set.of(antibioticMedications()))
                .build();
    }

    public static PrescriptionsDTO prescription4() {
        return PrescriptionsDTO.builder()
                .prescriptionDate(OffsetDateTime.of(LocalDateTime.of(
                        2023, 6, 24, 12, 0), ZoneOffset.UTC))
                .prescriptionDateEnd(OffsetDateTime.of(LocalDateTime.of(
                        2023, 8, 24, 12, 0), ZoneOffset.UTC))
                .prescriptionAvailable(true)
                .medications(Set.of(antibioticMedications()))
                .build();
    }

    public static DiseasesDTO fluDisease() {
        return DiseasesDTO.builder()
                .diseaseName("Flu")
                .diseaseDescription("Upper respiratory tract infection")
                .build();
    }

    public static DiseasesDTO headacheDisease() {
        return DiseasesDTO.builder()
                .diseaseName("Headache")
                .diseaseDescription("Frequent headaches and migraines")
                .build();
    }

    public static DiseasesDTO feverDisease() {
        return DiseasesDTO.builder()
                .diseaseName("Fever")
                .diseaseDescription("Condition of fever due to infection")
                .build();
    }

    public static DiseasesDTO tonsillitisDisease() {
        return DiseasesDTO.builder()
                .diseaseName("Tonsillitis")
                .diseaseDescription("In the most common sense, acute inflammation of the palatine tonsils")
                .build();
    }

    public static PatientCardDTO patientCard1() {
        return PatientCardDTO.builder()
                .diseases(Set.of(fluDisease(), headacheDisease()))
                .diagnosisDate(OffsetDateTime.of(LocalDateTime.parse("2023-06-15T10:00:00"), ZoneOffset.UTC))
                .diagnosisNote("Przykładowa diagnoza 1")
                .patient(patient1())
                .doctor(doctor1())
                .prescription(prescription1())
                .build();
    }

    public static PatientCardDTO patientCard2() {
        return PatientCardDTO.builder()
                .diagnosisDate(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T10:00:00"), ZoneOffset.UTC))
                .diagnosisNote("Przykładowa diagnoza 2")
                .patient(patient2())
                .doctor(doctor1())
                .prescription(prescription2())
                .diseases(Set.of(feverDisease()))
                .build();
    }

    public static PatientCardDTO patientCard3() {
        return PatientCardDTO.builder()
                .diagnosisDate(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T11:00:00"), ZoneOffset.UTC))
                .diagnosisNote("Przykładowa diagnoza 3")
                .patient(patient4())
                .doctor(doctor1())
                .prescription(prescription3())
                .build();
    }

    public static PatientCardDTO patientCard4() {
        return PatientCardDTO.builder()
                .diagnosisDate(OffsetDateTime.of(LocalDateTime.parse("2023-06-24T12:00:00"), ZoneOffset.UTC))
                .diagnosisNote("Przykładowa diagnoza 4")
                .patient(patient4())
                .doctor(doctor1())
                .prescription(prescription4())
                .build();
    }

    public static DoctorUserDTO doctorUserDTO() {
        return DoctorUserDTO.builder()
                .username("exampleDoctor")
                .email("example.doctor@example.com")
                .password("examplePassword")
                .confirmPassword("examplePassword")
                .role("DOCTOR")
                .active(true)
                .name("John")
                .surname("Doe")
                .phone("12341189")
                .pesel("12341118901")
                .build();

    }

}
