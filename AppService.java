package com.example.sasankvh.greetingmanager;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.app.usage.UsageEvents;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.os.EnvironmentCompat;
import android.telephony.SmsManager;
import android.text.format.DateFormat;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AppService extends Service {
    DataBaseConnection dbconn=new DataBaseConnection(this);
//    Date c = Calendar.getInstance().getTime();
//        System.out.println("Current time => " + c);
//
//    SimpleDateFormat df = new SimpleDateFormat("dd MM yyyy");
//    String formattedDate = df.format(c);
//        System.out.println(formattedDate);
//    ArrayList<EventDetails>  eventList=dbconn.getEventDetails();
    public AppService() {
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
    public void onTaskRemoved(Intent rootIntent){
        Intent restartServieceIntent=new Intent(getApplicationContext(),this.getClass());
        restartServieceIntent.setPackage(getPackageName());
        startService(restartServieceIntent);
        super.onTaskRemoved(rootIntent);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // do your jobs here

        Date c = Calendar.getInstance().getTime();
        //System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd MM yyyy");
        String formattedDate = df.format(c);
        //System.out.println(formattedDate);
        ArrayList<EventDetails>  eventList=dbconn.getEventDetails();
        try {
            SmsManager smsManager = SmsManager.getDefault();
            if(eventList.size()>=1){
                System.out.println("OK arraylistSize greater than 1");
                for(EventDetails i:eventList){
                    System.out.println(i.getDate());
                    System.out.println(formattedDate);
                    if(i.getDate().toString().trim().equals(formattedDate.toString().trim())){
                        System.out.println("Date matched");
                        System.out.println(i.getName());
                        System.out.println(i.getDate());
                        System.out.println(i.getPhoneNumber());
                       //smsManager.sendTextMessage(i.getPhoneNumber().toString().trim()+"","",i.getMessage(),null,null);
                        //System.out.println(i.getPhoneNumber());
                        dbconn.deleteContact(i);

                    }
                }
                System.out.println("Sent Message");
            }


            //Toast.makeText(this, "Sent!", Toast.LENGTH_SHORT).show();
        }catch (NullPointerException e){
            System.out.println("Null pointer");
        }
        catch (Exception e) {
            System.out.println("Could not aend");
           // Toast.makeText(this, "Sent!", Toast.LENGTH_SHORT).show();
        }
        System.out.println(eventList.size());
        //onTaskRemoved(intent);
        return super.onStartCommand(intent, flags, startId);
    }

}