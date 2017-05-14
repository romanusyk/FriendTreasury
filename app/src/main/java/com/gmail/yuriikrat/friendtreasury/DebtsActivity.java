package com.gmail.yuriikrat.friendtreasury;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.yuriikrat.friendtreasury.domain.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by romm on 09.05.17.
 */

public class DebtsActivity extends AppCompatActivity {

    private MyApplication app;
    private RequestQueue queue;

    private LinkedList<String> debts;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debts);

        app = (MyApplication) getApplicationContext();
        queue = Volley.newRequestQueue(this);

        debts = new LinkedList<>();

        adapter = new ArrayAdapter<String>(this,
//                R.layout.activity_debts,
                R.layout.list_view,
                debts);

        ListView listview = (ListView) findViewById(R.id.debts_list);
        listview.setEnabled(true);
        listview.setAdapter(adapter);

        updateDebts();

    }

    public void updateDebts() {
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, app.URL + "user_debts/" + app.getId() + "/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.i("Debts:", response);

                        Gson gson = new Gson();
                        Type type = new TypeToken<Map<Integer, BigDecimal>>(){}.getType();
                        Map<Integer, BigDecimal> debtsMap = gson.fromJson(response, type);

                        debts.clear();
                        for (Integer k : debtsMap.keySet()) {
                            debts.add("" + k + " : " + debtsMap.get(k));
                        }
                        adapter.notifyDataSetChanged();


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Debts:", error.toString());
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void goLogin(View view) {
        Intent intent = new Intent(DebtsActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}
