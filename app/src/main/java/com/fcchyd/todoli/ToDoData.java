package com.fcchyd.todoli;

public class ToDoData {

    private String todoText;

    public ToDoData(String todoText){
        this.todoText = todoText;
    }

    protected String getTodoText() {
        return todoText;
    }
}
