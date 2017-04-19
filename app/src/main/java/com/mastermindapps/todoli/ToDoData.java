package com.mastermindapps.todoli;

class ToDoData {

    private String todoText;
    private boolean selected;

    ToDoData(String todoText){
        this.todoText = todoText;
        selected = false;
    }

    String getTodoText() {
        return todoText;
    }

    void setTodoText(String todoText) {
        this.todoText = todoText;
    }

    boolean isSelected(){
        return selected;
    }

    void setSelected(boolean selected) {
        this.selected = selected;
    }
}
