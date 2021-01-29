package demo.com.myweather.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import demo.com.myweather.R;
@Database(entities = {CurrentForecast.class,DailyForecast.class,HourlyForecast.class,FavoriteCityForecast.class},version = 3,exportSchema = false)
public abstract class ForecastDatabase extends RoomDatabase {
    private static ForecastDatabase database;
    private static final String DB_NAME = "forecast.db";
    private static final Object LOCK = new Object();

    public static ForecastDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, ForecastDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
            }
        }
            return database;
    }
    public abstract ForecastDao forecastDao();
}
