package reflex.ischool.washington.edu.reflex;


import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Exercise> exerciseList;
    private CountDownTimer aCounter;
    private TextView timeLeft;
    private boolean isPaused;
    private long secondsLeft;
    private DatabaseReference mDatabase;
    private String workout;
    private boolean hasInitiated;

    public WorkoutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //create view

        final View rootView = inflater.inflate(R.layout.fragment_workout, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.workoutRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        exerciseList = new ArrayList<Exercise>();
        secondsLeft = 90;
        workout = "";
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            workout = bundle.getString("WorkoutPosition");
        }
        Log.i("WorkoutFrag", workout);

        final Activity a = this.getActivity();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!hasInitiated) {
                    setStuff(dataSnapshot);
                    hasInitiated=true;
                }
                adapter = new workoutAdapter(exerciseList, a);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        timeLeft = (TextView) rootView.findViewById(R.id.timeLeft);

        final Button start = (Button) rootView.findViewById(R.id.start);
        aCounter = resumeCountDown(secondsLeft * 1000);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (start.getText().toString().equals("Start")) {
                    //set to stop, start countdown

                    start.setText("Stop");
                    if (isPaused) {
                        aCounter = resumeCountDown(secondsLeft * 1000);
                    }
                    aCounter.start();
                    isPaused = false;
                } else {
                    start.setText("Start");
                    isPaused = true;
                    aCounter.cancel();
                    //set to start, end countdown
                }
            }
        });

        final Button reset = (Button) rootView.findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aCounter.cancel();
                secondsLeft = 90;
                timeLeft.setText("Rest Seconds Left: 90");
                aCounter = resumeCountDown(secondsLeft * 1000);
                if (start.getText().toString().equals("Stop")) {
                    start.setText("Start");
                }
                isPaused = false;
            }
        });


        Button finish = (Button) rootView.findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get data for all exercises in the list and post to firebase, then return to home page
                for(Exercise e: exerciseList) {
                    DatabaseReference newRef = mDatabase.child("Recent").push();
                    newRef.setValue(e);
                }
            }
        });

        return rootView;


    }


    private CountDownTimer resumeCountDown(long secondsLeft) {
        return new CountDownTimer(secondsLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                setSecondsLeft(millisUntilFinished / 1000);
                timeLeft.setText("Rest Seconds Left: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timeLeft.setText("Rest Time Over!");
            }

        };
    }

    private void setSecondsLeft(long secondsLeft) {
        this.secondsLeft = secondsLeft;
    }


    private void setStuff(DataSnapshot dataSnapshot) {
        for (DataSnapshot data: dataSnapshot.getChildren()) {
            if (data.getKey().equals("workoutStat")) {
                for (DataSnapshot workouts : data.getChildren()) {
                    if (workouts.getKey().equals(workout)) {
                        for (DataSnapshot exercise : workouts.getChildren()) {
                            Exercise e = new Exercise();
                            e.setName(exercise.getKey());
                            Log.i("WorkoutFrag", exercise.getKey());
                            for (DataSnapshot numbs : exercise.getChildren()) {
                                if (numbs.getKey().equals("Sets")) {
                                    e.setSets(Integer.parseInt(numbs.getValue().toString()));
                                } else {
                                    e.setReps(Integer.parseInt(numbs.getValue().toString()));
                                }
                            }
                            exerciseList.add(e);
                        }
                    }
                }
            }
        }
    }

}


