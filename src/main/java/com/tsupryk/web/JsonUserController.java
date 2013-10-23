package com.tsupryk.web;

import com.tsupryk.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * The class JsonUserController.
 * <p/>
 * User: Vitaliy
 * Date: 22.10.13
 */
@Controller
@RequestMapping("/users")
public class JsonUserController implements IJsonUserController {

    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";

    @Autowired
    private IUserService userService;

    @Override
    @ResponseBody
    @RequestMapping(value = "/create.json", produces = "application/json", method = RequestMethod.POST)
    public Object createUser(@RequestBody User user) {
        RestResponse response = null;
        try {
            userService.createUser(user);
            response = new RestResponse(SUCCESS, null);
        } catch (ServiceRuntimeException e) {
            response = new RestResponse(FAIL, e.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/all.json", produces = "application/json", method = RequestMethod.GET)
    public Object getAllUsers() {
        return new RestResponse(SUCCESS, userService.getAllUsers());
    }
}
