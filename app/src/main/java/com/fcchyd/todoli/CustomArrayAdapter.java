package com.fcchyd.todoli;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<ToDoData> {
    private final Context context;
    private ArrayList<ToDoData> toDoDatum;

    public CustomArrayAdapter(Context context, ArrayList<ToDoData> todoDatum) {
        super(context, 0, todoDatum);
        this.context = context;
        this.toDoDatum = todoDatum;
    }

    public static class ViewHolder {
        Button deleteButton;
        CheckBox statusCheckBox;
    }

    @NonNull
    @Override
    public View getView(int position, View listViewItem, ViewGroup parent) {
        if (listViewItem == null) {
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.row_todo, parent, false);
        }
        ToDoData currentRow = getItem(position);
        CheckBox taskChecker = (CheckBox) listViewItem.findViewById(R.id.text_todo_checkbox);
        taskChecker.setText(currentRow.getTodoText());

        return listViewItem;
    }
}
