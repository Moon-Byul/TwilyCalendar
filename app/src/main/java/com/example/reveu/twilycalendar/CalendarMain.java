package com.example.reveu.twilycalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by reveu on 2017-06-12.
 */

public class CalendarMain extends AppCompatActivity
{
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (LinearLayout) findViewById(R.id.activity_main);

        CalendarMonth temp = new CalendarMonth(getApplicationContext());
        temp.makeCalendar(2017, 5);
        container.addView(temp);

        ListView temp2 = new ListView(getApplicationContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = 4;
        temp2.setLayoutParams(params);
        //container.addView(temp2);
    }
}
