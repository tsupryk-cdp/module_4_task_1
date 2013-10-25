package com.tsupryk.repository;

import com.tsupryk.api.entity.Ticket;
import com.tsupryk.api.IFiltrable;
import com.tsupryk.api.ITicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;

/**
 * The class JdbcTicketRepository.
 * <p/>
 * User: Vitaliy
 * Date: 12.10.13
 */
//@Repository
public class JdbcTicketRepository implements ITicketRepository {

    private static final String BASE_SELECT = "SELECT * FROM tickets";

    private static final String BASE_UPDATE_BY_ID = "UPDATE tickets SET user_id = :userId, film_name = :filmName, category = :category, " +
            "film_start_date = :filmStartDate, place_number = :placeNumber, status = :status WHERE id = :id";

    private static final String SELECT_BY_ID = "SELECT id, user_id, film_name, category, film_start_date, place_number, status " +
            "FROM tickets WHERE id = :id";


    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public List<Ticket> getTickets(IFiltrable filter) {
        String query = createQueryWithFilter(BASE_SELECT, filter);
        SqlParameterSource parameterSource = createMapSource(filter);
        return namedParameterJdbcTemplate.query(query, parameterSource, new BeanPropertyRowMapper<>(Ticket.class));
    }

    @Override
    public boolean updateTicket(Ticket ticket) {
        namedParameterJdbcTemplate.update(BASE_UPDATE_BY_ID, createMapSource(ticket));
        return true;
    }

    @Override
    public Ticket getById(Integer id) {
        List<Ticket> tickets = namedParameterJdbcTemplate.query(SELECT_BY_ID, new MapSqlParameterSource().addValue("id", id),
                new BeanPropertyRowMapper<>(Ticket.class));
        return tickets.get(0);
    }

    @Override
    public void initTickets() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private SqlParameterSource createMapSource(Object obj) {
        SqlParameterSource source = null;
        if (obj instanceof IFiltrable) {
            IFiltrable filter = (IFiltrable) obj;
            source = new MapSqlParameterSource()
                    .addValue("status", getValue(filter.getStatus()))
                    .addValue("filmName", filter.getFilmName())
                    .addValue("filmStartDate", filter.getFilmStartDate())
                    .addValue("category", getValue(filter.getCategory()))
                    .addValue("userId", filter.getUserId());
        } else if (obj instanceof Ticket) {
            Ticket ticket = (Ticket) obj;
            source = new MapSqlParameterSource()
                    .addValue("status", getValue(ticket.getStatus()))
                    .addValue("filmName", ticket.getFilmName())
                    .addValue("filmStartDate", ticket.getFilmStartDate())
                    .addValue("category", getValue(ticket.getCategory()))
                    .addValue("placeNumber", ticket.getPlaceNumber())
                    .addValue("id", ticket.getId());
        }
        return source;
    }

    private String getValue(Object obj) {
        return obj == null ? null : obj.toString();
    }

    private String createQueryWithFilter(String baseSql, IFiltrable filter) {
        StringBuilder sql = new StringBuilder(baseSql);

        sql.append(" WHERE");

        if (filter.getStatus() != null) {
            appendParam(sql, "status", "status");
        }
        if (filter.getFilmName() != null) {
            appendParam(sql, "film_name", "filmName");
        }
        if (filter.getFilmStartDate() != null) {
            appendParam(sql, "film_start_date", "filmStartDate");
        }
        if (filter.getCategory() != null) {
            appendParam(sql, "category", "category");
        }
        if (filter.getUserId() != null) {
            appendParam(sql, "user_id", "userId");
        }

        return sql.toString();
    }

    private void appendParam(StringBuilder sql, String tableFieldName, String objectFieldName) {
        String stringParam = "? = :$";
        stringParam = stringParam.replace("?", tableFieldName);
        stringParam = stringParam.replace("$", objectFieldName);
        if (sql.lastIndexOf("WHERE") != sql.length() - 5) {
            sql.append(" AND ");
        } else {
            sql.append(" ");
        }
        sql.append(stringParam);
    }

}
