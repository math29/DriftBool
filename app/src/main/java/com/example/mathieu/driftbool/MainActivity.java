package com.example.mathieu.driftbool;

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

        /*Display ecran = getWindowManager().getDefaultDisplay();
        int width = ecran.getWidth();
        int height = ecran.getHeight();
        */
        //mCustomDrawableView = (CustomDrawableView) findViewById(R.id.surfaceView);
        //Canvas c = mCustomDrawableView.getCanvas();
        //m3Drawable = new ShapeDrawable(new OvalShape());
        //m3Drawable.setBounds(300, 300, 300 + 20, 300 + 20);
        //m3Drawable.draw(mCustomDrawableView.getCanvas());

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {

        final double alpha = 0.8;

        Log.v("Interruption Sensor", "On a une interruption ici !!!!!!!!!! La valeur est : "+gravity[0]);

        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];


        if(gravity[0] > 1.0){
            mCustomDrawableView.addX(10);
            mCustomDrawableView.move(1);
            Log.v("Mouvement ", "On monte vers le haut ici, notre valeur de gravity 0 est :  "+gravity[0]);
        }
        /*else if(gravity[0] < -1.0){
            mCustomDrawableView.move(3);
        }

        if(gravity[1] > 1.0){
            mCustomDrawableView.move(2);
        }else if(gravity[1] < -1.0){
            mCustomDrawableView.move(4);
        }*/

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
