package reflex.ischool.washington.edu.reflex;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private DatabaseReference dataRef;

    public WorkoutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //create view
        dataRef = new Firebase("https://reflex-c4b55.firebaseio.com/workoutStat");
        recyclerView = (RecyclerView) getView().findViewById(R.id.workoutRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        exerciseList = new ArrayList<Exercise>();
        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Read each child of the user
                for(DataSnapshot child : dataSnapshot.getChildren()) {

                //Add the new posts from the database to the List that is connected to an adapter and later on a ListView.
                Exercise exercise = child.getValue();
                exerciseList.add(exercise);

                // We need notify the adapter that the data have been changed
                adapter.notifyDataSetChanged();

                // Call onLoadMoreComplete when the LoadMore task, has finished
                //mListView.onRefreshComplete();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        }

//        for (int i = 0; i < 3; i++) {
//            Exercise temp = new Exercise();
//            if (i == 0) {
//                temp.setName("Pushups");
//                temp.setSets(3);
//                temp.setReps(12);
//            } else if (i == 1) {
//                temp.setName("Situps");
//                temp.setSets(3);
//                temp.setReps(12);
//            } else {
//                temp.setName("Pullups");
//                temp.setSets(4);
//                temp.setReps(6);
//            }
//            exerciseList.add(temp);
//        }

        adapter = new workoutAdapter(exerciseList, this.getActivity());
        recyclerView.setAdapter(adapter);



        return inflater.inflate(R.layout.fragment_workout, container, false);


    }

}
