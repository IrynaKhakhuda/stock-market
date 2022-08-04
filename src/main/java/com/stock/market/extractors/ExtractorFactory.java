package com.stock.market.extractors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class ExtractorFactory {

    private ApplicationContext context;

    @Autowired
    public void context(ApplicationContext context) {
        this.context = context;
    }

    public HolidayExtractor getCoincidentExtractor(int datesLength) {
        if (datesLength > 1500) {
            return context.getBean(PeriodicalExtractor.class);
        }
        if (datesLength > 700) {
            return context.getBean(AlgorithmicExtractor.class);
        }
        if (datesLength > 400) {
            return context.getBean(SimpleExtractor.class);
        }
        return context.getBean(ShortRangeExtractor.class);
    }


}
