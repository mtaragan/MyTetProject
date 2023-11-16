package com.project1;

import java.util.ArrayList;

public class Partner {
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private ArrayList<String > availableDates;

    public Partner(String firstName, String lastName, String email, String country, ArrayList<String > availableDates) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.availableDates = availableDates;
    }
}
