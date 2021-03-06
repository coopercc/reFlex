package reflex.ischool.washington.edu.reflex;

/**
 * Created by Sarah on 3/1/2017.
 */

import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.app.Notification;
        import android.app.NotificationManager;

public class AlarmReceiver extends BroadcastReceiver {
    public static String NOTIFICATION_ID = "notification-id";
    public static String NOTIFICATION = "notification";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = intent.getParcelableExtra(NOTIFICATION);
        int id = intent.getIntExtra(NOTIFICATION_ID, 0);
        notificationManager.notify(id, notification);
    }
}

