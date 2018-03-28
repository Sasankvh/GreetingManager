package com.example.sasankvh.greetingmanager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class PickedDate extends AppCompatActivity {
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picked_date);


    }
    public void onClick(View view){
        Bundle Data=getIntent().getExtras();
        str=new String(Data.getString("PickedDate").toString().trim());
        TextView Name=(TextView)findViewById(R.id.Name);
        TextView PhoneNo=(TextView)findViewById(R.id.PhoneNumber);
        TextView TextMessage=(TextView)findViewById(R.id.GreetingMessage);
        Button SetGreeting=(Button)findViewById(R.id.SetGreeting);

        String name=Name.getText().toString().trim();
        String phoneNo=PhoneNo.getText().toString().trim();
        String message=TextMessage.getText().toString().trim();
        String Record=name+" "+phoneNo+" "+str+" "+message;
        //System.out.println("Done till Here");
        EventDetails evnobj=new EventDetails();
        evnobj.setDetails(name,phoneNo,str,message);
        DataBaseConnection dbconn=new DataBaseConnection(this);
        dbconn.addEvent(evnobj);
        //System.out.println("Done till Here");
        ArrayList<EventDetails> eventsList=dbconn.getEventDetails();
        for(EventDetails i:eventsList){
           System.out.println(i.getName()+" "+i.getPhoneNumber()+" "+i.getDate()+" "+i.getMessage());
        }
    }
}