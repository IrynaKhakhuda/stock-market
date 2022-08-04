package com.stock.market.extractors;

import com.stock.market.aspects.ExecutionTimeTracked;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import static com.stock.market.utils.DateUtils.getFrom;
import static com.stock.market.utils.DateUtils.getTo;

@Component
public class ShortRangeExtractor implements HolidayExtractor {

    @Override
    @ExecutionTimeTracked
    public List<LocalDate> extractHolidays(List<LocalDate> existed) {
        return getFrom(existed).datesUntil(getTo(existed)).filter(filterOthers(existed)).toList();
    }

    private static Predicate<LocalDate> filterOthers(@NotNull List<LocalDate> income) {
        Predicate<LocalDate> predicate = ld -> ld.getDayOfWeek() != DayOfWeek.SATURDAY
                && ld.getDayOfWeek() != DayOfWeek.SUNDAY && !income.contains(ld);
        return predicate;
    }

}
