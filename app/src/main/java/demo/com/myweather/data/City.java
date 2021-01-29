package demo.com.myweather.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class City {
    private LiveData<CurrentForecast> currentForecastLiveData;
    private LiveData<List<HourlyForecast>> hourlyForecastLiveData;
    private LiveData<List<DailyForecast>> dailyForecastLiveData;

    public City(LiveData<CurrentForecast> currentForecastLiveData, LiveData<List<HourlyForecast>> hourlyForecastLiveData, LiveData<List<DailyForecast>> dailyForecastLiveData) {
        this.currentForecastLiveData = currentForecastLiveData;
        this.hourlyForecastLiveData = hourlyForecastLiveData;
        this.dailyForecastLiveData = dailyForecastLiveData;
    }

    public LiveData<CurrentForecast> getCurrentForecastLiveData() {
        return currentForecastLiveData;
    }

    public LiveData<List<HourlyForecast>> getHourlyForecastLiveData() {
        return hourlyForecastLiveData;
    }

    public LiveData<List<DailyForecast>> getDailyForecastLiveData() {
        return dailyForecastLiveData;
    }
}
