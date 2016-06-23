package com.vin.urlshortener.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by aravindp on 22/6/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimePeriod {
    private long shortUrlClicks;
    private long longUrlClicks;
    private List<Counts> referrers;
    private List<Counts> countries;
    private List<Counts> browsers;
    private List<Counts> platforms;

    public long getShortUrlClicks() {
        return shortUrlClicks;
    }

    public void setShortUrlClicks(long shortUrlClicks) {
        this.shortUrlClicks = shortUrlClicks;
    }

    public long getLongUrlClicks() {
        return longUrlClicks;
    }

    public void setLongUrlClicks(long longUrlClicks) {
        this.longUrlClicks = longUrlClicks;
    }

    public List<Counts> getReferrers() {
        return referrers;
    }

    public void setReferrers(List<Counts> referrers) {
        this.referrers = referrers;
    }

    public List<Counts> getCountries() {
        return countries;
    }

    public void setCountries(List<Counts> countries) {
        this.countries = countries;
    }

    public List<Counts> getBrowsers() {
        return browsers;
    }

    public void setBrowsers(List<Counts> browsers) {
        this.browsers = browsers;
    }

    public List<Counts> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Counts> platforms) {
        this.platforms = platforms;
    }
}
