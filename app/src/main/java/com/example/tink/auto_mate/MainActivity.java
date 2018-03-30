package com.example.tink.auto_mate;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";
    private SensorManager sensorManager;
    private Sensor accelerometer, gyroscope;
    private TextView xvalue, yvalue, zvalue, xGyroVal, yGyroVal, zGyroVal;

    double previousAcc = 0, currAcc = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xvalue = (TextView) findViewById(R.id.xValue);
        yvalue = (TextView) findViewById(R.id.yValue);
        zvalue = (TextView) findViewById(R.id.zValue);
        xGyroVal = (TextView) findViewById(R.id.xGyro);
        yGyroVal = (TextView) findViewById(R.id.yGyro);
        zGyroVal = (TextView) findViewById(R.id.zGyro);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if(accelerometer != null) {
            sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);
            Log.d(TAG,"onCreate: Registered accelerometer listener");

        }else{
            xvalue.setText("Accelerometer not supported");
            yvalue.setText("Accelerometer not supported");
            zvalue.setText("Accelerometer not supported");

        }


        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if(gyroscope != null) {
            sensorManager.registerListener(MainActivity.this, gyroscope, SensorManager.SENSOR_DELAY_FASTEST);
            Log.d(TAG,"onCreate: Registered Gyroscope listener");

        }else{
            xGyroVal.setText("Gyroscope not supported");
            yGyroVal.setText("Gyroscope not supported");
            zGyroVal.setText("Gyroscope not supported");
        }


    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            double acc = Math.sqrt(Math.pow(sensorEvent.values[0], 2) + Math.pow(sensorEvent.values[1], 2) + Math.pow(sensorEvent.values[2], 2));

            Log.d(TAG, "X: " + sensorEvent.values[0] + " Y: " + sensorEvent.values[1] + "Z: " + sensorEvent.values[2]);
            xvalue.setText("Acceleration = " + acc);
            yvalue.setText("Yvalue = " + sensorEvent.values[1]);
            zvalue.setText("Zvalue = " + sensorEvent.values[2]);
        }

        else if (sensor.getType() == Sensor.TYPE_GYROSCOPE)
        {
            Log.d(TAG, "X gyro: " + sensorEvent.values[0] + " Y Gyro: " + sensorEvent.values[1] + "Z Gyro: " + sensorEvent.values[2]);
            xGyroVal.setText("X gyro Val = " + sensorEvent.values[0]);
            yGyroVal.setText("Y Gyro value = " + sensorEvent.values[1]);
            zGyroVal.setText("Z Gyro value = " + sensorEvent.values[2]);


        }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
