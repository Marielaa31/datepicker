package com.laundry.myapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    static TextView FirstDate;
    static TextView SecondDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirstDate=findViewById(R.id.FirstDate);
        SecondDate=findViewById(R.id.SecondDate);


        FirstDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c= Calendar.getInstance();

                int year1= c.get(Calendar.YEAR);
                int month1= c.get(Calendar.MONTH);
                int day1= c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        int monthreal=month+1;
                        String firstdate=dayOfMonth+"/"+monthreal+"/"+year;

                        FirstDate.setText(firstdate);
                    }
                },year1,month1,day1
                );
                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                datePickerDialog.show();
            }
        });


        SecondDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstDateSelected();

            }
        });



    }
    public Boolean FirstDateSelected(){
        String getfromdate = FirstDate.getText().toString().trim();
        // String getfrom[] = getfromdate.split("/");
        String datetrim=FirstDate.getText().toString().replaceAll("/","");

        if (datetrim.matches("[0-9]+")){
            //String getfromdate = FirstDate.getText().toString().trim();
            String getfrom[] = getfromdate.split("/");
            //String datetrim=FirstDate.getText().toString().replaceAll("/","");
            Log.d("num",datetrim);
            int year,month,day;
            year= Integer.parseInt(getfrom[2]);
            month = Integer.parseInt(getfrom[1])-1;
            day = Integer.parseInt(getfrom[0]);
            final Calendar c = Calendar.getInstance();
            c.set(year,month,day+1);
            DatePickerDialog datePickerDialog=new DatePickerDialog(
                    MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    int monthreal=month+1;
                    String firstdate=dayOfMonth+"/"+monthreal+"/"+year;

                    SecondDate.setText(firstdate);
                }
            },year,month,day
            );
            datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
            datePickerDialog.show();
            return true;
        }
        else{
            Toast.makeText(MainActivity.this,"Please select the first date",Toast.LENGTH_SHORT).show();
            return false;
        }


    }


}