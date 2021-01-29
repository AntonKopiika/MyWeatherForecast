package demo.com.myweather.utils;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class NetworkUtils {
    private static final String CURRENT_WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String PARAMS_CITY = "q";

    private static final String EXTENDED_WEATHER_URL = "http://api.openweathermap.org/data/2.5/onecall";
    private static final String PARAMS_LATITUDE = "lat";
    private static final String PARAMS_LONGITUDE = "lon";
    private static final String PARAMS_EXCLUDE = "exclude";
    private static final String PARAMS_API_ID = "appid";
    private static final String PARAMS_LANG = "lang";
    private static final String PARAMS_UNITS = "units";

    private static final String API_KEY = "a2be272178d83c04f44d74cb32a4d3eb";
    private static final String DAILY_EXCLUDE_KEY = "daily";
    private static final String HOURLY_EXCLUDE_KEY = "hourly";
    private static final String MINUTELY_EXCLUDE_KEY = "minutely";
    private static final String CURRENT_EXCLUDE_KEY = "current";
    private static final String LANGUAGE_KEY = "ru";
    private static final String UNITS_KEY = "metric";

    private static URL createExtendedURL(String lat, String lon) {
        URL url = null;
        Uri uri = Uri.parse(EXTENDED_WEATHER_URL).buildUpon()
                .appendQueryParameter(PARAMS_LATITUDE, lat)
                .appendQueryParameter(PARAMS_LONGITUDE, lon)
                .appendQueryParameter(PARAMS_API_ID, API_KEY)
                .appendQueryParameter(PARAMS_LANG, LANGUAGE_KEY)
                .appendQueryParameter(PARAMS_EXCLUDE, String.format("%s, %s", CURRENT_EXCLUDE_KEY, MINUTELY_EXCLUDE_KEY))
                .appendQueryParameter(PARAMS_UNITS, UNITS_KEY).build();
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static URL createCurrentURLFromName(String name) {
        URL url = null;
        Uri uri = Uri.parse(CURRENT_WEATHER_URL).buildUpon()
                .appendQueryParameter(PARAMS_CITY, name)
                .appendQueryParameter(PARAMS_API_ID, API_KEY)
                .appendQueryParameter(PARAMS_LANG, LANGUAGE_KEY)
                .appendQueryParameter(PARAMS_UNITS, UNITS_KEY).build();
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static URL createCurrentURLFromLocation(String lat, String lon) {
        URL url = null;
        Uri uri = Uri.parse(CURRENT_WEATHER_URL).buildUpon()
                .appendQueryParameter(PARAMS_LATITUDE, lat)
                .appendQueryParameter(PARAMS_LONGITUDE, lon)
                .appendQueryParameter(PARAMS_API_ID, API_KEY)
                .appendQueryParameter(PARAMS_LANG, LANGUAGE_KEY)
                .appendQueryParameter(PARAMS_UNITS, UNITS_KEY).build();
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
    public static JSONObject getExtendedJSON(String lat,String lon){
        URL url = createExtendedURL(lat,lon);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONLoader().execute(url).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static JSONObject getCurrentJSON(String name) {
        URL url = createCurrentURLFromName(name);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONLoader().execute(url).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static JSONObject getCurrentJSON(String lat, String lon) {
        URL url = createCurrentURLFromLocation(lat, lon);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONLoader().execute(url).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static class JSONLoader extends AsyncTask<URL, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(URL... urls) {

            HttpURLConnection httpURLConnection = null;


            try {
                httpURLConnection = (HttpURLConnection) urls[0].openConnection();
                InputStream stream = httpURLConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(stream);
                BufferedReader bufferedReader = new BufferedReader(reader);
                StringBuilder result = new StringBuilder();
                String line = bufferedReader.readLine();
                while (line != null) {
                    result.append(line);
                    line = bufferedReader.readLine();
                }
                return new JSONObject(result.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }
            return null;
        }
    }

}


