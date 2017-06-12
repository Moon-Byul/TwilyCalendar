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
 * Created by reveu on 2017-06-12.
 */

public class CalendarMonth extends LinearLayout
{
    private Context context;
    private ArrayList<LinearLayout> weeks;
    private ArrayList<CalendarDay> days;
    private LinearLayout showMonth;
    private TextView tvShowMonth;

    private int year;
    private int month;

    public CalendarMonth(Context context)
    {
        this(context, null);
    }

    public CalendarMonth(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        setOrientation(LinearLayout.VERTICAL);

        if(weeks == null)
        {

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.weight = 0;

            showMonth = new LinearLayout(context); // 월을 표시하기 위한 레이아웃
            showMonth.setOrientation(LinearLayout.HORIZONTAL);
            showMonth.setLayoutParams(params);
            this.addView(showMonth);

            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            this.setLayoutParams(params);

            weeks = new ArrayList<LinearLayout>(6); // 한달에 최대 6주
            days = new ArrayList<CalendarDay>(42); // 7일 * 6주 = 42일

            LinearLayout tempLayout = null;

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

                CalendarDay day = new CalendarDay(context);
                day.setOrientation(LinearLayout.VERTICAL);
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
            days.get(seekDay).setVisible(false);
        }

        //달력 위에 n월 텍스트뷰를 넣으려함
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        params.setMargins(getDensityPx(5), getDensityPx(5), getDensityPx(5), getDensityPx(5));
        for(i=0; i<7; i++)
        {
            TextView tvTemp = new TextView(context);
            tvTemp.setLayoutParams(params);
            if(i == seekDay)
            {
                tvTemp.setVisibility(VISIBLE);
                tvTemp.setGravity(Gravity.CENTER);
                tvTemp.setText(month + "월");
                tvTemp.setTextColor(Color.rgb(0, 0, 0));
                tvTemp.setTextSize(20.0f);
                tvShowMonth = tvTemp;
            }
            else
            {
                tvTemp.setVisibility(INVISIBLE);
            }
            showMonth.addView(tvTemp);
        }

        for(i = seekDay; i < maxOfMonth + seekDay; i++)
        {
            cal.set(year, month, i-seekDay + 1); // i-seekDay하면 -1된 상태로 오길래 +1 처리했음.
            days.get(i).setDay(cal);
            days.get(i).setPointed(false); //나중에 해당 일에 이벤트 있으면 true로 처리한다.

            // 주말은 살짝 흐리게한다.
            if(i % 7 == 0 || i % 7 == 6)
                days.get(i).setTextDayColor(Color.argb(85, 0, 0, 0));
        }

        // 나머지 일은 안보이게 처리
        for(; i<42; i++)
        {
            days.get(i).setVisible(false);
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

    /*
        안드로이드는 dp를 쓰기 때문에 값을 dp로 바꿔주는 작업이 필요. 그때 쓰이는 메소드
     */
    public int getDensityPx(int value)
    {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (value * scale);
    }

    /*
    private void init(Context context)
    {
        View v = View.inflate(context, R.layout.layout_month, this);
        v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        LinearLayout temp = (LinearLayout) v.findViewById(R.id.month_first);
        TextView day = (TextView) ((LinearLayout) temp.findViewById(R.id.week_mon)).findViewById(R.id.day_number);
        day.setText("2");

        temp = (LinearLayout) v.findViewById(R.id.month_second);
        day = (TextView) ((LinearLayout) temp.findViewById(R.id.week_mon)).findViewById(R.id.day_number);
        day.setText("3");

        //tvDayNumber = (TextView) v.findViewById(R.id.day_number);
        //tvDayPoint = (TextView) v.findViewById(R.id.day_point);
        //data = new CalendarDayData();
    }
    */
}
