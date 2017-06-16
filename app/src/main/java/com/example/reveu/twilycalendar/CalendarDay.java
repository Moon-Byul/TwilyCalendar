package com.example.reveu.twilycalendar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

import static android.view.Gravity.CENTER;

/**
 * Created by reveu on 2017-06-12.
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

    public void setTvNumSize(int width, int height)
    {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        params.gravity = Gravity.CENTER;
        tvDayNumber.setLayoutParams(params);
    }

    public void setPointed(boolean pointed)
    {
        if(pointed)
            tvDayPoint.setVisibility(VISIBLE);
        else
            tvDayPoint.setVisibility(INVISIBLE);
    }

    public void setBackgroundTextView(@android.support.annotation.DrawableRes int resId, boolean isToday)
    {
        tvDayNumber.setBackgroundResource(resId);

        if(isToday)
        {
            tvDayNumber.getBackground().setColorFilter(Color.argb(255, 255, 0, 0), PorterDuff.Mode.SRC_ATOP);
        }
        else if(resId != 0)
        {
            tvDayNumber.getBackground().setColorFilter(Color.argb(255, 0, 0, 0), PorterDuff.Mode.SRC_ATOP);
        }

        if(resId != 0)
            tvDayNumber.setTextColor(Color.rgb(255, 255, 255));
        else
            tvDayNumber.setTextColor(Color.rgb(0, 0, 0));

        if(resId == R.drawable.rounded_day && isToday)
            tvDayNumber.setAlpha(0.7f);
        else
            tvDayNumber.setAlpha(1.0f);
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

    public int getDensityPx(int value)
    {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (value * scale);
    }
}
