package com.example.reveu.twilycalendar;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.Calendar;

/**
 * Created by reveu on 2017-06-12.
 */

public class CalendarMain extends AppCompatActivity
{
    FragCalendarMonth fragCalMonth;
    FragCalendarYear fragCalYear;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragCalMonth = (FragCalendarMonth) getSupportFragmentManager().findFragmentById(R.id.fragCalMonth);
//        fragCalYear = (FragCalendarYear) getSupportFragmentManager().findFragmentById(R.id.fragCalYear);

        /*
        container = (VerticalViewPager) findViewById(R.id.mainVrvp);

        MonthlyPagerAdapter adapter = new MonthlyPagerAdapter(getApplicationContext(), 2017, 5, container);

        container.setAdapter(adapter);
        container.setCurrentItem(adapter.getPosition(2017, 5));
        container.setOffscreenPageLimit(1);
        */

        /*
        CalendarMonth temp = new CalendarMonth(getApplicationContext());
        temp.makeCalendar(2017, 5);
        container.addView(temp);
        */
        /*
        ListView temp2 = new ListView(getApplicationContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = 4;
        temp2.setLayoutParams(params);
        //container.addView(temp2);
        */
    }
}
