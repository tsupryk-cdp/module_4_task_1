package com.tsupryk.api;

import com.tsupryk.api.entity.Film;

import java.util.List;

/**
 * The Interface IFilmService.
 * <p/>
 * Date: 28.10.13
 *
 * @author Vitaliy_Tsupryk
 */
public interface IFilmService {

    public Film getFilmByTitle(String title);

    public void init();

    List<Film> findByParameter(String title, String studio, String actor);
}
