package com.fcchyd.todoli;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

public class UIActivity extends AppCompatActivity {

    int undoneTaskCount = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewTask();
            }
        });

        TaskCounter(undoneTaskCount);
    }

    protected void createNewTask(){
        AlertDialog.Builder newTaskBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        newTaskBuilder.setView(inflater.inflate(R.layout.add_todo,null));

        // create alert dialog
        AlertDialog alertDialog = newTaskBuilder.create();

        // show it
        alertDialog.show();
    }

    protected void TaskCounter(int undoneTaskCount){
        if (undoneTaskCount == 0){
            this.setTitle("You are all done!");
        }
        else if (undoneTaskCount == 1){
            this.setTitle(undoneTaskCount + " Pending Task");
        }
        else {
            this.setTitle(undoneTaskCount + " Pending Tasks");
        }
    }

}
