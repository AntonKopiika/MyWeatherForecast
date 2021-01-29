package demo.com.myweather.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import demo.com.myweather.data.CurrentForecast;
import demo.com.myweather.data.DailyForecast;
import demo.com.myweather.data.HourlyForecast;


public class JSONUtils {
    //Current forecast
    private static final String ICON_URL = "http://openweathermap.org/img/wn/%s@2x.png";
    private static final String KEY_ID="id";
    private static final String OBJECT_COORDINATES = "coord";
    private static final String KEY_LONGITUDE = "lon";
    private static final String KEY_LATITUDE = "lat";
    private static final String ARRAY_WEATHER = "weather";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_ICON = "icon";
    private static final String OBJECT_MAIN = "main";
    private static final String KEY_TEMPERATURE = "temp";
    private static final String KEY_FEELS_LIKE = "feels_like";
    private static final String KEY_PRESSURE = "pressure";
    private static final String KEY_HUMIDITY = "humidity";
    private static final String KEY_VISIBILITY = "visibility";
    private static final String OBJECT_WIND = "wind";
    private static final String KEY_SPEED = "speed";
    private static final String KEY_DEG = "deg";
    private static final String KEY_NAME = "name";
    //Hourly forecast
    private static final String ARRAY_HOURLY = "hourly";
    private static final String KEY_DAYTIME = "dt";
    private static final String KEY_WIND_SPEED = "wind_speed";
    private static final String KEY_WIND_DEG = "wind_deg";
    //DailyForecast
    private static final String ARRAY_DAILY = "daily";
    private static final String OBJECT_TEMPERATURE = "temp";
    private static final String KEY_SUNRISE = "sunrise";
    private static final String KEY_SUNSET = "sunset";
    private static final String KEY_TEMP_MIN = "min";
    private static final String KEY_TEMP_MAX = "max";

    public static CurrentForecast getCurrentForecastFromJSON(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        CurrentForecast currentForecast;
        try {
            int cityId =jsonObject.getInt(KEY_ID);
            String cityName = jsonObject.getString(KEY_NAME);
            JSONObject coord = jsonObject.getJSONObject(OBJECT_COORDINATES);
            double lon = coord.getDouble(KEY_LONGITUDE);
            double lat = coord.getDouble(KEY_LATITUDE);
            JSONArray weather = jsonObject.getJSONArray(ARRAY_WEATHER);
            JSONObject weatherJSONObject = weather.getJSONObject(0);
            String description = weatherJSONObject.getString(KEY_DESCRIPTION);
            String icon = weatherJSONObject.getString(KEY_ICON);
            JSONObject main = jsonObject.getJSONObject(OBJECT_MAIN);
            int temperature = (int) main.getDouble(KEY_TEMPERATURE);
            int feelsLike = (int) main.getDouble(KEY_FEELS_LIKE);
            int pressure = main.getInt(KEY_PRESSURE);
            int humidity = main.getInt(KEY_HUMIDITY);
            int visibility = jsonObject.getInt(KEY_VISIBILITY);
            JSONObject wind = jsonObject.getJSONObject(OBJECT_WIND);
            int windSpeed = (int) wind.getDouble(KEY_SPEED);
            int windDeg = wind.getInt(KEY_DEG);
            currentForecast = new CurrentForecast(cityId,cityName, lon, lat, description, icon, temperature, feelsLike, pressure, humidity, visibility, windSpeed, windDeg);
            return currentForecast;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<HourlyForecast> getHourlyForecastFromJSON(JSONObject jsonObject, int cityId) {
        ArrayList<HourlyForecast> hourlyForecasts = new ArrayList<>();
        try {
            JSONArray hourly = jsonObject.getJSONArray(ARRAY_HOURLY);
            for (int i = 0; i < hourly.length(); i++) {
                JSONObject object = hourly.getJSONObject(i);
                JSONArray weather = object.getJSONArray(ARRAY_WEATHER);
                JSONObject weatherObject = weather.getJSONObject(0);
                HourlyForecast forecast = new HourlyForecast(cityId,object.getInt(KEY_DAYTIME),
                        (int) object.getDouble(KEY_TEMPERATURE), (int) object.getDouble(KEY_FEELS_LIKE),
                        object.getInt(KEY_PRESSURE), object.getInt(KEY_HUMIDITY), (int) object.getDouble(KEY_WIND_SPEED),
                        (int) object.getDouble(KEY_WIND_DEG), weatherObject.getString(KEY_ICON));
                hourlyForecasts.add(forecast);
            }
            return hourlyForecasts;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<DailyForecast> getDailyForecastFromJSON(JSONObject jsonObject,int cityId) {
        ArrayList<DailyForecast> dailyForecasts = new ArrayList<>();
        try {
            JSONArray daily = jsonObject.getJSONArray(ARRAY_DAILY);
            for (int i = 0; i < daily.length(); i++) {
                JSONObject object = daily.getJSONObject(i);
                JSONObject temp = object.getJSONObject(OBJECT_TEMPERATURE);
                JSONArray weather = object.getJSONArray(ARRAY_WEATHER);
                JSONObject weatherObject = weather.getJSONObject(0);
                DailyForecast forecast = new DailyForecast(cityId,object.getLong(KEY_DAYTIME),
                        object.getLong(KEY_SUNRISE), object.getLong(KEY_SUNSET),
                        temp.getInt(KEY_TEMP_MAX), temp.getInt(KEY_TEMP_MIN), object.getInt(KEY_PRESSURE),
                        object.getInt(KEY_HUMIDITY), object.getInt(KEY_WIND_SPEED), object.getInt(KEY_WIND_DEG),
                        weatherObject.getString(KEY_ICON),weatherObject.getString(KEY_DESCRIPTION));
                dailyForecasts.add(forecast);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dailyForecasts;
    }
}
