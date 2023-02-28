package com.example.lab6;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoListItemDao {
    @Insert
    long insert(TodoListItem todoListItem);

    @Query("SELECT * FROM `todo_list_items` WHERE `id` =:id")
    TodoListItem get(long id);


    @Query("SELECT * FROM `todo_list_items` ORDER BY `order`")
    LiveData<List<TodoListItem>> getAllLive();

    @Query("SELECT * FROM `todo_list_items` ORDER BY `order`")
    List<TodoListItem> getAll();


    @Query("SELECT `order` + 1 From `todo_list_items` ORDER BY `order` DESC LIMIT 1")
    int gerOrderForAppend();

    @Update
    int update(TodoListItem todoListItem);

    @Delete
    int delete(TodoListItem todoListItem);

    @Insert
    List<Long> insertAll(List<TodoListItem> todoListItem);

}
