package johnny.nguyen.myportfolio;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;


public class DiceActivity extends AppCompatActivity {
    //Score field
    int score;

    //Textview for results
    TextView txtResults;
    TextView txtScore;

    //Random dice value
    Random rand;

    //Dice fields
    int die1, die2, die3;

    //Array list
    ArrayList<Integer> Dice;
    ArrayList<ImageView> diceImages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice(view);
            }
        });

        //Score starts at 0
        score = 0;

        //Display score
        Toast.makeText(getApplicationContext(),"Welcome to Johnny Nguyen's Dice Game",Toast.LENGTH_LONG);

        txtResults = (TextView)findViewById(R.id.txtResults);
        txtScore = (TextView) findViewById(R.id.txtScore);

        rand = new Random();

        Dice = new ArrayList<Integer>();

        ImageView die1Image = (ImageView)findViewById(R.id.die1Image);
        ImageView die2Image = (ImageView)findViewById(R.id.die2Image);
        ImageView die3Image = (ImageView)findViewById(R.id.die3Image);

        diceImages = new ArrayList<ImageView>();
        diceImages.add(die1Image);
        diceImages.add(die2Image);
        diceImages.add(die3Image);
    }

    public void rollDice(View v){
        die1 = rand.nextInt(6) + 1;
        die2 = rand.nextInt(6) + 1;
        die3 = rand.nextInt(6) + 1;

        //Array list
        Dice.clear();
        Dice.add(die1);
        Dice.add(die2);
        Dice.add(die3);

        String msg;

        if(die1 == die2 && die2 == die3){
            //triple score
            score += die1 * 100;
            msg = "You rolled a triple for triple points";
        }
        else if(die1 == die2 || die1 == die3 || die2 == die3){
            //double score
            score += 50;
            msg = "You rolled a double for 50 points";
        }
        else{
            msg = "You didn't score this roll... haha sucker";
        }

        for(int i = 0; i < 3; i++){
            String imagePath = "die_face_" + Dice.get(i) + ".png";

            try {
                InputStream stream = getAssets().open(imagePath);
                Drawable draw = Drawable.createFromStream(stream,null);
                diceImages.get(i).setImageDrawable(draw);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String results = "You rolled:" + die1 + "," + die2 + "," + die3;

        txtResults.setText(msg);
        txtScore.setText("Score: " + score);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
