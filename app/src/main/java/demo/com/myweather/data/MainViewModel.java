package demo.com.myweather.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainViewModel extends AndroidViewModel {
    private static ForecastDatabase database;
    private LiveData<List<HourlyForecast>> hourlyForecasts;
    private LiveData<List<DailyForecast>> dailyForecasts;
    private LiveData<List<FavoriteCityForecast>> favoriteCityForecasts;
    private LiveData<CurrentForecast> currentForecast;


    public MainViewModel(@NonNull Application application) {
        super(application);
        database=ForecastDatabase.getInstance(getApplication());
        hourlyForecasts=database.forecastDao().getHourlyForecasts();
        dailyForecasts=database.forecastDao().getDailyForecasts();
        favoriteCityForecasts=database.forecastDao().getFavoriteCitiesForecast();
        currentForecast=database.forecastDao().getCurrentForecast();
    }
    //Current
    public LiveData<CurrentForecast> getCurrentForecast(){
        return currentForecast;
    }
    public void deleteCurrentForecast (){
        new DeleteCurrentForecastTask().execute();
    }
    public void insertCurrentForecast(CurrentForecast forecast){
        new InsertCurrentForecastTask().execute(forecast);
    }
    //Hourly
    public LiveData<List<HourlyForecast>> getHourlyForecasts() {
        return hourlyForecasts;
    }
    public void deleteHourlyForecasts(){
        new DeleteHourlyForecastTask().execute();
    }
    public void insertHourlyForecast(HourlyForecast forecast){
        new InsertHourlyForecastTask().execute(forecast);
    }
    // Daily
    public LiveData<List<DailyForecast>> getDailyForecasts() {
        return dailyForecasts;
    }
    public void deleteDailyForecast(){
        new DeleteDailyForecastTask().execute();
    }
    public void insertDailyForecast(DailyForecast forecast){
        new InsertDailyForecastTask().execute(forecast);
    }
    //Favorite
    public LiveData<List<FavoriteCityForecast>> getFavoriteCityForecasts() {
        return favoriteCityForecasts;
    }
    public void insertFavoriteCityForecast(FavoriteCityForecast forecast){
        new InsertFavoriteCityForecastTask().execute(forecast);
    }
    public void deleteFavoriteCityForecast(FavoriteCityForecast forecast){
        new DeleteFavoriteCityForecastTask().execute(forecast);
    }
    public LiveData<FavoriteCityForecast> getFavoriteCityForecastByCityId(int cityId){
        try {
            return new GetFavoriteCityForecastByCityId().execute(cityId).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class DeleteCurrentForecastTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            database.forecastDao().deleteCurrentForecast();
            return null;
        }
    }
    private static class DeleteHourlyForecastTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            database.forecastDao().deleteHourlyForecasts();
            return null;
        }
    }
    private static class DeleteDailyForecastTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            database.forecastDao().deleteDailyForecasts();
            return null;
        }
    }
    private static class DeleteFavoriteCityForecastTask extends AsyncTask<FavoriteCityForecast,Void,Void>{

        @Override
        protected Void doInBackground(FavoriteCityForecast... favoriteCityForecasts) {
            if (favoriteCityForecasts!=null&&favoriteCityForecasts.length!=0){
                database.forecastDao().deleteFavoriteCityForecast(favoriteCityForecasts[0]);
            }
            return null;
        }
    }
    private static class GetFavoriteCityForecastByCityId extends AsyncTask<Integer,Void,LiveData<FavoriteCityForecast>>{


        @Override
        protected LiveData<FavoriteCityForecast> doInBackground(Integer... integers) {
            if (integers!=null&&integers.length!=0){
                return database.forecastDao().getFavoriteCityForecastByID(integers[0]);
            }
            return null;
        }
    }

    private static class InsertCurrentForecastTask extends AsyncTask<CurrentForecast,Void,Void>{

        @Override
        protected Void doInBackground(CurrentForecast... currentForecasts) {
            if (currentForecasts!=null&&currentForecasts.length!=0) {
                database.forecastDao().insertCurrentForecast(currentForecasts[0]);
            }
            return null;
        }
    }
    private static class InsertHourlyForecastTask extends AsyncTask<HourlyForecast,Void,Void>{

        @Override
        protected Void doInBackground(HourlyForecast... hourlyForecasts) {
            if (hourlyForecasts!=null&&hourlyForecasts.length!=0) {
                database.forecastDao().insertHourlyForecast(hourlyForecasts[0]);
            }
            return null;
        }
    }
    private static class InsertDailyForecastTask extends AsyncTask<DailyForecast,Void,Void>{

        @Override
        protected Void doInBackground(DailyForecast... dailyForecasts) {
            if(dailyForecasts!=null&&dailyForecasts.length!=0){
                database.forecastDao().insertDailyForecast(dailyForecasts[0]);
            }
            return null;
        }
    }
    private static class InsertFavoriteCityForecastTask extends AsyncTask<FavoriteCityForecast,Void,Void>{

        @Override
        protected Void doInBackground(FavoriteCityForecast... favoriteCityForecasts) {
            if(favoriteCityForecasts!=null&&favoriteCityForecasts.length!=0){
                database.forecastDao().insertFavoriteCityForecast(favoriteCityForecasts[0]);
            }
            return null;
        }
    }


}
