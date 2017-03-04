package reflex.ischool.washington.edu.reflex;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutListFragment extends Fragment {


    public WorkoutListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        List<String> workoutList = new ArrayList<String>();
        workoutList.add("Workout 1");
        workoutList.add("Workout 2");
        workoutList.add("Workout 3");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, workoutList);
        final ListView listView = (ListView) getView().findViewById(R.id.workoutList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //change to workoutFragment

                Bundle bundle = new Bundle();
                bundle.putInt("WorkoutPosition", position);
                WorkoutFragment workoutFrag = new WorkoutFragment();
                workoutFrag.setArguments(bundle);
                FragmentTransaction tx = getFragmentManager().beginTransaction();
                tx.replace(R.id.fragment_placeholder, workoutFrag);
                tx.commit();

            }
        });

        return inflater.inflate(R.layout.fragment_workout_list, container, false);
    }

}
