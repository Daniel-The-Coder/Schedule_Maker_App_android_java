package com.hfad.schedule_maker;

import java.util.Comparator;

/**
 * Created by Lord Daniel on 6/20/2016.
 */
public class classEndEarlyComp implements Comparator<ScheduleConfig> {
    public int compare(ScheduleConfig s1, ScheduleConfig s2){
        return Float.compare(s1.aveEndTime,s2.aveEndTime);
    }
}
