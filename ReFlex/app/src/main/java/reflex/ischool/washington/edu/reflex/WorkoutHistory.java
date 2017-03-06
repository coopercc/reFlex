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

public class WorkoutHistory extends AppCompatActivity {
    DatabaseReference dataRef;
    ListView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        view = (ListView) findViewById(R.id.act_hist);
        dataRef = FirebaseDatabase.getInstance().getReference();
        dataRef.addChildEventListener(new ChildEventListener(){

        }
    })
    }




