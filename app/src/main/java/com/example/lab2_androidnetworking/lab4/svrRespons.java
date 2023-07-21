package com.example.lab2_androidnetworking.lab4;

public class svrRespons {
    private Prd prd;
    private String meString;

    public svrRespons(Prd prd, String meString) {
        this.prd = prd;
        this.meString = meString;
    }

    public Prd getPrd() {
        return prd;
    }

    public void setPrd(Prd prd) {
        this.prd = prd;
    }

    public String getMeString() {
        return meString;
    }

    public void setMeString(String meString) {
        this.meString = meString;
    }
}
