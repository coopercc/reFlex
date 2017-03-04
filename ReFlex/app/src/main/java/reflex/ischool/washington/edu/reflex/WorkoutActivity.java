package reflex.ischool.washington.edu.reflex;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class WorkoutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        WorkoutListFragment workoutFrag = new WorkoutListFragment();
        FragmentTransaction tx = getFragmentManager().beginTransaction();
        tx.replace(R.id.fragment_placeholder, workoutFrag);
        tx.commit();
    }
}
