package ru.ac.uniyar.testingcourse;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class BookingSystem {
    private TreeMap<Integer, String> bookedHours = new TreeMap<>();

    public List<Integer> getBookedHoursList() {
        return new LinkedList<>(bookedHours.keySet());
    }

    public boolean book(String user, int from, int till) {
        if (from < 8 || till >= 20) return false;
        for (int i = from; i < till; i++) {
            bookedHours.put(i, user);
        }
        return true;
    }

    public boolean unBook(String user, int from, int till) {
        if (from < 8 || till >= 20) return false;
        for (int i = from; i < till; i++) {
            if (bookedHours.get(i) == user) {
                bookedHours.remove(i);
            }
        }
        return true;
    }
}
