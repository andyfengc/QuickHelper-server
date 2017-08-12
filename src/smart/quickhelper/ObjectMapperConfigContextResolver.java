package smart.quickhelper;

import java.text.SimpleDateFormat;

import javax.ws.rs.ext.ContextResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperConfigContextResolver implements ContextResolver<ObjectMapper> {

	ObjectMapper mapper =new ObjectMapper();

	public ObjectMapperConfigContextResolver() {
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return mapper;
	}
}