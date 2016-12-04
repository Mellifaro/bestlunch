package ua.bestlunch.util.converter;

import org.springframework.format.Formatter;
import ua.bestlunch.util.TimeUtil;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by Виктор on 01.12.2016.
 */
//this class necessary for convertation json to object
public class LocalDateFormatter implements Formatter<LocalDate>{
    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        return TimeUtil.parseLocalDate(text);
    }

    @Override
    public String print(LocalDate localDate, Locale locale) {
        return localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
