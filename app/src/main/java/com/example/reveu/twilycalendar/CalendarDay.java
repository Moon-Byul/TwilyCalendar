package com.example.reveu.twilycalander;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by reveu on 2017-06-12..
 */

public class CalendarDay extends LinearLayout
{
    private TextView tvDayNumber;
    private TextView tvDayPoint;
    private CalendarDayData data;

    private boolean isVisible = true;


    public CalendarDay(Context context)
    {
        super(context);
        init(context);
    }

    public CalendarDay(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    private void init(Context context)
    {
        View v = View.inflate(context, R.layout.layout_day, this);

        tvDayNumber = (TextView) v.findViewById(R.id.day_number);
        tvDayPoint = (TextView) v.findViewById(R.id.day_point);
        data = new CalendarDayData();
    }

    public void setDay(int year, int month, int day)
    {
        this.data.setDay(year, month, day);
        setTextDayOfMonth(this.data.getDay());
}

    public void setDay(Calendar cal)
    {
        this.data.setDay(cal);
        setTextDayOfMonth(this.data.getDay());
    }

    public void setVisible(boolean visible)
    {
        isVisible = visible;

        if(visible)
            this.setVisibility(VISIBLE);
        else
            this.setVisibility(INVISIBLE);
    }

    public boolean getVisible()
    {
        return isVisible;
    }

    public void setPointed(boolean pointed)
    {
        if(pointed)
            tvDayPoint.setVisibility(VISIBLE);
        else
            tvDayPoint.setVisibility(INVISIBLE);
    }

    public void setTextDayColor(int rgb)
    {
        tvDayNumber.setTextColor(rgb);
    }

    private void setTextDayOfMonth(Calendar cal)
    {
        int day = cal.get(Calendar.DAY_OF_MONTH);
        tvDayNumber.setText(String.valueOf(day));
    }
}
