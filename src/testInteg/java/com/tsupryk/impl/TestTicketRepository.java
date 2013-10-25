package com.tsupryk.impl;

import com.tsupryk.api.entity.Ticket;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * The class TestTicketRepository.
 * <p/>
 * User: Vitaliy
 * Date: 16.10.13
 */
@Repository
public class TestTicketRepository {

    private static final String SELECT = "SELECT * FROM app.tickets";

    private final static String INSERT = "INSERT INTO app.tickets (id, user_id, film_name, category, film_start_date, place_number, status) " +
                                                "VALUES (:id, null, :filmName, null, null, null, null)";
    private final static String DELETE = "DELETE FROM app.tickets";

    @Resource(name = "testNamedParameterJdbcTemplate")
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void insertNotSafe(Ticket ticket) {
        namedParameterJdbcTemplate.update(INSERT,
                new MapSqlParameterSource()
                        .addValue("id", ticket.getId())
                        .addValue("filmName", ticket.getFilmName()));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertRequiresNew(Ticket ticket, boolean throwException) {
        insert(ticket, throwException);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertRequired(Ticket ticket, boolean throwException) {
        insert(ticket, throwException);
    }

    private void insert(Ticket ticket, boolean throwException) {
        namedParameterJdbcTemplate.update(INSERT,
                new MapSqlParameterSource()
                        .addValue("id", ticket.getId())
                        .addValue("filmName", ticket.getFilmName()));
        if (throwException) {
            throw new RuntimeException("Repository exception");
        }
    }

    public void deleteAll() {
        namedParameterJdbcTemplate.update(DELETE, new MapSqlParameterSource());
    }

    public List<Ticket> selectAll() {
        return namedParameterJdbcTemplate.query(SELECT, new MapSqlParameterSource(), new BeanPropertyRowMapper<>(Ticket.class));
    }
}
