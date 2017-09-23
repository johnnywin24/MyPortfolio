package johnny.nguyen.myportfolio;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class BioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio);
        Configuration config = getResources().getConfiguration();

        //Update Layout file based on orientation
        if(config.orientation == Configuration.ORIENTATION_LANDSCAPE){
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.bioLinear);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            ImageView profilePic = (ImageView) findViewById(R.id.ivProfile);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity= Gravity.CENTER_VERTICAL;
            profilePic.setLayoutParams(layoutParams);

            Button button = (Button) findViewById(R.id.btnHi);
            button.setGravity(Gravity.CENTER_VERTICAL);

        }
    }

    public void sendNotification(View view){

        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this);

        Intent intent = new Intent(this, WeatherActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        mBuilder.setContentIntent(pendingIntent);

        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Hi!")
                .setContentText("Well hello to you too");

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        /*

        When you issue multiple notifications about the same type of event, it’s best practice for
        your app to try to update an existing notification with this new information, rather than
        immediately creating a new notification. If you want to update this notification at a later
        date, you need to assign it an ID. You can then use this ID whenever you issue a subsequent
        notification. If the previous notification is still visible, the system will update this
        existing notification, rather than create a new one. In this example, the notification’s
        ID is 001//
         */

        mNotificationManager.notify(001, mBuilder.build());
    }
}
