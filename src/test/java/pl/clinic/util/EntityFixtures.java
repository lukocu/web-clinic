package pl.clinic.util;

import lombok.experimental.UtilityClass;
import pl.clinic.domain.*;
import pl.clinic.infrastructure.database.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Set;

@UtilityClass
public class EntityFixtures {
    public static PatientsEntity patientWithoutVisits() {
        return PatientsEntity.builder()
                .patientId(100)
                .name("John")
                .surname("Mago")
                .pesel("95011257943")
                .birthDate(LocalDate.of(1990, 6, 15))
                .address("123 Main St")
                .phone(String.valueOf(111 - 222 - 333))
                .build();
    }

    public static PatientsEntity patientWithOnlyCompletedApp() {
        return PatientsEntity.builder()
                .patientId(50)
                .name("Jane")
                .surname("Doe")
                .pesel("96052312345")
                .birthDate(LocalDate.of(1996, 5, 23))
                .address("456 Elm St")
                .phone("444-555-666")
                .appointments(Set.of(appointment2(),appointment3()))
                .build();
    }



    public static AppointmentsEntity appointment1() {
        return AppointmentsEntity.builder()
                .probableStartTime(OffsetDateTime.now())
                .actualEndTime(OffsetDateTime.now().plusMinutes(30))
                .appointmentTakenDate(LocalDate.now())
                .appointmentStatus(AppointmentStatusEntity.builder()
                        .status(Status.Scheduled)
                        .build())
                .office()
                .
                .build();
    }

    public static AppointmentsEntity appointment2() {
        return AppointmentsEntity.builder()
                .probableStartTime(OffsetDateTime.now().plusDays(30))
                .actualEndTime(OffsetDateTime.now())
                .appointmentTakenDate(LocalDate.now())
                .appointmentStatus(AppointmentStatusEntity.builder()
                        .status(Status.Completed)
                        .build())
                .build();
    }

    public static AppointmentsEntity appointment3() {
        return AppointmentsEntity.builder()
                .probableStartTime(OffsetDateTime.now().plusHours(10))
                .actualEndTime(OffsetDateTime.now())
                .appointmentTakenDate(LocalDate.now())
                .appointmentStatus(AppointmentStatusEntity.builder()
                        .status(Status.Completed)
                        .build())
                .build();
    }

    public static OfficeDoctorAvailabilityEntity availability1() {
        return OfficeDoctorAvailabilityEntity.builder()
                .date(LocalDate.of(2023, 9, 25))
                .startTime(LocalTime.of(10, 0))
                .endTime(LocalTime.of(11, 0))
                .availabilityStatus(true)
                .build();
    }

    public static OfficeDoctorAvailabilityEntity availability2() {
        return OfficeDoctorAvailabilityEntity.builder()
                .date(LocalDate.of(2023, 9, 30))
                .startTime(LocalTime.of(11, 0))
                .endTime(LocalTime.of(12, 0))
                .availabilityStatus(true)
                .build();
    }


    public static OfficeEntity office1() {
        return OfficeEntity.builder()
                .officeId(100)
                .firstConsultationFee(new BigDecimal("100.00"))
                .followupConsultationFee(new BigDecimal("80.00"))
                .officeDoctorAvailabilities(Set.of(availability1()))
                .build();
    }

    public static OfficeEntity existingOffice() {
        return OfficeEntity.builder()
                .officeId(1)
                .firstConsultationFee(new BigDecimal("100.00"))
                .followupConsultationFee(new BigDecimal("50.00"))
                .doctor(existingDoctor())
                .build();
    }

    private static DoctorsEntity existingDoctor() {
        return DoctorsEntity.builder()
                .name("John")
                .surname("Smith")
                .phone("123456789")
                .pesel("88092556231")
                .user(exisitngUser())
                .build();
    }

    private static UserEntity exisitngUser() {
        return UserEntity.builder()
                .userId(3)
                .username("doctor2")
                .email("doctor2@example.com")
                .password("$2a$12$FCAlDbUksXTRONN7F8R/nuItbH4EGIpiVrCYiWDW8Tp3WhDBl/rxO")
                .active(true)
                .roles(Set.of(RoleEntity.builder()
                        .role("DOCTOR")
                        .build()))
                .build();
    }

    public static DoctorsEntity doctor2() {
        return DoctorsEntity.builder()
                .name("Stan")
                .surname("Abgil")
                .phone("145-463-934")
                .pesel("560251146745")
                .build();
    }

    public static UserEntity doctorUser() {
        return UserEntity.builder()
                .username("doctor100")
                .email("doctor@example.com")
                .password("test")
                .active(true)
                .roles(Set.of(RoleEntity.builder()
                        .role("DOCTOR")
                        .build()))
                .patient(null)
                .build();
    }
    public static DoctorsEntity doctor1() {
        return DoctorsEntity.builder()
                .name("John")
                .surname("Smith")
                .phone("123-456-789")
                .pesel("84051212345")
                .offices(Set.of(office1()))
                .user(doctorUser())
                .offices(Set.of(office1()))
                .build();
    }

    public static UserEntity patientUser() {
        return UserEntity.builder()
                .username("patient100")
                .email("patient100@example.com")
                .password("test")
                .active(true)
                .roles(Set.of(RoleEntity.builder()
                        .role("PATIENT")
                        .build()))
                .doctors(null)
                .build();
    }

    public static MedicationsEntity createSampleMedication1() {
        return MedicationsEntity.builder()
                .medicationName("Sample Medication")
                .dosage("10 mg")
                .frequency("Once daily")
                .duration("7 days")
                .build();
    }

    public static MedicationsEntity createSampleMedication2() {
        return MedicationsEntity.builder()
                .medicationName("Sample Medication 2")
                .dosage("100 mg")
                .frequency("twice daily")
                .duration("12 days")
                .build();
    }

    public static PatientCardEntity patientCard1() {
        OffsetDateTime offsetDateTime =
                OffsetDateTime.of(LocalDate.of(2023, 9, 25),
                        LocalTime.of(9, 10, 0),
                        ZoneOffset.UTC);

        return PatientCardEntity.builder()
                .diagnosisDate(offsetDateTime)
                .diagnosisNote("Diagnosis note test")
                .patient(patientWithoutVisits())
                .doctor(doctor1())
                .diseases(Set.of(DiseasesEntity.builder()
                        .diseaseName("flu")
                        .diseaseDescription("example description")
                        .build()))
                .prescription(PrescriptionsEntity.builder()
                        .prescriptionDate(offsetDateTime)
                        .prescriptionDateEnd(offsetDateTime.plusYears(1))
                        .prescriptionAvailable(true)
                        .medications(Set.of(MedicationsEntity.builder()
                                .medicationName("Ibuprom")
                                .dosage("100mg")
                                .frequency("2 times week")
                                .duration("2 months")
                                .build()))
                        .build())
                .build();
    }

    public static PatientCardEntity patientCard2() {
        OffsetDateTime offsetDateTime =
                OffsetDateTime.of(LocalDate.of(2023, 6, 30),
                        LocalTime.of(10, 10, 0),
                        ZoneOffset.UTC);

        return PatientCardEntity.builder()
                .diagnosisDate(offsetDateTime)
                .diagnosisNote("Diagnosis note test")
                .patient(patientWithoutVisits())
                .doctor(doctor1())
                .diseases(Set.of(DiseasesEntity.builder()
                        .diseaseName("flu")
                        .diseaseDescription("example description")
                        .build()))
                .prescription(prescription2())
                .build();
    }

    public static PrescriptionsEntity prescription2() {
        OffsetDateTime offsetDateTime =
                OffsetDateTime.of(LocalDate.of(2023, 6, 30),
                        LocalTime.of(10, 10, 0),
                        ZoneOffset.UTC);

        return PrescriptionsEntity.builder()
                .prescriptionDate(offsetDateTime)
                .prescriptionDateEnd(offsetDateTime.plusYears(1))
                .prescriptionAvailable(true)
                .medications(Set.of(MedicationsEntity.builder()
                        .medicationName("Aspirin")
                        .dosage("100mg")
                        .frequency("2 times week")
                        .duration("2 months")
                        .build()))
                .build();
    }
}
