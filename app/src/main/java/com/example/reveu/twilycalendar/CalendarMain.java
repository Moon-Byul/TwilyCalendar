package com.example.reveu.twilycalendar;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Debug;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;

import static android.R.attr.type;
import static android.R.attr.value;
import static com.example.reveu.twilycalendar.R.drawable.addbtn;
import static com.example.reveu.twilycalendar.R.drawable.backbtn;

/**
 * Created by reveu on 2017-06-12.
 */

public class CalendarMain extends AppCompatActivity
{
    FragCalendarMonth fragCalMonth;
    FragCalendarYear fragCalYear;
    LinearLayout backBtn;
    ImageView addBtn;
    ImageView showEventBtn;

    private boolean isLvVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragCalMonth = (FragCalendarMonth) getSupportFragmentManager().findFragmentById(R.id.fragCal);
        fragCalYear = new FragCalendarYear();

        isLvVisible = false;

        setCustomActionBar();
    }

    private void setCustomActionBar()
    {
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        View customBar = LayoutInflater.from(this).inflate(R.layout.actionbar_calendar, null);
        actionBar.setCustomView(customBar);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f7f7f7")));

        Toolbar parent = (Toolbar) customBar.getParent();
        parent.setContentInsetsAbsolute(0, 0);

        backBtn = (LinearLayout) customBar.findViewById(R.id.custombar_calendar_backbtn);
        backBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                changeFragment(fragCalMonth.getYear());
            }
        });

        addBtn = (ImageView) customBar.findViewById(R.id.custombar_calendar_addbtn);
        addBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Twily", "야후!");
            }
        });

        showEventBtn = (ImageView) customBar.findViewById(R.id.custombar_calendar_showevent);
        showEventBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setLvVisible(!isLvVisible);
            }
        });
    }

    public void changeFragment(int year)
    {
        fragCalYear.setYear(year);
        getSupportFragmentManager().beginTransaction().replace(R.id.ctMain, fragCalYear).commit();
        backBtn.setVisibility(View.INVISIBLE);
        showEventBtn.setVisibility(View.INVISIBLE);
    }

    public void changeFragment(int year, int month)
    {
        fragCalMonth.setYear(year);
        fragCalMonth.setMonth(month);
        getSupportFragmentManager().beginTransaction().replace(R.id.ctMain, fragCalMonth).commit();
        backBtn.setVisibility(View.VISIBLE);
        showEventBtn.setVisibility(View.VISIBLE);
        setActionBarYearText(year);
    }

    public void setActionBarYearText(int year)
    {
        ((TextView) backBtn.findViewById(R.id.custombar_calendar_year)).setText(year + "년");
    }

    public boolean getLvVisible()
    {
        return isLvVisible;
    }

    public void setLvVisible(boolean value)
    {
        isLvVisible = value;
        if(isLvVisible)
        {
            showEventBtn.setColorFilter(Color.argb(255, 255, 255, 255), PorterDuff.Mode.SRC_ATOP);
            showEventBtn.setBackgroundColor(Color.argb(128, 255, 0, 0));
        }
        else
        {
            showEventBtn.setColorFilter(Color.argb(255, 255, 0, 0), PorterDuff.Mode.SRC_ATOP);
            showEventBtn.setBackgroundColor(0);
        }
        fragCalMonth.setListViewVisiblity(value);
    }
}
