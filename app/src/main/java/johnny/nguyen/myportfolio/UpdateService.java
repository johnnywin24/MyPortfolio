package johnny.nguyen.myportfolio;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.IBinder;
import android.widget.RemoteViews;

public class UpdateService extends Service {
    public UpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String time = getCurrentDateTime();

        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.weather_widget);
        remoteViews.setTextViewText(R.id.appwidget_temp, time);
        ComponentName theWidget = new ComponentName(this, WidgetDataProvider.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        manager.updateAppWidget(theWidget, remoteViews);

        return super.onStartCommand(intent, flags, startId);
    }


    public String getCurrentDateTime(){
        Calendar calendar = Calendar.getInstance();
        int minute = calendar.get(Calendar.MINUTE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        return hour + ":" + minute;
    }
}
