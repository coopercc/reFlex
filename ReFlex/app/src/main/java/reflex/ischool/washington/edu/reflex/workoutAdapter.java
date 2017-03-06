package reflex.ischool.washington.edu.reflex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
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
    public void onBindViewHolder(final workoutAdapter.ViewHolder holder, int position) {
        Exercise e = listItems.get(position);

        holder.title.setText(e.getName());

        holder.reps.setText(e.getReps()+"", TextView.BufferType.EDITABLE);

        String[] items = new String[]{"1","2","3","4","5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, items);
        holder.sets.setAdapter(adapter);

        final int defSets = e.getSets();
        holder.sets.setSelection(defSets - 1);
        holder.setBoxes.get(3).setVisibility(View.INVISIBLE);
        holder.setBoxes.get(4).setVisibility(View.INVISIBLE);


        holder.sets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < 5; i++) {
                    if (i <= position) {
                        holder.setBoxes.get(i).setVisibility(View.VISIBLE);
                    } else {
                        holder.setBoxes.get(i).setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//
//        holder.sets.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
        //sets listener to the check boxes

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public Spinner sets;

        public EditText reps;
        public List<CheckBox> setBoxes;



        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.cardItemTitle);
            sets = (Spinner) itemView.findViewById(R.id.cardItemSets);
            reps = (EditText) itemView.findViewById(R.id.cardItemReps);
            setBoxes = new ArrayList<CheckBox>();
            setBoxes.add((CheckBox) itemView.findViewById(R.id.set1));
            setBoxes.add((CheckBox) itemView.findViewById(R.id.set2));
            setBoxes.add((CheckBox) itemView.findViewById(R.id.set3));
            setBoxes.add((CheckBox) itemView.findViewById(R.id.set4));
            setBoxes.add((CheckBox) itemView.findViewById(R.id.set5));



        }
    }
}
