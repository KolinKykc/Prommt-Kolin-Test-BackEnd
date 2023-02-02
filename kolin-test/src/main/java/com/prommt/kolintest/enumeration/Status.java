package com.prommt.kolintest.enumeration;

public enum Status {
    PAID("PAID"),
    NOT_PAID("NOT_PAID");

    private final String status;

    Status(String status){
        this.status = status;
    }

    public  String getStatus(){
        return this.status;
    }
}
