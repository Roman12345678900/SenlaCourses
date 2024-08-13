package senla.com.context;

import lombok.Getter;
import lombok.Setter;
import senla.com.annotation.Component;
import senla.com.config.Config;
import senla.com.factory.ObjectFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    private static ApplicationContext context;
    @Setter
    private ObjectFactory factory;

    private Map<Class, Object> cache = new ConcurrentHashMap<>();
    @Getter
    private Config config;

    private ApplicationContext(Config config) {
        this.config = config;
    }

    public static ApplicationContext getInstance(Config config) {
        if (context == null) {
            context = new ApplicationContext(config);
            return context;
        } else {
            return context;
        }
    }

    public <T> T getObject(Class<T> type) {
        if (cache.containsKey(type)) {
            return (T) cache.get(type);
        }
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }
        T t = factory.createObject(implClass);
        if (implClass.isAnnotationPresent(Component.class)) {
            cache.put(type, t);
        }
        return t;
    }
}
