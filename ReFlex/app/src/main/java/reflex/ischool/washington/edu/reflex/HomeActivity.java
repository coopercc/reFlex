package reflex.ischool.washington.edu.reflex;

import android.app.Activity;
import android.content.Intent;
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
                            case R.id.home:
                                Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                                // start new Activity/fragment
                                break;
                            case R.id.recents:
                                Toast.makeText(HomeActivity.this, "Recent", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.workout:
                                Toast.makeText(HomeActivity.this, "Start", Toast.LENGTH_SHORT).show();
                                // start new Activity/fragment
                                break;
                            case R.id.schedule:
                                Toast.makeText(HomeActivity.this, "Schedule", Toast.LENGTH_SHORT).show();
                                // start new Activity/fragment
                                break;
                        }
                        return true;
                    }
                });
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
    }
}