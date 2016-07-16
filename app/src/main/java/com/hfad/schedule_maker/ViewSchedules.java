package com.hfad.schedule_maker;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewSchedules extends AppCompatActivity {

    ArrayList<ScheduleConfig> schedules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedules);

        this.schedules = Storage.getSchedulesSorted();

        TextView tx = (TextView)findViewById(R.id.textView);
        tx.setText("Schedule "+"1/"+Storage.getSchedulesSorted().size());

        HorizontalScrollView scroll = (HorizontalScrollView)findViewById(R.id.horizontalScrollView);
        scroll.removeAllViews();
        scroll.addView(createScheduleView(this.schedules.get(0).scheduleFormat()));
    }

    public String formatTime(int time){
        String st = Integer.toString(time);
        if (st.length()==3){
            st = "0"+st;
        }
        st = st.substring(0,2)+":"+st.substring(2,4);
        return st;
    }

    public LinearLayout createDayView(String day, ArrayList<Class> ar){
        LinearLayout L = new LinearLayout(this);
        L.setOrientation(LinearLayout.HORIZONTAL);

        Button b = new Button(this);
        b.setText(day);
        b.setTextColor(Color.WHITE);
        b.setBackgroundResource(R.drawable.daybuttons);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        param.setMargins(20,10,20,10);
        L.addView(b,param);

        if (ar==null){
            return L;
        }

        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        param2.setMargins(10,2,10,2);

        for (Class c:ar){
            Button button = new Button(this);
            button.setText(c.getName()+"-"+c.getSection()+"\n"+formatTime(c.getStartTime())+"-"+formatTime(c.getEndTime()));
            button.setTextColor(Color.WHITE);
            button.setBackgroundColor(Storage.colorMap.get(c.getName()));
            L.addView(button,param2);
        }

        return L;
    }

    public LinearLayout createScheduleView(HashMap<String, ArrayList<Class>> schedule){
        LinearLayout  L = new LinearLayout(this);
        L.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1.0f);
        param.setMargins(5, 2, 5, 2);

        L.addView(createDayView("Monday", schedule.get("Mo")), param);
        L.addView(createDayView("Tuesday", schedule.get("Tu")), param);
        L.addView(createDayView("Wednesday",schedule.get("We")),param);
        L.addView(createDayView("Thursday", schedule.get("Th")), param);
        L.addView(createDayView("Friday", schedule.get("Fr")), param);

        return L;
    }

    public void changeView(View view, int index){
        HorizontalScrollView scroll = (HorizontalScrollView)findViewById(R.id.horizontalScrollView);
        scroll.removeAllViews();
        scroll.addView(createScheduleView(this.schedules.get(index).scheduleFormat()));

        TextView tx = (TextView)findViewById(R.id.textView);
        tx.setText("Schedule viewer. Schedule " + Storage.getScheduleIndex() + "/" + Storage.getSchedulesSorted().size());
    }

    public void previous(View view){
        if(Storage.scheduleIndex==1){
            CharSequence text = "Unavailable. This is the first schedule.";
            Toast toast=Toast.makeText(this, text, Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            Storage.prevSchedule();
            changeView(view,Storage.getScheduleIndex());
        }
    }

    public void next(View view){
        if(Storage.scheduleIndex==Storage.getSchedulesSorted().size()){
            CharSequence text = "Unavailable. This is the last schedule.";
            Toast toast=Toast.makeText(this, text, Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            Storage.nextSchedule();
            changeView(view,Storage.getScheduleIndex());
        }
    }

    public boolean isNumeric(String str) {
        return str.matches("\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public void go(View view){
        int i = Integer.parseInt(((EditText)findViewById(R.id.editText)).getText().toString());
        if(isNumeric(Integer.toString(i))) {
            if (i > Storage.getSchedulesSorted().size() || i < 1) {
                CharSequence text = "Invalid index. Try 1-" + Integer.toString(Storage.getSchedulesSorted().size());
                Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Storage.scheduleIndex = Integer.parseInt(((EditText) findViewById(R.id.editText)).getText().toString());
                changeView(view, Storage.getScheduleIndex() - 1);
            }
        }
        else{
            CharSequence text = "Enter a number.";
            Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
