package com.hfad.schedule_maker;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Lord Daniel on 6/22/2016.
 */
public class SchedulesWrapper implements Serializable{

    private ArrayList<ScheduleConfig> schedules;

    public SchedulesWrapper(ArrayList<ScheduleConfig> sch){
        this.schedules=sch;
    }

    public ArrayList<ScheduleConfig> getSchedules(){
        return this.schedules;
    }
}
