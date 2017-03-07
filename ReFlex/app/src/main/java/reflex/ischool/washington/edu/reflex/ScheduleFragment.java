package reflex.ischool.washington.edu.reflex;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;

public class ScheduleFragment extends Fragment {

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    Button scheduleBtn;
    public final static String MESSAGE = "reflex.MESSAGE";

    DatePicker datePicker;
    TimePicker timePicker;
    TextView info;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);
        final MyApplication app = (MyApplication)this.getActivity().getApplication();
        scheduleBtn = (Button) v.findViewById(R.id.scheduleBtn);
        info = (TextView) v.findViewById(R.id.info);
        datePicker = (DatePicker) v.findViewById(R.id.datePicker);
        timePicker = (TimePicker) v.findViewById(R.id.timePicker);
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
        alarmMgr = (AlarmManager) this.getActivity().getSystemService(Context.ALARM_SERVICE);
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
                    Toast.makeText(app.getApplicationContext(),
                            "Invalid Date/Time",
                            Toast.LENGTH_LONG).show();
                }else{
                    scheduleNotification(makeNotification("You have a workout scheduled now."), cal);
                    Toast.makeText(app.getApplicationContext(),
                            "Workout scheduled for " + cal.getTime(),
                            Toast.LENGTH_LONG).show();
                }
            }

        });

        return v;
    }

    private void scheduleNotification(Notification notification, Calendar cal) {
/*
        info.setText("\n\n***\n"
                + "Alarm is set@ " + cal.getTime() + "\n"
                + "***\n");
*/
        Intent notificationIntent = new Intent(this.getActivity(), AlarmReceiver.class);
        notificationIntent.putExtra(AlarmReceiver.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(AlarmReceiver.NOTIFICATION, notification);
        ((MyApplication) this.getActivity().getApplication()).incrRequestCode();
        int requestCode = ((MyApplication) this.getActivity().getApplication()).getRequestCode();
        //Log.i("Schedule", "requestCode is " + requestCode);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getActivity(), requestCode, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)this.getActivity().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
    }

    private Notification makeNotification(String content) {
        Notification.Builder builder = new Notification.Builder(this.getActivity());
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.reflex_icon);
        builder.setVibrate(new long[] {1000, 1000, 1000, 1000, 1000});
        builder.setPriority(Notification.PRIORITY_HIGH);
        return builder.build();
    }

}
