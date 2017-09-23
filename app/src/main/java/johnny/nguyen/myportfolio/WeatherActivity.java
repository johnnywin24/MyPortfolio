package johnny.nguyen.myportfolio;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Class with an Async Task that will create a JSON Object Array

public class WeatherActivity extends AppCompatActivity {

    String apiKey = "5d0a97b217591c5628f33fce58ddc810";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
    }

    //Create the Async Task
    public class DownloadWeather extends AsyncTask<String, Integer, String>{


        //Do the http call in the backgroun
        @Override
        protected String doInBackground(String... urls) {
            try {
                String result = "";
                URL url = new URL(urls[0]);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();

                InputStream stream = http.getInputStream();
                InputStreamReader reader = new InputStreamReader(stream);

                int data = reader.read();
                while(data != -1){
                    char letter = (char) data;
                    result += letter;
                    data = reader.read();
                }


                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "FAILED";
        }

        //After download is completed, create our JSON array
        @Override
        protected void onPostExecute(String s) {
            Log.i("TEST", s);
            List<WeatherData> data = new ArrayList<WeatherData>();

            try {
                JSONObject object = new JSONObject(s);
                String list = object.getString("list");
                JSONArray jsonArray = new JSONArray(list);

                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    WeatherData weatherData = new WeatherData();

                    String weather = jsonObject.getString("weather");
                    String description = parseString(weather);
                    weatherData.description = description;

                    String main = jsonObject.getString("main");
                    JSONObject objectT = new JSONObject(main);
                    String temp = objectT.getString("temp");
                    String tempF = getTemperature(Double.parseDouble(temp));
                    weatherData.temp = tempF;
                    data.add(weatherData);
                }

                RecyclerView rv = (RecyclerView) findViewById(R.id.rvWeather);
                WeatherAdapter weatherAdapter = new WeatherAdapter(data);
                rv.setAdapter(weatherAdapter);
                rv.setLayoutManager(new LinearLayoutManager(WeatherActivity.this));

            } catch (JSONException e) {
                e.printStackTrace();
            }

            super.onPostExecute(s);
        }
    }

    public String parseString(String weather) {


        String main = "Not Found";

        String desc = "None";

        Pattern p = Pattern.compile("main\":\"(.*?)\"");
        Matcher m = p.matcher(weather);
        if (m.find()) main = m.group(1);

        p = Pattern.compile("description\":\"(.*?)\"");
        m = p.matcher(weather);
        if (m.find()) desc = m.group(1);

        return main + ": " + desc;
    }

    public static String getTemperature(double kelvin) {


        //° F = 9/5 (K - 273) + 32
        int newTemp = (int) (kelvin - 273);
        float conv = (float) 9 / 5;
        newTemp = (int) (newTemp * conv);
        newTemp = newTemp + 32;

        return String.valueOf(newTemp) + "° Fahrenheit";
    }

    public void SearchWeather(View view) {

        EditText editText = (EditText) findViewById(R.id.etWeatherInput);
        String city = editText.getText().toString();


        if (city == null || city.isEmpty())
            Toast.makeText(this, "Must Enter a City or Zipcode", Toast.LENGTH_SHORT).show();
        else {
            String apiURL = "http://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid=" + apiKey;

            DownloadWeather downloadWeather = new DownloadWeather();

            try {
                String result = downloadWeather.execute(apiURL).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

}
