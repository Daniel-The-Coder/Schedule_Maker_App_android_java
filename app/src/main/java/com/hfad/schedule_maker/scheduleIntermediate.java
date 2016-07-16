package com.hfad.schedule_maker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class scheduleIntermediate extends AppCompatActivity {

    ArrayList<ScheduleConfig> schedules;
    ArrayList<ScheduleConfig> schedulesSorted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_intermediate);

        //Intent intent = getIntent();
        //SchedulesWrapper wrap = (SchedulesWrapper)intent.getSerializableExtra("solution");
        //this.schedules = wrap.getSchedules();
        this.schedules = Storage.getSchedules();
        TextView txt = (TextView)findViewById(R.id.textView5);
        txt.setText(this.schedules.size()+" schedules found.");
    }

    public void viewSchedules(View view){
        //calculate average start and end time for each schedule
        for (ScheduleConfig s:Storage.schedules){
            HashMap<String, Integer> map1 = new HashMap<>();
            HashMap<String, Integer> map2 = new HashMap<>();
            for (Course c:s.getSchedule()){
                for (Class cl:c.getClasses()){
                    if (map1.containsKey(cl.getDay())){
                        if (map1.get(cl.getDay())<cl.getEndTime()){
                            map1.put(cl.getDay(), cl.getEndTime());
                        }
                        if (map2.get(cl.getDay())>cl.getStartTime()){
                            map2.put(cl.getDay(), cl.getStartTime());
                        }
                    }
                    else{
                        map1.put(cl.getDay(), cl.getEndTime());
                        map2.put(cl.getDay(), cl.getStartTime());
                    }
                }
            }
            //calculate average end time and update value in the schedule configuration
            float y = 0;
            float z = 0;
            for (String k:map1.keySet()){
                y += (float)map1.get(k);
                z += (float)map2.get(k);
            }
            float ave1 = y/5;
            float ave2 = z/5;
            s.aveEndTime=ave1;
            s.aveStartTime=ave2;
        }

        RadioButton r1 = (RadioButton)findViewById(R.id.radioButton);
        RadioButton r2 = (RadioButton)findViewById(R.id.radioButton2);
        RadioButton r3 = (RadioButton)findViewById(R.id.radioButton3);
        Storage.schedulesSorted = Storage.schedules;
        ArrayList<ScheduleConfig> schedule= Storage.getSchedules();
        if (r1.isChecked()){
            Collections.sort(schedule,new classStartLateComp());
            Storage.schedulesSorted=schedule;
            Intent intent = new Intent(this, ViewSchedules.class);
            startActivity(intent);
        }
        else if (r2.isChecked()){
            Collections.sort(schedule,new classEndEarlyComp());
            Storage.schedulesSorted=schedule;
            Intent intent = new Intent(this, ViewSchedules.class);
            startActivity(intent);
        }
        else if (r3.isChecked()){
            //leave it unsorted
            Storage.schedulesSorted=schedule;
            Intent intent = new Intent(this, ViewSchedules.class);
            startActivity(intent);
        }
        else{
            CharSequence text = "Select sort setting.";
            Toast toast=Toast.makeText(this, text, Toast.LENGTH_SHORT);
            toast.show();
        }

    }

}
