package com.tsupryk.axon.controller;

import com.tsupryk.api.IUserService;
import com.tsupryk.api.RestResponse;
import com.tsupryk.api.ServiceRuntimeException;
import com.tsupryk.api.entity.User;
import com.tsupryk.axon.commands.CreateUserCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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


    @ResponseBody
    @RequestMapping(value = "/create.json", produces = "application/json", method = RequestMethod.POST)
    public Object createUser(@RequestBody CreateUserCommand command) {

        command.setUserId(UUID.randomUUID().toString());

        commandGateway.send(command);

        RestResponse response = new RestResponse(SUCCESS, null);

        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/all.json", produces = "application/json", method = RequestMethod.GET)
    public Object getAllUsers() {
//        return new RestResponse(SUCCESS, userService.getAllUsers());
        return null;
    }
}
