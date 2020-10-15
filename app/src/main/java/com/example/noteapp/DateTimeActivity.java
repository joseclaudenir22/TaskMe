package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.Intent;
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
    int             hora , minuto;
    String          horario = "";
    String          date    = "12";
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
                                hora    = hourOfDay;
                                minuto  = minute;

                                //inicia o calendário
                                Calendar calendar = Calendar.getInstance();

                                //set a hora e o minuto
                                calendar.set(0,0,0,hora, minuto);

                                //atribui no texto hora e minuto
                                btnHorario.setText(DateFormat.format("hh:mm aa", calendar));
                                horario     = btnHorario.getText().toString();
                            }
                        },12,0,true
                );



                //Mostra o horário selecionado assim que o TimePicker foi reaberto
                timePickerDialog.updateTime(hora, minuto);

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

                Bundle bundleBS = new Bundle();
                bundleBS.putString("time", horario);
                bundleBS.putString("date", String.valueOf(date));
                BottomSheetDialogFragment bottomSheet = new BottomSheetDialogFragment();
                bottomSheet.setArguments(bundleBS);
                bottomSheet.show(getSupportFragmentManager(),"TAG");
                BottomSheet bottomSheet = r
                finish();

            }
        });


    }




}