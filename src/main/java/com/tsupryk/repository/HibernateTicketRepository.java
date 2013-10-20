package com.tsupryk.repository;

import com.tsupryk.api.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * The class HibernateTicketRepository.
 * <p/>
 * User: Vitaliy
 * Date: 20.10.13
 */
@Repository
public class HibernateTicketRepository implements ITicketRepository {

    private static final String BASE_SELECT = "from com.tsupryk.api.Ticket";
    private static final String SELECT_BY_ID = "from com.tsupryk.api.Ticket where id=:id";

    private static final String ID = "id";
    private static final String STATUS = "status";
    private static final String FILM_NAME = "filmName";
    private static final String FILM_START_DATE = "filmStartDate";
    private static final String CATEGORY = "category";
    private static final String USER_ID = "userId";

    @Autowired
    private SessionFactory factory;

    @Override
    public void initTickets() {
        int count = 0;
        for (int j = 0; j < 2; j++) {
            for (int i = 1; i <= 10; i++) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.DAY_OF_MONTH, 5 + j);

                Ticket ticket = new Ticket();
                ticket.setId(count++);
                ticket.setStatus(TicketStatus.FREE);
                ticket.setCategory(TicketCategory.STANDARD);
                if (j > 0) {
                    ticket.setFilmName("Terminator");
                } else {
                    ticket.setFilmName("Independence Day");
                }
                ticket.setFilmStartDate(calendar.getTime());
                ticket.setPlaceNumber(i);

                getSession().save(ticket);
            }
        }

    }


    @Override
    public List<Ticket> getTickets(IFiltrable filter) {
        Query query =  getSession().createQuery(createQueryWithFilter(BASE_SELECT, filter));
        setParameters(query, filter);
        return query.list();
    }

    @Override
    public boolean updateTicket(Ticket ticket) {
        getSession().update(ticket);
        return true;
    }

    @Override
    public Ticket getById(Integer id) {
        return (Ticket) getSession().createQuery(SELECT_BY_ID).setParameter(ID, id).uniqueResult();
    }

    private Session getSession() {
        return factory.getCurrentSession();
    }

    private void setParameters(Query query, IFiltrable filter) {
        if (filter.getTicketStatus() != null) {
            query.setParameter(STATUS, filter.getTicketStatus());
        }
        if (filter.getFilmName() != null) {
            query.setParameter(FILM_NAME, filter.getFilmName());
        }
        if (filter.getFilmStartDate() != null) {
            query.setParameter(FILM_START_DATE, filter.getFilmStartDate());
        }
        if (filter.getCategory() != null) {
            query.setParameter(CATEGORY, filter.getCategory());
        }
        if (filter.getUserId() != null) {
            query.setParameter(USER_ID, filter.getUserId());
        }
    }

    private String createQueryWithFilter(String baseSql, IFiltrable filter) {
        StringBuilder sql = new StringBuilder(baseSql);

        sql.append(" WHERE");

        if (filter.getTicketStatus() != null) {
            appendParam(sql, STATUS);
        }
        if (filter.getFilmName() != null) {
            appendParam(sql, FILM_NAME);
        }
        if (filter.getFilmStartDate() != null) {
            appendParam(sql, FILM_START_DATE);
        }
        if (filter.getCategory() != null) {
            appendParam(sql, CATEGORY);
        }
        if (filter.getUserId() != null) {
            appendParam(sql, USER_ID);
        }

        return sql.toString();
    }

    private void appendParam(StringBuilder sql, String fieldName) {
        String stringParam = "? = :?";
        stringParam = stringParam.replace("?", fieldName);
        if (sql.lastIndexOf("WHERE") != sql.length() - 5) {
            sql.append(" AND ");
        } else {
            sql.append(" ");
        }
        sql.append(stringParam);
    }
}
