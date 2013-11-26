package com.tsupryk.api.events;

import java.util.Date;

/**
 * The class FilmCreatedEvent.
 * <p/>
 * Date: 26.11.13
 * <p/>
 * Author: Vitaliy
 */
public class FilmCreatedEvent {

    private String id;

    private String title;

    private Date startTime;

    private Date endTime;

    private String studio;

    private String description;

    public FilmCreatedEvent(String id, String title, Date startTime, Date endTime, String studio, String description) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.studio = studio;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getStudio() {
        return studio;
    }

    public String getDescription() {
        return description;
    }
}
