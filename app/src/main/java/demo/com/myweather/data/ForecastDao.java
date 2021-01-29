package demo.com.myweather.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ForecastDao {
    //currentForecast
    @Query("SELECT * FROM current_forecast")
    LiveData<CurrentForecast> getCurrentForecast();

    @Query("DELETE FROM current_forecast")
    void deleteCurrentForecast();

    @Insert
    void insertCurrentForecast(CurrentForecast currentForecast);

    //hourlyForecast
    @Query("SELECT * FROM hourly_forecasts")
    LiveData<List<HourlyForecast>> getHourlyForecasts();

    @Query("DELETE FROM hourly_forecasts")
    void deleteHourlyForecasts();

    @Insert
    void insertHourlyForecast(HourlyForecast hourlyForecast);

    //dailyForecast
    @Query("SELECT * FROM daily_forecasts")
    LiveData<List<DailyForecast>> getDailyForecasts();

    @Query("DELETE FROM daily_forecasts")
    void deleteDailyForecasts();

    @Insert
    void insertDailyForecast(DailyForecast dailyForecast);

    //FavoriteForecasts
    @Query("SELECT * FROM favorite_city_forecasts")
    LiveData<List<FavoriteCityForecast>> getFavoriteCitiesForecast();

    @Insert
    void insertFavoriteCityForecast(FavoriteCityForecast favoriteCityForecast);

    @Query("SELECT * FROM favorite_city_forecasts WHERE cityId== :cityId")
    LiveData<FavoriteCityForecast> getFavoriteCityForecastByID(int cityId);

    @Delete
    void deleteFavoriteCityForecast(FavoriteCityForecast favoriteCityForecast);
}
