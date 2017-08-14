package smart.quickhelper;


import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


import smart.quickhelper.controllers.TaskController;

@ApplicationPath("/")
public class WebConfig extends Application {
//	@Override
//    public Set<Class<?>> getClasses() {
//        Set<Class<?>> classes = new HashSet<>();
//        classes.add(ObjectMapperConfigContextResolver.class);
//        return classes;
//    }
	
}
