package demo.com.myweather.data;

import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "favorite_city_forecasts")
public class FavoriteCityForecast extends CurrentForecast{
    public FavoriteCityForecast(int id, int cityId, String cityName, double lon, double lat, String weatherDescription, String weatherIcon, int temp, int feelsLike, int pressure, int humidity, int visibility, int windSpeed, int windDeg) {
        super(id, cityId, cityName, lon, lat, weatherDescription, weatherIcon, temp, feelsLike, pressure, humidity, visibility, windSpeed, windDeg);
    }
    @Ignore
    public FavoriteCityForecast(CurrentForecast forecast){
        super(forecast.getId(),forecast.getCityId(),forecast.getCityName(),forecast.getLon(),forecast.getLat(),forecast.getWeatherDescription(),forecast.getWeatherIcon(),forecast.getTemp(),forecast.getFeelsLike(),forecast.getPressure(),forecast.getHumidity(),forecast.getVisibility(),forecast.getWindSpeed(),forecast.getWindDeg());
    }
}
