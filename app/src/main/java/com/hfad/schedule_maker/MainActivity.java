package com.hfad.schedule_maker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public ArrayList<ArrayList<Course>> allCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout courses = (LinearLayout)findViewById(R.id.allCourses);
        courses.removeAllViews();
        courses.addView(createCourse());

        Storage.colors = new ArrayList<>();
        Storage.colors.add(Color.parseColor("#ff0000"));
        Storage.colors.add(Color.parseColor("#00ff00"));
        Storage.colors.add(Color.parseColor("#0000ff"));
        Storage.colors.add(Color.parseColor("#9400d3"));
        Storage.colors.add(Color.parseColor("#00bfff"));
        Storage.colors.add(Color.parseColor("#838b8b"));
        Storage.colors.add(Color.parseColor("#00ced1"));
        Storage.colors.add(Color.parseColor("#00c957"));
        Storage.colors.add(Color.parseColor("#ffa500"));
        Storage.colors.add(Color.parseColor("#388e3e"));//10
        Storage.colors.add(Color.parseColor("#ff4500"));
        Storage.colors.add(Color.parseColor("#c67171"));
        Storage.colors.add(Color.parseColor("#7171c6"));
        Storage.colors.add(Color.parseColor("#b0171f"));
        Storage.colors.add(Color.parseColor("#ff3e96"));
        Storage.colors.add(Color.parseColor("#da70d6"));
        Storage.colors.add(Color.parseColor("#000080"));
        Storage.colors.add(Color.parseColor("#00cd66"));
        Storage.colors.add(Color.parseColor("#9c661f"));
        Storage.colors.add(Color.parseColor("#8b8682"));//20

        Storage.colorMap = new HashMap<>();

    }

    public Button createCloseButton(){
        Button b = new Button(this);
        b.setBackgroundResource(R.drawable.closebutton);
        int dpValue = 15; // dimension in dips
        float d = this.getResources().getDisplayMetrics().density;
        int dim = (int)(dpValue * d); // dimension in pixels
        b.setLayoutParams(new LinearLayout.LayoutParams(dim,dim));
        b.setTextColor(Color.WHITE);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                remove(v);
            }
        });
        return b;
    }

    public LinearLayout createSection(){
        LinearLayout L = new LinearLayout(this);
        L.setOrientation(LinearLayout.VERTICAL);
        L.setBackgroundColor(Color.parseColor("#00ced1"));

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);

        LinearLayout L1 = new LinearLayout(this);
        L1.setOrientation(LinearLayout.HORIZONTAL);
        TextView T1 = new TextView(this);
        T1.setTextAppearance(this, android.R.style.TextAppearance_Large);
        T1.setText("  Section: ");
        EditText E1 = new EditText(this);
        L1.addView(T1);
        L1.addView(E1, param);
        L1.addView(createCloseButton());

        LinearLayout L2 = new LinearLayout(this);
        L2.setOrientation(LinearLayout.HORIZONTAL);
        TextView T2 = new TextView(this);
        T2.setTextAppearance(this, android.R.style.TextAppearance_Large);
        T2.setText("  Time: ");
        EditText E2 = new EditText(this);
        TextView T3 = new TextView(this);
        T3.setTextAppearance(this, android.R.style.TextAppearance_Large);
        T3.setText(" - ");
        EditText E3 = new EditText(this);
        L2.addView(T2);
        L2.addView(E2, param);
        L2.addView(T3);
        L2.addView(E3, param);

        LinearLayout L3 = new LinearLayout(this);
        L1.setOrientation(LinearLayout.HORIZONTAL);
        TextView T4 = new TextView(this);
        T4.setTextAppearance(this, android.R.style.TextAppearance_Large);
        CheckBox C1 = new CheckBox(this);
        C1.setText("M");
        CheckBox C2 = new CheckBox(this);
        C2.setText("T");
        CheckBox C3 = new CheckBox(this);
        C3.setText("W");
        CheckBox C4 = new CheckBox(this);
        C4.setText("T");
        CheckBox C5 = new CheckBox(this);
        C5.setText("F");
        LinearLayout.LayoutParams param5 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        L3.addView(C1,param5);
        L3.addView(C2,param5);
        L3.addView(C3,param5);
        L3.addView(C4,param5);
        L3.addView(C5,param5);

        LinearLayout.LayoutParams param4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param4.setMargins(0, 20, 20, 0);
        L.addView(L1,param4);
        L.addView(L2);
        L.addView(L3);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(30, 60, 30, 30);
        L.setLayoutParams(layoutParams);
        return L;
    }

    public LinearLayout createCourse(){
        LinearLayout L = new LinearLayout(this);
        L.setOrientation(LinearLayout.VERTICAL);
        L.setBackgroundColor(Color.parseColor("#bbffff"));

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);

        LinearLayout L1 = new LinearLayout(this);
        L1.setOrientation(LinearLayout.HORIZONTAL);
        TextView T1 = new TextView(this);
        T1.setTextAppearance(this, android.R.style.TextAppearance_Large);
        T1.setText("  Course name: ");
        EditText E1 = new EditText(this);
        L1.addView(T1);
        L1.addView(E1, param);
        L1.addView(createCloseButton());

        LinearLayout L2 = new LinearLayout(this);
        L2.setOrientation(LinearLayout.VERTICAL);
        L2.addView(createSection());

        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout L3 = new LinearLayout(this);
        L3.setOrientation(LinearLayout.HORIZONTAL);
        Button b = new Button(this);
        LinearLayout.LayoutParams param3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param3.setMargins(30, 30,30,30);
        b.setText("  + section  ");
        b.setTextColor(Color.WHITE);
        b.setBackgroundResource(R.drawable.buttons);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSection(v);
            }
        });
        L3.addView(b, param3);

        LinearLayout.LayoutParams param4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param4.setMargins(0,20,20,0);
        L.addView(L1,param4);
        L.addView(L2);
        L.addView(L3,param2);

        return L;
    }

    public void addSection(View view){
        //LinearLayout sections = (LinearLayout)findViewById(R.id.allSections);
        LinearLayout course = (LinearLayout) ((Button) view).getParent().getParent();
        LinearLayout sections = (LinearLayout)course.getChildAt(1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(30,30,30,30);
        sections.addView(createSection(),layoutParams);
    }

    public void addCourse(View view){
        LinearLayout courses = (LinearLayout)findViewById(R.id.allCourses);

        //View line = new View(this);
        //line.setBackgroundColor(Color.parseColor("#00ced1"));  //(new ColorDrawable(Integer.decode("#00ced1")));
        if (courses.getChildCount()>20){
            CharSequence text = "Can't add more courses; you have reached the 20-course limit..";
            Toast toast=Toast.makeText(this, text, Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(10, 30, 10, 10);
            //courses.addView(line);
            courses.addView(createCourse(), layoutParams);
        }
    }

    public void clear(View view){
        this.allCourses = new ArrayList<>();
        LinearLayout courses = (LinearLayout)findViewById(R.id.allCourses);
        courses.removeAllViews();
        courses.addView(createCourse());
        Storage.clear();
    }

    public void remove(View view){
        LinearLayout toRemove = (LinearLayout)((Button)view).getParent().getParent();
        LinearLayout parent = (LinearLayout)toRemove.getParent();
        parent.removeView(toRemove);
    }

    public boolean isNumeric(String str) {
        return str.matches("\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public void makeSchedules(View view){
        //populate allCourses

        boolean error = false;
        this.allCourses = new ArrayList<>();
        LinearLayout courses = (LinearLayout)findViewById(R.id.allCourses);
        int n = courses.getChildCount();
        for (int i=0;i<n;i++){
            ArrayList<Course> sectionsList = new ArrayList<>();
            LinearLayout thisCourse = (LinearLayout)courses.getChildAt(i);
            LinearLayout sections = (LinearLayout)thisCourse.getChildAt(1);
            String courseName = ((EditText) ((LinearLayout) thisCourse.getChildAt(0)).getChildAt(1)).getText().toString();
            Storage.colorMap.put(courseName,Storage.colors.get(i));
            if (courseName.equals("")){
                CharSequence text = "Course name missing.";
                Toast toast=Toast.makeText(this, text, Toast.LENGTH_SHORT);
                toast.show();
                error=true;
                break;
            }
            int m = sections.getChildCount();
            for (int j=0;j<m;j++){
                LinearLayout thisSection = (LinearLayout)sections.getChildAt(j);
                String sectionStr = ((EditText) ((LinearLayout) thisSection.getChildAt(0)).getChildAt(1)).getText().toString();
                if (sectionStr.equals("")){
                    CharSequence text = "Section missing.";
                    Toast toast=Toast.makeText(this, text, Toast.LENGTH_SHORT);
                    toast.show();
                    error=true;
                    break;
                }
                else if (!isNumeric(sectionStr)){
                    CharSequence text = "Error. Section must be a number.";
                    Toast toast=Toast.makeText(this, text, Toast.LENGTH_SHORT);
                    toast.show();
                    error=true;
                    break;
                }
                int section = Integer.parseInt(sectionStr);
                String startTimeStr = ((EditText) ((LinearLayout) thisSection.getChildAt(1)).getChildAt(1)).getText().toString();
                String endTimeStr = ((EditText) ((LinearLayout) thisSection.getChildAt(1)).getChildAt(3)).getText().toString();
                //make sure time input is military time.
                if (isNumeric(startTimeStr) && isNumeric(endTimeStr)){
                    //continue
                }
                else{
                    if (startTimeStr.equals("") || endTimeStr.equals("")){
                        CharSequence text = "Time missing.";
                        Toast toast=Toast.makeText(this, text, Toast.LENGTH_SHORT);
                        toast.show();
                        error=true;
                        break;
                    }
                    else {
                        CharSequence text = "Error. Time must be military. Example: 1:45pm is 1345.";
                        Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
                        toast.show();
                        error = true;
                        break;
                    }
                }
                int startTime = Integer.parseInt(startTimeStr);
                int endTime = Integer.parseInt(endTimeStr);
                ArrayList<String> days = new ArrayList<>();
                //MONDAY
                CheckBox checkMo = ((CheckBox)((LinearLayout) thisSection.getChildAt(2)).getChildAt(0));
                if (checkMo.isChecked()){
                    days.add("Mo");
                }
                //TUESDAY
                CheckBox checkTu = ((CheckBox)((LinearLayout) thisSection.getChildAt(2)).getChildAt(1));
                if (checkTu.isChecked()){
                    days.add("Tu");
                }
                //WEDNESDAY
                CheckBox checkWe = ((CheckBox)((LinearLayout) thisSection.getChildAt(2)).getChildAt(2));
                if (checkWe.isChecked()){
                    days.add("We");
                }
                //THURSDAY
                CheckBox checkTh = ((CheckBox)((LinearLayout) thisSection.getChildAt(2)).getChildAt(3));
                if (checkTh.isChecked()){
                    days.add("Th");
                }
                //FRIDAY
                CheckBox checkFr = ((CheckBox)((LinearLayout) thisSection.getChildAt(2)).getChildAt(4));
                if (checkFr.isChecked()){
                    days.add("Fr");
                }

                if (days.size()==0){
                    CharSequence text = "Error: Day selections missing.";
                    Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
                    toast.show();
                    error = true;
                    break;
                }
                ArrayList<Class> classes = new ArrayList<>();
                for(String day:days){
                    Class thisClass = new Class(courseName,section,day, startTime, endTime);
                    classes.add(thisClass);
                }
                sectionsList.add(new Course(courseName, section, classes));
            }
            if(error){
                break;
            }
            this.allCourses.add(sectionsList);
        }
        if(error){
            return;
        }

        ScheduleConfig init = new ScheduleConfig(this.allCourses);
        BFSolver BFS = new BFSolver();
        ArrayList<ScheduleConfig> ar = new ArrayList<>();
        ar.add(init);
        ArrayList<ScheduleConfig> solution = BFS.solve(ar);
        Storage.schedules = solution;
        Intent intent = new Intent(this, scheduleIntermediate.class);
        startActivity(intent);
    }

}

