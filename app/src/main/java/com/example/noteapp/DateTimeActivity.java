package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;

public class DateTimeActivity extends AppCompatActivity {

    Button          btnHorario, btnOk, btnCancel;
    int             hour , minute;
    String          time ;
    String          date ;
    CalendarView    calendarView;
    private static final String TAG = MainActivity.class.getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);
        this.setFinishOnTouchOutside(true);



        //atribuições elementos
        btnHorario  = findViewById(R.id.btnTime);
        btnOk       = findViewById(R.id.btnOk);
        btnCancel   = findViewById(R.id.btnCancel);
        calendarView  = findViewById(R.id.calendarView);

        //TimePicker para selecionar horário da tarefa
        btnHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        DateTimeActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //atribui hora e minuto
                                hour    = hourOfDay;
                                minute  = minute;

                                //inicia o calendário
                                Calendar calendar = Calendar.getInstance();

                                //set a hora e o minuto
                                calendar.set(0,0,0,hour, minute);

                                //atribui no texto hora e minuto
                                btnHorario.setText(DateFormat.format("hh:mm aa", calendar));
                                time     = btnHorario.getText().toString();
                            }
                        },12,0,true
                );



                //Mostra o horário selecionado assim que o TimePicker foi reaberto
                timePickerDialog.updateTime(hour, minute);

                //Mostra Dialog
                timePickerDialog.show();

            }
        });

        //listener para extrair a data
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = dayOfMonth + "/" + (month + 1) + "/" + year;
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Envia dados para a BottomSheet
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);

            }
        });

    }

    public void clear(){

    }

}