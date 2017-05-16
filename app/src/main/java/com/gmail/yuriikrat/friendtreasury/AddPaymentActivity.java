package com.gmail.yuriikrat.friendtreasury;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.yuriikrat.friendtreasury.domain.Payment;
import com.gmail.yuriikrat.friendtreasury.domain.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class AddPaymentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private MyApplication app;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addPayment(View view) {

        Integer meID = app.getId();
        Integer[] toIDs = new Integer[] {2};

        TextView textView = (TextView) findViewById(R.id.amount);
        BigDecimal amount = new BigDecimal(textView.getText().toString());

        Switch s = (Switch) findViewById(R.id.switch1);
        Integer shallIPayForMyself = s.isChecked() ? 1 : 0;
        Log.i("SIPFMS", "" + shallIPayForMyself);

        textView = (TextView) findViewById(R.id.description);
        String description = textView.getText().toString();

        DatePicker datePicker = (DatePicker) findViewById(R.id.dpResult);
        Date date = new GregorianCalendar(
                datePicker.getYear(),
                datePicker.getMonth(),
                datePicker.getDayOfMonth()
        ).getTime();

        Payment payment = new Payment(meID, toIDs, amount, description, date, shallIPayForMyself);

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MMM-dd").create();
        final String jsonPayment = gson.toJson(payment);

        Log.i("payment", jsonPayment);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, app.URL + "payment/",
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
                return jsonPayment.getBytes();
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
    }
}
