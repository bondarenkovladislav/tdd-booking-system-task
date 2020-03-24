package ru.ac.uniyar.testingcourse;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BookingSystemTest {

    private BookingSystem bookingSystem = new BookingSystem();

    @Test
    public void bookedHoursListShouldBeEmptyAfterCreation() {
        List<Integer> bookedHours = bookingSystem.getBookedHoursList();
        assertThat(bookedHours).isEmpty();
    }

}
