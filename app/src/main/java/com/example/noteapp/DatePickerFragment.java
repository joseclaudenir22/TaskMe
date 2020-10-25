package com.example.noteapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int year    = calendar.get(Calendar.YEAR);
        int month   = calendar.get(Calendar.MONTH);
        int day     = calendar.get(Calendar.DAY_OF_MONTH);
        int hour    = calendar.get(Calendar.HOUR_OF_DAY);
        int minute  = calendar.get(Calendar.MINUTE);

        final TimePickerDialog timePicker = new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(), hour, minute, DateFormat.is24HourFormat(getActivity()));

        DatePickerDialog dialog = new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
        dialog.setButton(DialogInterface.BUTTON3, "TESTE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                timePicker.show();
            }
        });

        return dialog;
    }
}
