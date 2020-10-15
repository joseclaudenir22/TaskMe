package com.example.noteapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.noteapp.databinding.RowAddItemBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.type.DateTime;

import java.util.Calendar;

import static com.firebase.ui.auth.AuthUI.TAG;


public class BottomSheet extends BottomSheetDialogFragment {

    Button      btnAdd;
    Button      btnTime;
    Button      datepicker;
    EditText    taskName;
    TextView    txtDateTime;
    String      date, time, visibility;


    public BottomSheet(){}

    @SuppressLint("RestrictedApi")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view       = inflater.inflate(R.layout.row_add_item, container, false);


            //recupera dados de data e horario da DateTimeActivity
            Bundle bundle = getArguments();
            if(bundle != null){
                date = bundle.getString("date");
                txtDateTime.setText(date);
            }


            //findViewById
            btnAdd      = view.findViewById(R.id.btnAddItem);
            taskName    = view.findViewById(R.id.taskName);
            txtDateTime = view.findViewById(R.id.txtDateTime);
            datepicker  = view.findViewById(R.id.datepicker);
            //txtDateTime.setText(date);
            taskName.requestFocus();


            //Verifica se o campo da tarefa está em branco e, se sim, inativa o botão
            taskName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.toString().trim().length()==0){
                        btnAdd.setEnabled(false);
                        btnAdd.setTextColor(Color.LTGRAY);
                    } else {
                        btnAdd.setEnabled(true);
                        btnAdd.setTextColor(R.color.colorPrimaryDark);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            //inicia a activity para selecionar a data e o horário
            datepicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent datetime = new Intent(getContext(), DateTimeActivity.class);
                    startActivity(datetime);


                }
            });


            //botão para adicionar a tarefa
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("RestrictedApi")
                @Override
                public void onClick(View v) {

                    Bundle bundle = getArguments();
                    if(bundle != null){
                        date = bundle.getString("date");
                        Log.d("TAG", "DATA é: " + date + "***********************************");
                    }
                }
            });


            return view;

   }

    //função para adicionar a tarefa no Firebase
   private void addTask(String text){

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Task task = new Task(text, false, userId);

       FirebaseFirestore.getInstance()
               .collection("tasks")
               .add(task)
               .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                   @SuppressLint("RestrictedApi")
                   @Override
                   public void onSuccess(DocumentReference documentReference) {
                       Log.d(TAG, "onSuccess: Task succesfully created");
                   }
               })
               .addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                   }
               });

   }

}
