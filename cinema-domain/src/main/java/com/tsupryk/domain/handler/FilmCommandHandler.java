package com.tsupryk.domain.handler;

import com.tsupryk.domain.ServiceRuntimeException;
import com.tsupryk.domain.aggregate.FilmAR;
import com.tsupryk.api.commands.CreateFilmCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class FilmCommandHandler.
 * <p/>
 * Date: 26.11.13
 * <p/>
 * Author: Vitaliy
 */
@Component
public class FilmCommandHandler {

    @Resource(name = "filmES")
    private EventSourcingRepository<FilmAR> repository;

    @CommandHandler
    public void handleCreateFilm(CreateFilmCommand command) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("DD.MM.YYYY:HH:MM");
            Date startTime = format.parse(command.getStartTime());
            Date endTime = format.parse(command.getEndTime());
        } catch (ParseException e) {
            throw new ServiceRuntimeException("Wrong film start or end time");
        }
        FilmAR film = new FilmAR(command);
        repository.add(film);
    }

}
