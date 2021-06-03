package com.example.linea_rapida.model;

public class CaseTicket {

    private long date;
    private String username;
    private String userid;
    private String location;
    private String body;
    private String status;
    private long number;

    public CaseTicket() {
    }

    public CaseTicket(long date, String username, String userid, String location, String body, String status, long number) {
        this.date = date;
        this.username = username;
        this.userid = userid;
        this.location = location;
        this.body = body;
        this.status = status;
        this.number = number;
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
}
