package com.example.reveu.twilycalendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.Calendar;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by reveu on 2017-06-14.
 */

public class FragCalendarMonth extends Fragment implements ViewPager.OnPageChangeListener
{
    private int currentYear;
    private int currentMonth;
    private ListView lvEventMonth;
    private VerticalViewPager vrvpCalMonth;
    private MonthlyPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_calendar_month, container, false);

        if(currentYear == 0 && currentMonth == 0)
        {
            Calendar cal = Calendar.getInstance();

            currentYear = cal.get(Calendar.YEAR);
            currentMonth = cal.get(Calendar.MONTH);
        }

        vrvpCalMonth = new VerticalViewPager(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = 1.0f;
        vrvpCalMonth.setLayoutParams(params);

        lvEventMonth = new ListView(getContext());
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = 2.0f;
        lvEventMonth.setLayoutParams(params);

        rootView.addView(vrvpCalMonth);
        rootView.addView(lvEventMonth);

        adapter = new MonthlyPagerAdapter(rootView.getContext(), currentYear, currentMonth);

        setListViewVisiblity(((CalendarMain) getActivity()).getLvVisible());

        vrvpCalMonth.setAdapter(adapter);
        vrvpCalMonth.setCurrentItem(adapter.getPosition(currentYear, currentMonth));
        vrvpCalMonth.setOffscreenPageLimit(1);
        vrvpCalMonth.setOnPageChangeListener(this);

        return rootView;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {}

    @Override
    public void onPageScrollStateChanged(int state)
    {
        int base = adapter.getCount()/2;
        Calendar cal = adapter.getBaseCal();
        cal.add(Calendar.MONTH, vrvpCalMonth.getCurrentItem() - base);
        if(state == 0)
        {
            currentMonth = cal.get(Calendar.MONTH);
            for(int i=0; i<adapter.PAGES; i++)
            {
                if(adapter.monthViews[i].getYear() > 0)
                {
                    if (adapter.monthViews[i].getYear() != currentYear || adapter.monthViews[i].getMonth() != currentMonth)
                    {
                        adapter.monthViews[i].setSelectDay(0);
                    }
                }
            }
        }
        else
        {
            if (currentYear != cal.get(Calendar.YEAR))
            {
                currentYear = cal.get(Calendar.YEAR);
                ((CalendarMain) getActivity()).setActionBarYearText(currentYear);
            }
        }
    }

    public void setListViewVisiblity(boolean visiblity)
    {
        if(visiblity)
        {
            adapter.changeDp(getDensityPx(32));
            lvEventMonth.setVisibility(VISIBLE);
        }
        else
        {
            adapter.changeDp(getDensityPx(38));
            lvEventMonth.setVisibility(GONE);
        }
    }

    /*
        안드로이드는 dp를 쓰기 때문에 값을 dp로 바꿔주는 작업이 필요. 그때 쓰이는 메소드
     */
    public int getDensityPx(int value)
    {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (value * scale);
    }

    public int getYear()
    {
        return currentYear;
    }

    public int getMonth()
    {
        return currentMonth;
    }

    public void setYear(int year)
    {
        this.currentYear = year;
    }

    public void setMonth(int month)
    {
        this.currentMonth = month;
    }
}
