package com.example.linea_rapida.model;

public class CaseTicket {

    public static final String STATUS_STARTED = "1";
    public static final String STATUS_IN_PROGRESS = "2";
    public static final String STATUS_FINISHED = "3";

    private long date;
    private String username;
    private String userid;
    private String location;
    private String body;
    private String status;
    private String pacientName;
    private long number;
    private long pacientAge;
    private String id;


    public CaseTicket() {
    }

    public CaseTicket(long date, String username, String userid, String location, String body, String status, String pacientName, long number, long pacientAge, String id) {
        this.date = date;
        this.username = username;
        this.userid = userid;
        this.location = location;
        this.body = body;
        this.status = status;
        this.pacientName = pacientName;
        this.number = number;
        this.pacientAge = pacientAge;
        this.id = id;
    }


    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getPacientName() {
        return pacientName;
    }

    public void setPacientName(String pacientName) {
        this.pacientName = pacientName;
    }

    public long getPacientAge() {
        return pacientAge;
    }

    public void setPacientAge(long pacientAge) {
        this.pacientAge = pacientAge;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
