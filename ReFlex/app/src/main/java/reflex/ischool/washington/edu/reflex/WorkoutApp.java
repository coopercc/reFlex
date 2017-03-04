package reflex.ischool.washington.edu.reflex;

import android.app.Application;

/**
 * Created by coopercain on 3/2/17.
 */

public class WorkoutApp extends Application {
    private workoutRepository workoutRepo;


    public workoutRepository getRepository() {
        return workoutRepo;
    }

    @Override
    public void onCreate() {
        workoutRepo = new workoutRepository();
    }
}
