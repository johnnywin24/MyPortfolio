package johnny.nguyen.myportfolio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class CurrencyConverterActivity extends AppCompatActivity {

    Random rand = new Random();
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_converter);
        num = rand.nextInt(20) + 1;
    }

    public void convert(View view){

        Spinner spinIn = (Spinner) findViewById(R.id.spinnerInput);
        Spinner spinOut = (Spinner) findViewById(R.id.spinnerOutput);

        String currencyIn = spinIn.getSelectedItem().toString();
        String currencyOut = spinOut.getSelectedItem().toString();

        EditText amount = (EditText) findViewById(R.id.etInput);
        TextView tv = (TextView) findViewById(R.id.tvOutput);

        double inAmount = Integer.parseInt(amount.getText().toString());
        double converted = inAmount;

        switch (currencyIn){
            case "Euros":
                converted = inAmount * 1.18;
                break;
            case "Pounds":
                converted = inAmount * 1.29;
                break;
            case "Rupees":
                converted = inAmount * .016;
                break;
            case "Yen":
                converted = inAmount * .0092;
                break;
        }

        switch (currencyOut){
            case "Euros":
                converted = converted * .85;
                break;
            case "Pounds":
                converted = converted * .77;
                break;
            case "Rupees":
                converted = converted * 64.08;
                break;
            case "Yen":
                converted = converted * 109.2;
                break;
        }

        String output = String.format("%.2f", converted);

        tv.setText(output);
    }

}
