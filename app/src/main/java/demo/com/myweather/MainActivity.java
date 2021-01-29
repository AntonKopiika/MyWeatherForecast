package demo.com.myweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import demo.com.myweather.adapters.DailyForecastListAdapter;
import demo.com.myweather.adapters.HourlyForecastAdapter;
import demo.com.myweather.data.CurrentForecast;
import demo.com.myweather.data.DailyForecast;
import demo.com.myweather.data.DataHelper;
import demo.com.myweather.data.HourlyForecast;
import demo.com.myweather.data.MainViewModel;
import demo.com.myweather.utils.JSONUtils;
import demo.com.myweather.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout constraintLayout;
    private TextView textViewCityName;
    private TextView textViewCurrentTemperature;
    private TextView textViewDescription;
    private TextView textViewFeelsLike;
    private TextView textViewWind;
    private TextView textViewHumidity;
    private TextView textViewPressure;
    private TextView textViewVisibility;
    private ImageView weatherIcon;
    private CardView cardViewDetails;
    private TextView textViewDetails;
    private Boolean isDetailsShown=false;
    private List<HourlyForecast> hourlyForecasts;
    private CurrentForecast currentForecast;
    private RecyclerView recyclerViewHourly;
    private ImageView imageViewCurrentWindDirection;
    private ArrayList<DailyForecast> dailyForecasts;
    private MainViewModel viewModel;
    private HourlyForecastAdapter hourlyAdapter;
    private ListView listViewDailyForecast;
    private DailyForecastListAdapter dailyAdapter;

    private TextView textViewFirstDayInfo;
    private ImageView imageViewFirstDayWeatherIcon;
    private TextView textViewFirstDayTemperature;
    private TextView textViewSecondDayInfo;
    private ImageView imageViewSecondDayWeatherIcon;
    private TextView textViewSecondDayTemperature;
    private TextView textViewThirdDayInfo;
    private ImageView imageViewThirdDayWeatherIcon;
    private TextView textViewThirdDayTemperature;
    private Toolbar toolbar;
    private FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
            getSupportActionBar().setTitle("");
        }
        constraintLayout=findViewById(R.id.mainConstraint);
        cardViewDetails=findViewById(R.id.cardViewDetails);
        textViewDetails=findViewById(R.id.textViewDetails);
        textViewCityName = findViewById(R.id.textViewCityName);
        textViewCurrentTemperature = findViewById(R.id.textViewCurrentTemperature);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewFeelsLike = findViewById(R.id.textViewFeelsLike);
        textViewWind = findViewById(R.id.textViewWind);
        textViewHumidity = findViewById(R.id.textViewHumidity);
        textViewPressure = findViewById(R.id.textViewPressure);
        textViewVisibility = findViewById(R.id.textViewVisibility);
        weatherIcon = findViewById(R.id.imageViewWeatherIcon);
        recyclerViewHourly = findViewById(R.id.recyclerViewHourly);
        imageViewCurrentWindDirection = findViewById(R.id.imageViewCurrentWindDirection);

        textViewFirstDayInfo=findViewById(R.id.textViewFirstDayInfo);
        textViewFirstDayTemperature=findViewById(R.id.textViewFirstDayTemperature);
        imageViewFirstDayWeatherIcon=findViewById(R.id.imageViewFirstDayWeatherIcon);
        textViewSecondDayInfo=findViewById(R.id.textViewSecondDayInfo);
        textViewSecondDayTemperature=findViewById(R.id.textViewSecondDayTemperature);
        imageViewSecondDayWeatherIcon=findViewById(R.id.imageViewSecondDayWeatherIcon);
        textViewThirdDayInfo=findViewById(R.id.textViewThirdDayInfo);
        textViewThirdDayTemperature=findViewById(R.id.textViewThirdDayTemperature);
        imageViewThirdDayWeatherIcon=findViewById(R.id.imageViewThirdDayWeatherIcon);

//        listViewDailyForecast=findViewById(R.id.listViewDailyForecast);
//        dailyAdapter=new DailyForecastListAdapter(this);
//        listViewDailyForecast.setAdapter(dailyAdapter);
        hourlyAdapter = new HourlyForecastAdapter();
        recyclerViewHourly.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewHourly.setAdapter(hourlyAdapter);

        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
        Intent intent=getIntent();
        if (intent==null){
            getLocalForecast();
        }


        LiveData<CurrentForecast> currentForecastLiveData=viewModel.getCurrentForecast();
        currentForecastLiveData.observe(this, new Observer<CurrentForecast>() {
            @Override
            public void onChanged(CurrentForecast currentForecastLive) {
                setCurrentWeatherView(currentForecastLive);
            }
        });

        LiveData<List<HourlyForecast>> hourlyForecastFromLiveData = viewModel.getHourlyForecasts();
        List<HourlyForecast> test=hourlyForecastFromLiveData.getValue();
        hourlyForecastFromLiveData.observe(this, new Observer<List<HourlyForecast>>() {
            @Override
            public void onChanged(List<HourlyForecast> hourlyLiveForecasts) {
                hourlyAdapter.setHourlyForecasts(hourlyLiveForecasts);
            }
        });
        LiveData<List<DailyForecast>> dailyForecastFromLiveData =viewModel.getDailyForecasts();
        dailyForecastFromLiveData.observe(this, new Observer<List<DailyForecast>>() {
            @Override
            public void onChanged(List<DailyForecast> dailyForecasts) {
                setDailyWeatherView(dailyForecasts);
                //dailyAdapter.setForecasts(dailyForecasts);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.location:
                getLocalForecast();
                break;
            case R.id.search:
                Intent intent =new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==44 && grantResults.length>0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            getLocation();
        }else {
            downloadData("Kyiv");
        }
    }

    private void downloadData(String lat, String lon){
        JSONObject jsonObject=NetworkUtils.getCurrentJSON(lat,lon);
        currentForecast = JSONUtils.getCurrentForecastFromJSON(jsonObject);
        if (currentForecast != null) {
            viewModel.deleteCurrentForecast();
            viewModel.insertCurrentForecast(currentForecast);
            JSONObject jsonObjectDaily = NetworkUtils.getExtendedJSON(Double.toString(currentForecast.getLat()), Double.toString(currentForecast.getLon()));
            hourlyForecasts = JSONUtils.getHourlyForecastFromJSON(jsonObjectDaily, currentForecast.getCityId());
            dailyForecasts = JSONUtils.getDailyForecastFromJSON(jsonObjectDaily, currentForecast.getCityId());
        }
        if (hourlyForecasts != null && !hourlyForecasts.isEmpty()) {
            viewModel.deleteHourlyForecasts();
            for (HourlyForecast hourlyForecast : hourlyForecasts) {
                viewModel.insertHourlyForecast(hourlyForecast);
            }
        }
        if (dailyForecasts !=null && !dailyForecasts.isEmpty()){
            viewModel.deleteDailyForecast();
            for (DailyForecast dailyForecast : dailyForecasts){
                viewModel.insertDailyForecast(dailyForecast);
            }
        }
    }
    public void downloadData(String cityName) {
        JSONObject jsonObject=NetworkUtils.getCurrentJSON(cityName);
        currentForecast = JSONUtils.getCurrentForecastFromJSON(jsonObject);
        if (currentForecast != null) {
            viewModel.deleteCurrentForecast();
            viewModel.insertCurrentForecast(currentForecast);
            JSONObject jsonObjectDaily = NetworkUtils.getExtendedJSON(Double.toString(currentForecast.getLat()), Double.toString(currentForecast.getLon()));
            hourlyForecasts = JSONUtils.getHourlyForecastFromJSON(jsonObjectDaily, currentForecast.getCityId());
            dailyForecasts = JSONUtils.getDailyForecastFromJSON(jsonObjectDaily, currentForecast.getCityId());
        }
        if (hourlyForecasts != null && !hourlyForecasts.isEmpty()) {
            viewModel.deleteHourlyForecasts();
            for (HourlyForecast hourlyForecast : hourlyForecasts) {
                viewModel.insertHourlyForecast(hourlyForecast);
            }
        }
        if (dailyForecasts !=null && !dailyForecasts.isEmpty()){
            viewModel.deleteDailyForecast();
            for (DailyForecast dailyForecast : dailyForecasts){
                viewModel.insertDailyForecast(dailyForecast);
            }
        }
    }
    private void setCurrentWeatherView(CurrentForecast currentForecast){
        if(currentForecast!=null) {
            textViewCityName.setText(currentForecast.getCityName());
            textViewCurrentTemperature.setText(String.format("%s°C", currentForecast.getTemp()));
            textViewDescription.setText(currentForecast.getWeatherDescription());
            textViewFeelsLike.setText(String.format("Чувствуется как %s°C", currentForecast.getFeelsLike()));
            textViewWind.setText(String.format("Ветер: %s м/с", currentForecast.getWindSpeed()));
            DataHelper.setWindDirection(currentForecast.getWindDeg(), currentForecast.getWindSpeed(), imageViewCurrentWindDirection);
            textViewHumidity.setText(String.format("Влажность: %s%%", currentForecast.getHumidity()));
            textViewPressure.setText(String.format("Давление: %s", currentForecast.getPressure()));
            textViewVisibility.setText(String.format("Видимость: %s", currentForecast.getVisibility()));
            DataHelper.setWeatherIcon(currentForecast.getWeatherIcon(), weatherIcon);
        }
    }
    private void setDailyWeatherView(List<DailyForecast> forecasts){
        if (forecasts!=null&&!forecasts.isEmpty()) {
            textViewFirstDayInfo.setText(String.format("%s\n%s", getDate(forecasts.get(0)), forecasts.get(0).getWeatherDescription()));
            textViewFirstDayTemperature.setText(getTemperature(forecasts.get(0)));
            DataHelper.setWeatherIcon(forecasts.get(0).getWeatherIcon(), imageViewFirstDayWeatherIcon);
            textViewSecondDayInfo.setText(String.format("%s\n%s", getDate(forecasts.get(1)), forecasts.get(1).getWeatherDescription()));
            textViewSecondDayTemperature.setText(getTemperature(forecasts.get(1)));
            DataHelper.setWeatherIcon(forecasts.get(1).getWeatherIcon(), imageViewSecondDayWeatherIcon);
            textViewThirdDayInfo.setText(String.format("%s\n%s", getDate(forecasts.get(2)), forecasts.get(2).getWeatherDescription()));
            textViewThirdDayTemperature.setText(getTemperature(forecasts.get(2)));
            DataHelper.setWeatherIcon(forecasts.get(2).getWeatherIcon(), imageViewThirdDayWeatherIcon);
        }
    }
    private String getDate(DailyForecast forecast){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date(forecast.getDaytime()*1000L));
        return String.format("%s %s.%s",calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()),calendar.get(Calendar.DAY_OF_MONTH),calendar.getDisplayName(Calendar.MONTH,Calendar.SHORT,Locale.getDefault()));
    }
    private String getTemperature(DailyForecast forecast){
        return String.format("%s°/%s°",forecast.getMaxTemp(),forecast.getMinTemp());
    }

    private void getLocalForecast(){
        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            getLocation();
        }else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }
    }

    @SuppressLint("MissingPermission")
    private void getLocation(){
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location=task.getResult();
                if (location!=null){
                    downloadData(String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude()));
                }
            }
        });
    }
    private void showDetails(){

        ConstraintSet set=new ConstraintSet();
        set.clone(constraintLayout);

        if (isDetailsShown){
            set.setVisibility(R.id.cardViewDetails,ConstraintSet.INVISIBLE);
            set.connect(R.id.textViewDetails, ConstraintSet.TOP, R.id.textViewCurrentTemperature, ConstraintSet.BOTTOM);
            TransitionManager.beginDelayedTransition(constraintLayout);
            set.applyTo(constraintLayout);
            textViewDetails.setText(getResources().getString(R.string.show_detailed_info));
            isDetailsShown=false;
        }else {

            set.connect(R.id.textViewDetails, ConstraintSet.TOP, R.id.cardViewDetails, ConstraintSet.BOTTOM);
            set.setVisibility(R.id.cardViewDetails,ConstraintSet.VISIBLE);
            TransitionManager.beginDelayedTransition(constraintLayout);
            set.applyTo(constraintLayout);
            textViewDetails.setText(getResources().getString(R.string.hide_detailed_info));
            isDetailsShown=true;
        }

    }
    public void onClickShowDetails(View view) {
      showDetails();
    }
}