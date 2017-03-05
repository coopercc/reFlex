package reflex.ischool.washington.edu.reflex;

import android.app.Application;

/**
 * Created by Sarah on 3/5/2017.
 */

public class MyApplication extends Application {
    private int requestCode = 0;

    public int getRequestCode() {
        return requestCode;
    }

    public void incrRequestCode() {
        requestCode++;
    }

}
