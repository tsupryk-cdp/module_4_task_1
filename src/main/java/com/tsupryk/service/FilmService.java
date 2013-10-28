package com.tsupryk.service;

import com.tsupryk.api.IFilmRepository;
import com.tsupryk.api.IFilmService;
import com.tsupryk.api.entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Class FilmService.
 * <p/>
 * Date: 28.10.13
 *
 * @author Vitaliy_Tsupryk
 */
@Service
public class FilmService implements IFilmService {

    @Autowired
    private IFilmRepository repository;

    @Override
    public Film getFilmByTitle(String title) {
        return repository.findByTitle(title);
    }


    @Override
    public List<Film> findByParameter(String title, String studio, String actor) {
        return repository.findByParameters(title, studio, actor);
    }

    @Override
    public void init() {
        repository.init();
    }
}
