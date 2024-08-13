package senla.com.config.implementation;

import lombok.SneakyThrows;
import senla.com.annotation.Autowire;
import senla.com.config.ObjectConfigurer;
import senla.com.context.ApplicationContext;

import java.lang.reflect.Field;

public class AutowiredConstructorAnnotationObjectConfig implements ObjectConfigurer {
    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowire.class)) {
                field.setAccessible(true);
                Object object = context.getObject(field.getType());
                field.set(t, object);
            }
        }
    }
}
