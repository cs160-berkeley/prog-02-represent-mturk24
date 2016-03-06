package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by matthewturk on 3/5/16.
 */
public class SecondClass extends Activity {
    Button button3;
    Button button4;
    Button button5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        addListenerOnButton3();
        addListenerOnButton4();
        addListenerOnButton5();
    }

    public void getNewView(View v) {
        Intent intent = new Intent(getApplicationContext(), ThirdClass.class);
        startActivity(intent);
    }
    public void addListenerOnButton3() {

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                sendIntent.putExtra("CAT_NAME", "Matt");
                startService(sendIntent);
                Intent intent = new Intent(getApplicationContext(), ThirdClass.class);
                startActivity(intent);

            }
        });
    }
    public void addListenerOnButton4() {

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                sendIntent.putExtra("CAT_NAME", "Hansmeet");
                startService(sendIntent);
                Intent intent = new Intent(getApplicationContext(), ThirdClass.class);
                startActivity(intent);

            }
        });
    }
    public void addListenerOnButton5() {

        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                sendIntent.putExtra("CAT_NAME", "Duddu");
                startService(sendIntent);
                Intent intent = new Intent(getApplicationContext(), ThirdClass.class);
                startActivity(intent);

            }
        });
    }

//    public void addListenerOnButton() {
//
//
//        button3 = (Button) findViewById(R.id.button3);
//        button4 = (Button) findViewById(R.id.button4);
//        button5 = (Button) findViewById(R.id.button5);
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (myInput.getText().length() == 5) {
//                    Intent intent = new Intent(getApplicationContext(), ThirdClass.class);
//                    startActivity(intent);
//                }
//            }
//        });
//    }
}

