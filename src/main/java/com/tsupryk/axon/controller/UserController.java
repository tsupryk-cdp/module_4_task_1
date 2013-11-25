package com.tsupryk.axon.controller;

import com.tsupryk.api.IUserService;
import com.tsupryk.api.RestResponse;
import com.tsupryk.api.ServiceRuntimeException;
import com.tsupryk.api.entity.User;
import com.tsupryk.axon.aggregates.UserAR;
import com.tsupryk.axon.commands.ChangePasswordCommand;
import com.tsupryk.axon.commands.Command;
import com.tsupryk.axon.commands.CreateUserCommand;
import com.tsupryk.axon.listeners.UserEventListener;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * The class UserController.
 * <p/>
 * Date: 17.11.13
 * <p/>
 * Author: Vitaliy
 */
@Controller
@RequestMapping("axon/users")
public class UserController {

    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private EventSourcingRepository<UserAR> repository;

    @Autowired
    private MongoOperations mongoOperations;


    @ResponseBody
    @RequestMapping(value = "/create.json", produces = "application/json", method = RequestMethod.POST)
    public Object createUser(@RequestBody CreateUserCommand command) {

//        String id = UUID.randomUUID().toString();
//        command.setUserId(id);

        commandGateway.send(command);

        RestResponse response = new RestResponse(SUCCESS, null);

        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/getById.json", produces = "application/json", method = RequestMethod.GET)
    public Object getUserById(@RequestParam String id) {
        return new RestResponse(SUCCESS, mongoOperations.findById(id, UserAR.class, UserEventListener.COLLECTION_USERS));
    }

    @ResponseBody
        @RequestMapping(value = "/changePassword", produces = "application/json", method = RequestMethod.POST)
    public Object changePassword(@RequestBody ChangePasswordCommand command) {
        commandGateway.send(command);
        return new RestResponse(SUCCESS, null);
    }
}
