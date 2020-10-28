package com.example.noteapp;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class TaskRecyclerAdapter extends FirestoreRecyclerAdapter<Task, TaskRecyclerAdapter.TaskViewHolder> {


    public TaskRecyclerAdapter(@NonNull FirestoreRecyclerOptions<Task> options) {
        super(options);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onBindViewHolder(@NonNull TaskViewHolder holder, int position, @NonNull Task task) {

        holder.taskTextView.setText(task.getText());
        CharSequence dateCharSeq = (CharSequence) new SimpleDateFormat("d, MMM \nHH:mm", Locale.forLanguageTag(task.getDateTime()));
        holder.dateTextView.setText(dateCharSeq);


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
