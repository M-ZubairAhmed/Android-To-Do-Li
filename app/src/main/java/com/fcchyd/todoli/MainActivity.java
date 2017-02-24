package com.fcchyd.todoli;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
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
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(customView)
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newtask_String = addNewTask_Edittext.getText().toString();
                        newtask_String = newtask_String.trim();
                        if (newtask_String.length() != 0){
                            Toast.makeText(MainActivity.this, "New task added", Toast.LENGTH_LONG).show();
                            todoArrayList.add(new ToDoData(newtask_String));
//                            Collections.reverse(todoArrayList);
                            customArrayAdapter.notifyDataSetChanged();
                            taskCounter++;
                            TaskCounter(taskCounter);
                        }
                    }
                })
                .create();
        alertDialog.show();
    }

    protected void TaskCounter(int taskCounter) {
        if (taskCounter == 0) {
            this.setTitle("You are all done!");
        } else if (taskCounter == 1) {
            this.setTitle(taskCounter + " Pending Task");
        } else {
            this.setTitle(taskCounter + " Pending Tasks");
        }
    }




}
