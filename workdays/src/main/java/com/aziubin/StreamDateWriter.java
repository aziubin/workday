package com.aziubin;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.time.LocalDate;

class StreamDateWriter extends abstractJsonStreamSupport implements DateWriter {
    boolean isBof = true;
    boolean isEof = false;
    OutputStreamWriter writer;

    StreamDateWriter(OutputStream stream) throws Exception {
        CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();
        writer = new OutputStreamWriter(stream, encoder);
    }

    private void writeJsonArrayStart() throws IOException {
        writer.write(JSON_ARRAY_START);
    }

    public void writeJsonArrayEnd() throws IOException  {
        writer.write(JSON_ARRAY_END);
    }

    @Override
    public void write(LocalDate date) throws IOException {
        if (isEof) {
            throw new EOFException("Attempt to write over the end of JSON array.");
        } else if (isBof) {
            isBof = false;
            writeJsonArrayStart();
        } else {
            writer.write(JSON_SEPARATOR);
        }
        writer.write(JSON_QUOTE);
        writer.write(date.format(DATE_TIME_FORMATTER));
        writer.write(JSON_QUOTE);
    }

    @Override
    public void setEnd() throws IOException {
        isEof = true;
        writeJsonArrayEnd();
        writer.flush();
    }

}