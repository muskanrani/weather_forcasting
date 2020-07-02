package com.example.weatherforcasting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
//TextView t1_temp,t2_city,t3_description,t1,t2,t3,t4,t5,t4_date,ti1,temp1,des1;
RequestQueue requestQueue;
Button bt_getlocation;
RecyclerView.LayoutManager layoutManager;
ArrayList<String> time=new ArrayList<String>();
ArrayList<String> description=new ArrayList<String>();
ArrayList<String> temperature=new ArrayList<String>();
    double lat;
    double lon;
FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_main);
//        t1_temp=findViewById(R.id.textview1);
//        t2_city=findViewById(R.id.textview3);
//        t3_description=findViewById(R.id.textview4);
//        t4_date=findViewById(R.id.textview2);
//        bt_getlocation=findViewById(R.id.bt_location);
//        t1=findViewById(R.id.tv1);
//        t2=findViewById(R.id.tv2);
//        t3=findViewById(R.id.tv3);
//        t4=findViewById(R.id.tv4);
//        t5=findViewById(R.id.tv5);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

//        bt_getlocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    getLocation();
                }
                else{
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);

                }
//            }
//        });

   //     find_weather();
        weatherforcast();

    }

    private void getLocation(){
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location =task.getResult();
                if(location != null){
                    try {
                        Geocoder geocoder = new Geocoder(MainActivity.this,
                                Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(),location.getLongitude(),1
                        );

                        lat = addresses.get(0).getLatitude();
                        lon = addresses.get(0).getLongitude();
//                        //set Latitude on Textview
//                        t1.setText(Html.fromHtml(
//                                "<font color='#6200EE'><b>Latitude :</b><br></font>"
//                                + addresses.get(0).getLatitude()
//                        ));
//                        //set longitude
//                        t2.setText(Html.fromHtml(
//                                "<font color='#6200EE'><b>Longitude :</b><br></font>"
//                                        + addresses.get(0).getLongitude()
//                        ));
//                        //set country name
//                        t3.setText(Html.fromHtml(
//                                "<font color='#6200EE'><b>Country Name :</b><br></font>"
//                                        + addresses.get(0).getCountryName()
//                        ));
//                        //set locality
//                        t4.setText(Html.fromHtml(
//                                "<font color='#6200EE'><b>Locality :</b><br></font>"
//                                        + addresses.get(0).getLocality()
//                        ));
//                        //set address
//                        t5.setText(Html.fromHtml(
//                                "<font color='#6200EE'><b>Address :</b><br></font>"
//                                        + addresses.get(0).getAddressLine(0)
//                        ));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
//    public void find_weather() {
//        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=3aec92dea777a013fd9ee9c488d58e2e&units=Imperial";
//        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                {
//
//                    try {
//                        JSONObject main_object = response.getJSONObject("main");
//                        JSONArray array = response.getJSONArray("weather");
//                        JSONObject object = array.getJSONObject(0);
//                        String temp = String.valueOf(main_object.getDouble(("temp")));
//                        String description = object.getString("description");
//
//                        t1_temp.setText(temp);
//                        t2_city.setText(response.getString("name"));
//                        t3_description.setText(description);
//
//                        Calendar calendar = Calendar.getInstance();
//                        SimpleDateFormat sdf = new SimpleDateFormat("EEEE-MM-dd");
//                        String formatted_date = sdf.format(calendar.getTime());
//
//                        t4_date.setText(formatted_date);
//
//                        double temp_int = Double.parseDouble(temp);
//                        double centi = (temp_int - 32) / 1.8000;
//                        centi = Math.round(centi);
//                        int i = (int) centi;
//                        t1_temp.setText(String.valueOf(i)+"°C");
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
//        );
//        RequestQueue queue = Volley.newRequestQueue(this);
//        queue.add(jor);
//    }
    public void weatherforcast(){
    String urll = "http://api.openweathermap.org/data/2.5/forecast?lat="+lat+"&lon="+lon+"&appid=3aec92dea777a013fd9ee9c488d58e2e&units=Imperial";
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urll, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        for(int k=0;k<30;k++){
                            time.add(response.getJSONArray("list").getJSONObject(k).getString("dt_txt"));
                        }

                        for(int j=0;j<30;j++){
                                    String tempfetch=response.getJSONArray("list"). getJSONObject(j).getJSONObject("main").getString("feels_like");
                                    double t=Double.parseDouble(tempfetch);
                                    double centi = (t - 32) / 1.8000;
                                    centi = Math.round(centi);
                                    int v = (int) centi;
                                    temperature.add(Integer.toString(v)+"°C");
                                    t=0;
                                    centi=0;
                                    v=0;
                           // temperature.add(response.getJSONArray("list"). getJSONObject(j).getJSONObject("main").getString("temp"));
                        }
                        for(int i=0;i<30;i++) {
                            description.add(response.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("description"));
                        }
                        initRecyclerView();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
    //add request to queue
    requestQueue.add(jsonObjectRequest);


}

    private void initRecyclerView(){
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        RecyclerView.Adapter adapter = new weatherlistAdapter(this,time,temperature,description);
        recyclerView.setAdapter(adapter);
    }
}
