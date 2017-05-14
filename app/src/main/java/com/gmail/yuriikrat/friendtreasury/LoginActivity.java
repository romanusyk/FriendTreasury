package com.gmail.yuriikrat.friendtreasury;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.yuriikrat.friendtreasury.R;
import com.gmail.yuriikrat.friendtreasury.domain.User;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by romm on 09.05.17.
 */

public class LoginActivity extends AppCompatActivity {

    private MyApplication app;

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        app = (MyApplication) getApplicationContext();

        queue = Volley.newRequestQueue(this);
    }

    public void validate(View view) {
        Log.i("Login : ", "ID : " + app.getId());
        Log.i("Login : ", "Username : " + app.getUsername());

        TextView textView = (TextView)findViewById(R.id.input_email);
        final String username = textView.getText().toString();
        textView = (TextView)findViewById(R.id.input_password);
        String password = textView.getText().toString();

        Gson gson = new Gson();
        User user = new User(null, username, password);
        final String jsonUser = gson.toJson(user);

        Log.i("user", jsonUser);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.PATCH, app.URL + "users",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.i("UP:", response);
                        goTo(AddPaymentActivity.class);
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

    }

    public void goTo(Class clazz) {
        Intent intent = new Intent(LoginActivity.this, clazz);
        startActivity(intent);
    }

    public void goSignup(View view) {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }


}
