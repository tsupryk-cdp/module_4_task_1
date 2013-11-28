package com.tsupryk.view.listeners;

import com.tsupryk.view.entity.Film;
import com.tsupryk.api.events.FilmCreatedEvent;
import com.tsupryk.view.service.FilmService;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

/**
 * The class FilmEventListener.
 * <p/>
 * Date: 26.11.13
 * <p/>
 * Author: Vitaliy
 */
@Component
public class FilmEventListener {

    @Autowired
    private MongoOperations mongoOperations;

    @EventHandler
    public void handleFilmCreated(FilmCreatedEvent event) {
        Film film = new Film();
        film.setId(event.getId());
        film.setDescription(event.getDescription());
        film.setEndTime(event.getEndTime());
        film.setStartTime(event.getStartTime());
        film.setStudio(event.getStudio());
        film.setTitle(event.getTitle());

        mongoOperations.insert(film, FilmService.COLLECTION_FILMS);
    }
}
