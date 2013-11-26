package com.tsupryk.axon.service;

import com.tsupryk.api.entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

/**
 * The class FilmService.
 * <p/>
 * Date: 26.11.13
 * <p/>
 * Author: Vitaliy
 */
@Component
public class FilmService {

    public static final String COLLECTION_FILMS = "films";

    @Autowired
    MongoOperations repository;

    public Film getById(String id) {
        return repository.findById(id, Film.class, COLLECTION_FILMS);
    }
}
