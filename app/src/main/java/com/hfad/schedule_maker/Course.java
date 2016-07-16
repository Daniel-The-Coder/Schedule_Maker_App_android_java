package com.hfad.schedule_maker;

import java.util.ArrayList;

/**
 * Created by Lord Daniel on 6/20/2016.
 */
public class Course {
    private String name;
    private int section;
    private ArrayList<Class> classes;

    public Course(String name, int section, ArrayList<Class> classes){
        this.name=name;
        this.section=section;
        this.classes=classes;
    }

    public ArrayList<Class> getClasses(){
        return  this.classes;
    }

    @Override
    public String toString(){
        String st = "#  | "+name+", section "+Integer.toString(section)+"\n";
        for (Class c:classes){
            st += "#  | "+c.toString()+"\n";
        }
        st+="#   ___________________";
        return st;
    }

}
