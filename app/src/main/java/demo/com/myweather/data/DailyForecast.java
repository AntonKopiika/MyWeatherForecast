package demo.com.myweather.data;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "daily_forecasts")
public class DailyForecast {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int cityId;
    private long daytime;
    private long sunrise;
    private long sunset;
    private int maxTemp;
    private int minTemp;
    private int pressure;
    private int humidity;
    private int windSpeed;
    private int windDeg;
    private String weatherIcon;
    private String weatherDescription;



    public DailyForecast(int id, int cityId, long daytime, long sunrise, long sunset, int maxTemp, int minTemp, int pressure, int humidity, int windSpeed, int windDeg, String weatherIcon, String weatherDescription) {
        this.id = id;
        this.cityId=cityId;
        this.daytime = daytime;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDeg = windDeg;
        this.weatherIcon = weatherIcon;
        this.weatherDescription=weatherDescription;
    }

    @Ignore
    public DailyForecast(int cityId,long daytime, long sunrise, long sunset, int maxTemp, int minTemp, int pressure, int humidity, int windSpeed, int windDeg, String weatherIcon,String weatherDescription) {
        this.cityId=cityId;
        this.daytime = daytime;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDeg = windDeg;
        this.weatherIcon = weatherIcon;
        this.weatherDescription=weatherDescription;
    }
    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
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

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
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
