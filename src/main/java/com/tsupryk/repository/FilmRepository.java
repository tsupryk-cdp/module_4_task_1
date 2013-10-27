package com.tsupryk.repository;

import com.tsupryk.api.entity.Film;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The interface FilmRepository.
 * <p/>
 * User: Vitaliy
 * Date: 27.10.13
 */
public interface FilmRepository extends MongoRepository<Film, String> {

    public Film findByTitle(String title);
}
