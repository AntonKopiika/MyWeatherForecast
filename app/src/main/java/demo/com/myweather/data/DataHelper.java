package demo.com.myweather.data;

import android.widget.ImageView;

import demo.com.myweather.R;

public class DataHelper {
    public static void setWindDirection(int deg,int speed, ImageView imageView){
        if (speed==0){
            imageView.setImageResource(R.drawable.no_wind);
        }else if (deg>=22&&deg<67){
            imageView.setImageResource(R.drawable.northeast);
        }else if (deg>=67&&deg<112){
            imageView.setImageResource(R.drawable.east);
        }else if (deg>=112&&deg<157){
            imageView.setImageResource(R.drawable.southeast);
        }else if (deg>=157&&deg<202){
            imageView.setImageResource(R.drawable.south);
        }else if (deg>=202&&deg<247){
            imageView.setImageResource(R.drawable.southwest);
        }else if (deg>=247&&deg<292){
            imageView.setImageResource(R.drawable.west);
        }else if (deg>=292&&deg<337){
            imageView.setImageResource(R.drawable.northwest);
        }else {
            imageView.setImageResource(R.drawable.north);
        }
    }
    public static void setWeatherIcon(String name,ImageView imageView){
        switch (name){
            case "01d":
                imageView.setImageResource(R.drawable.w01d);
                break;
            case "01n":
                imageView.setImageResource(R.drawable.w01n);
                break;
            case "02d":
                imageView.setImageResource(R.drawable.w02d);
                break;
            case "02n":
                imageView.setImageResource(R.drawable.w02n);
                break;
            case "03d":
                imageView.setImageResource(R.drawable.w03d);
                break;
            case "03n":
                imageView.setImageResource(R.drawable.w03n);
                break;
            case "04d":
                imageView.setImageResource(R.drawable.w04d);
                break;
            case "04n":
                imageView.setImageResource(R.drawable.w04n);
                break;
            case "09d":
                imageView.setImageResource(R.drawable.w09d);
                break;
            case "09n":
                imageView.setImageResource(R.drawable.w09n);
                break;
            case "10d":
                imageView.setImageResource(R.drawable.w10d);
                break;
            case "10n":
                imageView.setImageResource(R.drawable.w10n);
                break;
            case "11d":
                imageView.setImageResource(R.drawable.w11d);
                break;
            case "11n":
                imageView.setImageResource(R.drawable.w11n);
                break;
            case "13d":
                imageView.setImageResource(R.drawable.w13d);
                break;
            case "13n":
                imageView.setImageResource(R.drawable.w13n);
                break;
            case "50d":
                imageView.setImageResource(R.drawable.w50d);
                break;
            case "50n":
                imageView.setImageResource(R.drawable.w50n);
                break;
        }

    }
}
