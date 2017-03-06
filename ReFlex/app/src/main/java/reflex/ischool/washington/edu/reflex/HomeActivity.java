package reflex.ischool.washington.edu.reflex;

import android.app.Activity;
import android.app.FragmentTransaction;
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
                                WorkoutListFragment workoutList = new WorkoutListFragment();
                                FragmentTransaction tx = getFragmentManager().beginTransaction();
                                tx.replace(R.id.fragment_placeholder, workoutList);
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
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
    }
}


//set up firebase
private DatabaseReference mDatabase;
// ...
mDatabase = FirebaseDatabase.getInstance().getReference();

public class User {

    public String username;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

}

    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        mDatabase.child("users").child(userId).setValue(user);
    }

mDatabase.child("users").child(userId).child("username").setValue(name);
