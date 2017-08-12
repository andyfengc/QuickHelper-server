package smart.quickhelper;

import java.text.SimpleDateFormat;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@Provider
public class JsonProvider extends JacksonJsonProvider {

	public JsonProvider() {
//		ObjectMapper objectMapper = locateMapper(ObjectMapper.class, MediaType.APPLICATION_JSON_TYPE);
		ObjectMapper objectMapper =new ObjectMapper();

		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		objectMapper.setDateFormat(new SimpleDateFormat(dateFormat));
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	}

}