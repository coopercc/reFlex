package reflex.ischool.washington.edu.reflex;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by coopercain on 3/1/17.
 */

public class Exercise {

    private String name;
    private int sets, reps;
    private Timestamp timestamp;


    public void Exercise() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getSets() {
        return sets;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getReps() {
        return reps;
    }

    public void setTime(Timestamp timestamp){this.timestamp = timestamp;}

    public Timestamp getTimestamp(){return timestamp;}
}

