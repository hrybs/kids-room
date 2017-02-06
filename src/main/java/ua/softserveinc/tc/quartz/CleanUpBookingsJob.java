package ua.softserveinc.tc.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.softserveinc.tc.constants.QuartzConstants;
import ua.softserveinc.tc.entity.Booking;
import ua.softserveinc.tc.entity.BookingState;
import ua.softserveinc.tc.repo.BookingRepository;
import ua.softserveinc.tc.service.BookingService;

import java.util.List;

import static ua.softserveinc.tc.util.DateUtil.dateDayAgo;
import static ua.softserveinc.tc.util.DateUtil.toDate;


@Service(QuartzConstants.CLEAN_UP_BOOKINGS)
public class CleanUpBookingsJob {

    @Autowired
    private BookingService bookingService;

    private void task() {
        List<Booking> bookings = bookingService
                .findByBookingStateAndBookingStartTimeLessThan(BookingState.ACTIVE, toDate(dateDayAgo()));

        bookings.forEach(booking -> {
            booking.setBookingState(BookingState.CALCULATE_SUM);
            bookingService.update(booking);
        });
    }
}
