package com.vuforia.samples.VuforiaSamples.app.ImageTargets;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class AsyncFetchApi extends AsyncTask<String, Void, String[]> {
    private ProgressDialog dialog;
    private Activity activity;
    private View viewCard;

    public AsyncFetchApi(Activity activity, View viewCard) {
        this.activity = activity;
        this.viewCard = viewCard;
        dialog = new ProgressDialog(activity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setMessage("Loading ...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    protected String[] doInBackground(String... params) {
        String response;
        String query;
        String request = params[0];
        String id = null;

        switch(request) {
            case "blue_dream":
                id = "14084";
            case "green_crack":
                id = "14109";
            case "gsc":
                id = "14013";
            case "gdp":
                id = "14007";
            case "sour_diesel":
                id = "14005";
        }

        try {
            if (id != null) {
                query = "https://mystrain-stg.herokuapp.com/api/v1/products/" + id;
                Log.d("FETCH API", "" + query);
                response = get(query);
                Log.d("REPONSE", "" + response);
                return new String[]{response};
            } else {
                throw new Exception("Request not understood");
            }
        } catch (Exception e) {
            return new String[]{"error"};
        }
    }

    @Override
    protected void onPostExecute(String... result) {
        String TAG = "--POST FETCH API--";
        CannabisStrain c = new CannabisStrain(viewCard, activity);
        String indica_p = null;
        String sativa_p = null;

        try {
            JSONObject jsonObject = new JSONObject(result[0]);
            JSONObject product = jsonObject.getJSONObject("data").getJSONObject("product");
            c.time = product.getString("time");
            c.name = product.getString("name");
            c.logo = product.getJSONObject("logo").getString("small"); //or large
            c.background = product.getJSONObject("background").getString("medium");
            c.flavor = product.getString("flavor");
            c.flavor_icon = product.getString("flavor_icon");

            JSONObject positive_effect = null;
            positive_effect = jsonObject.getJSONObject("data").getJSONArray("positive_effects").getJSONObject(0);
            c.positive_effect_name  = positive_effect.getString("name");
            c.positive_effect_icon = positive_effect.getJSONObject("icon").getString("small");
            indica_p = product.getString("indica_percentage");
            sativa_p = product.getString("sativa_percentage");
            c.indica_sativa = indica_p + "/" +sativa_p;
            c.description = jsonObject.getString("description");
        } catch (JSONException e) {
            dialog.dismiss();
            e.printStackTrace();
        }
        if (dialog.isShowing()) {
            dialog.dismiss();
        }

        c.load();
    }


    public String get(String requestURL) {
        URL url;
        String response = "";
        try {
            url = new URL(requestURL);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                Log.d("--CONNECT---", "Could not connect to "+ requestURL);
                response = "";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("--RESPONSE--", response);
        return response;
    }
}