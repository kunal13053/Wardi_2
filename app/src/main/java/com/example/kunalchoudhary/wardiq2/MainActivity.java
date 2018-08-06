package com.example.kunalchoudhary.wardiq2;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import java.util.*;
import android.os.Bundle;
import android.util.Log;
import android.hardware.SensorEvent;
import android.view.View;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.Button;
import android.hardware.SensorEventListener;



public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private SensorEventListener sensorEventListener;
    private Sensor gyroscope;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView message  = (TextView) findViewById(R.id.sms);

           message.setText("See,I told you");




        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if(gyroscope == null) {
            message.setText("Your Device doesn't have a gyroscope. This application uses gyroscope");
        }

        else
        {
            sensorEventListener = new SensorEventListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onSensorChanged(SensorEvent sensorEvent)
                {

                    if (sensorEvent.values[1] < -0.7f)
                    {

                        message.setText("Don’t you get bored of me");



                    }

                    else if (sensorEvent.values[1] > 0.7f )
                    {
                       
                        message.setText("See,I told you");


                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            };

            sensorManager.registerListener(sensorEventListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        sensorManager.registerListener(sensorEventListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);

    }
}