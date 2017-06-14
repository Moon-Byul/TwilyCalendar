package com.example.reveu.twilycalendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Calendar;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by reveu on 2017-06-14.
 */

public class FragCalendarMonth extends Fragment
{
    private ListView lvEventMonth;
    private VerticalViewPager vrvpCalMonth;
    private MonthlyPagerAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        int year, month;
        Calendar cal = Calendar.getInstance();

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_calendar_month, container, false);

        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);

        vrvpCalMonth = (VerticalViewPager) rootView.findViewById(R.id.vrvpCalMonth);
        lvEventMonth = (ListView) rootView.findViewById(R.id.lvEventMonth);
        adapter = new MonthlyPagerAdapter(rootView.getContext(), year, month);

        setListViewVisiblity(false);

        vrvpCalMonth.setAdapter(adapter);
        vrvpCalMonth.setCurrentItem(adapter.getPosition(year, month));
        vrvpCalMonth.setOffscreenPageLimit(1);

        return rootView;
    }

    public void setListViewVisiblity(boolean visiblity)
    {
        if(visiblity)
        {
            adapter.changeDp(getDensityPx(28));
            lvEventMonth.setVisibility(VISIBLE);
        }
        else
        {
            adapter.changeDp(getDensityPx(34));
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
}
