//package com.example.barterapp;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//
//import android.Manifest;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.location.*;
//import android.os.Bundle;
//import android.os.CountDownTimer;
//import android.provider.Settings;
//import android.text.format.DateFormat;
//import android.view.View;
//import android.widget.*;
//
//
//import static android.Manifest.permission.ACCESS_FINE_LOCATION;
//
//import com.firebase.ui.auth.AuthUI;
//import com.firebase.ui.database.FirebaseListAdapter;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Locale;
//
//public class MainActivity extends AppCompatActivity {
//
//    private LocationManager locationManager;
//    private String provider;
//    private FirebaseListAdapter<ChatMessage> adapter;
//    private static final int SIGN_IN_REQUEST_CODE = 1;
//    private MyLocationListener mylistener;
//    Button button5;
//
//    //@TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    //@SuppressLint("NewApi")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chat_main);
//        //button5 = findViewById(R.id.register_submit);
//
//        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
//            // Start sign in/sign up activity
//            startActivityForResult(
//                    AuthUI.getInstance().createSignInIntentBuilder().build(),
//                    SIGN_IN_REQUEST_CODE
//            );
//        } else {
//            // User is already signed in. Therefore, display
//            // a welcome Toast
//            Toast.makeText(this,
//                            "Welcome " + FirebaseAuth.getInstance()
//                                    .getCurrentUser()
//                                    .getDisplayName(),
//                            Toast.LENGTH_LONG)
//                    .show();
//
//            displayChatMessages();
//        }
//
//
//
//        FloatingActionButton fab =
//                (FloatingActionButton)findViewById(R.id.fab);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                EditText input = (EditText)findViewById(R.id.input);
//
//                // Read the input field and push a new instance
//                // of ChatMessage to the Firebase database
//                FirebaseDatabase.getInstance()
//                        .getReference()
//                        .push()
//                        .setValue(new ChatMessage(input.getText().toString(),
//                                FirebaseAuth.getInstance()
//                                        .getCurrentUser()
//                                        .getDisplayName())
//
//                        );
//
//                // Clear the input
//                input.setText("");
//            }
//        });
//
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//        // user defines the criteria
//        Criteria criteria = new Criteria();
//        criteria.setAccuracy(Criteria.ACCURACY_FINE);   //default
//        criteria.setCostAllowed(false);
//        // get the best provider depending on the criteria
//        provider = locationManager.getBestProvider(criteria, false);
//        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            int REQUEST_LOCATION = 99;
//            ActivityCompat.requestPermissions(MainActivity.this, new String[]
//                    {ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
//        }
//
//        final Location location = locationManager.getLastKnownLocation(provider);
//
//        mylistener = new MyLocationListener();
//
//
//        /*
//        button5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if (location != null) {
//
//                    mylistener.onLocationChanged(location);
//                } else {
//                    // leads to the settings because there is no last known location
//                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                    startActivity(intent);
//                    //Using 12 seconds timer till it gets location
//                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
//                    alertDialog.setTitle("Obtaining Location ...");
//                    alertDialog.setMessage("00:12");
//                    alertDialog.show();
//
//                    new CountDownTimer(12000, 1000) {
//                        @Override
//                        public void onTick(long millisUntilFinished) {
//                            alertDialog.setMessage("00:" + (millisUntilFinished / 1000));
//                        }
//
//                        @Override
//                        public void onFinish() {
//                            alertDialog.dismiss();
//                        }
//                    }.start();
//                }
//                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    // this will be automatically asked to be implemented
//                }
//                // location updates: at least 1 meter and 200millsecs change
//                locationManager.requestLocationUpdates(provider, 200, 1, mylistener);
//                if(location!=null) {
//                    Double lat = location.getLatitude();
//                    Double lon = location.getLongitude();
//
//                    Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
//                    List<Address> addresses = null;
//                    try {
//                        addresses = geocoder.getFromLocation(lat, lon, 1);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    String cityName = addresses.get(0).getLocality();
//                    String stateName = addresses.get(0).getAdminArea();
//                    String countryName = addresses.get(0).getCountryName();
//                    String postalCode = addresses.get(0).getPostalCode();
//                    //set text of xml file
//                }
//                else {
//                    Toast.makeText(MainActivity.this, "Please open your location", Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//
//        });*/
//    }
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == SIGN_IN_REQUEST_CODE) {
//            if(resultCode == RESULT_OK) {
//                Toast.makeText(this,
//                                "Successfully signed in. Welcome!",
//                                Toast.LENGTH_LONG)
//                        .show();
//
//                displayChatMessages();
//            } else {
//                Toast.makeText(this,
//                                "We couldn't sign you in. Please try again later.",
//                                Toast.LENGTH_LONG)
//                        .show();
//                finish();
//            }
//        }
//    }
//
//    private void displayChatMessages(){
//        ListView listOfMessages = (ListView)findViewById(R.id.list_of_messages);
//
//        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
//                R.layout.item_chat_textview, FirebaseDatabase.getInstance().getReference()) {
//            @Override
//            protected void populateView(View v, ChatMessage model, int position) {
//                // Get references to the views of message.xml
//                TextView messageText = (TextView)v.findViewById(R.id.message_text);
//                TextView messageUser = (TextView)v.findViewById(R.id.message_user);
//                TextView messageTime = (TextView)v.findViewById(R.id.message_time);
//
//                // Set their text
//                messageText.setText(model.getMessageText());
//                messageUser.setText(model.getMessageUser());
//
//                // Format the date before showing it
//                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
//                        model.getMessageTime()));
//            }
//        };
//
//        listOfMessages.setAdapter(adapter);
//    }
//    private class MyLocationListener implements LocationListener {
//
//        @Override
//        public void onLocationChanged(Location location) {
//            Toast.makeText(MainActivity.this, "" + location.getLatitude() + location.getLongitude(),
//                    Toast.LENGTH_SHORT).show();
//
//
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//            Toast.makeText(MainActivity.this, provider + "'s status changed to " + status + "!",
//                    Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onProviderEnabled(String provider) {
//            Toast.makeText(MainActivity.this, "Provider " + provider + " enabled!",
//                    Toast.LENGTH_SHORT).show();
//
//        }
//
//        @Override
//        public void onProviderDisabled(String provider) {
//            Toast.makeText(MainActivity.this, "Provider " + provider + " disabled!",
//                    Toast.LENGTH_SHORT).show();
//        }
//    }
//}