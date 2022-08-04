package com.stock.market.utils;

import jakarta.validation.constraints.NotEmpty;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class DateUtils {
    public static boolean isNotWeekend(DayOfWeek day) {
        return day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
    }

    public static boolean isNotWeekend(LocalDate day) {
        return day.getDayOfWeek() != DayOfWeek.SATURDAY && day.getDayOfWeek() != DayOfWeek.SUNDAY;
    }

    public static LocalDate getFrom(@NotEmpty List<LocalDate> range) {
        return range.get(0);
    }

    public static LocalDate getTo(@NotEmpty List<LocalDate> range) {
        return range.get(range.size() - 1);
    }
}
