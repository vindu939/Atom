package com.vin.urlshortener.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by aravindp on 22/6/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Analytics {
    private TimePeriod allTime;
    private TimePeriod month;
    private TimePeriod week;
    private TimePeriod day;
    private TimePeriod twoHours;

    public TimePeriod getAllTime() {
        return allTime;
    }

    public void setAllTime(TimePeriod allTime) {
        this.allTime = allTime;
    }

    public TimePeriod getMonth() {
        return month;
    }

    public void setMonth(TimePeriod month) {
        this.month = month;
    }

    public TimePeriod getWeek() {
        return week;
    }

    public void setWeek(TimePeriod week) {
        this.week = week;
    }

    public TimePeriod getDay() {
        return day;
    }

    public void setDay(TimePeriod day) {
        this.day = day;
    }

    public TimePeriod getTwoHours() {
        return twoHours;
    }

    public void setTwoHours(TimePeriod twoHours) {
        this.twoHours = twoHours;
    }
}
