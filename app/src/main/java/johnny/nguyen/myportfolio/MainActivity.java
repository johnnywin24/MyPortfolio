package johnny.nguyen.myportfolio;

import android.content.Intent;
import android.content.res.Configuration;
import android.icu.util.Currency;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //Method for processing different button clicks on our home screen menu
    public void selectNav(View view) {
        Intent intent;
        switch (view.getTag().toString()) {
            case "bio":
                intent = new Intent(this, BioActivity.class);
                startActivity(intent);
                break;
            case "weather":
                intent = new Intent(this, WeatherActivity.class);
                startActivity(intent);
                break;
            case "braingame":
                intent = new Intent(this, BrainGame.class);
                startActivity(intent);
                break;
            case "tictactoe":
                intent = new Intent(this, TicTacToeActivity.class);
                startActivity(intent);
                break;
            case "sounds":
                intent = new Intent(this, SoundActivity.class);
                startActivity(intent);
                break;
            case "dice":
                intent = new Intent(this, DiceActivity.class);
                startActivity(intent);
                break;
            case "converter":
                intent = new Intent(this, CurrencyConverterActivity.class);
                startActivity(intent);
                break;
        }

    }
}

