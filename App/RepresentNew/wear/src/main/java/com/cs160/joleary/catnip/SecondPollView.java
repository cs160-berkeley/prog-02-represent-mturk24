package com.cs160.joleary.catnip;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by matthewturk on 3/5/16.
 */
public class SecondPollView extends WearableActivity {
    private static final SimpleDateFormat AMBIENT_DATE_FORMAT =
            new SimpleDateFormat("HH:mm", Locale.US);

    private BoxInsetLayout mContainerView;
    private TextView mTextView;
    private TextView mClockView;
    TextView t;
    private TextView int1;
    private TextView int2;
    private int num;
    private String temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_poll_layout);
        setAmbientEnabled();
        int1 = (TextView) findViewById(R.id.textView5);
        int1.setText((String) Integer.toString((int) Math.ceil(Math.random() * 100)));
        temp = int1.getText().toString();
        num = 100 - Integer.parseInt(temp);

        int2 = (TextView) findViewById(R.id.textView6);
        int2.setText((String) Integer.toString(num));
        addListenerOnImage();
    }

//        Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
//        sendIntent.putExtra("CAT_NAME", "Bob Monk");
//        startService(sendIntent);
//        addListenerOnImage();

    public void getOtherView(SecondPollView v) {
        Intent intent = new Intent(getApplicationContext(), SecondView.class);
        startActivity(intent);
    }
    public void addListenerOnImage() {
        t = (TextView) findViewById(R.id.textView4);
        t.setOnTouchListener(new OnSwipeTouchListener(SecondPollView.this) {
            @Override
            public void onSwipeTop() {
                Toast.makeText(SecondPollView.this, "top", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeRight() {
                Toast.makeText(SecondPollView.this, "right", Toast.LENGTH_SHORT).show();
                getOtherView(SecondPollView.this);
            }

            public void onSwipeLeft() {
                Toast.makeText(SecondPollView.this, "left", Toast.LENGTH_SHORT).show();

            }

            public void onSwipeBottom() {
                Toast.makeText(SecondPollView.this, "bottom", Toast.LENGTH_SHORT).show();
            }

        });
    }

//        mContainerView = (BoxInsetLayout) findViewById(R.id.container);
//        mTextView = (TextView) findViewById(R.id.text);
//        mClockView = (TextView) findViewById(R.id.clock);

//    public void getOtherView(MainActivity v) {
//        Intent intent = new Intent(getApplicationContext(), FirstSecondView.class);
//        startActivity(intent);
//    }
//    public void addListenerOnImage() {
//        imageV = (ImageView) findViewById(R.id.imageView1);
//        imageV.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
//        @Override
//        public void onSwipeTop() {
//            Toast.makeText(MainActivity.this, "top", Toast.LENGTH_SHORT).show();
//        }
//        public void onSwipeRight() {
//            Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
//        }
//        public void onSwipeLeft() {
//            Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
//            getOtherView(MainActivity.this);
//        }
//        public void onSwipeBottom() {
//            Toast.makeText(MainActivity.this, "bottom", Toast.LENGTH_SHORT).show();
//        }
//
//        });
//    }

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
}
