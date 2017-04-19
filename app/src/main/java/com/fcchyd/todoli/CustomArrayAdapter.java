package com.fcchyd.todoli;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<ToDoData> {
    private final Context context;
    private ArrayList<ToDoData> toDoDatum;


    public CustomArrayAdapter(Context context, ArrayList<ToDoData> todoDatum) {
        super(context, 0, todoDatum);
        this.context = context;
        this.toDoDatum = todoDatum;
    }

    private static class ViewHolder {
        protected Button deleteButton;
        protected CheckBox statusCheckBox;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        final ViewHolder viewHolder;
        if (convertView == null) {
            //Individual row list is inflated below.
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.row_todo, parent, false);
            //viewholder instantiation
            viewHolder = new ViewHolder();
            //saving view holder member variables with XML IDs.
            viewHolder.deleteButton = (Button) view.findViewById(R.id.row_todo_delete);
            viewHolder.statusCheckBox = (CheckBox) view.findViewById(R.id.text_todo_checkbox);
            //
            viewHolder.statusCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    ToDoData element = (ToDoData) viewHolder.statusCheckBox.getTag();
                    element.setSelected(buttonView.isChecked());
                }
            });
            view.setTag(viewHolder);
            viewHolder.statusCheckBox.setTag(toDoDatum.get(position));
            viewHolder.deleteButton.setTag(toDoDatum.get(position));
        } else {
            view = convertView;
            ((ViewHolder)view.getTag()).statusCheckBox.setTag(toDoDatum.get(position));
            ((ViewHolder)view.getTag()).deleteButton.setTag(toDoDatum.get(position));
        }
        final ViewHolder holder = (ViewHolder) view.getTag();
        //Get relevant text from array list.
        holder.statusCheckBox.setText(toDoDatum.get(position).getTodoText());
        //Get checked status from array list.
        holder.statusCheckBox.setChecked(toDoDatum.get(position).isSelected());
        //When the checkbox button of any individual item is pressed
        holder.statusCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    buttonView.setPaintFlags(buttonView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    buttonView.setTextColor(Color.rgb(158, 158, 158));

                    ((MainActivity) context).TaskCounter(5);
                } else {
                    buttonView.setPaintFlags(0);
                    buttonView.setTextColor(Color.WHITE);
                }
            }
        });
        //When the delete button of any individual item is pressed.
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDoDatum.remove(toDoDatum.get(position));
                notifyDataSetChanged();
                holder.statusCheckBox.setSelected(true);

            }
        });
        return view;
    }
}
