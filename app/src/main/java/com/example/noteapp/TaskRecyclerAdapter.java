package com.example.noteapp;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class TaskRecyclerAdapter extends FirestoreRecyclerAdapter<Task, TaskRecyclerAdapter.TaskViewHolder> {


    public TaskRecyclerAdapter(@NonNull FirestoreRecyclerOptions<Task> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TaskViewHolder holder, int position, @NonNull Task model) {

    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
