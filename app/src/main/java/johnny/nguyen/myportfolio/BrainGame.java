package johnny.nguyen.myportfolio;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class BrainGame extends AppCompatActivity {

    //Declare variables
    CountDownTimer countDownTimer;
    int correct = 0;
    int wrong = 0;
    String finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain_game);
    }

    //Button to start the game
    public void go(View view){
        TextView txt = (TextView) findViewById(R.id.txtFinalScore);
        txt.setText("");
        view.setVisibility(View.INVISIBLE);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.Game);
        layout.setVisibility(View.VISIBLE);

        startTimer();
        setEquation();
    }

    //See if their input is correct, update the score with 1 or 0
    public void checkGuess(View view){
        TextView txtEquation = (TextView) findViewById(R.id.txtEquation);
        TextView txtFinalScore = (TextView) findViewById(R.id.txtFinalScore);

        if(view.getTag().toString() == "True"){
            txtEquation.setText("CORRECT!");
            txtFinalScore.setText("CORRECT!");
            updateScore(1);
        }
        else{
            txtFinalScore.setText("WRONG!");
            updateScore(0);
        };
        setEquation();
    }

    //Add to the users score
    public void updateScore(int check){
        TextView txtScore = (TextView) findViewById(R.id.txtScore);

        if (check == 1) correct++;
        else wrong++;

        finalScore = "" + correct + "/" + (correct + wrong);

        txtScore.setText(finalScore);
    }

    //Create a simple math equation
    public void setEquation(){
        TextView txtEquation = (TextView) findViewById(R.id.txtEquation);
        Random rand = new Random();
        int x = rand.nextInt(40) + 1;
        int y = rand.nextInt(40) + 1;
        txtEquation.setText("" + x + " + " + y);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        int picker = rand.nextInt(4) + 1;
        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            setChoices(picker, x + y, i+1,gridLayout.getChildAt(i));
        }
    }

    //Create random answer choices
    public void setChoices(int picker, int answer, int choice, View view){
        TextView txt = (TextView)  findViewById(view.getId());
        Random rand = new Random();

        int wrongAnswer = rand.nextInt(40) + 1;

        if(picker == choice){
            txt.setText(String.valueOf(answer));
            txt.setTag("True");
        }
        else {
            txt.setText(String.valueOf(wrongAnswer));
            txt.setTag("False");
        }

    }

    //Start game timer
    public void startTimer(){
        countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                endGame();
            }
        }.start();
    }

    public void updateTimer(long millisUntilFinished){
        int seconds = (int) (millisUntilFinished / 1000);
        TextView txtTimer = (TextView) findViewById(R.id.txtTimer);
        txtTimer.setText(":" + seconds);
    }

    public void endGame(){
        TextView txtTimer = (TextView) findViewById(R.id.txtTimer);
        txtTimer.setText("DONE!");
        Button btnGo = (Button) findViewById(R.id.btnGo);
        btnGo.setVisibility(View.VISIBLE);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.Game);
        layout.setVisibility(View.INVISIBLE);

        TextView txt = (TextView) findViewById(R.id.txtFinalScore);
        txt.setText("Game Over! Final Score:" + finalScore);


    }
}
