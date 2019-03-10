package com.example.datetimepicker;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textviewTP, textviewDP;
    Button buttonDate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textviewTP = (TextView) findViewById(R.id.textView3);
        textviewDP = (TextView) findViewById(R.id.textView2);


        DatePicker datePicker = findViewById(R.id.datepicker);
        buttonDate = (Button) findViewById(R.id.button);
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textviewDP.setVisibility(View.VISIBLE);
                DatePicker dP = findViewById(R.id.datepicker);
                textviewDP.setText("Button Update :" + ((dP.getDayOfMonth())) + (String.valueOf(dP.getMonth())) + (String.valueOf(dP.getYear())));
            }
        });

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                textviewDP.setText("DateChange Update: " + year + monthOfYear + dayOfMonth);
            }
        });


        //        initiate timePicker
        TimePicker timePicker = findViewById(R.id.timepickerview);
        timePicker.setIs24HourView(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.getMinute();
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            timePicker.getCurrentMinute();
        }

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                // display a toast with changed values of time picker
                Toast.makeText(getApplicationContext(), hourOfDay + "  " + minute, Toast.LENGTH_SHORT).show();
                textviewTP.setVisibility(View.VISIBLE);
                textviewTP.setText("Time is : " + hourOfDay + minute); // set the current time in text view

            }
        });


    }
}
