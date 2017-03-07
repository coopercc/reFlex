package reflex.ischool.washington.edu.reflex;


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


        for (int i = 0; i < 3; i++) {
            Exercise temp = new Exercise();
            if (i == 0) {
                temp.setName("Pushups");
                temp.setSets(3);
                temp.setReps(12);
            } else if (i == 1) {
                temp.setName("Situps");
                temp.setSets(3);
                temp.setReps(12);
            } else {
                temp.setName("Pullups");
                temp.setSets(4);
                temp.setReps(6);
            }
            exerciseList.add(temp);
        }

        adapter = new workoutAdapter(exerciseList, this.getActivity());
        recyclerView.setAdapter(adapter);

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

}


