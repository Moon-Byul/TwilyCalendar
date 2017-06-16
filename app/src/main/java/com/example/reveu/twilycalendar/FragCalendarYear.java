package com.example.reveu.twilycalendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by reveu on 2017-06-14.
 */

public class FragCalendarYear extends Fragment
{
    private int year = 2017;
    private ListView lvYear;
    private YearlyPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_calendar_year, container, false);

        lvYear = (ListView) rootView.findViewById(R.id.lvYear);
        adapter = new YearlyPagerAdapter(getContext(), year, lvYear);
        //adapter.setYearList(2017);
        lvYear.requestFocusFromTouch();
        lvYear.setAdapter(adapter);

//        LinearLayout tempview = (LinearLayout) rootView.findViewById(R.id.testview);
//
//        CalendarYear temp = new CalendarYear(getContext());
//        temp.makeCalendar(2017);
//
//        CalendarYear temp2 = new CalendarYear(getContext());
//        temp2.makeCalendar(2018);
//
//        tempview.addView(temp);
//        tempview.addView(temp2);

//
//        CalendarYear temp = new CalendarYear(getContext());
//        temp.makeCalendar(2017);
//
//        rootView.addView(temp);

        return rootView;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        adapter.setDefaultPage();
        lvYear.setOnScrollListener(adapter);
    }

    public void setYear(int year)
    {
        this.year = year;
    }
}
