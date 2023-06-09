package com.aziubin;

import java.time.LocalDate;

/**
 * Date reader implementation, which generates sequence of dates in specified range
 * for testing purposes.
 */
class SequentialDateReader implements DateReader {
    LocalDate date;
    LocalDate finishDate;

    SequentialDateReader(LocalDate startDate, LocalDate finishDate) {
        this.date = startDate;
        this.finishDate = finishDate;
    }

    @Override
    public LocalDate read() throws DateReaderException {
        if (finishDate.isBefore(date)) {
            return null;
        }

        LocalDate tmp = date;
        date = date.plusDays(1);
        return tmp;
    }

}
