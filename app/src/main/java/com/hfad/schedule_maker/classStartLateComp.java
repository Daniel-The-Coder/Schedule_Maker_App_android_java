package com.hfad.schedule_maker;

import java.util.Comparator;

/**
 * Created by Lord Daniel on 6/20/2016.
 */
public class classStartLateComp implements Comparator<ScheduleConfig> {
    public int compare(ScheduleConfig s1, ScheduleConfig s2){
        return -1*Float.compare(s1.aveStartTime,s2.aveStartTime);
    }
}
