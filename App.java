package com.example.sasankvh.greetingmanager;

import android.app.Application;
import android.content.Intent;

/**
 * Created by SasankVH on 20-Feb-18.
 */

public class App extends Application {
    public void onCreate(){
        super.onCreate();
        startService(new Intent(this,AppService.class));
    }
}
