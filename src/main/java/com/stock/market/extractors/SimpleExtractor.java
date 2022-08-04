package com.stock.market.extractors;

import com.stock.market.aspects.ExecutionTimeTracked;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.stock.market.utils.DateUtils.getFrom;
import static com.stock.market.utils.DateUtils.getTo;

@Component
public class SimpleExtractor implements HolidayExtractor {

    @Override
    @ExecutionTimeTracked
    public List<LocalDate> extractHolidays(@NotNull List<LocalDate> income) {
        List<LocalDate> range = getFrom(income).datesUntil(getTo(income).plusDays(1)).collect(Collectors.toList());
        range.removeAll(income);
        return range.stream().filter(ld -> ld.getDayOfWeek() != DayOfWeek.SATURDAY && ld.getDayOfWeek() != DayOfWeek.SUNDAY).toList();
    }
}
