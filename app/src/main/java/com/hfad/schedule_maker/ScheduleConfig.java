package com.hfad.schedule_maker;

/**
 * Created by Lord Daniel on 6/20/2016.
 */

import android.os.Parcelable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class ScheduleConfig {

    public ArrayList<ArrayList<Course>> allCourses;
    private ArrayList<Course> schedule;
    private int curIndex;
    public float aveEndTime;
    public float aveStartTime;

    /**
     * To create a list of all courses from files
     *
     *
     * @param allCourses;
     */
    public ScheduleConfig(ArrayList<ArrayList<Course>> allCourses) {
        this.allCourses = allCourses;
        this.curIndex=0;
        this.schedule=new ArrayList<>();
    }

    /**
     * copy constructor
     */
    public ScheduleConfig(ScheduleConfig other){
        this.allCourses = new ArrayList<>(other.allCourses);
        this.schedule = new ArrayList<>(other.schedule);
        this.curIndex = new Integer(other.curIndex);
        this.nextCourse();
    }

    /**
     * no overlap
     */
    public boolean isValid(Course course){
        boolean valid = true;
        for (Course crs:this.schedule){
            for (Class cls1:crs.getClasses()){
                for (Class cls2:course.getClasses()){
                    //System.out.println("class1: "+cls1.getStartTime()+"-"+cls1.getEndTime()+",  class2: "+cls2.getStartTime()+"-"+cls2.getEndTime());
                    if(cls1.getDay().equals(cls2.getDay())) {
                        if (  cls1.getStartTime() > cls2.getStartTime() && cls1.getStartTime() < cls2.getEndTime() ){
                            valid=false;
                            //System.out.println(1);
                        }
                        if (  cls1.getEndTime() > cls2.getStartTime() && cls1.getEndTime() < cls2.getEndTime() ){
                            valid=false;
                            //System.out.println(2);
                        }
                        if ( cls1.getStartTime() < cls2.getStartTime() && cls1.getEndTime() > cls2.getEndTime() ){
                            valid=false;
                            //System.out.println(3);
                        }
                        if ( cls1.getStartTime() > cls2.getStartTime() && cls1.getEndTime() < cls2.getEndTime() ){
                            valid=false;
                            //System.out.println(4);
                        }
                        if ( cls1.getStartTime()==cls2.getStartTime() ){
                            valid=false;
                            //System.out.println(5);
                        }
                        if ( cls1.getEndTime()==cls2.getEndTime() ){
                            valid=false;
                            //System.out.println(6);
                        }
                    }
                }
            }
        }
        return valid;
    }

    /**
     * Check if all classes added
     */
    public boolean isGoal(){
        return this.schedule.size() == this.allCourses.size();
    }


    public int getCurIndex(){
        return this.curIndex;
    }

    /**
     * increment index by 1 to go to next course
     */
    public void nextCourse(){
        curIndex++;
    }

    public void addCourse(Course course){
        this.schedule.add(course);
    }

    public ArrayList<Course> getSchedule(){
        return  this.schedule;
    }

    @Override
    public String toString(){
        String st = "####################################################\n#   SCHEDULE\n#";
        for (Course c:this.schedule){
            st+="\n"+c.toString()+"\n#";
        }
        st += "####################################################";
        return st;
    }

    public HashMap<String, ArrayList<Class>> scheduleFormat(){
        HashMap<String, ArrayList<Class>> map = new HashMap<>();
        for (Course c:this.schedule){
            for (Class cl:c.getClasses()){
                if(!map.containsKey(cl.getDay())){
                    ArrayList<Class> ar = new ArrayList<>();
                    ar.add(cl);
                    map.put(cl.getDay(),ar);
                }
                else{
                    ArrayList<Class> ar = map.get(cl.getDay());
                    ar.add(cl);
                    Collections.sort(ar,new sortClassesComparator());
                    map.put(cl.getDay(),ar);
                }
            }
        }
        return map;
    }


}
