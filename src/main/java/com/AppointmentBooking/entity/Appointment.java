package com.AppointmentBooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Appointment")
public class Appointment {



      @Id
     private  String appointmentId;
    @NotEmpty(message = "Enter appointemtdate")
private String Appointmentdate;
    @NotEmpty(message = "Enter doctorid")
private String doctorId;


    @NotEmpty(message = "Enter bookingdate")
private String bookingdate;
    private String patientname;
    private String patientage;
    private String patientId;
    private boolean medicalhistory;
    private String patientcontact;
private  boolean bookingchargepaid;
private boolean  ActiveAppointment;
}
