package com.example.reveu.twilycalendar;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Calendar;

import static com.example.reveu.twilycalendar.MonthlyPagerAdapter.PAGES;

/**
 * Created by reveu on 2017-06-15.
 */

public class YearlyPagerAdapter extends BaseAdapter implements AbsListView.OnScrollListener
{
    private Context context;
    private ArrayList<CalendarYear> yearList = new ArrayList<CalendarYear>();

    final int startYear;

    private int currentYear;

    final static int ITEMS = 5;

    public YearlyPagerAdapter(Context context, int startYear)
    {
        this.context = context;
        this.startYear = currentYear = startYear;

        for(int i=0; i<ITEMS; i++)
            yearList.add(new CalendarYear(context));
    }

    @Override
    public int getCount()
    {
        return yearList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return yearList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return null;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
    {
        //year
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {}

    private void setYearList(int changedValue)
    {
        this.currentYear += changedValue;

        for(int i=0; i<ITEMS; i++)
        {
            yearList.get(i).makeCalendar(currentYear - (ITEMS / 2) + i);
        }
    }
}
