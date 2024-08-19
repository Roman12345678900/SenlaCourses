package senla.com.context;

import lombok.Getter;
import org.reflections.Reflections;
import senla.com.annotation.Component;
import senla.com.config.Config;
import senla.com.factory.ObjectFactory;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    private static ApplicationContext context;
    private ObjectFactory factory;

    private final Map<Class<?>, Object> cache = new ConcurrentHashMap<>();
    @Getter
    private final Config config;

    private ApplicationContext(Config config) {
        this.config = config;
        this.factory = new ObjectFactory(this);
        initializeComponents();
    }

    public static ApplicationContext getInstance(Config config) {
        if (context == null) {
            context = new ApplicationContext(config);
        }
        return context;
    }

    private void initializeComponents() {
        Reflections reflections = config.getScanner();
        Set<Class<?>> componentClasses = reflections.getTypesAnnotatedWith(Component.class);

        for (Class<?> beanClass : componentClasses) {
            getObject(beanClass);
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
