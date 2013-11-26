package com.tsupryk.api.aggregate;

import com.tsupryk.api.commands.CreateFilmCommand;
import com.tsupryk.api.events.FilmCreatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class UserAR.
 * <p/>
 * Date: 26.11.13
 * <p/>
 * Author: Vitaliy
 */
public class FilmAR extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String id;

    private String title;

    private Date startTime;

    private Date endTime;

    private String studio;

    private String description;

    public FilmAR() {

    }

    public FilmAR(CreateFilmCommand command) {
        Date startTime = null;
        Date endTime = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("DD.MM.YYYY");
            startTime = format.parse(command.getStartTime());
            endTime = format.parse(command.getEndTime());
        } catch (ParseException e) {

        }

        FilmCreatedEvent event = new FilmCreatedEvent(command.getFilmId(), command.getTitle(),
                startTime, endTime, command.getStudio(), command.getDescription());
        apply(event);
    }

    @EventHandler
    public void on(FilmCreatedEvent event) {
        id = event.getId();
        title = event.getTitle();
        startTime = event.getStartTime();
        endTime = event.getEndTime();
        studio = event.getStudio();
        description = event.getDescription();
    }
}
