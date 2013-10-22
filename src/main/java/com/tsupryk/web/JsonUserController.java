package com.tsupryk.web;

import com.tsupryk.api.IJsonUserController;
import com.tsupryk.api.IUserService;
import com.tsupryk.api.RestResponse;
import com.tsupryk.api.User;
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

    @Autowired
    private IUserService userService;

    @Override
    @ResponseBody
    @RequestMapping(value = "/create.json", produces = "application/json", method = RequestMethod.POST)
    public Object createUser(@RequestBody User user) {
        RestResponse response = null;

        response = new RestResponse(SUCCESS, null);

        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/all.json", produces = "application/json", method = RequestMethod.GET)
    public Object getAllUsers() {
        return new RestResponse(SUCCESS, userService.getAllUsers());
    }
}
