package com.example.mathieu.driftbool;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private TextView axeX;
    private TextView axeY;
    private TextView axeZ;
    private double gravity[] = {0, 0, 0};
    private ShapeDrawable m3Drawable;
    //private double linear_acceleration[] = {0, 0, 0};
    //private ShapeDrawable mDrawable;

    //private int height, width;
    CustomDrawableView mCustomDrawableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mCustomDrawableView = new CustomDrawableView(this);
        //setContentView(mCustomDrawableView);

        Display ecran = getWindowManager().getDefaultDisplay();
        int width = ecran.getWidth();
        int height = ecran.getHeight();

        mCustomDrawableView = (CustomDrawableView) findViewById(R.id.surfaceView);
        //Canvas c = mCustomDrawableView.getCanvas();
        //m3Drawable = new ShapeDrawable(new OvalShape());
        //m3Drawable.setBounds(300, 300, 300 + 20, 300 + 20);
        //m3Drawable.draw(mCustomDrawableView.getCanvas());
        //mCustomDrawableView.setX(300);


        axeX = (TextView)findViewById(R.id.axeX);
        axeY = (TextView)findViewById(R.id.axeY);
        axeZ = (TextView)findViewById(R.id.axeZ);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if( (mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)) != null){
            axeX.setText("sensor configured");
        }else{
            axeX.setText("We've got a problem !");
        }
        //OvalShape oval = new OvalShape();
        //Paint paint = new Paint();
        //paint.setColor(Color.parseColor("#CD5C5C"));
        //Bitmap bg = Bitmap.createBitmap(480, 800, Bitmap.Config.ARGB_8888);
        //Canvas canvas = new Canvas(bg);
        //canvas.drawCircle(width / 2, height / 2, 30, paint);
        //RelativeLayout ll = (RelativeLayout)findViewById(R.id.oval);
        //ll.setBackgroundDrawable(new BitmapDrawable(bg));
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        // In this example, alpha is calculated as t / (t + dT),
        // where t is the low-pass filter's time-constant and
        // dT is the event delivery rate.

        final double alpha = 0.8;


        // Isolate the force of gravity with the low-pass filter.
        axeX.setText(String.valueOf(gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0]));
        axeY.setText(String.valueOf(gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1]));
        axeZ.setText(String.valueOf(gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2]));

        if(gravity[0] > 1){

        }

        // Remove the gravity contribution with the high-pass filter.
        //linear_acceleration[0] = event.values[0] - gravity[0];
        //linear_acceleration[1] = event.values[1] - gravity[1];
        //linear_acceleration[2] = event.values[2] - gravity[2];
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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
