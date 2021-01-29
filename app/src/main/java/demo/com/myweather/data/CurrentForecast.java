package demo.com.myweather.data;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "current_forecast")
public class CurrentForecast {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int cityId;
    private String cityName;
    private double lon;
    private double lat;
    private String weatherDescription;
    private String weatherIcon;
    private int temp;
    private int feelsLike;
    private int pressure;
    private int humidity;
    private int visibility;
    private int windSpeed;
    private int windDeg;

    public CurrentForecast(int id, int cityId, String cityName, double lon, double lat, String weatherDescription, String weatherIcon, int temp, int feelsLike, int pressure, int humidity, int visibility, int windSpeed, int windDeg) {
        this.id = id;
        this.cityId = cityId;
        this.cityName = cityName;
        this.lon = lon;
        this.lat = lat;
        this.weatherDescription = weatherDescription;
        this.weatherIcon = weatherIcon;
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.pressure = pressure;
        this.humidity = humidity;
        this.visibility = visibility;
        this.windSpeed = windSpeed;
        this.windDeg = windDeg;
    }
    @Ignore
    public CurrentForecast(int cityId, String cityName, double lon, double lat, String weatherDescription, String weatherIcon, int temp, int feelsLike, int pressure, int humidity, int visibility, int windSpeed, int windDeg) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.lon = lon;
        this.lat = lat;
        this.weatherDescription = weatherDescription;
        this.weatherIcon = weatherIcon;
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.pressure = pressure;
        this.humidity = humidity;
        this.visibility = visibility;
        this.windSpeed = windSpeed;
        this.windDeg = windDeg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getLon() {
        return lon;
    }
    @Ignore
    public String getLonAsString(){
        return Double.toString(lon);
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
    @Ignore
    public String getLatAsString(){
        return Double.toString(lat);
    }
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(int feelsLike) {
        this.feelsLike = feelsLike;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(int windDeg) {
        this.windDeg = windDeg;
    }
}
