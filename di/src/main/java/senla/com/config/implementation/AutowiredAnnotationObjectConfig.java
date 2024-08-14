package senla.com.config.implementation;

import lombok.SneakyThrows;
import senla.com.annotation.Autowire;
import senla.com.config.ObjectConfigurer;
import senla.com.context.ApplicationContext;

import java.lang.reflect.Constructor;

public class AutowiredAnnotationObjectConfig implements ObjectConfigurer {
    @Override
    @SneakyThrows
    public void configurer(Object t, ApplicationContext context) {
        Constructor<?>[] constructors = t.getClass().getDeclaredConstructors();

        for (Constructor<?> constructor : constructors) {
            if (constructor.isAnnotationPresent(Autowire.class) && constructor.getParameterTypes().length != 0) {
                constructor.setAccessible(true);

                Class<?>[] parameterTypes = constructor.getParameterTypes();
                Object[] parameters = new Object[parameterTypes.length];

                for (int i = 0; i < parameterTypes.length; i++) {
                    parameters[i] = context.getObject(parameterTypes[i]);
                }
                constructor.newInstance(parameters);
                break;
            }
        }
    }
}