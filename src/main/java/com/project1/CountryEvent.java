package com.project1;

import java.util.ArrayList;
import java.util.List;

public class CountryEvent {
    private int attendeeCount;
    private List<String> attendees;
    private String name;
    private String startDate;

    public CountryEvent(int attendeeCount, List<String> attendees, String name, String startDate) {
        this.attendeeCount = attendeeCount;
        this.attendees = attendees;
        this.name = name;
        this.startDate = startDate;
    }


    public int getAttendeeCount() {
        return attendeeCount;
    }

    public List<String> getAttendees() {
        return attendees;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setAttendeeCount(int attendeeCount) {
        this.attendeeCount = attendeeCount;
    }

    public void setAttendees(ArrayList<String> attendees) {
        this.attendees = attendees;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}

    /*
    "attendeeCount": 1,
            "attendees": [
            "cbrenna@hubspotpartners.com"
            ],
            "name": "Ireland",
            "startDate": "2017-04-29"
},
        */

