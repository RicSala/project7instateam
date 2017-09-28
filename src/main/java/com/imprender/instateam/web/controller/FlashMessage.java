package com.imprender.instateam.web.controller;

public class FlashMessage {
    private String meesage;
    private Status status;

    public FlashMessage(String meesage, Status status) {
        this.meesage = meesage;
        this.status = status;
    }

    public String getMeesage() {
        return meesage;
    }

    public Status getStatus() {
        return status;
    }

    public static enum Status {
        SUCCESS, FAILURE
    }



}
