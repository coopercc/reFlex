package reflex.ischool.washington.edu.reflex;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setUpBottomNavigationView();
    }

    public void setUpBottomNavigationView() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.recent:
                                Toast.makeText(HomeActivity.this, "Recent", Toast.LENGTH_SHORT).show();
                                // start new Activity/fragment
                                break;
                            case R.id.start:
                                Toast.makeText(HomeActivity.this, "Start", Toast.LENGTH_SHORT).show();
                                // start new Activity/fragment
                                WorkoutListFragment listFrag = new WorkoutListFragment();
                                FragmentTransaction tx = getFragmentManager().beginTransaction();
                                tx.replace(R.id.fragment_placeholder, listFrag);
                                tx.commit();
                                break;
                            case R.id.schedule:
                                Toast.makeText(HomeActivity.this, "Schedule", Toast.LENGTH_SHORT).show();
                                // start new Activity/fragment
                                Intent intent = new Intent(HomeActivity.this, Schedule.class);
                                startActivity(intent);
                                break;
                        }
                        return true;
                    }
                });
    }
}
