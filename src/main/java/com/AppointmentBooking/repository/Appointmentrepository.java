package com.AppointmentBooking.repository;

import com.AppointmentBooking.entity.Appointment;


import com.AppointmentBooking.payload.Appointmentdto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Appointmentrepository extends MongoRepository<Appointment,String> {

    Appointment findByAppointmentId(String appointmentId);
     List<Appointmentdto> findByPatientId(String patientId);
     List<Appointmentdto> findByDoctorId(String DoctoriId);
}
