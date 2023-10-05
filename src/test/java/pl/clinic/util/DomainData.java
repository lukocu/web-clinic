package pl.clinic.util;

import lombok.experimental.UtilityClass;
import pl.clinic.domain.*;

import java.math.BigDecimal;
import java.time.*;
import java.util.Set;

@UtilityClass
public class DomainData {
    public static User user1() {
        return User.builder()
                .userId(1)
                .username("admin123")
                .email("admin@example.com")
                .password("test")
                .active(true)
                .build();
    }

    public static User user2() {
        return User.builder()

                .username("doctor1")
                .email("doctor1@example.com")
                .password("test")
                .roles(Set.of(doctorRole()))
                .active(true)
                .build();
    }

    public static User user3() {
        return User.builder()
                .username("doctor2")
                .email("doctor2@example.com")
                .password("test")
                .roles(Set.of(doctorRole()))
                .active(true)
                .build();
    }

    public static User user4() {
        return User.builder()
                .username("patient1")
                .email("patient1@example.com")
                .password("test")
                .roles(Set.of(patientRole()))
                .active(true)
                .build();
    }

    public static User user5() {
        return User.builder()
                .username("patient2")
                .email("patient2@example.com")
                .password("test")
                .active(true)
                .roles(Set.of(patientRole()))
                .build();
    }
    public static User user6() {
        return User.builder()
                .username("patient3")
                .email("patient3@example.com")
                .password("test")
                .roles(Set.of(patientRole()))
                .active(true)
                .build();
    }
    public static User user7() {
        return User.builder()
                .username("patient4")
                .email("patient4@example.com")
                .password("test")
                .roles(Set.of(patientRole()))
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
    public static Specialization cardiologySpecialization() {
        return Specialization.builder()
                .specializationId(1)
                .specializationName("Cardiology")
                .build();
    }

    public static Specialization dermatologySpecialization() {
        return Specialization.builder()
                .specializationId(2)
                .specializationName("Dermatology")
                .build();
    }

    public static Specialization pediatricsSpecialization() {
        return Specialization.builder()
                .specializationId(3)
                .specializationName("Pediatrics")
                .build();
    }
    public static Doctors doctor1() {
        return Doctors.builder()
                .name("John")
                .surname("Smith")
                .pesel("88092556231")
                .phone("123456789")
                .user(user2())
                .offices(Set.of(officeForDoctor1()))
                .specializations(Set.of(pediatricsSpecialization()))
                .build();
    }

    public static Doctors doctor2() {
        return Doctors.builder()
                .name("Emily")
                .surname("Johnson")
                .pesel("78051523148")
                .phone("987654321")
                .user(user3())

                .specializations(Set.of(dermatologySpecialization(),cardiologySpecialization()))
                .build();
    }
    public static Office officeForDoctor1() {
        return Office.builder()
                .firstConsultationFee(BigDecimal.valueOf(100.00))
                .followupConsultationFee(BigDecimal.valueOf(50.00))
                .build();
    }

    public static Office officeForDoctor2() {
        return Office.builder()
                .doctor(doctor2())
                .firstConsultationFee(BigDecimal.valueOf(150.00))
                .followupConsultationFee(BigDecimal.valueOf(75.00))
                .build();
    }
    public static Patients patient1() {
        return Patients.builder()
                .patientId(1)
                .name("Alice")
                .surname("Smith")
                .pesel("95011257943")
                .birthDate(LocalDate.of(1990, 5, 15))
                .address("123 Main St")
                .phone("555-1234")
                .user(user4())
                .build();
    }

    public static Patients patient2() {
        return Patients.builder()
                .patientId(2)
                .name("Bob")
                .surname("Johnson")
                .pesel("99022878014")
                .birthDate(LocalDate.of(1985, 10, 20))
                .address("456 Elm St")
                .phone("555-5678")
                .user(user5())
                .build();
    }

    public static Patients patient3() {
        return Patients.builder()
                .patientId(3)
                .name("Jake")
                .surname("John")
                .pesel("99027978014")
                .birthDate(LocalDate.of(1987, 12, 23))
                .address("456 Dln St")
                .phone("555-567-258")
                .user(user6())
                .build();
    }

    public static Patients patient4() {
        return Patients.builder()
                .patientId(4)
                .name("Blake")
                .surname("Mood")
                .pesel("95022878014")
                .birthDate(LocalDate.of(1995, 12, 24))
                .address("456 Main St")
                .phone("555-567-128")
                .user(user7())
                .build();
    }
    public static OfficeDoctorAvailability officeAvailabilityDoctor1() {
        return OfficeDoctorAvailability.builder()
                .officeAvailabilityId(1)
                .office(officeForDoctor1())
                .date(LocalDate.of(2023, 8, 15))
                .startTime(LocalTime.of(8, 0, 0))
                .endTime(LocalTime.of(12, 0, 0))
                .availabilityStatus(true)
                .build();
    }

    public static OfficeDoctorAvailability officeAvailabilityDoctor2_1() {
        return OfficeDoctorAvailability.builder()
                .officeAvailabilityId(2)
                .office(officeForDoctor2())
                .date(LocalDate.of(2023, 8, 17))
                .startTime(LocalTime.of(14, 0, 0))
                .endTime(LocalTime.of(15, 0, 0))
                .availabilityStatus(false)
                .build();
    }

    public static OfficeDoctorAvailability officeAvailabilityDoctor2_2() {
        return OfficeDoctorAvailability.builder()
                .officeAvailabilityId(3)
                .office(officeForDoctor2())
                .date(LocalDate.of(2023, 8, 17))
                .startTime(LocalTime.of(16, 0, 0))
                .endTime(LocalTime.of(17, 0, 0))
                .availabilityStatus(true)
                .build();
    }

    public static OfficeDoctorAvailability officeAvailabilityDoctor2_3() {
        return OfficeDoctorAvailability.builder()
                .officeAvailabilityId(4)
                .office(officeForDoctor2())
                .date(LocalDate.of(2023, 8, 17))
                .startTime(LocalTime.of(17, 0, 0))
                .endTime(LocalTime.of(18, 0, 0))
                .availabilityStatus(true)
                .build();
    }

    public static OfficeDoctorAvailability officeAvailabilityDoctor2_4() {
        return OfficeDoctorAvailability.builder()
                .officeAvailabilityId(5)
                .office(officeForDoctor2())
                .date(LocalDate.of(2023, 8, 22))
                .startTime(LocalTime.of(15, 0, 0))
                .endTime(LocalTime.of(16, 0, 0))
                .availabilityStatus(false)
                .build();
    }

    public static AppointmentStatus scheduledStatus() {
        return AppointmentStatus.builder()
                .appointmentStatusId(1)
                .status(Status.Scheduled)
                .build();
    }

    public static AppointmentStatus completedStatus() {
        return AppointmentStatus.builder()
                .appointmentStatusId(2)
                .status(Status.Completed)
                .build();
    }

    public static AppointmentStatus canceledStatus() {
        return AppointmentStatus.builder()
                .appointmentStatusId(3)
                .status(Status.Canceled)
                .build();
    }
    public static Appointments appointment1() {
        return Appointments.builder()
                .patient(patient1())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-08-17T14:00:00"), ZoneOffset.UTC))
                .appointmentStatus(scheduledStatus())
                .appointmentTakenDate(LocalDate.parse("2023-08-17"))
                .build();
    }

    public static Appointments appointment2() {
        return Appointments.builder()
                .patient(patient2())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-08-22T15:00:00"), ZoneOffset.UTC))
                .appointmentStatus(scheduledStatus())
                .appointmentTakenDate(LocalDate.parse("2023-08-18"))
                .build();
    }

    public static Appointments appointment3() {
        return Appointments.builder()
                .patient(patient1())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-08-17T14:00:00"), ZoneOffset.UTC))
                .appointmentStatus(canceledStatus())
                .appointmentTakenDate(LocalDate.parse("2023-08-17"))
                .build();
    }

    public static Appointments appointment4() {
        return Appointments.builder()
                .patient(patient2())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-08-17T17:00:00"), ZoneOffset.UTC))
                .appointmentStatus(canceledStatus())
                .appointmentTakenDate(LocalDate.parse("2023-08-18"))
                .build();
    }

    public static Appointments appointment5() {
        return Appointments.builder()
                .patient(patient2())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-15T10:00:00"), ZoneOffset.UTC))
                .actualEndTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-15T11:00:00"), ZoneOffset.UTC))
                .appointmentStatus(completedStatus())
                .appointmentTakenDate(LocalDate.parse("2023-06-10"))
                .build();
    }

    public static Appointments appointment6() {
        return Appointments.builder()
                .patient(patient2())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T10:00:00"), ZoneOffset.UTC))
                .actualEndTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T11:00:00"), ZoneOffset.UTC))
                .appointmentStatus(completedStatus())
                .appointmentTakenDate(LocalDate.parse("2023-06-10"))
                .build();
    }

    public static Appointments appointment7() {
        return Appointments.builder()
                .patient(patient3())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T10:00:00"), ZoneOffset.UTC))
                .actualEndTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T11:00:00"), ZoneOffset.UTC))
                .appointmentStatus(canceledStatus())
                .appointmentTakenDate(LocalDate.parse("2023-06-11"))
                .build();
    }

    public static Appointments appointment8() {
        return Appointments.builder()
                .patient(patient3())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-17T10:00:00"), ZoneOffset.UTC))
                .actualEndTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T11:00:00"), ZoneOffset.UTC))
                .appointmentStatus(canceledStatus())
                .appointmentTakenDate(LocalDate.parse("2023-06-12"))
                .build();
    }

    public static Appointments appointment9() {
        return Appointments.builder()
                .patient(patient4())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T11:00:00"), ZoneOffset.UTC))
                .actualEndTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T11:00:00"), ZoneOffset.UTC))
                .appointmentStatus(completedStatus())
                .appointmentTakenDate(LocalDate.parse("2023-06-13"))
                .build();
    }

    public static Appointments appointment10() {
        return Appointments.builder()
                .patient(patient4())
                .office(officeForDoctor2())
                .probableStartTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-24T12:00:00"), ZoneOffset.UTC))
                .actualEndTime(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T11:00:00"), ZoneOffset.UTC))
                .appointmentStatus(completedStatus())
                .appointmentTakenDate(LocalDate.parse("2023-06-23"))
                .build();
    }
    public static Medications aspirinMedications() {
        return Medications.builder()
                .medicationId(1)
                .medicationName("Aspirin")
                .dosage("500mg")
                .frequency("Once a day")
                .duration("7 days")
                .build();
    }

    public static Medications ibuprofenMedications() {
        return Medications.builder()
                .medicationId(2)
                .medicationName("Ibuprofen")
                .dosage("200mg")
                .frequency("Twice a day")
                .duration("10 days")
                .build();
    }

    public static Medications antibioticMedications() {
        return Medications.builder()
                .medicationId(3)
                .medicationName("Antibiotic")
                .dosage("250mg")
                .frequency("Three times a day")
                .duration("14 days")
                .build();
    }
    public static Prescriptions prescription1() {
        return Prescriptions.builder()
                .prescriptionDate(OffsetDateTime.of(
                        LocalDateTime.of(2023, 9, 26, 8, 0), ZoneOffset.UTC))
                .prescriptionDateEnd(OffsetDateTime.of(
                        LocalDateTime.of(2023, 10, 5, 8, 0), ZoneOffset.UTC))
                .prescriptionAvailable(true)
                .medications(Set.of(aspirinMedications()))
                .build();
    }

    public static Prescriptions prescription2() {
        return Prescriptions.builder()
                .prescriptionDate(OffsetDateTime.of(
                        LocalDateTime.of(2023, 9, 27, 10, 30), ZoneOffset.UTC))
                .prescriptionDateEnd(OffsetDateTime.of(
                        LocalDateTime.of(2023, 10, 10, 10, 30), ZoneOffset.UTC))
                .prescriptionAvailable(true)
                .medications(Set.of(ibuprofenMedications(),antibioticMedications()))
                .build();
    }

    public static Prescriptions prescription3() {
        return Prescriptions.builder()
                .prescriptionDate(OffsetDateTime.of(LocalDateTime.of(
                        2023, 6, 16, 11, 0), ZoneOffset.UTC))
                .prescriptionDateEnd(OffsetDateTime.of(LocalDateTime.of(
                        2023, 8, 16, 11, 0), ZoneOffset.UTC))
                .prescriptionAvailable(true)
                .medications(Set.of(antibioticMedications()))
                .build();
    }

    public static Prescriptions prescription4() {
        return Prescriptions.builder()
                .prescriptionDate(OffsetDateTime.of(LocalDateTime.of(
                        2023, 6, 24, 12, 0), ZoneOffset.UTC))
                .prescriptionDateEnd(OffsetDateTime.of(LocalDateTime.of(
                        2023, 8, 24, 12, 0), ZoneOffset.UTC))
                .prescriptionAvailable(true)
                .medications(Set.of(antibioticMedications()))
                .build();
    }
    public static Diseases fluDisease() {
        return Diseases.builder()
                .diseaseId(1)
                .diseaseName("Flu")
                .diseaseDescription("Upper respiratory tract infection")
                .build();
    }

    public static Diseases headacheDisease() {
        return Diseases.builder()
                .diseaseId(2)
                .diseaseName("Headache")
                .diseaseDescription("Frequent headaches and migraines")
                .build();
    }

    public static Diseases feverDisease() {
        return Diseases.builder()
                .diseaseId(3)
                .diseaseName("Fever")
                .diseaseDescription("Condition of fever due to infection")
                .build();
    }

    public static Diseases tonsillitisDisease() {
        return Diseases.builder()
                .diseaseId(4)
                .diseaseName("Tonsillitis")
                .diseaseDescription("In the most common sense, acute inflammation of the palatine tonsils")
                .build();
    }
    public static PatientCard patientCard1() {
        return PatientCard.builder()
                .diseases(Set.of(fluDisease(),headacheDisease()))
                .diagnosisDate(OffsetDateTime.of(LocalDateTime.parse("2023-06-15T10:00:00"), ZoneOffset.UTC))
                .diagnosisNote("Przykładowa diagnoza 1")
                .patient(patient1())
                .doctor(doctor1())
                .prescription(prescription1())
                .build();
    }

    public static PatientCard patientCard2() {
        return PatientCard.builder()
                .diagnosisDate(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T10:00:00"), ZoneOffset.UTC))
                .diagnosisNote("Przykładowa diagnoza 2")
                .patient(patient2())
                .doctor(doctor1())
                .prescription(prescription2())
                .diseases(Set.of(feverDisease()))
                .build();
    }

    public static PatientCard patientCard3() {
        return PatientCard.builder()
                .diagnosisDate(OffsetDateTime.of(LocalDateTime.parse("2023-06-16T11:00:00"), ZoneOffset.UTC))
                .diagnosisNote("Przykładowa diagnoza 3")
                .patient(patient4())
                .doctor(doctor1())
                .prescription(prescription3())
                .build();
    }

    public static PatientCard patientCard4() {
        return PatientCard.builder()
                .diagnosisDate(OffsetDateTime.of(LocalDateTime.parse("2023-06-24T12:00:00"), ZoneOffset.UTC))
                .diagnosisNote("Przykładowa diagnoza 4")
                .patient(patient4())
                .doctor(doctor1())
                .prescription(prescription4())
                .build();
    }
}
