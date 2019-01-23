package com.loftschool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate() called");
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG,"onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume() called");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(TAG,"onPause() called");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(TAG,"onStop() called");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG,"onDestroy() called");
    }
}
