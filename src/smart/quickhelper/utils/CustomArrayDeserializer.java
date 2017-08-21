package smart.quickhelper.utils;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import smart.quickhelper.entities.User;

public class CustomArrayDeserializer extends JsonDeserializer<Object> {
	
	@Override
	public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String value = p.getText();
		Collection<User> readValues = new ObjectMapper().readValue(value, new TypeReference<Collection<User>>() { });
		return readValues;
	}
}
