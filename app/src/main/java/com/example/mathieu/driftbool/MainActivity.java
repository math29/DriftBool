package com.example.mathieu.driftbool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;


public class MainActivity extends ActionBarActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private double gravity[] = {0, 0, 0};
    private ShapeDrawable m3Drawable;
    //private double linear_acceleration[] = {0, 0, 0};
    //private ShapeDrawable mDrawable;

    //private int height, width;
    CustomDrawableView mCustomDrawableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        mCustomDrawableView = new CustomDrawableView(this);
        setContentView(mCustomDrawableView);

        mCustomDrawableView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressLint("NewApi")
            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                //now we can retrieve the width and height
                mCustomDrawableView.setViewHeight(mCustomDrawableView.getHeight());
                mCustomDrawableView.setViewWidth(mCustomDrawableView.getWidth());
                //...
                //do whatever you want with them
                //...
                //this is an important step not to keep receiving callbacks:
                //we should remove this listener
                //I use the function to remove it based on the api level!

                if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN)
                    mCustomDrawableView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                else
                    mCustomDrawableView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {

        final double alpha = 0.8;

        //Log.v("Interruption Sensor", "On a une interruption ici !!!!!!!!!! La valeur est : "+gravity[0]);

        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];


        if(gravity[0] < -1.0){
            Log.v("1 : ", "Ici c'est le 1");
            mCustomDrawableView.moveViewToScreenCenter(mCustomDrawableView, "right");
        }
        else if(gravity[0] > 1.0){
            Log.v("2 : ", "Ici c'est le 2");
            mCustomDrawableView.moveViewToScreenCenter(mCustomDrawableView, "left");
        }

        if(gravity[1] > 1.0){
            Log.v("3 : ", "Ici c'est le 3");
            mCustomDrawableView.moveViewToScreenCenter(mCustomDrawableView, "down");
        }else if(gravity[1] < -1.0){
            Log.v("4 : ", "Ici c'est le 4");
            mCustomDrawableView.moveViewToScreenCenter(mCustomDrawableView, "up");
        }

        // Remove the gravity contribution with the high-pass filter.
        //linear_acceleration[0] = event.values[0] - gravity[0];
        //linear_acceleration[1] = event.values[1] - gravity[1];
        //linear_acceleration[2] = event.values[2] - gravity[2];
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
