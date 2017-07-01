package br.com.a3ysoftwarehouse.vcdguest.data.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.util.Constants;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;

/**
 * Created by Iago Belo on 22/06/17.
 */

public class ApiHelper implements IApiHelper {
    private RequestQueue mQueue;
    private Gson mGson;

    public ApiHelper(Context context) {
        mGson = new Gson();
        mQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void getPassengers(final IApiRequestListener<List<Passenger>> listener) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, Constants.Api.PASSENGERS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Passenger[] passengers = mGson.fromJson(response, Passenger[].class);

                List<Passenger> passengerList = new ArrayList<>();

                Collections.addAll(passengerList, passengers);

                listener.onSuccess(passengerList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onFailed();
            }
        });

        mQueue.add(stringRequest);
    }
}
