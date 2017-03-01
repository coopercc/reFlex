package reflex.ischool.washington.edu.reflex;

import android.app.Activity;
import android.os.Bundle;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}


//set up firebase
private DatabaseReference mDatabase;
// ...
mDatabase = FirebaseDatabase.getInstance().getReference();

public class User {

    public String username;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

}

    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        mDatabase.child("users").child(userId).setValue(user);
    }

mDatabase.child("users").child(userId).child("username").setValue(name);
