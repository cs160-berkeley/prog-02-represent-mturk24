package com.cs160.joleary.catnip;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.location.Location;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.location.LocationServices;

public class MainActivity extends Activity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    Button button;
    Button button2;
    String string1;
    String string2;
    String string3;
    String zip;
    String latitude;
    String longitude;
    public GoogleApiClient mGoogleApiClient;
    public Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        final EditText myText = (EditText) findViewById(R.id.editText);
        addListenerOnButton();
        addListenerOnButton1();
//        if (myText.getText() != null) {
//            addListenerOnButton();
//        } else {
//            addListenerOnButton1();
//        }


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        final TextView myText1 = (TextView)findViewById(R.id.textView4);
//        string1 = (String) myText1.getText();
//        final TextView myText2 = (TextView)findViewById(R.id.textView7);
//        string2 = (String) myText2.getText();
//        final TextView myText3 = (TextView)findViewById(R.id.textView8);
//        string3 = (String) myText3.getText();


    }

    public void getOtherView(View v) {
        Intent intent = new Intent(getApplicationContext(), SecondClass.class);
        startActivity(intent);
    }

    public void addListenerOnButton() {
        final EditText myInput = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (myInput.getText().length() == 5) {
//                    Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//                    sendIntent.putExtra("CAT_NAME", "Fred");
//                    startService(sendIntent);

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                if (mLastLocation != null) {
                    latitude = String.valueOf(mLastLocation.getLatitude());
                    longitude = String.valueOf(mLastLocation.getLongitude());
                }
                EditText myText = (EditText) findViewById(R.id.editText);
                zip = myText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), SecondClass.class);
                Bundle bundle = new Bundle();
                bundle.putString("ZIP", zip);
                bundle.putString("Lat", latitude);
                bundle.putString("Long", longitude);
//                intent.putExtra("ZIP", zip);
//                intent.putExtra("Lat", latitude);
//                intent.putExtra("Long", longitude);
                intent.putExtras(bundle);
                startActivity(intent);


//                }
            }
        });
    }

    //
//                watchIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//                watchIntent.putExtra("CANDIDATE_NAME", "John Doe");
//                watchIntent.putExtra("PARTY_NAME", "Democrat");
//                startService(watchIntent);
//
//                zipCodeEntry = zipCode.getText().toString();
//                phoneIntent = new Intent(phoneHomeScreen.this,phoneCongressionalCandidates.class);
//                phoneIntent.putExtra("zipCodeEntry", zipCodeEntry);
//                startActivity(phoneIntent);
//
    public void addListenerOnButton1() {

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//                sendIntent.putExtra("CAT_NAME", "Lexy");
//                startService(sendIntent);
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                Log.d("LOLLLL", String.valueOf(mGoogleApiClient));
                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                if (mLastLocation != null) {
                    latitude = String.valueOf(mLastLocation.getLatitude());
                    longitude = String.valueOf(mLastLocation.getLongitude());
                }
                Log.d("locationnn", String.valueOf(latitude));
                Log.d("aksdkalsdkla", String.valueOf(longitude));
                Intent intent = new Intent(getApplicationContext(), SecondClass.class);
                Bundle bundle = new Bundle();
                bundle.putString("Lat", latitude);
                bundle.putString("Long", longitude);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }


    //    ArrayAdapter<String> adapter;
//    EditText editText;
//    ArrayList<String> itemList;
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        String[] items={"Apple","Banana","Clementine"};
//        itemList=new ArrayList<String>(Arrays.asList(items));
//        adapter=new ArrayAdapter<String>(this,R.layout.list_layout,R.id.txtview,itemList);
//        ListView listV=(ListView)findViewById(R.id.list);
//        listV.setAdapter(adapter);
//        editText=(EditText)findViewById(R.id.txtInput);
//        Button btAdd=(Button)findViewById(R.id.btAdd);
//        btAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String newItem=editText.getText().toString();
//                // add new item to arraylist
//                itemList.add(newItem);
//                // notify listview of data changed
//                adapter.notifyDataSetChanged();
//            }
//
//        });
//
//    }
    @Override
    public void onConnected(Bundle connectionHint) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("TAG", "Connection suspended");
        mGoogleApiClient.connect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i("TAG", "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
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

}