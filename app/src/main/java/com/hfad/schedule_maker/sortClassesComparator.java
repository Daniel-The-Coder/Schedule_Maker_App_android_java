package com.hfad.schedule_maker;

import java.util.Comparator;

/**
 * Created by Lord Daniel on 6/22/2016.
 */
public class sortClassesComparator implements Comparator<Class> {
    public int compare(Class c1, Class c2){
        int x=c1.getStartTime();
        int y=c2.getStartTime();
        if (x > y){
            return 1;
        }
        else if (x==y){
            return 0;
        }
        else{
            return -1;
        }
    }
}