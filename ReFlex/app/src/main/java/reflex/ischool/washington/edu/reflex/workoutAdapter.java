package reflex.ischool.washington.edu.reflex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by coopercain on 3/1/17.
 */

public class workoutAdapter extends RecyclerView.Adapter<workoutAdapter.ViewHolder> {

    private List<Exercise> listItems;
    private Context context;

    public workoutAdapter(List<Exercise> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public workoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(workoutAdapter.ViewHolder holder, int position) {
        Exercise e = listItems.get(position);

        holder.title.setText(e.getName());
        holder.sets.setText(e.getSets());
        holder.reps.setText(e.getReps());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public EditText sets;
        public EditText reps;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.cardItemTitle);
            sets = (EditText) itemView.findViewById(R.id.cardItemSets);
            reps = (EditText) itemView.findViewById(R.id.cardItemReps);
        }
    }
}
