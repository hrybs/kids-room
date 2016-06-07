package ua.softserveinc.tc.controller.util;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.softserveinc.tc.constants.ApiConstants;
import ua.softserveinc.tc.dto.ChildDto;
import ua.softserveinc.tc.dto.UserDto;
import ua.softserveinc.tc.entity.Child;
import ua.softserveinc.tc.entity.User;
import ua.softserveinc.tc.service.ChildService;
import ua.softserveinc.tc.service.UserService;
import ua.softserveinc.tc.util.ApplicationConfigurator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edward on 5/17/16.
 */
@Controller
public class ApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private ChildService childService;

    @Autowired
    private ApplicationConfigurator configurator;

    @RequestMapping(value = ApiConstants.usersRestUrl, method = RequestMethod.GET)
    public @ResponseBody String getUser() {
        List<UserDto> result = new ArrayList<UserDto>();
        List<User> users = userService.findAll();

        for (User user : users) {
            result.add(new UserDto(user));
        }

        Gson gson = new Gson();
        return gson.toJson(result);
    }

    @RequestMapping(value = ApiConstants.usersRestByIdUrl, method = RequestMethod.GET)
    public @ResponseBody String getUserById(@PathVariable long id) {
        User user = userService.findById(id);
        Gson gson = new Gson();

        return gson.toJson(new UserDto(user));
    }

    @RequestMapping(value = ApiConstants.childrenRestUrl, method = RequestMethod.GET)
    public @ResponseBody String getChild() {
        List<ChildDto> result = new ArrayList<ChildDto>();
        List<Child> children = childService.findAll();

        for (Child child : children) {
            result.add(new ChildDto(child));
        }

        Gson gson = new Gson();
        return gson.toJson(result);
    }

    @RequestMapping(value = ApiConstants.childrenRestUrl, method = RequestMethod.POST)
    public @ResponseBody String addChild(@RequestBody Child child) {
        childService.create(child);
        Gson gson = new Gson();
        return gson.toJson(child);
    }

    @RequestMapping(value = ApiConstants.childrenByIdRestUrl, method = RequestMethod.GET)
    public @ResponseBody String getChildById(@PathVariable long id) {
        Child child = childService.findById(id);
        Gson gson = new Gson();

        return gson.toJson(new ChildDto(child));
    }

    @RequestMapping(value = ApiConstants.getChildrenParentRestUrl, method = RequestMethod.GET)
    public @ResponseBody String getParentByChild(@PathVariable long id) {
        Child child = childService.findById(id);
        User user = child.getParentId();
        Gson gson = new Gson();

        return gson.toJson(new UserDto(user));
    }

    @RequestMapping(value = ApiConstants.getAppConfiguration, method = RequestMethod.GET)
    public @ResponseBody String getAppConfiguration() {
        Gson gson = new Gson();
        return gson.toJson(configurator);
    }

}
