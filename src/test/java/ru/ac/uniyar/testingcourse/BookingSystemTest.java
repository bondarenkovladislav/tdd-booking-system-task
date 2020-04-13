package ru.ac.uniyar.testingcourse;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class BookingSystemTest {

    private BookingSystem bookingSystem = new BookingSystem();

    @Test
    public void bookedHoursListShouldBeEmptyAfterCreation() {
        List<Integer> bookedHours = bookingSystem.getBookedHoursList();
        assertThat(bookedHours).isEmpty();
    }

    @Test
    public void possibleToBookOneInterval() {
        assertThat(bookingSystem.book("user", 12, 14)).isTrue();
        List<Integer> bookedHours = bookingSystem.getBookedHoursList();
        assertThat(bookedHours).containsExactly(12, 13);
    }

    @Test
    @Parameters({"4, 7", "20, 22"})
    public void impossibleToBookIntervalBeyondBoundaries(int from, int till) {
        assertThat(bookingSystem.book("user", from, till)).isFalse();
        assertThat(bookingSystem.getBookedHoursList()).isEmpty();
    }

    @Test
    public void cancelBooking() {
        assertThat(bookingSystem.book("user", 12, 14)).isTrue();
        assertThat(bookingSystem.unBook("user", 12, 13)).isTrue();
        List<Integer> bookedHours = bookingSystem.getBookedHoursList();
        assertThat(bookedHours.size()).isEqualTo(1);
        assertThat(bookedHours.get(0)).isEqualTo(13);
    }

    @Test public void cancelBookingFullInterval() {
        assertThat(bookingSystem.book("user", 12, 14)).isTrue();
        assertThat(bookingSystem.unBook("user", 12, 14)).isTrue();
        List<Integer> bookedHours = bookingSystem.getBookedHoursList();
        assertThat(bookedHours).isEmpty();
    }

    @Test
    @Parameters({"4, 7", "20, 22"})
    public void unbookBeyondInterval(int from, int till) {
        bookingSystem.book("user", 8, 12);
        int startListSize = bookingSystem.getBookedHoursList().size();
        assertThat(bookingSystem.unBook("user", from, till)).isFalse();
        int endListSize = bookingSystem.getBookedHoursList().size();
        assertThat(startListSize).isEqualTo(endListSize);
    }

    @Test
    public void unbookDifferentUser() {
        bookingSystem.book("user", 8, 12);
        int startListSize = bookingSystem.getBookedHoursList().size();
        assertThat(bookingSystem.unBook("user1", 8, 12)).isFalse();
        int endListSize = bookingSystem.getBookedHoursList().size();
        assertThat(startListSize).isEqualTo(endListSize);
    }
}
