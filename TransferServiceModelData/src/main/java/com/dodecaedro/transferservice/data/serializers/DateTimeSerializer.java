package com.dodecaedro.transferservice.data.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;

/**
 * Created by juan on 06/09/14.
 */
public class DateTimeSerializer extends JsonSerializer<DateTime> {
  @Override
  public void serialize(DateTime dateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
          throws IOException, JsonProcessingException {
    jsonGenerator.writeString(dateTime.toString(ISODateTimeFormat.dateTimeNoMillis()));
  }
}
