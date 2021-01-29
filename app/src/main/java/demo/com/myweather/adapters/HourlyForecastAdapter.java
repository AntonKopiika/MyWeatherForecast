package demo.com.myweather.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import demo.com.myweather.R;
import demo.com.myweather.data.DataHelper;
import demo.com.myweather.data.HourlyForecast;


public class HourlyForecastAdapter extends RecyclerView.Adapter<HourlyForecastAdapter.ForecastViewHolder> {
    private List<HourlyForecast> hourlyForecasts;

    public List<HourlyForecast> getHourlyForecasts() {
        return hourlyForecasts;
    }

    public void setHourlyForecasts(List<HourlyForecast> hourlyForecasts) {
        this.hourlyForecasts = hourlyForecasts;
        notifyDataSetChanged();
    }

    public HourlyForecastAdapter() {
        hourlyForecasts = new ArrayList<>();
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_forecast_item,parent,false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        HourlyForecast forecast=hourlyForecasts.get(position);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date(forecast.getDaytime()*1000L));
        SimpleDateFormat formatter;
        if (calendar.get(Calendar.HOUR_OF_DAY)==0){
            formatter = new SimpleDateFormat("d.MM", Locale.getDefault());
        }else {
            formatter =new SimpleDateFormat("H:mm", Locale.getDefault());
        }
        DataHelper.setWindDirection(forecast.getWindDeg(),forecast.getWindSpeed(),holder.imageViewHourlyWindDirection);
        String time = formatter.format(forecast.getDaytime()*1000L);
        holder.textViewHourlyDayTime.setText(time);
        holder.textViewHourlyTemperature.setText(String.format("%d°C",forecast.getTemp()));
        holder.textViewHourlyWindSpeed.setText(String.format("%d м/c",forecast.getWindSpeed()));
        DataHelper.setWeatherIcon(forecast.getWeatherIcon(),holder.imageViewHourlyWeatherIcon);
    }

    @Override
    public int getItemCount() {
        return hourlyForecasts.size();
    }

    class ForecastViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewHourlyDayTime;
        private ImageView imageViewHourlyWeatherIcon;
        private TextView textViewHourlyTemperature;
        private TextView textViewHourlyWindSpeed;
        private ImageView imageViewHourlyWindDirection;
        public ForecastViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHourlyDayTime=itemView.findViewById(R.id.textViewHourlyDaytime);
            imageViewHourlyWeatherIcon=itemView.findViewById(R.id.imageViewHourlyWeatherIcon);
            textViewHourlyTemperature=itemView.findViewById(R.id.textViewHourlyTemperature);
            textViewHourlyWindSpeed=itemView.findViewById(R.id.textViewHourlyWindSpeed);
            imageViewHourlyWindDirection=itemView.findViewById(R.id.imageViewHourlyWindDirection);
        }
    }
}
