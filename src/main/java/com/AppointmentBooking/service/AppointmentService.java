package com.AppointmentBooking.service;

import com.AppointmentBooking.config.resttemplateConfig;
import com.AppointmentBooking.entity.Appointment;
import com.AppointmentBooking.payload.Appointmentdayslots;
import com.AppointmentBooking.payload.Appointmentdto;
import com.AppointmentBooking.payload.AvailableSlots;
import com.AppointmentBooking.repository.Appointmentrepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    @Autowired
    private resttemplateConfig resttemplateConfig;
    @Autowired
    private Appointmentrepository appointmentrepository;
    private final ModelMapper modelMapper;
    private static final ModelMapper modelMapper1 = new ModelMapper();
    public AppointmentService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public String bookappointment(String DoctorId, String Appointmentdate, String patientid,Appointmentdto apppointmentdto) {
        try{
            Appointment appointment = new Appointment();
            String id = UUID.randomUUID().toString();
            appointment.setAppointmentId(id);
            Date date = new Date();
            String todaydate = date.toString();
            appointment.setAppointmentdate(Appointmentdate);
            appointment.setPatientId(patientid);
            appointment.setDoctorId(DoctorId);
            appointment.setBookingdate(todaydate);
            appointment.setMedicalhistory(apppointmentdto.isMedicalhistory());
            appointment.setPatientcontact(apppointmentdto.getPatientcontact());
            appointment.setPatientname(apppointmentdto.getPatientname());
            appointment.setPatientage(apppointmentdto.getPatientage());
            appointment.setActiveAppointment(true);
            appointment.setBookingchargepaid(true);
            Appointment save = appointmentrepository.save(appointment);
            modelMapper.map(appointment,apppointmentdto);

            return "Appointment booked" + save;
        }
        catch (Exception e){
            throw  new RuntimeException("Something went wrong appointment is booked ");
        }
            }




//        return "payment";

    public String cancelappointment(String patientId, String appointmentId) {
        Optional<Appointment> byId = appointmentrepository.findById(appointmentId);
        if (byId == null) {
            throw new RuntimeException("There is no appointment with id " + appointmentId);
        }
        appointmentrepository.deleteById(appointmentId);
        return "Appointment is cancelld";
    }

    public String updateappointment(String appointmentId, Appointment appointment) {
        Appointment byAppointmentId = appointmentrepository.findByAppointmentId(appointmentId);
        AvailableSlots[] doctoravailable = resttemplateConfig.getRestTemplate().getForObject("http://localhost:8081/doctor/available/api/" + appointment.getDoctorId(), AvailableSlots[].class);
        List<AvailableSlots> availableSlotsList = Arrays.stream(doctoravailable).collect(Collectors.toList());
        for (AvailableSlots availableSlots : doctoravailable) {
            if (availableSlots.getDate() != appointment.getAppointmentdate()) {
                return "Doctor is not available for this date";
            }
            appointment.setAppointmentId(byAppointmentId.getAppointmentId());
            Appointment save = appointmentrepository.save(appointment);
            if(save != null){
                return "Appointmet is updated successfully";
            }
        }
        return "payment";
    }
    public List<Appointmentdto> getallappointments(String patientId) {
        List<Appointmentdto> byPatientId = appointmentrepository.findByPatientId(patientId);
        return byPatientId;
    }
    public  List<Appointmentdto> geallappointmemtsbydoctorId(String DoctorId){
        List<Appointmentdto> byDoctorId = appointmentrepository.findByDoctorId(DoctorId);
        return  byDoctorId;
    }
}






