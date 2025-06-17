package com.example.studify_app;
import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class ControladorVolley {
        private static ControladorVolley instance;
        private static RequestQueue requestQueue;
        private static final String BASE_URL = "http://192.168.1.225:5000";

        private ControladorVolley(Context context) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        public static synchronized void init(Context context) {
            if (instance == null) {
                instance = new ControladorVolley(context);
            }
        }

        public static void postJSON(String endpoint, Map<String, String> params, final VolleyCallback callback) {
            if (requestQueue == null) {
                throw new IllegalStateException("VolleyHelper no ha sido inicializado. Llama a init(context) primero.");
            }

            String url = BASE_URL + endpoint;

            JSONObject jsonBody = new JSONObject(params);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    url,
                    jsonBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            callback.onSuccess(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            JSONObject errorObj = new JSONObject();
                            try {
                                errorObj.put("error", error.toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            callback.onError(errorObj);
                        }
                    }){
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
            };
                    ;
            requestQueue.add(jsonObjectRequest);
        }

        public interface VolleyCallback {
            void onSuccess(JSONObject response);
            void onError(JSONObject error);
        }
}

