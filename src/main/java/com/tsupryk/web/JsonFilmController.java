package com.tsupryk.web;

import com.tsupryk.api.IFilmService;
import com.tsupryk.api.RestResponse;
import com.tsupryk.api.ServiceRuntimeException;
import com.tsupryk.api.entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The Class JsonFilmController.
 * <p/>
 * Date: 28.10.13
 *
 * @author Vitaliy_Tsupryk
 */

@Controller
@RequestMapping("/film")
public class JsonFilmController {

    @Autowired
    private IFilmService filmService;

    @ResponseBody
    @RequestMapping(value = "/get.json", produces = "application/json", method = RequestMethod.GET)
    public Object getFilmByTitle(@RequestParam String title) {
        return new RestResponse("SUCCESS", filmService.getFilmByTitle(title));
    }

    @ResponseBody
    @RequestMapping(value = "/params.json", produces = "application/json", method = RequestMethod.GET)
    public Object getFilmByParameter(@RequestParam(required = false) String title,
                                     @RequestParam(required = false) String studio,
                                     @RequestParam(required = false) String actor) {
        RestResponse response = null;
        try {
            if (title == null && studio == null && actor == null) {
                throw new ServiceRuntimeException("At least one of title, studio, actor parameters should be set.");
            }
            response = new RestResponse("SUCCESS", filmService.findByParameter(title, studio, actor));
        } catch (ServiceRuntimeException e) {
            response = new RestResponse("FAIL", e.getMessage());
        }
        return response;
    }

}
