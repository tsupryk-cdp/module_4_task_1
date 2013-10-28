package com.tsupryk.repository;

import com.tsupryk.api.IFilmRepository;
import com.tsupryk.api.entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * The Class FilmRepository.
 * <p/>
 * Date: 28.10.13
 *
 * @author Vitaliy_Tsupryk
 */
@Repository
public class FilmRepository implements IFilmRepository {

    private MongoOperations operations;

    @Autowired
    public FilmRepository(MongoOperations operations) {
        this.operations = operations;
    }

    @Override
    public Film findByTitle(String title) {
        return operations.findById(title, Film.class);
    }

    @Override
    public List<Film> findByParameters(String title, String studio, String actor) {
//        if (title != null) {
//
//        }
//        Query query = Query.query()
//        return operations.find(query(
//                where("title").is(title)
//                        .orOperator(where("studio").is("studio"))
//                        .orOperator(where(actor).in("actor"))
//        ), Film.class);
        return null;
    }

    @Override
    public void init(){
        for (int i = 0; i < 2; i++) {
            Film film = new Film();
            film.setTitle("Terminator " + i);
            film.setStudio("WB");
            film.setDescription("Film about robots.");
            film.setActors(Arrays.asList("Shwartsenegger", "Willis", "Stallone"));
            operations.save(film);
        }
        for (int i = 0; i < 2; i++) {
            Film film = new Film();
            film.setTitle("Independence Day " + i);
            film.setStudio("Paramound");
            film.setDescription("Film about UFO.");
            film.setActors(Arrays.asList("Shwartsenegger", "Willis", "Smith"));
            operations.save(film);
        }
    }
}
