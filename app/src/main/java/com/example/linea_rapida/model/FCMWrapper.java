package com.example.linea_rapida.model;

public class FCMWrapper {

    private String to;
    private FCMMessage data;

    public FCMWrapper() {
    }

    public FCMWrapper(String to, FCMMessage data) {
        this.to = to;
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public FCMMessage getData() {
        return data;
    }

    public void setData(FCMMessage data) {
        this.data = data;
    }
}
