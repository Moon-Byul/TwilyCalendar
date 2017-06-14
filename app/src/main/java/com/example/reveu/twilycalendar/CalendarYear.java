package com.example.reveu.twilycalendar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by reveu on 2017-06-15.
 */

public class CalendarYear extends LinearLayout
{
    private Context context;
    private ArrayList<LinearLayout> monthColumns;
    private ArrayList<CalendarMonthInYear> months;
    private TextView showYear;

    private int year;

    public CalendarYear(Context context)
    {
        this(context, null);
    }

    public CalendarYear(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        this.context = context;
        this.setOrientation(LinearLayout.VERTICAL);
        this.setPadding(getDensityPx(5), getDensityPx(5), getDensityPx(5), getDensityPx(5));
        this.setLayoutParams(params);

        if(monthColumns == null)
        {
            showYear = new TextView(context); // 년도를 표시하기 위한 레이아웃
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, getDensityPx(5));
            showYear.setTextSize(28.0f);
            showYear.setLayoutParams(params);
            showYear.setTextColor(Color.rgb(0, 0, 0));
            this.addView(showYear);


            LinearLayout tempLayout = new LinearLayout(context); // 선 표시하는 용도의 레이아웃
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2);
            tempLayout.setLayoutParams(params);
            tempLayout.setOrientation(VERTICAL);
            tempLayout.setBackgroundColor(Color.argb(68, 0, 0, 0));
            tempLayout.setPadding(0, 0, 0, getDensityPx(2));
            this.addView(tempLayout);


            LinearLayout tempAllMonthsLayout = new LinearLayout(context); // 월들을 묶어주는 레이아웃
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            tempAllMonthsLayout.setLayoutParams(params);
            tempAllMonthsLayout.setOrientation(VERTICAL);
            this.addView(tempAllMonthsLayout);

            monthColumns = new ArrayList<LinearLayout>(4); // 4줄
            months = new ArrayList<CalendarMonthInYear>(12); // 4줄에 3달씩 = 12개월

            params.weight = 1;

            for(int i=0; i<12; i++)
            {
                //month의 layout을 생성한다.
                if(i % 3 == 0)
                {
                    tempLayout = new LinearLayout(context);
                    tempLayout.setOrientation(LinearLayout.HORIZONTAL);
                    params.setMargins(0, 0, 0, 0);
                    tempLayout.setLayoutParams(params);
                    monthColumns.add(tempLayout);
                    tempAllMonthsLayout.addView(tempLayout);
                }

                CalendarMonthInYear month = new CalendarMonthInYear(context);
                params.setMargins(getDensityPx(3), getDensityPx(3), getDensityPx(3), getDensityPx(3));
                month.setLayoutParams(params);

                tempLayout.addView(month);
                months.add(month);
            }
        }
    }

    public void makeCalendar(int year)
    {
        this.year = year;

        showYear.setText(year + "년");
        for(int i=0; i<12; i++)
        {
            months.get(i).makeCalendar(year, i);
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
