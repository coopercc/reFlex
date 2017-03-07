package reflex.ischool.washington.edu.reflex;





import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.quickstart.database.models.Post;
import com.google.firebase.quickstart.database.models.User;

import java.util.HashMap;
import java.util.Map;

public class WorkoutHistory extends AppCompatActivity {
    private ListView view;
    private DatabaseReference dataRef;
    private ArrayList<Exercise> list = new ArrayList<>();
    private static final String TAG = "NewPostActivity";
    private static final String REQUIRED = "Required";

    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]

    private EditText mTitleField;
    private EditText mBodyField;
    private FloatingActionButton mSubmitButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        view = (ListView) findViewById(R.id.act_hist);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.activity_history, list);
        view.setAdapter(adapter);
        // [START initialize_database_ref]
        dataRef = new Firebase("https://reflex-c4b55.firebaseio.com/");
        dataRef.addChildEventListener(new ChildEventListener) {
            @Override
            public void onChildAdded (DataSnapshot snap, String s){
                list.add(snap.getValue(String.class));
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged (DataSnapshot snap, String s){

            }
            @Override
            public void onChildRemoved (DataSnapshot snap, String s){
                list.remove(snap.getValue(String.class));
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onChildMoved (DataSnapshot snap, String s){

            }
            @Override
            public void onCancelld (DataSnapshot snap, String s){

            }
        }
        //dataRef = FirebaseDatabase.getInstance().getReference();
    }





