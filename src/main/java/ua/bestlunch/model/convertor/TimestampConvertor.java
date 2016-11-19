package ua.bestlunch.model.convertor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Виктор on 19.11.2016.
 */
@Converter(autoApply = true)
public class TimestampConvertor implements AttributeConverter<LocalDateTime, Timestamp>{
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

}
