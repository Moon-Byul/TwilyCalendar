package com.example.reveu.twilycalander;

import java.util.Calendar;

/**
 * Created by reveu on 2017-06-12..
 */

public class CalendarDayData
{
    private Calendar cal;

    public CalendarDayData()
    {
        this.cal = Calendar.getInstance();
    }

    public void setDay(int year, int month, int day)
    {
        cal.set(year, month, day);
    }

    public void setDay(Calendar cal)
    {
        this.cal = (Calendar) cal.clone();
    }

    public Calendar getDay()
    {
        return cal;
    }
}
