package com.example.reveu.twilycalendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by 615 on 2017-06-14.
 */

public class CalendarMonthInYear extends LinearLayout
{
    private Context context;
    private ArrayList<LinearLayout> weeks;
    private ArrayList<AutoResizeTextView> days;
    private AutoResizeTextView showMonth;

    private int year;
    private int month;

    public CalendarMonthInYear(Context context)
    {
        this(context, null);
    }

    public CalendarMonthInYear(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        this.context = context;
        this.setOrientation(LinearLayout.VERTICAL);
        this.setLayoutParams(params);

        if(weeks == null)
        {
            showMonth = new AutoResizeTextView(context); // 월을 표시하기 위한 레이아웃

            params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            params.setMargins(getDensityPx(2), getDensityPx(2), getDensityPx(2), getDensityPx(2));
            showMonth.setLayoutParams(params);
            showMonth.setTextSize(58.0f);
            this.addView(showMonth);

            weeks = new ArrayList<LinearLayout>(6); // 한달에 최대 6주
            days = new ArrayList<AutoResizeTextView>(42); // 7일 * 6주 = 42일

            LinearLayout tempLayout = null;
            params.height = LinearLayout.LayoutParams.MATCH_PARENT;
            params.setMargins(0,0,0,0);
            params.weight = 1;

            for(int i=0; i<42; i++)
            {
                //week의 layout을 생성한다.
                if(i % 7 == 0)
                {
                    tempLayout = new LinearLayout(context);
                    tempLayout.setOrientation(LinearLayout.HORIZONTAL);
                    tempLayout.setLayoutParams(params);
                    weeks.add(tempLayout);
                    this.addView(tempLayout);
                }

                AutoResizeTextView day = new AutoResizeTextView(context);
                day.setGravity(Gravity.CENTER);
                day.setTextSize(36.0f);
                day.setLayoutParams(params);

                tempLayout.addView(day);
                days.add(day);
            }
        }
    }

    public void makeCalendar(int year, int month)
    {
        this.year = year;
        this.month = month;

        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        cal.setFirstDayOfWeek(Calendar.SUNDAY); // 일요일을 주의 시작일로 지정

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1일의 요일
        int maxOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 마지막 일수

        int seekDay, i;

        // 첫번째 주 설정, dayOfWeek이 1부터 시작되서 -1 해줬음...
        for(seekDay = 0; seekDay<dayOfWeek-1; seekDay++)
        {
            days.get(seekDay).setVisibility(INVISIBLE);
        }

        for(i = seekDay; i < maxOfMonth + seekDay; i++)
        {
            int day = i-seekDay + 1; // i-seekDay하면 -1된 상태로 오길래 +1 처리했음.

            cal.set(year, month, day);

            days.get(i).setVisibility(VISIBLE);
            days.get(i).setText(day);

            if(dateisToday(cal, Calendar.getInstance()))
                days.get(i).setBackgroundResource(R.drawable.rounded_day);
            else
                days.get(i).setBackgroundResource(0);
        }

        // 나머지 일은 안보이게 처리
        for(; i<42; i++)
        {
            days.get(i).setVisibility(INVISIBLE);
        }

    }

    public int getYear()
    {
        return year;
    }

    public int getMonth()
    {
        return month;
    }

    private boolean dateisToday(Calendar cal1, Calendar cal2)
    {
        if(cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH))
            return true;
        else
            return false;
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
