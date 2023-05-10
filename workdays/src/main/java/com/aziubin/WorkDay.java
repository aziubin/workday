package com.aziubin;

import java.io.IOException;
import java.time.LocalDate;

public interface WorkDay {
    int delta(LocalDate startDate, LocalDate endDate);
    int delta(LocalDate startDate);
    int load() throws DateReaderException;
    int save() throws IOException;

    DateReader getReader();
    DateWriter getWriter();
    void setReader(DateReader reader);
    void setWriter(DateWriter writer);

}

