package com.tsupryk.repository.service;

import com.tsupryk.api.ITicket;
import com.tsupryk.api.Ticket;
import com.tsupryk.api.TicketCategory;
import com.tsupryk.api.TicketStatus;
import com.tsupryk.repository.api.IFiltrable;
import com.tsupryk.repository.api.ITicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * The class TicketRepository.
 * <p/>
 * User: Vitaliy
 * Date: 12.10.13
 */
@Repository
public class TicketRepository implements ITicketRepository {

    private static final String BASE_SELECT = "SELECT * FROM app.tickets";
    private static final String BASE_UPDATE_BY_ID = "UPDATE app.tickets SET user_id = :userId, film_name = :filmName, category = :category, " +
            "film_start_date = :filmStartDate, place_number = :placeNumber, status = :status WHERE id = :id";

    private static final String SELECT_BY_ID = "SELECT id, user_id, film_name, category, film_start_date, place_number, " +
            "status FROM app.tickets WHERE id = :id";


    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public List<ITicket> getTickets(IFiltrable filter) {
        String query = createQueryWithFilter(BASE_SELECT, filter);
        SqlParameterSource parameterSource = createMapSource(filter);
        return namedParameterJdbcTemplate.query(query, parameterSource, ticketRowMapper);
    }

    @Override
    public boolean updateTicket(ITicket ticket) {
        namedParameterJdbcTemplate.update(BASE_UPDATE_BY_ID, createMapSource(ticket));
        return true;
    }

    @Override
    public ITicket getById(Integer id) {
        List<ITicket> tickets = namedParameterJdbcTemplate.query(SELECT_BY_ID, new MapSqlParameterSource().addValue("id", id),
                ticketRowMapper);
        return tickets.get(0);
    }

    private SqlParameterSource createMapSource(Object obj) {
        SqlParameterSource source = null;
        if (obj instanceof IFiltrable) {
            IFiltrable filter = (IFiltrable) obj;
            source = new MapSqlParameterSource()
                    .addValue("status", filter.getTicketStatus() == null ? null : filter.getTicketStatus().toString())
                    .addValue("filmName", filter.getFilmName())
                    .addValue("filmStartDate", filter.getFilmStartDate())
                    .addValue("category", filter.getCategory() == null ? null : filter.getCategory().toString())
                    .addValue("userId", filter.getUserId());
        } else if (obj instanceof ITicket) {
            ITicket ticket = (ITicket) obj;
            source = new MapSqlParameterSource()
                    .addValue("status", ticket.getStatus() == null ? null : ticket.getStatus().toString())
                    .addValue("filmName", ticket.getFilmName())
                    .addValue("filmStartDate", ticket.getFilmStartDate())
                    .addValue("category", ticket.getCategory() == null ? null : ticket.getCategory().toString())
                    .addValue("userId", ticket.getUserId())
                    .addValue("placeNumber", ticket.getPlaceNumber())
                    .addValue("id", ticket.getId());
        }
        return source;
    }

    private String createQueryWithFilter(String baseSql, IFiltrable filter) {
        StringBuilder sql = new StringBuilder(baseSql);

        sql.append(" WHERE");

        if (filter.getTicketStatus() != null) {
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


    private ITicket extractTicket(ResultSet rs) throws SQLException {
        ITicket ticket = new Ticket();
        ticket.setId(rs.getInt("id"));
        ticket.setPlaceNumber(rs.getInt("place_number"));
        ticket.setStatus(TicketStatus.valueOf(rs.getString("status")));
        ticket.setCategory(TicketCategory.valueOf(rs.getString("category")));
        ticket.setFilmName(rs.getString("film_name"));
        ticket.setFilmStartDate(rs.getDate("film_start_date"));
        ticket.setUserId(rs.getInt("user_id"));
        return ticket;
    }

    private RowMapper<ITicket> ticketRowMapper = new RowMapper<ITicket>() {

        @Override
        public ITicket mapRow(ResultSet rs, int rowNum) throws SQLException {
            ITicket ticket = extractTicket(rs);
            return ticket;
        }
    };

}
