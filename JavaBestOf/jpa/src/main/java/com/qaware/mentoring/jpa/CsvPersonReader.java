package com.qaware.mentoring.jpa;

import com.google.common.base.Preconditions;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads {@link Person} objects from a CSV file.
 */
/* package-private */ class CsvPersonReader {

    private static final char DELIMITER = ',';

    /**
     * The header names of the CSV
     */
    private enum HEADERS {
        FIRST_NAME,
        LAST_NAME
    }

    private final File file;

    /**
     * Constructs a Person CSV reader for a file.
     *
     * @param file the file
     */
    /* package-private */ CsvPersonReader(File file) {
        Preconditions.checkNotNull(file, "File must be not null");
        this.file = file;
    }

    /**
     * Reads all the persons from the CSV.
     *
     * @return the persons
     */
    /* package-private */ List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();
        Reader reader = null;
        CSVParser parser = null;
        try {
            reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            parser = new CSVParser(reader, CSVFormat.EXCEL
                    .withDelimiter(DELIMITER)
                    .withHeader(HEADERS.class)
                    .withSkipHeaderRecord());
            for (final CSVRecord record : parser) {
                persons.add(new Person(record.get(HEADERS.FIRST_NAME), record.get(HEADERS.LAST_NAME)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(parser);
            close(reader);
        }
        return persons;
    }

    private void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
