package reflex.ischool.washington.edu.reflex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.app.Notification;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;
import android.os.Build;
import android.widget.Toast;

public class Schedule extends Activity {

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    Button scheduleBtn;
    public final static String MESSAGE = "reflex.MESSAGE";

    DatePicker datePicker;
    TimePicker timePicker;
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        scheduleBtn = (Button) findViewById(R.id.scheduleBtn);
        info = (TextView)findViewById(R.id.info);
        datePicker = (DatePicker)findViewById(R.id.datePicker);
        timePicker = (TimePicker)findViewById(R.id.timePicker);
        Calendar now = Calendar.getInstance();

        datePicker.init(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH),
                null);

        if (Build.VERSION.SDK_INT >= 23 ) {
            timePicker.setHour(now.get(Calendar.HOUR_OF_DAY));
            timePicker.setMinute(now.get(Calendar.MINUTE));
        }
        else{
            timePicker.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
            timePicker.setCurrentMinute(now.get(Calendar.MINUTE));
        }
        alarmMgr = (AlarmManager) Schedule.this.getSystemService(Context.ALARM_SERVICE);
        scheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int date = Integer.parseInt(dateEdit.getText().toString());
                Calendar current = Calendar.getInstance();
                Calendar cal = Calendar.getInstance();
                if (Build.VERSION.SDK_INT >= 23 )
                    cal.set(datePicker.getYear(),
                            datePicker.getMonth(),
                            datePicker.getDayOfMonth(),
                            timePicker.getHour(),
                            timePicker.getMinute(),
                            00);
                 else
                    cal.set(datePicker.getYear(),
                            datePicker.getMonth(),
                            datePicker.getDayOfMonth(),
                            timePicker.getCurrentHour(),
                            timePicker.getCurrentMinute(),
                            00);

                if(cal.compareTo(current) <= 0){
                    //The set Date/Time already passed
                    Toast.makeText(getApplicationContext(),
                            "Invalid Date/Time",
                            Toast.LENGTH_LONG).show();
                }else{
                    scheduleNotification(makeNotification("You have a workout scheduled now."), cal);
                }
            }

        });
    }

    private void scheduleNotification(Notification notification, Calendar cal) {

        info.setText("\n\n***\n"
                + "Alarm is set@ " + cal.getTime() + "\n"
                + "***\n");

        Intent notificationIntent = new Intent(this, AlarmReceiver.class);
        notificationIntent.putExtra(AlarmReceiver.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(AlarmReceiver.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
    }

    private Notification makeNotification(String content) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.reflex_icon);
        return builder.build();
    }
}
