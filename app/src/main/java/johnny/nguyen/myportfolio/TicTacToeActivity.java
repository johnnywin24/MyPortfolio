package johnny.nguyen.myportfolio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TicTacToeActivity extends AppCompatActivity {


    boolean active = true;
    boolean blueTurn = true;
    int[] board = {2,2,2,2,2,2,2,2,2};
    int[][] winner = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
    }


    public void addChip(View view){

        ImageView spot = (ImageView) view;
        int grid = Integer.parseInt(view.getTag().toString());

        if (board[grid] == 2 && active){
            if (blueTurn == true) {
                spot.setImageResource(R.drawable.circle_blue);
                board[grid] = 0;
                blueTurn = false;
            }
            else{
                spot.setImageResource(R.drawable.circle_orange);
                board[grid] = 1;
                blueTurn = true;
            }

            spot.animate().alpha(1f).setDuration(2000);
            spot.animate().rotationXBy(180f).setDuration(1000);

            checkScore();
        }

    }

    public void playAgain(View view){
        LinearLayout layout = (LinearLayout) findViewById(R.id.gamOver);
        layout.setVisibility(View.INVISIBLE);
        blueTurn = true;
        for(int i = 0; i < board.length;i++){
            board[i] = 2;
        }
        GridLayout glayout = (GridLayout) findViewById(R.id.grid);

        for(int i = 0; i < glayout.getChildCount();i++){
            ((ImageView)glayout.getChildAt(i)).setImageResource(0);
        }

        active = true;

    }

    public void checkScore(){

        TextView txt = (TextView) findViewById(R.id.txtResults);
        LinearLayout layout = (LinearLayout) findViewById(R.id.gamOver);

        for(int[] winningNumbers : winner){
            if (board[winningNumbers[0]] == board[winningNumbers[1]]&&
                    board[winningNumbers[0]] == board[winningNumbers[2]]&&
                    board[winningNumbers[0]] != 2){

                active = false;
                if (board[winningNumbers[0]] == 0){
                    Toast.makeText(this, "Blue Wins!", Toast.LENGTH_SHORT).show();
                    txt.setText("Blue Wins!");
                    layout.setVisibility(View.VISIBLE);
                    break;
                }
                else{
                    Toast.makeText(this, "Orange Wins!", Toast.LENGTH_SHORT).show();
                    txt.setText("Orange Wins!");
                    layout.setVisibility(View.VISIBLE);
                    break;
                }
            }
            else{
                int counter = 0;
                for (int i = 0;i < board.length; i++){
                    if(board[i] == 2){counter++;};
                }

                if(counter == 0){
                    txt.setText("Its a Draw!");
                    layout.setVisibility(View.VISIBLE);
                }
            }
        }

    }

}
