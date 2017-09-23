package johnny.nguyen.myportfolio;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SoundActivity extends AppCompatActivity {

    MediaPlayer media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);
    }

    public void playSound(View view){

        if (media != null && media.isPlaying()){
            media.stop();
        }
        //VideoView vid = (VideoView) findViewById(R.id.videoView);
        ImageView image = (ImageView) findViewById(R.id.ivSound);
        RelativeLayout main = (RelativeLayout) findViewById(R.id.mainSound);
        main.setBackgroundColor(Color.BLACK);


        switch (view.getTag().toString()){
            case "farm":
                media = MediaPlayer.create(this,R.raw.farm);
                media.start();
                image.setImageResource(R.drawable.farm);
                break;

            case "water":
                media = MediaPlayer.create(this,R.raw.water);
                media.start();
                image.setImageResource(R.drawable.index);
                break;

            case "waterfall":
                media = MediaPlayer.create(this,R.raw.waterfall);
                media.start();
                image.setImageResource(R.drawable.maxresdefault);
                break;

            case "medow":
                media = MediaPlayer.create(this,R.raw.medow);
                media.start();
                image.setImageResource(R.drawable.medowimg);
                break;
        }
    }
}
