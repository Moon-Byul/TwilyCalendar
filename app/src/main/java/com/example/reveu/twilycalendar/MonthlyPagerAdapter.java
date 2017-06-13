package com.example.reveu.twilycalendar;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.HashMap;

import static android.support.v7.widget.AppCompatDrawableManager.get;

/**
 * Created by reveu on 2017-06-13.
 */

public class MonthlyPagerAdapter extends PagerAdapter
{
    private Context context;
    private CalendarMonth[] monthViews;

    final int baseYear;
    final int baseMonth;
    final Calendar baseCal;
    final static int PAGES = 5;
    final static int LOOPS = 1000;
    final static int BASE_POSITION = PAGES * LOOPS / 2;

    public MonthlyPagerAdapter(Context context, int startYear, int startMonth)
    {
        this.context = context;

        baseYear = startYear;
        baseMonth = startMonth;

        Calendar cal = Calendar.getInstance();
        cal.set(baseYear, baseMonth, 1);
        baseCal = cal;

        monthViews = new CalendarMonth[PAGES];
        for(int i = 0; i < PAGES; i++)
            monthViews[i] = new CalendarMonth(context);
    }

    public int getPosition(int year, int month)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        return BASE_POSITION + howFarFromBase(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH));
    }

    public void changeDp(int dp)
    {
        for(int i = 0; i < PAGES; i++)
        {
            monthViews[i].setTvDp(dp);
            monthViews[i].makeCalendar();
        }
    }

    private int howFarFromBase(int year, int month)
    {
        int disY = (year - baseYear) * 12;
        int disM = month - baseMonth;

        return disY + disM;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        Log.d("Twily", "instantiate : " + position);

        int howFarFromBase = position - BASE_POSITION;
        Calendar cal = (Calendar) baseCal.clone();
        cal.add(Calendar.MONTH, howFarFromBase);

        position = position % PAGES;

        container.addView(monthViews[position]);

        monthViews[position].makeCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH));

        return monthViews[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView((View) object);
    }

    @Override
    public int getCount()
    {
        return PAGES * LOOPS;
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == object;
    }
}
