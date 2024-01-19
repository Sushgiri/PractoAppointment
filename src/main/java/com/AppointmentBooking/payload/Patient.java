package com.AppointmentBooking.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    private  String patiendId;
    private  String patientname;
    private String age;
    private String gender;
    private String phone;
    private boolean Medicalhistory;

}
