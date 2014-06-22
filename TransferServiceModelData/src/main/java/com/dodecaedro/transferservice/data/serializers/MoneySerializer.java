package com.dodecaedro.transferservice.data.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.money.Money;

import java.io.IOException;

/**
 * Created by juan on 15/06/14.
 */
public class MoneySerializer extends JsonSerializer<Money> {
  @Override
  public void serialize(Money money, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
          throws IOException, JsonProcessingException {
    jsonGenerator.writeString(money.toString());
  }
}
