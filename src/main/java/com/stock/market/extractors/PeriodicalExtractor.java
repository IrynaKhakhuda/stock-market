package com.stock.market.extractors;

import com.stock.market.aspects.ExecutionTimeTracked;
import com.stock.market.utils.DateUtils;
import jakarta.validation.constraints.NotNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.stock.market.utils.DateUtils.getFrom;

@Log4j2
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PeriodicalExtractor implements HolidayExtractor {

    private List<LocalDate> holidays = new ArrayList<>();
    private List<LocalDate> existed;
    private int existedSize;

    private int index;

    @Override
    @ExecutionTimeTracked
    public List<LocalDate> extractHolidays(@NotNull List<LocalDate> existed) {
        this.existed = existed;
        existedSize = existed.size() - 1;
        nextMonday(getFrom(existed));
        findHolidays(existed.subList(0, index));
        doWhileNoHolidays();
        return holidays;
    }

    private void doWhileNoHolidays() {
        if (index == existedSize) return;
        int savedIndex = index;
        if (isFriday(existed.get(checkFore()))) {
            index++;
            doWhileNoHolidays();
        } else {
            nextMonday(existed.get(savedIndex + 1));
            findHolidays(existed.subList(savedIndex - 2, index));
            doWhileNoHolidays();
        }
    }


    private int checkFore() {
        return index + 4 < existedSize ? index += 4 : existedSize;
    }

    private void findHolidays(List<LocalDate> subList) {
        if (subList.size() > 0) {
            LocalDate from = subList.get(0);
            from.datesUntil(subList.get(subList.size() - 1).plusDays(1)).filter(DateUtils::isNotWeekend).filter(d -> !subList.contains(d))
                    .forEach(holidays::add);
        }

    }

    private boolean isFriday(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.FRIDAY;
    }

    private boolean isNotMonday(LocalDate date) {
        return date.getDayOfWeek() != DayOfWeek.MONDAY;
    }

    private void nextMonday(LocalDate day) {
        LocalDate localDate = day;
        while (isNotMonday(localDate)) {
            localDate = localDate.plusDays(1);
        }
        int indexOf = existed.indexOf(localDate);
        if (indexOf == -1) {
            index = existedSize;
        } else {
            index = indexOf;
        }
    }

}
