package demo.com.myweather.data;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "hourly_forecasts")
public class HourlyForecast {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int cityId;
    private long daytime;
    private int temp;
    private int feelsLike;
    private int pressure;
    private int humidity;
    private int windSpeed;
    private int windDeg;
    private String weatherIcon;


    public HourlyForecast(int id, int cityId, long daytime, int temp, int feelsLike, int pressure, int humidity, int windSpeed, int windDeg, String weatherIcon) {
        this.id = id;
        this.cityId = cityId;
        this.daytime = daytime;
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDeg = windDeg;
        this.weatherIcon = weatherIcon;
    }


    @Ignore
    public HourlyForecast(int cityId,long daytime, int temp, int feelsLike, int pressure, int humidity, int windSpeed, int windDeg, String weatherIcon) {
        this.cityId=cityId;
        this.daytime = daytime;
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDeg = windDeg;
        this.weatherIcon = weatherIcon;
    }
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public long getDaytime() {
        return daytime;
    }

    public void setDaytime(long daytime) {
        this.daytime = daytime;
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

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }
}

