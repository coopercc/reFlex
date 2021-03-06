package reflex.ischool.washington.edu.reflex;


import android.app.Application;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View rootView;


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        // grab exercises from firebase to compare and show progress to user
        // do some algebra
        // display user their improvement/progress

        MyApplication app = (MyApplication) getActivity().getApplication();
        TextView welcome = (TextView) rootView.findViewById(R.id.welcome);
        if (app.getUserName().length() != 0) {
            welcome.setText("Welcome " + app.getUserName() + "!");
            welcome.setTextColor(Color.BLACK);
            Log.d("HOMEFRAGMENT", app.getUserName());
        }

        Button btn = (Button) rootView.findViewById(R.id.usernameBtn);
        final EditText usr = (EditText) rootView.findViewById(R.id.userName);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication app = (MyApplication) getActivity().getApplication();
                app.setUserName(usr.getText().toString());
                Toast.makeText(getActivity(), "Saved Username as: " + usr.getText().toString(), Toast.LENGTH_SHORT).show();
                Log.i("HomeFrag", app.getUserName());
                TextView welcome = (TextView) rootView.findViewById(R.id.welcome);
                if (app.getUserName().length() != 0) {
                    welcome.setText("Welcome " + app.getUserName() + "!");
                    Log.d("HOMEFRAGMENT", app.getUserName());
                }
            }
        });
        return rootView;
    }

}
