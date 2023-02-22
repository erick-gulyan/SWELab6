package com.example.lab6;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoListActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public TodoListViewModel viewModel;
    private EditText newTodoText;
    private Button addTodoButton;
    private TextView deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        viewModel = new ViewModelProvider(this)
                .get(TodoListViewModel.class);

        TodoListAdapter adapter = new TodoListAdapter();
        adapter.setHasStableIds(true);
        adapter.setOnCheckBoxClickedHandler(viewModel::toggleCompleted);
        adapter.setOnTextEditedHandler(viewModel::updateText);
        adapter.setOnDeleteClicked(viewModel::deleteTodo);
        viewModel.getTodoListItems().observe(this, adapter::setTodoListItems);


        recyclerView = findViewById(R.id.todo_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        this.newTodoText = this.findViewById(R.id.new_todo_text);
        this.addTodoButton = this.findViewById(R.id.add_todo_btn);
        this.deleteButton = this.findViewById(R.id.delete_btn);

        addTodoButton.setOnClickListener(this::onAddTodoClicked);

    }

    void onAddTodoClicked(View view){
        String text =  newTodoText.getText().toString();
        newTodoText.setText("");
        viewModel.createTodo(text);
    }





}
