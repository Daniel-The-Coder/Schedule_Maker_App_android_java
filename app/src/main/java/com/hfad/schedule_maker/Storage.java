package com.hfad.schedule_maker;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Lord Daniel on 6/22/2016.
 */
public class Storage {
    public static int scheduleIndex=1;
    public static ArrayList<ScheduleConfig> schedules;
    public static ArrayList<ScheduleConfig> schedulesSorted;
    public static ArrayList<Integer> colors;
    public static HashMap<String,Integer> colorMap;

    public static ArrayList<ScheduleConfig> getSchedules(){
        return schedules;
    }

    public static void prevSchedule(){
        scheduleIndex--;
    }

    public static int getScheduleIndex(){
        return scheduleIndex;
    }

    public static ArrayList<ScheduleConfig> getSchedulesSorted(){
        return schedulesSorted;
    }

    public static void nextSchedule(){
        scheduleIndex++;
    }

    public static void clear(){
        scheduleIndex=1;
        schedules=new ArrayList<>();
        schedulesSorted=new ArrayList<>();
        colorMap = new HashMap<>();
    }
}
