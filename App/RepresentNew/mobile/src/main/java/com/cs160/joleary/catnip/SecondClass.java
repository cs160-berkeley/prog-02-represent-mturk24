package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.app.ListActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


/**
 * Created by matthewturk on 3/5/16.
 */
public class SecondClass extends Activity {
    Button button3;
    Button button4;
    Button button5;
    String zipCode;
    String locLat;
    String locLong;
    String url;
    JsonObject jsonObj;
    JsonElement jsonElement;
    JsonArray dataJSON;
    ListView list;
    String[] itemname = {
            "",
            ""

    };
    Integer[] imgid={
            R.drawable.bobjindal,
            R.drawable.rickrosscompressor,
            R.drawable.bieber,

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        Intent currentIntent = getIntent();



//        CustomListAdapter adapter = new CustomListAdapter(this, itemname, imgid);
//        list = (ListView) findViewById(R.id.list);
//        list.setAdapter(adapter);
//
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                // TODO Auto-generated method stub
//                String Slecteditem = itemname[+position];
//                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        if (currentIntent != null) {
//            onStartCommand(currentIntent);
//        }



        addListenerOnButton3();
        addListenerOnButton4();
        addListenerOnButton5();

    }


    public void getNewView(View v) {
        Intent intent = new Intent(getApplicationContext(), ThirdClass.class);
        startActivity(intent);
    }

    public void onStartCommand(Intent intent) {
        Log.d("bbb", "hereLOL");
        // Which cat do we want to feed? Grab this info from INTENT
        // which was passed over when we called startService
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Log.d("bbb", "hereHAHA");
            zipCode = extras.getString("ZIP");
//            Log.d("myZip", zipCode);
            locLat = extras.getString("Lat");
            Log.d("myLat", locLat);
            locLong = extras.getString("Long");
            Log.d("myLong", locLong);
            if ((zipCode == null) && (locLat != null) && (locLong != null)) {
                url = "congress.api.sunlightfoundation.com/legislators/locate?latitude=" + locLat + "&longitude=" + locLong + "&apikey=455c52eb62e14c09a8a8934b1cd5d60d";
                Log.d("axy", "here1");
                Log.d("URLLL BBY", url);
            }
            else if ((zipCode != null) && (locLat != null) && (locLong != null)) {
                url = "congress.api.sunlightfoundation.com/legislators/locate?latitude=" + locLat + "&longitude=" + locLong + "&zip=" + zipCode + "&apikey=455c52eb62e14c09a8a8934b1cd5d60d";
                Log.d("axz", "here2");
            }
            else if ((zipCode != null) && (locLat == null) && (locLong == null)) {
                url = "congress.api.sunlightfoundation.com/legislators/locate?zip=" + zipCode + "&apikey=455c52eb62e14c09a8a8934b1cd5d60d";
                Log.d("azz", "here3");
            }


        }
        else {
            Log.d("null", "bundle null");
        }

    }

    public JsonArray jsonParser(JsonObject json) {
        //Convert json to parsable format
        String resultJSON = json.toString();
        jsonElement = new JsonParser().parse(resultJSON);
        jsonObj = jsonElement.getAsJsonObject();
        dataJSON = jsonObj.getAsJsonArray("results");
        return dataJSON;
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

