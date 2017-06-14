package com.example.reveu.twilycalendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by reveu on 2017-06-14.
 */

public class FragCalendarYear extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_calendar_year, container, false);

        CalendarYear temp = new CalendarYear(getContext());
        temp.makeCalendar(2017);

        rootView.addView(temp);

        return rootView;
    }
}
