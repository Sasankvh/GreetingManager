package com.example.sasankvh.greetingmanager;

/**
 * Created by SasankVH on 20-Feb-18.
 */

public class EventDetails {
    private String phoneNumber;
    private String Message;
    private String Name;
    private String Date;
    public EventDetails(){

    }
    public void setDetails(String Name,String phoneNumber,String Date,String Message){
        this.Name=Name;
        this.phoneNumber=phoneNumber;
        this.Date=Date;
        this.Message=Message;
    }
    public String getName(){
        return this.Name;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public String getMessage(){
        return this.Message;
    }
    public String getDate(){
        return this.Date;
    }
}
