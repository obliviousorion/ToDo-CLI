package com.orion.oblivious.ToDo.models;

public enum Status{
    COMPLETED("completed"),
    IN_PROGRESS("in-progress"),
    NOT_STARTED("not-started");

    private final String value;

    // private constructor that assigns the string value to each of the constants
    Status(String value) {
        this.value = value;
    }

    // factory method
    public static Status fromString(String value){
        for(Status status : Status.values()){
            if(status.value.equalsIgnoreCase(value) || status.name().equalsIgnoreCase(value)){
                return status;
            }
        } throw new IllegalArgumentException("Unrecognized Status value: " + value);
    }
}
