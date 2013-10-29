package com.tsupryk.api;

import com.tsupryk.api.entity.Film;

import java.util.List;

/**
 * The interface IFilmRepository.
 * <p/>
 * User: Vitaliy
 * Date: 27.10.13
 */
public interface IFilmRepository {

    public Film findByTitle(String title);

    public List<Film> findByParameters(String title, String studio, String actor);

    public void init();
}
