package com.example.reveu.twilycalendar;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;

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
    private ListView lvYear;

    int startYear;

    private int currentYear;

    final static int ITEMS = 6;

    private boolean isDefaultSettings = false;

    public YearlyPagerAdapter(Context context, int startYear, ListView lvYear)
    {
        this.context = context;
        this.startYear = currentYear = startYear;
        this.lvYear = lvYear;
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
        convertView = yearList.get(position);
        return convertView;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
    {
        if(visibleItemCount > 0)
        {
            if (firstVisibleItem == 0)
            {
                setYearList(ITEMS / 2 - (firstVisibleItem + 1));
            }
             else if (firstVisibleItem == ITEMS - 2)
            {
                setYearList(ITEMS / 2 - firstVisibleItem);
            }
        }
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {}

    public void setDefaultPage()
    {
        for(int i=0; i<ITEMS; i++)
        {
            yearList.add(new CalendarYear(context));
        }
        this.notifyDataSetChanged();
        setYearList(0);
    }

    private void setYearList(int changedValue)
    {
        this.currentYear -= changedValue;

        for(int i=0; i<ITEMS; i++)
        {
            yearList.get(i).makeCalendar(currentYear - (ITEMS / 2) + i);
        }
        this.notifyDataSetChanged();
        lvYear.setSelection(ITEMS/2);
    }

    public void setYear(int year)
    {
        this.startYear = this.currentYear = year;
        setYearList(0);
    }
}
