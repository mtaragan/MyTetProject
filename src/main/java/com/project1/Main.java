package com.project1;

public class Main {
    public static void main(String[] args) throws Exception{
        System.out.println("============ start1 =33333===");

        callHttpRequests();
    }

    public static void callHttpRequests() throws Exception {
        //HttpUtil.sendGetRequest();

        HttpUtilAlbum.getAlbum();

        HttpUtilAlbum.createAlbum();
    }
}
