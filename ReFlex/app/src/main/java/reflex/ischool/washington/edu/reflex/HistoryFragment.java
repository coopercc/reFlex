package reflex.ischool.washington.edu.reflex;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
