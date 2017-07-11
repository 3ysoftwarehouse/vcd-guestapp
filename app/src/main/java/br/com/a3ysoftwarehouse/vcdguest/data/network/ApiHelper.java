package br.com.a3ysoftwarehouse.vcdguest.data.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.OkHttpResponseListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Tag;
import br.com.a3ysoftwarehouse.vcdguest.util.Constants;

/**
 * Created by Iago Belo on 22/06/17.
 */

public class ApiHelper implements IApiHelper {
    // Constants
    private static final String TAG = "ApiHelper";

    private RequestQueue mQueue;
    private Gson mGson;

    public ApiHelper(Context context) {
        mGson = new Gson();
        mQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void getPassengers(final IApiRequestListener<List<Passenger>> listener) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, Constants.Api.GET_ALL_PASSENGERS, new Response.Listener<String>() {
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

    @Override
    public void restoreTags(final IApiRequestListener<List<Tag>> listener) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                Constants.Api.GET_TAGS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Tag[] passengers = mGson.fromJson(response, Tag[].class);

                        List<Tag> tagList = new ArrayList<>();

                        Collections.addAll(tagList, passengers);

                        listener.onSuccess(tagList);
                    }
                },
                    new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onFailed();
                    }
                });

        mQueue.add(stringRequest);
    }

    @Override
    public void backupTags(List<Tag> tagList, final IApiRequestListener<Void> listener) {
        AndroidNetworking.post(Constants.Api.POST_TAGS)
                .addBodyParameter("tags", tagListToJson(tagList))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsOkHttpResponse(new OkHttpResponseListener() {
                    @Override
                    public void onResponse(okhttp3.Response response) {
                        if (response.code() == 200) listener.onSuccess(null);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onFailed();
                    }
                });
    }

    @Override
    public void downloadFile(String url, String dirPath, String fileName,
                             final IApiRequestListener<Void> listener) {
        AndroidNetworking.download(url, dirPath, fileName)
                .setPriority(Priority.MEDIUM)
                .build()
                .startDownload(new DownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        listener.onSuccess(null);
                    }

                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();

                        listener.onFailed();
                    }
                });
    }

    private String tagListToJson(List<Tag> tagList) {
        String jsonString = "[";

        for (int i = 0; i < tagList.size(); i++) {
            jsonString += tagList.get(i).toJson();

            if (i != tagList.size() - 1) jsonString += ",\n";
        }

        jsonString += "]";

        jsonString = jsonString.replace("'", "\"");

        return jsonString;
    }
}
