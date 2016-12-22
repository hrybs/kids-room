package ua.softserveinc.tc.controller.util;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.softserveinc.tc.constants.SearchConstants;
import ua.softserveinc.tc.dto.BookingDto;
import ua.softserveinc.tc.dto.ChildDto;
import ua.softserveinc.tc.dto.UserDto;
import ua.softserveinc.tc.entity.Booking;
import ua.softserveinc.tc.entity.Child;
import ua.softserveinc.tc.entity.User;
import ua.softserveinc.tc.search.BookingSearch;
import ua.softserveinc.tc.search.ChildSearch;
import ua.softserveinc.tc.search.UserSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edward on 5/14/16.
 */
@RestController
public class SearchController {

    @Autowired
    private UserSearch userSearch;

    @Autowired
    private ChildSearch childSearch;

    @Autowired
    private BookingSearch bookingSearch;

    @GetMapping(SearchConstants.USER_SEARCH_URL)
    public String searchUser(@RequestParam("field") String field) {
        List<UserDto> result = new ArrayList<>();

        if (isValidRequestField(field)) {
            List<User> users = userSearch.search(field);
            for (User user : users) {
                result.add(new UserDto(user));
            }
        }

        Gson gson = new Gson();
        return gson.toJson(result);
    }

    @GetMapping(SearchConstants.CHILD_SEARCH_URL)
    public String searchChild(@RequestParam("field") String field) {
        List<ChildDto> result = new ArrayList<>();

        if (isValidRequestField(field)) {
            List<Child> children = childSearch.search(field);
            for (Child child : children) {
                result.add(new ChildDto(child));
            }
        }

        Gson gson = new Gson();
        return gson.toJson(result);
    }

    @GetMapping(SearchConstants.BOOKING_SEARCH_URL)
    public String searchBooking(@RequestParam("field") String field) {
        List<BookingDto> result = new ArrayList<>();

        if (isValidRequestField(field)) {
            List<Booking> bookings = bookingSearch.search(field);
            for (Booking booking : bookings) {
                result.add(new BookingDto(booking));
            }
        }

        Gson gson = new Gson();
        return gson.toJson(result);
    }

    private boolean isValidRequestField(String field) {
        return field.length() >= 3 && field.length() <= 1024;
    }

}
