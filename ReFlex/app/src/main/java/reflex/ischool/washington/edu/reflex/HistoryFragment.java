package reflex.ischool.washington.edu.reflex;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.*;

/**
 * Created by apple on 3/6/17.
 */

public class HistoryFragment extends Fragment {
    private ListView view;
    private DatabaseReference dataRef;
    private ArrayList<Exercise> list = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]


    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dataRef = database.getReference();
        Query q = dataRef;
        final View rootView = inflater.inflate(R.layout.fragment_recent, container, false);
        Log.i("HistoryFragment", "onCreateView called");
//
//        dataRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot data: dataSnapshot.getChildren()) {
//                    if (data.getKey().equals("Recent")) {
//                        for (DataSnapshot example : data.getChildren()) {
//                            if (example.getKey().equals("test")) {
//                                Log.d("HistoryFragment", "the string is here: " + example.getValue());
//                                /*
//                                for (DataSnapshot exercise : workouts.getChildren()) {
//                                    Exercise e = new Exercise();
//                                    e.setName(exercise.getKey());
//                                    Log.i("WorkoutFrag", exercise.getKey());
//                                    for (DataSnapshot numbs : exercise.getChildren()) {
//                                        if (numbs.getKey().equals("Sets")) {
//                                            e.setSets(Integer.parseInt(numbs.getValue().toString()));
//                                        } else {
//                                            e.setReps(Integer.parseInt(numbs.getValue().toString()));
//                                        }
//                                    }
//                                    Log.i("WorkoutFrag", e.toString());
//                                    exerciseList.add(e);
//                                }
//                                */
//                            }
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });



//        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recent_list);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        //final ArrayAdapter<Exercise> adapter = new ArrayAdapter<Exercise>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, list);
        FirebaseListAdapter<Exercise> adapter = new FirebaseListAdapter<Exercise>(getActivity(), Exercise.class, android.R.layout.simple_list_item_1, q) {
            @Override
            protected void populateView(View v, Exercise exercise, int position) {


                Log.d("HistoryFragment", "populateView was called.");
            }
        };
//        dataRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                Log.i("WorkoutListFrag", "reading recent");
//                list.add(dataSnapshot.getValue(Exercise.class));
//                System.out.println(dataSnapshot.getValue(Exercise.class));
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                list.remove(dataSnapshot.getValue(String.class));
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//        adapter = new workoutAdapter(list, this.getActivity());
        ListView listView = (ListView) rootView.findViewById(R.id.recent_list);
        listView.setAdapter(adapter);
        return rootView;
    }
}
