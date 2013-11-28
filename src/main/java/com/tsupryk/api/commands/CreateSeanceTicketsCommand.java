package com.tsupryk.api.commands;

import com.tsupryk.api.TicketCategory;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.List;

/**
 * The class CreateSeanceTicketsCommand.
 * <p/>
 * Date: 17.11.13
 * <p/>
 * Author: Vitaliy
 */
public class CreateSeanceTicketsCommand {

    @TargetAggregateIdentifier
    private String seanceId;

    private String filmId;

    private List<String> ticketIds;

    private TicketCategory ticketsCategory;

    private Integer placeCount;


    public CreateSeanceTicketsCommand() {

    }

    public CreateSeanceTicketsCommand(String seanceId, String filmId, List<String> ticketIds, TicketCategory ticketsCategory, Integer placeCount) {
        this.seanceId = seanceId;
        this.filmId = filmId;
        this.ticketIds = ticketIds;
        this.ticketsCategory = ticketsCategory;
        this.placeCount = placeCount;
    }

    public List<String> getTicketIds() {
        return ticketIds;
    }

    public void setTicketIds(List<String> ticketIds) {
        this.ticketIds = ticketIds;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public TicketCategory getTicketsCategory() {
        return ticketsCategory;
    }

    public void setTicketsCategory(TicketCategory ticketsCategory) {
        this.ticketsCategory = ticketsCategory;
    }

    public String getSeanceId() {
        return seanceId;
    }

    public void setSeanceId(String seanceId) {
        this.seanceId = seanceId;
    }

    public Integer getPlaceCount() {
        return placeCount;
    }

    public void setPlaceCount(Integer placeCount) {
        this.placeCount = placeCount;
    }
}
