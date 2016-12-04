package ua.bestlunch.util.converter;

import org.springframework.format.Formatter;
import ua.bestlunch.util.TimeUtil;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by Виктор on 01.12.2016.
 */
//this class necessary for convertation json to object
public class LocalTimFormatter implements Formatter<LocalTime>{
    @Override
    public LocalTime parse(String text, Locale locale) throws ParseException {
        return TimeUtil.parseLocalTime(text);
    }

    @Override
    public String print(LocalTime localTime, Locale locale) {
        return localTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }
}
