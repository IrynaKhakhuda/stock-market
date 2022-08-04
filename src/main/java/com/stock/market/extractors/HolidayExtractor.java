package com.stock.market.extractors;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public interface HolidayExtractor {

    List<LocalDate> extractHolidays(@NotNull List<LocalDate> existed);

}
