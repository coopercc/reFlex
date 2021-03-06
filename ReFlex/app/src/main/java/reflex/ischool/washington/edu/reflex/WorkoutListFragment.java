package reflex.ischool.washington.edu.reflex;


import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;



import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutListFragment extends Fragment {
    private DatabaseReference mDatabase;
    private Context context;

    public WorkoutListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_workout_list, container, false);

        //get all workout names


        context = getActivity().getApplicationContext();

        final List<String> workoutList = new ArrayList<String>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    if (data.getKey().equals("workoutStat")) {
                        Log.i("WorkoutListFrag", data.getValue().toString());
                        for(DataSnapshot workouts: data.getChildren()) {
                            Log.i("WorkoutListFrag", workouts.getKey());
                            workoutList.add(workouts.getKey());
                            Log.i("WorkoutListFrag", workoutList.toString());
                        }
                    }
                    //Object value = data.child().getValue();

                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, workoutList);
                final ListView listView = (ListView) rootView.findViewById(R.id.workoutList);
                listView.setAdapter(adapter);
                Log.i("WorkoutListFrag", "Adding list");

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //change to workoutFragment

                        Bundle bundle = new Bundle();
                        bundle.putString("WorkoutPosition", workoutList.get(position));
                        WorkoutFragment workoutFrag = new WorkoutFragment();
                        workoutFrag.setArguments(bundle);
                        FragmentTransaction tx = getFragmentManager().beginTransaction();
                        tx.replace(R.id.fragment_placeholder, workoutFrag);
                        tx.commit();

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return rootView;
    }

}
