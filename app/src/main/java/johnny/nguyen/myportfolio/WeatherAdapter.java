package johnny.nguyen.myportfolio;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by johnnywin24 on 8/30/17.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder>{

    //Prepare our list of weather objects
    private List<WeatherData> weatherList;


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtTemp;
        TextView txtDescription;

        public MyViewHolder(final View itemView) {
            super(itemView);
            txtTemp = (TextView) itemView.findViewById(R.id.txtTemp);
            txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("TEST", txtTemp.getText().toString());
                    Intent intent = new Intent(itemView.getContext(), WeatherDetails.class);
                    intent.putExtra("TEMP", txtTemp.getText().toString());
                    v.getContext().startActivity(intent);

                }
            });
        }
    }



    public WeatherAdapter(List<WeatherData> weatherData) {
        this.weatherList = weatherData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_row, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        WeatherData weatherData = weatherList.get(position);
        holder.txtTemp.setText(weatherData.temp);
        holder.txtDescription.setText(weatherData.description);
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }
}
