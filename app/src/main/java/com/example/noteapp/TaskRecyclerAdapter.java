package com.example.noteapp;

import android.icu.text.UFormat;
import android.os.Build;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.text.Format;
import java.text.ParseException;
import java.util.Date;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class TaskRecyclerAdapter extends FirestoreRecyclerAdapter<Task, TaskRecyclerAdapter.TaskViewHolder> {


    public TaskRecyclerAdapter(@NonNull FirestoreRecyclerOptions<Task> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TaskViewHolder holder, int position, @NonNull Task task) {

        holder.taskTextView.setText(task.getText());
        holder.dateTextView.setText(task.getDate()  + " \n " + task.getTime());


    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.task_row, parent, false);
        return new TaskViewHolder(view);

    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView taskTextView, dateTextView, timeTextView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            taskTextView = itemView.findViewById(R.id.taskTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);

        }
    }
}
