package demo.com.myweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

import demo.com.myweather.data.CurrentForecast;
import demo.com.myweather.data.DailyForecast;
import demo.com.myweather.data.HourlyForecast;
import demo.com.myweather.data.MainViewModel;
import demo.com.myweather.utils.JSONUtils;
import demo.com.myweather.utils.NetworkUtils;

public class SearchActivity extends AppCompatActivity {
    private EditText editTextCityName;
    private CurrentForecast currentForecast;
    private MainViewModel viewModel;
    private ArrayList<HourlyForecast> hourlyForecasts;
    private ArrayList<DailyForecast> dailyForecasts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editTextCityName=findViewById(R.id.editTextCityName);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    public void onClickSearchForecast(View view) {
        String cityName=editTextCityName.getText().toString().trim();
        if (cityName!=null && !cityName.isEmpty()){
            JSONObject jsonObject= NetworkUtils.getCurrentJSON(cityName);
            currentForecast = JSONUtils.getCurrentForecastFromJSON(jsonObject);
            if (currentForecast == null) {
                Toast.makeText(getApplicationContext(),"Something goes wrong. Try another search!",Toast.LENGTH_SHORT).show();
                editTextCityName.setText("");
            }
            else {
                viewModel.deleteCurrentForecast();
                viewModel.insertCurrentForecast(currentForecast);
                JSONObject jsonObjectDaily = NetworkUtils.getExtendedJSON(Double.toString(currentForecast.getLat()), Double.toString(currentForecast.getLon()));
                hourlyForecasts = JSONUtils.getHourlyForecastFromJSON(jsonObjectDaily, currentForecast.getCityId());
                dailyForecasts = JSONUtils.getDailyForecastFromJSON(jsonObjectDaily, currentForecast.getCityId());

            if (hourlyForecasts != null && !hourlyForecasts.isEmpty()) {
                viewModel.deleteHourlyForecasts();
                for (HourlyForecast hourlyForecast : hourlyForecasts) {
                    viewModel.insertHourlyForecast(hourlyForecast);
                }
            }
            if (dailyForecasts !=null && !dailyForecasts.isEmpty()) {
                viewModel.deleteDailyForecast();
                for (DailyForecast dailyForecast : dailyForecasts) {
                    viewModel.insertDailyForecast(dailyForecast);
                }
            } Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
            }

        }
        else {
            Toast.makeText(getApplicationContext(),"Something goes wrong",Toast.LENGTH_SHORT).show();
            editTextCityName.setText("");
        }
    }
}