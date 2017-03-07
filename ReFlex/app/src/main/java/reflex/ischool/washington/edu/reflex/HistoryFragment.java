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
    private DatabaseReference mDaabase;
    // [END declare_database_ref]

    private EditText mTitleField;
    private EditText mBodyField;
    private FloatingActionButton mSubmitButton;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dataRef = database.getReference("workoutStat/Recent");
        Query q = dataRef;
        final View rootView = inflater.inflate(R.layout.fragment_recent, container, false);
        Log.i("WorkoutListFrag", "start reading recent");
//        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recent_list);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        //final ArrayAdapter<Exercise> adapter = new ArrayAdapter<Exercise>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, list);
        FirebaseListAdapter<Exercise> adapter = new FirebaseListAdapter<Exercise>(getActivity(), Exercise.class, R.layout.fragment_recent,q){

            @Override
            protected void populateView(View v, Exercise exercise, int position) {
                TextView text1=(TextView)v.findViewById(R.id.text1);
                TextView text2=(TextView)v.findViewById(R.id.text2);
                TextView text3=(TextView)v.findViewById(R.id.text3);
                text1.setText(exercise.getSets());
                text2.setText(exercise.getName());
                text3.setText(exercise.getReps());

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
        view.setAdapter(adapter);
        return rootView;
    }
}
