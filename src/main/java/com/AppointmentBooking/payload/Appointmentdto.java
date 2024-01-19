package com.AppointmentBooking.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Appointmentdto {

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
