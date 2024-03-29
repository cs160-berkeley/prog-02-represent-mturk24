package com.cs160.joleary.catnip;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by matthewturk on 3/5/16.
 */
public class SecondView extends WearableActivity {
    private static final SimpleDateFormat AMBIENT_DATE_FORMAT =
            new SimpleDateFormat("HH:mm", Locale.US);

    private BoxInsetLayout mContainerView;
    private TextView mTextView;
    private TextView mClockView;
    ImageButton imageV;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        setAmbientEnabled();
        Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
        sendIntent.putExtra("CAT_NAME", "Jorg Mason");
        startService(sendIntent);
        addListenerOnImage();
        addListenerOnButton();

//        mContainerView = (BoxInsetLayout) findViewById(R.id.container);
//        mTextView = (TextView) findViewById(R.id.text);
//        mClockView = (TextView) findViewById(R.id.clock);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
    }

    public void getOtherView(SecondView v) {
        Intent intent = new Intent(getApplicationContext(), FirstSecondView.class);
        startActivity(intent);
    }
    public void getNextView(SecondView v) {
        Intent intent = new Intent(getApplicationContext(), PollView.class);
        startActivity(intent);
    }
    public void addListenerOnImage() {
        imageV = (ImageButton) findViewById(R.id.imageView3);
        imageV.setOnTouchListener(new OnSwipeTouchListener(SecondView.this) {
            @Override
            public void onSwipeTop() {
                Toast.makeText(SecondView.this, "top", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeRight() {
                Toast.makeText(SecondView.this, "right", Toast.LENGTH_SHORT).show();
                getOtherView(SecondView.this);
            }

            public void onSwipeLeft() {
                Toast.makeText(SecondView.this, "left", Toast.LENGTH_SHORT).show();
                getNextView(SecondView.this);
            }

            public void onSwipeBottom() {
                Toast.makeText(SecondView.this, "bottom", Toast.LENGTH_SHORT).show();

            }

        });
    }
    public void addListenerOnButton() {

        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
//                sendIntent.putExtra("CAT_NAME", "Lexy");
                startService(sendIntent);
                Intent intent = new Intent(getApplicationContext(), PollView.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    private void updateDisplay() {
        if (isAmbient()) {
            mContainerView.setBackgroundColor(getResources().getColor(android.R.color.black));
            mTextView.setTextColor(getResources().getColor(android.R.color.white));
            mClockView.setVisibility(View.VISIBLE);

            mClockView.setText(AMBIENT_DATE_FORMAT.format(new Date()));
        } else {
            mContainerView.setBackground(null);
            mTextView.setTextColor(getResources().getColor(android.R.color.black));
            mClockView.setVisibility(View.GONE);
        }
    }
    /* put this into your activity class */
    private SensorManager mSensorManager;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity

    private final SensorEventListener mSensorListener = new SensorEventListener() {

        public void onSensorChanged(SensorEvent se) {
            float x = se.values[0];
            float y = se.values[1];
            float z = se.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta; // perform low-cut filter
            if (mAccel > 12) {
                int rand = (int) Math.ceil(Math.random() * 100);
                String toPass = "\n" + Integer.toString(rand);
                Intent sendZip = new Intent(SecondView.this, SecondPollView.class);
                sendZip.putExtra("rep", toPass);
                startActivity(sendZip);
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }
}