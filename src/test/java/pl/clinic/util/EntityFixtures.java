package pl.clinic.util;

import lombok.experimental.UtilityClass;
import pl.clinic.domain.*;
import pl.clinic.infrastructure.database.entity.*;

import java.math.BigDecimal;
import java.time.*;
import java.util.Set;

@UtilityClass
public class EntityFixtures {
    ///JPA TEST
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

    //UNIT TEST

    ///////////////////////////////////////////////////////////////////////////

    /////
    public static UserEntity userEntity1() {
        return UserEntity.builder()
                .username("admin123")
                .email("admin@example.com")
                .password("test")
                .roles(Set.of(adminRoleEntity()))
                .active(true)
                .build();
    }

    public static UserEntity userEntity2() {
        return UserEntity.builder()
                .username("doctor1")
                .email("doctor1@example.com")
                .password("test")
                .roles(Set.of(doctorRoleEntity()))
                .active(true)
                .build();
    }

    public static UserEntity userEntity3() {
        return UserEntity.builder()
                .username("doctor2")
                .email("doctor2@example.com")
                .password("test")
                .roles(Set.of(doctorRoleEntity()))
                .active(true)
                .build();
    }

    public static UserEntity userEntity4() {
        return UserEntity.builder()
                .username("patient1")
                .email("patient1@example.com")
                .password("test")
                .roles(Set.of(patientRoleEntity()))
                .active(true)
                .build();
    }

    public static UserEntity userEntity5() {
        return UserEntity.builder()
                .username("patient2")
                .email("patient2@example.com")
                .password("test")
                .roles(Set.of(patientRoleEntity()))
                .active(true)
                .build();
    }

    public static UserEntity userEntity6() {
        return UserEntity.builder()
                .username("patient3")
                .email("patient3@example.com")
                .password("test")
                .roles(Set.of(patientRoleEntity()))
                .active(true)
                .build();
    }

    public static UserEntity userEntity7() {
        return UserEntity.builder()
                .username("patient4")
                .email("patient4@example.com")
                .password("test")
                .roles(Set.of(patientRoleEntity()))
                .active(true)
                .build();
    }


    public static RoleEntity adminRoleEntity() {
        return RoleEntity.builder()
                .role("ADMIN")
                .build();
    }

    public static RoleEntity doctorRoleEntity() {
        return RoleEntity.builder()
                .role("DOCTOR")
                .build();
    }

    public static RoleEntity patientRoleEntity() {
        return RoleEntity.builder()
                .role("PATIENT")
                .build();
    }

    public static SpecializationEntity cardiologySpecializationEntity() {
        return SpecializationEntity.builder()
                .specializationId(1)
                .specializationName("Cardiology")
                .build();
    }

    public static SpecializationEntity dermatologySpecializationEntity() {
        return SpecializationEntity.builder()
                .specializationId(2)
                .specializationName("Dermatology")
                .build();
    }

    public static SpecializationEntity pediatricsSpecializationEntity() {
        return SpecializationEntity.builder()
                .specializationId(3)
                .specializationName("Pediatrics")
                .build();
    }

    public static DoctorsEntity doctor1() {
        return DoctorsEntity.builder()
                .name("John")
                .surname("Smith")
                .pesel("88092556231")
                .phone("123456789")
                .user(userEntity2())
                .offices(Set.of(officeForDoctor1()))
                .specializations(Set.of(pediatricsSpecializationEntity()))
                .build();
    }

    public static DoctorsEntity doctor2() {
        return DoctorsEntity.builder()
                .name("Emily")
                .surname("Johnson")
                .pesel("78051523148")
                .phone("987654321")
                .user(userEntity3())

                .specializations(Set.of(dermatologySpecializationEntity(), cardiologySpecializationEntity()))
                .build();
    }

    public static OfficeEntity officeForDoctor1() {
        return OfficeEntity.builder()
                .officeId(1)
                .firstConsultationFee(BigDecimal.valueOf(100.00))
                .followupConsultationFee(BigDecimal.valueOf(50.00))
                .officeDoctorAvailabilities(Set.of(officeAvailabilityDoctor1()))
                .build();
    }

    public static OfficeEntity officeForDoctor2() {
        return OfficeEntity.builder()
                .firstConsultationFee(BigDecimal.valueOf(150.00))
                .followupConsultationFee(BigDecimal.valueOf(75.00))
                .officeDoctorAvailabilities(Set.of(officeAvailabilityDoctor2_1(),
                        officeAvailabilityDoctor2_2(),
                        officeAvailabilityDoctor2_3(),
                        officeAvailabilityDoctor2_4()))
                .build();
    }

    public static PatientsEntity patient1() {
        return PatientsEntity.builder()
                .patientId(1)
                .name("Alice")
                .surname("Smith")
                .pesel("95011257943")
                .birthDate(LocalDate.of(1990, 5, 15))
                .address("123 Main St")
                .phone("555-1234")
                .user(userEntity4())
                .build();
    }

    public static PatientsEntity patient2() {
        return PatientsEntity.builder()
                .patientId(2)
                .name("Bob")
                .surname("Johnson")
                .pesel("99022878014")
                .birthDate(LocalDate.of(1985, 10, 20))
                .address("456 Elm St")
                .phone("555-5678")
                .user(userEntity5())
                .build();
    }

    public static PatientsEntity patient3() {
        return PatientsEntity.builder()
                .patientId(3)
                .name("Jake")
                .surname("John")
                .pesel("99027978014")
                .birthDate(LocalDate.of(1987, 12, 23))
                .address("456 Dln St")
                .phone("555-567-258")
                .user(userEntity6())
                .build();
    }

    public static PatientsEntity patient4() {
        return PatientsEntity.builder()
                .patientId(4)
                .name("Blake")
                .surname("Mood")
                .pesel("95022878014")
                .birthDate(LocalDate.of(1995, 12, 24))
                .address("456 Main St")
                .phone("555-567-128")
                .user(userEntity7())
                .build();
    }

    public static OfficeDoctorAvailabilityEntity officeAvailabilityDoctor1() {
        return OfficeDoctorAvailabilityEntity.builder()
                .officeAvailabilityId(1)
                .date(LocalDate.of(2023, 8, 15))
                .startTime(LocalTime.of(8, 0, 0))
                .endTime(LocalTime.of(12, 0, 0))
                .availabilityStatus(true)
                .build();
    }

    public static OfficeDoctorAvailabilityEntity officeAvailabilityDoctor2_1() {
        return OfficeDoctorAvailabilityEntity.builder()
                .officeAvailabilityId(2)
                .date(LocalDate.of(2023, 8, 17))
                .startTime(LocalTime.of(14, 0, 0))
                .endTime(LocalTime.of(15, 0, 0))
                .availabilityStatus(false)
                .build();
    }

    public static OfficeDoctorAvailabilityEntity officeAvailabilityDoctor2_2() {
        return OfficeDoctorAvailabilityEntity.builder()
                .officeAvailabilityId(3)
                .date(LocalDate.of(2023, 8, 17))
                .startTime(LocalTime.of(16, 0, 0))
                .endTime(LocalTime.of(17, 0, 0))
                .availabilityStatus(true)
                .build();
    }

    public static OfficeDoctorAvailabilityEntity officeAvailabilityDoctor2_3() {
        return OfficeDoctorAvailabilityEntity.builder()
                .officeAvailabilityId(4)
                .date(LocalDate.of(2023, 8, 17))
                .startTime(LocalTime.of(17, 0, 0))
                .endTime(LocalTime.of(18, 0, 0))
                .availabilityStatus(true)
                .build();
    }

    public static OfficeDoctorAvailabilityEntity officeAvailabilityDoctor2_4() {
        return OfficeDoctorAvailabilityEntity.builder()
                .officeAvailabilityId(5)
                .date(LocalDate.of(2023, 8, 22))
                .startTime(LocalTime.of(15, 0, 0))
                .endTime(LocalTime.of(16, 0, 0))
                .availabilityStatus(false)
                .build();
    }

    public static AppointmentStatusEntity scheduledStatusEntity() {
        return AppointmentStatusEntity.builder()
                .status(Status.Scheduled)
                .build();
    }

    public static AppointmentStatusEntity completedStatusEntity() {
        return AppointmentStatusEntity.builder()
                .status(Status.Completed)
                .build();
    }

    public static AppointmentStatusEntity canceledStatusEntity() {
        return AppointmentStatusEntity.builder()
                .status(Status.Canceled)
                .build();
    }

    public static AppointmentsEntity appointment1() {
        return AppointmentsEntity.builder()
                .patient(patient1())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-08-17T14:00:00"), ZoneOffset.UTC))
                .appointmentStatus(scheduledStatusEntity())
                .appointmentTakenDate(LocalDate.parse("2023-08-17"))
                .build();
    }

    public static AppointmentsEntity appointment2() {
        return AppointmentsEntity.builder()
                .patient(patient2())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-08-22T15:00:00"), ZoneOffset.UTC))
                .appointmentStatus(scheduledStatusEntity())
                .appointmentTakenDate(LocalDate.parse("2023-08-18"))
                .build();
    }

    public static AppointmentsEntity appointment3() {
        return AppointmentsEntity.builder()
                .patient(patient1())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-08-17T14:00:00"), ZoneOffset.UTC))
                .appointmentStatus(canceledStatusEntity())
                .appointmentTakenDate(LocalDate.parse("2023-08-17"))
                .build();
    }

    public static AppointmentsEntity appointment4() {
        return AppointmentsEntity.builder()
                .patient(patient2())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-08-17T17:00:00"), ZoneOffset.UTC))
                .appointmentStatus(canceledStatusEntity())
                .appointmentTakenDate(LocalDate.parse("2023-08-18"))
                .build();
    }

    public static AppointmentsEntity appointment5() {
        return AppointmentsEntity.builder()
                .patient(patient2())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-15T10:00:00"), ZoneOffset.UTC))
                .actualEndTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-15T11:00:00"), ZoneOffset.UTC))
                .appointmentStatus(completedStatusEntity())
                .appointmentTakenDate(LocalDate.parse("2023-06-10"))
                .build();
    }

    public static AppointmentsEntity appointment6() {
        return AppointmentsEntity.builder()
                .patient(patient2())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T10:00:00"), ZoneOffset.UTC))
                .actualEndTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T11:00:00"), ZoneOffset.UTC))
                .appointmentStatus(completedStatusEntity())
                .appointmentTakenDate(LocalDate.parse("2023-06-10"))
                .build();
    }

    public static AppointmentsEntity appointment7() {
        return AppointmentsEntity.builder()
                .patient(patient3())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T10:00:00"), ZoneOffset.UTC))
                .actualEndTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T11:00:00"), ZoneOffset.UTC))
                .appointmentStatus(canceledStatusEntity())
                .appointmentTakenDate(LocalDate.parse("2023-06-11"))
                .build();
    }

    public static AppointmentsEntity appointment8() {
        return AppointmentsEntity.builder()
                .patient(patient3())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-17T10:00:00"), ZoneOffset.UTC))
                .actualEndTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T11:00:00"), ZoneOffset.UTC))
                .appointmentStatus(canceledStatusEntity())
                .appointmentTakenDate(LocalDate.parse("2023-06-12"))
                .build();
    }

    public static AppointmentsEntity appointment9() {
        return AppointmentsEntity.builder()
                .patient(patient4())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T11:00:00"), ZoneOffset.UTC))
                .actualEndTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T11:00:00"), ZoneOffset.UTC))
                .appointmentStatus(completedStatusEntity())
                .appointmentTakenDate(LocalDate.parse("2023-06-13"))
                .build();
    }

    public static AppointmentsEntity appointment10() {
        return AppointmentsEntity.builder()
                .patient(patient4())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-24T12:00:00"), ZoneOffset.UTC))
                .actualEndTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T11:00:00"), ZoneOffset.UTC))
                .appointmentStatus(completedStatusEntity())
                .appointmentTakenDate(LocalDate.parse("2023-06-23"))
                .build();
    }

    public static MedicationsEntity aspirinMedicationsEntity() {
        return MedicationsEntity.builder()
                .medicationId(1)
                .medicationName("Aspirin")
                .dosage("500mg")
                .frequency("Once a day")
                .duration("7 days")
                .build();
    }

    public static MedicationsEntity ibuprofenMedicationsEntity() {
        return MedicationsEntity.builder()
                .medicationId(2)
                .medicationName("Ibuprofen")
                .dosage("200mg")
                .frequency("Twice a day")
                .duration("10 days")
                .build();
    }

    public static MedicationsEntity antibioticMedicationsEntity() {
        return MedicationsEntity.builder()
                .medicationId(3)
                .medicationName("Antibiotic")
                .dosage("250mg")
                .frequency("Three times a day")
                .duration("14 days")
                .build();
    }

    public static PrescriptionsEntity prescription1() {
        return PrescriptionsEntity.builder()
                .prescriptionDate(OffsetDateTime.of(LocalDateTime.of(
                        2023, 9, 26, 8, 0), ZoneOffset.UTC))
                .prescriptionDateEnd(OffsetDateTime.of(LocalDateTime.of(
                        2023, 10, 5, 8, 0), ZoneOffset.UTC))
                .prescriptionAvailable(true)
                .medications(Set.of(aspirinMedicationsEntity()))
                .build();
    }

    public static PrescriptionsEntity prescription2() {
        return PrescriptionsEntity.builder()
                .prescriptionDate(OffsetDateTime.of(LocalDateTime.of(
                        2023, 9, 27, 10, 30), ZoneOffset.UTC))
                .prescriptionDateEnd(OffsetDateTime.of(LocalDateTime.of(
                        2023, 10, 10, 10, 30), ZoneOffset.UTC))
                .prescriptionAvailable(true)
                .medications(Set.of(ibuprofenMedicationsEntity(),antibioticMedicationsEntity()))
                .build();
    }

    public static PrescriptionsEntity prescription3() {
        return PrescriptionsEntity.builder()
                .prescriptionDate(OffsetDateTime.of(LocalDateTime.of(
                        2023, 6, 16, 11, 0), ZoneOffset.UTC))
                .prescriptionDateEnd(OffsetDateTime.of(LocalDateTime.of(
                        2023, 8, 16, 11, 0), ZoneOffset.UTC))
                .prescriptionAvailable(true)
                .medications(Set.of(antibioticMedicationsEntity()))
                .build();
    }

    public static PrescriptionsEntity prescription4() {
        return PrescriptionsEntity.builder()
                .prescriptionDate(OffsetDateTime.of(LocalDateTime.of(
                        2023, 6, 24, 12, 0), ZoneOffset.UTC))
                .prescriptionDateEnd(OffsetDateTime.of(LocalDateTime.of(
                        2023, 8, 24, 12, 0), ZoneOffset.UTC))
                .prescriptionAvailable(true)
                .medications(Set.of(antibioticMedicationsEntity()))
                .build();
    }

    public static DiseasesEntity fluDiseaseEntity() {
        return DiseasesEntity.builder()
                .diseaseId(1)
                .diseaseName("Flu")
                .diseaseDescription("Upper respiratory tract infection")
                .build();
    }

    public static DiseasesEntity headacheDiseaseEntity() {
        return DiseasesEntity.builder()
                .diseaseId(2)
                .diseaseName("Headache")
                .diseaseDescription("Frequent headaches and migraines")
                .build();
    }

    public static DiseasesEntity feverDiseaseEntity() {
        return DiseasesEntity.builder()
                .diseaseId(3)
                .diseaseName("Fever")
                .diseaseDescription("Condition of fever due to infection")
                .build();
    }

    public static DiseasesEntity tonsillitisDiseaseEntity() {
        return DiseasesEntity.builder()
                .diseaseName("Tonsillitis")
                .diseaseDescription("In the most common sense, acute inflammation of the palatine tonsils")
                .build();
    }

    public static PatientCardEntity patientCardEntity1() {
        return PatientCardEntity.builder()
                .diagnosisDate(OffsetDateTime.of(LocalDateTime.parse("2023-06-15T10:00:00"), ZoneOffset.UTC))
                .diagnosisNote("Przykładowa diagnoza 1")
                .patient(patient1())
                .doctor(doctor1())
                .prescription(prescription1())
                .diseases(Set.of(fluDiseaseEntity(), headacheDiseaseEntity()))
                .build();
    }

    public static PatientCardEntity patientCardEntity2() {
        return PatientCardEntity.builder()
                .diagnosisDate(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T10:00:00"), ZoneOffset.UTC))
                .diagnosisNote("Przykładowa diagnoza 2")
                .patient(patient2())
                .doctor(doctor1())
                .prescription(prescription2())
                .build();
    }

    public static PatientCardEntity patientCardEntity3() {
        return PatientCardEntity.builder()
                .diagnosisDate(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T11:00:00"), ZoneOffset.UTC))
                .diagnosisNote("Przykładowa diagnoza 3")
                .patient(patient4())
                .doctor(doctor1())
                .prescription(prescription3())
                .build();
    }

    public static PatientCardEntity patientCardEntity4() {
        return PatientCardEntity.builder()
                .diagnosisDate(OffsetDateTime.of(LocalDateTime.parse("2023-06-24T12:00:00"), ZoneOffset.UTC))
                .diagnosisNote("Przykładowa diagnoza 4")
                .patient(patient4())
                .doctor(doctor1())
                .prescription(prescription4())
                .diseases(Set.of(tonsillitisDiseaseEntity()))
                .build();
    }

}
