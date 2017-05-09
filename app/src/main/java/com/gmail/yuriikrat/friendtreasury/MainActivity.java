package com.gmail.yuriikrat.friendtreasury;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.yuriikrat.friendtreasury.domain.User;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

//    TextView mTxtDisplay;
//
//    private String url = "http://localhost:8080/users/1";

    private MyApplication app;

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        mTxtDisplay = (TextView) findViewById(R.id.text);
//        getUser();

        // Instantiate the RequestQueue.

        app = (MyApplication) getApplicationContext();

        queue = Volley.newRequestQueue(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.group1) {

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, app.URL + "user_debts/2",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            Log.i("UD/2:", response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("UD/2:", error.toString());
                }
            });
            // Add the request to the RequestQueue.
            queue.add(stringRequest);

        } else if (id == R.id.group2) {

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, app.URL + "users/5",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            Gson gson = new Gson();
                            User user = gson.fromJson(response, User.class);
                            Log.i("U/2:", user.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("U/2:", error.toString());
                }
            });
            // Add the request to the RequestQueue.
            queue.add(stringRequest);

        } else if (id == R.id.group3) {

            Gson gson = new Gson();
            User user = new User("123456789012", "NewUser", "111");
            final String jsonUser = gson.toJson(user);

            Log.i("user", jsonUser);

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.POST, app.URL + "users",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            Log.i("UP:", response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("U/2:", error.toString());
                }
            }) {
                @Override
                public byte[] getBody() throws AuthFailureError {
                    return jsonUser.getBytes();
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap();
                    params.put("Content-Type","application/json");
                    return params;
                }
            };
            // Add the request to the RequestQueue.
            queue.add(stringRequest);

        } else if (id == R.id.nav_profile) {

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, app.URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            item.setTitle("Response is: "+ response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("/", error.toString());
                    item.setTitle("That didn't work!");
                }
            });
            // Add the request to the RequestQueue.
            queue.add(stringRequest);

        } else if (id == R.id.nav_logout) {

        } else if (id == R.id.nav_settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    private JSONObject getUser() {
//        JSONObject object = null;
//        JsonObjectRequest jsObjRequest = new JsonObjectRequest
//                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        mTxtDisplay.setText("Response: " + response.toString());
//                        System.out.println("YEEEEES");
//                    }
//                }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        System.out.println("ERRROR");
//                    }
//
//                });
////        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
//        return object;
//    }



}
