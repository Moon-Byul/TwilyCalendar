package com.example.reveu.twilycalendar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by 615 on 2017-06-14.
 */

public class CalendarMonthInYear extends LinearLayout
{
    private Context context;
    private ArrayList<LinearLayout> weeks;
    private ArrayList<TextView> days;
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

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        this.context = context;
        this.setOrientation(LinearLayout.VERTICAL);
        this.setLayoutParams(params);

        if(weeks == null)
        {
            showMonth = new AutoResizeTextView(context); // 월을 표시하기 위한 레이아웃

            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.setMargins(getDensityPx(2), getDensityPx(2), getDensityPx(2), getDensityPx(2));
            params.weight = 3.5f;
            showMonth.setTextSize(100.0f);
            showMonth.setLayoutParams(params);
            this.addView(showMonth);

            weeks = new ArrayList<LinearLayout>(6); // 한달에 최대 6주
            days = new ArrayList<TextView>(42); // 7일 * 6주 = 42일

            LinearLayout tempMainLayout = new LinearLayout(context);
            LinearLayout tempLayout = null;

            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.setMargins(0,0,0,0);
            params.weight = 1f;
            tempMainLayout.setOrientation(LinearLayout.VERTICAL);
            tempMainLayout.setLayoutParams(params);
            this.addView(tempMainLayout);

            for(int i=0; i<42; i++)
            {
                //week의 layout을 생성한다.
                if(i % 7 == 0)
                {
                    params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    params.setMargins(0,0,0,0);
                    params.weight = 1f;

                    tempLayout = new LinearLayout(context);
                    tempLayout.setOrientation(LinearLayout.HORIZONTAL);
                    tempLayout.setLayoutParams(params);
                    weeks.add(tempLayout);
                    tempMainLayout.addView(tempLayout);
                }

                params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0,0,0,0);
                params.weight = 1f;

                TextView day = new TextView(context);
                day.setGravity(Gravity.CENTER);
                day.setTextSize(10.0f);
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

        showMonth.setText(month+1 + "월");

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
            days.get(i).setText(String.valueOf(day));

            if(dateisToday(cal, Calendar.getInstance()))
            {
                days.get(i).setTextColor(Color.rgb(255, 0, 0));
                days.get(i).setPaintFlags(days.get(i).getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
            }
            else
            {
                days.get(i).setTextColor(Color.rgb(0, 0, 0));
                days.get(i).setPaintFlags(days.get(i).getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);
            }
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
        if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH))
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
