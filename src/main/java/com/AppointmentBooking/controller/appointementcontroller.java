package com.AppointmentBooking.controller;

import com.AppointmentBooking.entity.Appointment;
import com.AppointmentBooking.payload.Appointmentdayslots;
import com.AppointmentBooking.payload.Appointmentdto;
import com.AppointmentBooking.repository.Appointmentrepository;
import com.AppointmentBooking.service.AppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor/appointemnt")
public class appointementcontroller {

@Autowired
private AppointmentService appointmentService;
  //http://localhost:8080/doctor/appointemnt/book
 @PostMapping("/{DoctorId}/{Availabledate}/{patientid}")
    public ResponseEntity<?> bookappointment( @PathVariable  String DoctorId, @PathVariable String Availabledate,@PathVariable String patientid, @RequestBody Appointmentdto appointmentdto){
     String Appointment = appointmentService.bookappointment(DoctorId,Availabledate,patientid,appointmentdto);
     return new ResponseEntity<>(Appointment, HttpStatus.OK);
 }
 @DeleteMapping("/cancelappointment/{appointmentId}")
    public ResponseEntity<?> camcelappointment( @PathVariable String patientId,@PathVariable String appointmentId){
     String cancelappointment = appointmentService.cancelappointment(patientId, appointmentId);
     return new ResponseEntity<>(cancelappointment,HttpStatus.OK);
 }
 @PutMapping("/update/{appointemntId}")
    public ResponseEntity<?> updateappointment(@PathVariable String appointementId,@RequestBody Appointment appointment){
     String updateappointment = appointmentService.updateappointment(appointementId, appointment);
     return new ResponseEntity<>(updateappointment,HttpStatus.OK);
 }

@GetMapping("/patient/{patientId}")
    public ResponseEntity<?> getallappointmentby(@PathVariable String patientId){
    List<Appointmentdto> getallappointments =appointmentService.getallappointments(patientId);
    return new ResponseEntity<>(getallappointments,HttpStatus.OK);
}
   // http://localhost:8080/doctor/appointemnt/doctor/
@GetMapping("/doctor/{DoctorId}")
    public ResponseEntity<?> getallappointemntsbydocorid(@PathVariable String DoctorId){
    List<Appointmentdto> appointmentdtos = appointmentService.geallappointmemtsbydoctorId(DoctorId);
    return new ResponseEntity<>(appointmentdtos,HttpStatus.OK);
}
}
