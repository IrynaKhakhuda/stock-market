package com.stock.market.extractors;

import com.stock.market.aspects.ExecutionTimeTracked;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.stock.market.utils.DateUtils.getFrom;
import static com.stock.market.utils.DateUtils.isNotWeekend;

@Component
public class AlgorithmicExtractor implements HolidayExtractor {
    @Override
    @ExecutionTimeTracked
    public List<LocalDate> extractHolidays(@NotNull List<LocalDate> income) {
        List<LocalDate> holidays = new ArrayList<>();
        LocalDate day = getFrom(income);
        for (LocalDate localDay : income) {
            if (localDay.isEqual(day)) {
                day = day.plusDays(1);
            } else {
                day.datesUntil(localDay).filter(dt -> isNotWeekend(dt.getDayOfWeek())).forEach(holidays::add);
                day = localDay.plusDays(1);
            }
        }
        return holidays;
    }
}
