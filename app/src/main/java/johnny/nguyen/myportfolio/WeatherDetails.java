package johnny.nguyen.myportfolio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class WeatherDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);
        TextView txt = (TextView) findViewById(R.id.txtDetailsTemp);
        txt.setText(getIntent().getStringExtra("TEMP"));
    }
}
