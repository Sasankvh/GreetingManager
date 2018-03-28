package com.example.sasankvh.greetingmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by SasankVH on 20-Feb-18.
 */

public class DataBaseConnection extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="EventManager";
    private static final String TABLE_EVENTS="EVENTS";
    private static final String KEY_NAME="name";
    private static final String KEY_PHONE="phone";
    private static final String KEY_DATE="date";
    private static final String KEY_MESSAGE="message";
    public DataBaseConnection(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        //System.out.println("Done till DataBaseConnection Constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVENTS_TABLE="CREATE TABLE "+TABLE_EVENTS+" ( "+ KEY_NAME
+" VARCHAR[20] , "+KEY_PHONE+" VARCHAR[10] , "+KEY_DATE+" VARCHAR[15] , "+KEY_MESSAGE +" VARCHAR[200] "+ " ) ";                       ;
        db.execSQL(CREATE_EVENTS_TABLE);
        //System.out.println("Done till onCreate in DatabaseConnection class");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_EVENTS);

        onCreate(db);
    }
    void addEvent(EventDetails eventDetails){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,eventDetails.getName());
        values.put(KEY_PHONE,eventDetails.getPhoneNumber());
        values.put(KEY_DATE,eventDetails.getDate());
        values.put(KEY_MESSAGE,eventDetails.getMessage());
        db.insert(TABLE_EVENTS,null,values);
        db.close();
        //System.out.println("Record Inserted");
    }
     public ArrayList<EventDetails> getEventDetails(){
         ArrayList<EventDetails> eventsList =new ArrayList<EventDetails>();
         String selectQuery="SELECT *FROM "+TABLE_EVENTS;
         SQLiteDatabase db=this.getWritableDatabase();
         Cursor cursor=db.rawQuery(selectQuery,null);
         if(cursor.moveToFirst()){
             do{
                 EventDetails eventDetails=new EventDetails();
                 eventDetails.setDetails(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
                 eventsList.add(eventDetails);
             }while(cursor.moveToNext());
         }
         System.out.println("Sucessfull found all the records");
        return eventsList;
    }
    public void deleteContact(EventDetails eventDetails){
        SQLiteDatabase db=this.getWritableDatabase();
        System.out.println("Ikkada daka vacha");
      //  String deleteQuery="DELETE *FROM "+TABLE_EVENTS +" WHERE "+KEY_NAME +" ="+eventDetails.getName();
        System.out.println("Till here too");
        //db.delete()

        System.out.println("Deleted Record");
        //System.out.println(cursor.getColumnCount());
        db.delete(TABLE_EVENTS,KEY_NAME+" =? ",new String[]{eventDetails.getPhoneNumber()});
        System.out.println("Reached Altlast");
    }

}
