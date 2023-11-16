package com.project1;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class HttpUtil {
    static final String API_URL = "https://candidate.hubteam.com/candidateTest/v3/problem/dataset?userKey=e0cb975c2db47bd733d0611ea4f4";
    static final String API_POST_URL = "https://candidate.hubteam.com/candidateTest/v3/problem/result?userKey=e0cb975c2db47bd733d0611ea4f4";

    public static ArrayList<Partner> sendGetRequest() throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();

        ArrayList<Partner> partners;
        //send get request
        try {
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI(API_URL))
                    .GET()
                    .build();
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            int gettStatusCode = getResponse.statusCode();
            System.out.println("==get status code =" + gettStatusCode);
            String getResponseString = getResponse.body();
            partners = parseResponse(getResponseString);
            System.out.println("==get object count =" + partners.size());
            return partners;
        } catch (Exception e) {
            System.out.println("get failed!!!");
            e.printStackTrace();
            throw e;
        }
    }

    private static ArrayList<Partner> parseResponse(String responseBody) {
        ArrayList<Partner> partnersList = new ArrayList<>();
        JSONObject json = new JSONObject(responseBody.toString());
        JSONArray partners = json.getJSONArray("partners");
        for (int i=0; i<partners.length(); i++) {
            JSONObject partner = partners.getJSONObject(i);
            String firstName = partner.getString("firstName");
            String lastName = partner.getString("lastName");
            String email = partner.getString("email");
            String country = partner.getString("country");
            JSONArray availableDates = partner.getJSONArray("availableDates");
            ArrayList<String> dateList = new ArrayList<String>();
            if (availableDates != null) {
                for (int j=0;j<availableDates.length();j++){
                    dateList.add(availableDates.getString(j));
                }
            }
            Partner partnerObj = new Partner(firstName, lastName, email, country, dateList);
            partnersList.add(partnerObj);
        }
        return partnersList;
    }


    //POST
    public static void sendPostRequest(ArrayList<CountryEvent> countries) throws Exception {
        String postRequestString = parseCountyEventsToJson(countries);

        try {
            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(new URI(API_POST_URL))
                    .setHeader("Content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(postRequestString))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = null;

            postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());

            String postResponseString = postResponse.body();
            int statusCode = postResponse.statusCode();
            String temp = "44";
            System.out.println("status code =====999:::" + statusCode);
            //System.out.println("==post response =");
            //System.out.println(postResponseString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String parseCountyEventsToJson(ArrayList<CountryEvent> countries) {
        JSONArray countriesJsonArray = new JSONArray();
        for (CountryEvent countryEvent : countries) {
            /*
            JSONObject jo = new JSONObject();
            jo.put("attendeeCount", countryEvent.getAttendeeCount());
            JSONArray attendeesArray = new JSONArray();
            for (String att : countryEvent.getAttendees()) {
                attendeesArray.put(att);
            }
            jo.put("attendees", attendeesArray);
            jo.put("name", countryEvent.getName());
            jo.put("startDate", countryEvent.getStartDate());
            countriesJsonArray.put(jo);
            */

            JSONObject jo1 = getJSONObject(countryEvent);
            countriesJsonArray.put(jo1);
        }

        JSONObject mainObj = new JSONObject();
        mainObj.put("countries", countriesJsonArray);
        return mainObj.toString();
    }

    private static JSONObject getJSONObject(CountryEvent countryEvent) {
        JSONObject jsonObject = new JSONObject();
        try {
            Field changeMap = jsonObject.getClass().getDeclaredField("map");
            changeMap.setAccessible(true);
            changeMap.set(jsonObject, new LinkedHashMap<>());
            changeMap.setAccessible(false);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            System.out.println("unable to create json object");
        }
        jsonObject.put("attendeeCount", countryEvent.getAttendeeCount());
        JSONArray attendeesArray = new JSONArray();
        for (String att : countryEvent.getAttendees()) {
            attendeesArray.put(att);
        }
        jsonObject.put("attendees", attendeesArray);
        jsonObject.put("name", countryEvent.getName());
        jsonObject.put("startDate", countryEvent.getStartDate());
        return jsonObject;
    }
}
