package demo.com.myweather.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import demo.com.myweather.R;
import demo.com.myweather.data.DailyForecast;
import demo.com.myweather.data.DataHelper;

public class DailyForecastListAdapter extends BaseAdapter {
    public DailyForecastListAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.forecasts = new ArrayList<>();
        this.context = context;
    }

    public List<DailyForecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<DailyForecast> forecasts) {
        if (forecasts != null && !forecasts.isEmpty()) {
            this.forecasts = forecasts;
            notifyDataSetChanged();
        }
    }
    private LayoutInflater inflater;
    private List<DailyForecast> forecasts;
    private Context context;
    private TextView textViewDailyDate;
    private TextView textViewDailyDescription;
    private ImageView imageViewDailyWeatherIcon;
    private TextView textViewDailyTemperature;
    @Override
    public int getCount() {
        return forecasts.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view=inflater.inflate(R.layout.daily_forecast_list_item,null,false);
        DailyForecast forecast=forecasts.get(position);
        textViewDailyDate=view.findViewById(R.id.textViewDailyDate);
        textViewDailyDescription=view.findViewById(R.id.textViewDailyDescription);
        textViewDailyTemperature=view.findViewById(R.id.textViewDailyTemperature);
        imageViewDailyWeatherIcon=view.findViewById(R.id.imageViewDailyWeatherIcon);
        textViewDailyDate.setText(getDate(forecast));
        textViewDailyDescription.setText(forecast.getWeatherDescription());
        textViewDailyTemperature.setText(getTemperature(forecast));
        DataHelper.setWeatherIcon(forecast.getWeatherIcon(),imageViewDailyWeatherIcon);
        return view;
    }
    private String getDate(DailyForecast forecast){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date(forecast.getDaytime()*1000L));
        return String.format("%s %s.%s",calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()),calendar.get(Calendar.DAY_OF_MONTH),calendar.getDisplayName(Calendar.MONTH,Calendar.SHORT,Locale.getDefault()));
    }
    private String getTemperature(DailyForecast forecast){
        return String.format("%s°/%s°",forecast.getMaxTemp(),forecast.getMinTemp());
    }

}
