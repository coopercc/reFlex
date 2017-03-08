package reflex.ischool.washington.edu.reflex;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.LinearLayout;
import android.widget.TableLayout;
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

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by apple on 3/6/17.
 */

public class HistoryFragment extends Fragment {

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference dataRef = database.getReference();
        final View rootView = inflater.inflate(R.layout.fragment_recent, container, false);
        Log.i("HistoryFragment", "onCreateView called");
        MyApplication app = (MyApplication) getActivity().getApplication();
        final String username = app.getUserName();
        final DatabaseReference dbRef = dataRef.child("Recent/"+username);

//        dataRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
////                Query q = dataRef.child("Recent/"+username);
////                FirebaseListAdapter<Exercise> adapter = new FirebaseListAdapter<Exercise>(getActivity(), Exercise.class, android.R.layout.simple_list_item_1, q) {
////                    @Override
////                    protected void populateView(View v, Exercise exercise, int position) {
////
////                        Log.d("HistoryFragment", "populateView was called." + exercise.getName());
////                    }
////                };
////                ListView listView = (ListView) rootView.findViewById(R.id.recent_list);
////                listView.setAdapter(adapter);
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
        dbRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                TableLayout tableLayout = (TableLayout) rootView.findViewById(R.id.recent_list);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Exercise e = snapshot.getValue(Exercise.class);
                    int index = snapshot.getKey().indexOf(":");
                    String key = snapshot.getKey().substring(0, index);
                    long timestamp = Long.parseLong(key);
                    Date d = new Date(timestamp);
                    final TextView textView = new TextView(getActivity());
                    String label = "On " + d.toString() + ", you did \n\t\t\t\t" + e.getReps() +
                            " reps and \n\t\t\t\t" + e.getSets() + " sets of " + e.getName() + ".";
                    textView.setText(label);
                    textView.setTextColor(Color.BLACK);
                    textView.setPadding(5, 10, 5, 10);
                    textView.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                    tableLayout.addView(textView);
                    tableLayout.setPadding(50, 50, 50, 50);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        return rootView;
    }
}
