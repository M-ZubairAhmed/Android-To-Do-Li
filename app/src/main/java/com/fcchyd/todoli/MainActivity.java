package com.fcchyd.todoli;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public int taskCounter = 0;
    protected String newtask_String;
    protected ArrayList<ToDoData> todoArrayList;
    protected CustomArrayAdapter customArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewTask();
            }
        });

        todoArrayList = new ArrayList<>();
        customArrayAdapter = new CustomArrayAdapter(this, todoArrayList);
        ListView content_UI = (ListView) findViewById(R.id.content_ui);
        content_UI.setAdapter(customArrayAdapter);

        TaskCounter(taskCounter);
    }

    protected void createNewTask(){
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        final View customView = inflater.inflate(R.layout.add_todo_alertdia,null);
        final TextView addNewTask_Edittext = (EditText) customView.findViewById(R.id.alertdia_newtask_edittextxml);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(customView)
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        taskAddMethod(customView);
                    }
                })
                .create();
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        alertDialog.show();
        addNewTask_Edittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO){
                    taskAddMethod(customView);
                    alertDialog.dismiss();
                    return true;
                }
                else {
                    return false;
                }
            }
        });
    }

    protected void taskAddMethod(View customView){
        final TextView addNewTask_Edittext = (EditText) customView.findViewById(R.id.alertdia_newtask_edittextxml);
        newtask_String = addNewTask_Edittext.getText().toString();
        newtask_String = newtask_String.trim();
        if (newtask_String.length() != 0){
            Toast.makeText(MainActivity.this, "New task added", Toast.LENGTH_SHORT).show();
            todoArrayList.add(new ToDoData(newtask_String));
//                            Collections.reverse(todoArrayList);
            customArrayAdapter.notifyDataSetChanged();
            taskCounter++;
            TaskCounter(taskCounter);
        }
    }

    public void TaskCounter(int taskCounter) {
        if (taskCounter == 0) {
            this.setTitle("You are all done!");

        } else if (taskCounter == 1) {
            this.setTitle("" + taskCounter + " Pending Task");

        } else {
            this.setTitle("" + taskCounter + " Pending Tasks");

        }
    }
/*
    public void deletebutton_xml(View view){
        View parent = (View) view.getParent();
        CheckBox checkBox = (CheckBox) parent.findViewById(R.id.text_todo_checkbox);
        if (!checkBox.isChecked()) {
            taskCounter--;
        }
        TaskCounter(taskCounter);
        String a = String.valueOf(checkBox.getText());
        for (ToDoData X : todoArrayList) {
            if (X.getTodoText().equals(a)){
                int b = todoArrayList.indexOf(X);
                todoArrayList.remove(b);
                customArrayAdapter.notifyDataSetChanged();
                break;
            }
        }
    }*/

}

