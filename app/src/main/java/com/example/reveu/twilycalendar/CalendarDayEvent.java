package com.example.reveu.twilycalendar;

import java.util.Calendar;

/**
 * Created by reveu on 2017-06-12.
 */

public class CalendarDayEvent
{
    private String eventName;
    private boolean isAllDay;
    private Calendar startDate;
    private Calendar endDate;

    public CalendarDayEvent()
    {
        this.eventName = "없음";
        this.isAllDay = false;
        this.startDate = Calendar.getInstance();
        this.endDate = Calendar.getInstance();
    }

    public void setEventName(String eventName)
    {
        this.eventName = eventName;
    }

    public void setAllDay(boolean isAllDay)
    {
        this.isAllDay = isAllDay;
    }

    public void setStartDate(Calendar startDate)
    {
        this.startDate = startDate;
    }

    public void setEndDate(Calendar endDate)
    {
        this.endDate = endDate;
    }

    public String getEventName()
    {
        return eventName;
    }

    public boolean getAllDay()
    {
        return isAllDay;
    }

    public Calendar getStartDate()
    {
        return startDate;
    }

    public Calendar getEndDate()
    {
        return endDate;
    }
}
