package com.example.accelerometermagnetometer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private final static String LOG_TAG = MainActivity.class.getName();
    TextView accelx, accely, accelz;
    TextView magx, magy, magz;
    TextView position;
    private SensorManager sm;
    private Sensor accel;
    private Sensor magnetometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        accelx = (TextView) findViewById(R.id.accelx);
        accely = (TextView) findViewById(R.id.accely);
        accelz = (TextView) findViewById(R.id.accelz);
        magx = (TextView) findViewById(R.id.magx);
        magy = (TextView) findViewById(R.id.magy);
        magz = (TextView) findViewById(R.id.magz);
        position = (TextView) findViewById(R.id.position);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sm.registerListener(this, accel, SensorManager.SENSOR_DELAY_GAME);
        sm.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor eachSensor = event.sensor;
        float xa = 0, ya = 0, za = 0;
        float xm = 0, ym, zm = 0;
        switch (eachSensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                xa = event.values[0];
                accelx.setText(String.valueOf(xa));
                ya = event.values[1];
                accely.setText(String.valueOf(ya));
                za = event.values[2];
                accelz.setText(String.valueOf(za));
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                xm = event.values[0];
                magx.setText(String.valueOf(xm));
                ym = event.values[1];
                magy.setText(String.valueOf(ym));
                zm = event.values[2];
                magz.setText(String.valueOf(zm));
                break;

            default:
                break;
        }

        if(xa>4.5 && ya<4.5 && ya>-4.5){
            position.setText("horizontal izquierdo");
        }
        else if(xa<4.5 && xa>-4.5 && ya>4.5){
            position.setText("Vertical arriba");
        }
        else if(xa<-4.5 && ya>-4.5 && ya<4.5){
            position.setText("Horizontal derecho");
        }
        else if(xa>-4.5 && xa<4.5 && ya<-4.5){
            position.setText("Vertical abajo");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}