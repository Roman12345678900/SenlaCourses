package senla.com.factory;

import lombok.SneakyThrows;
import senla.com.config.ObjectConfigurer;
import senla.com.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectFactory {
    private final ApplicationContext context;
    private List<ObjectConfigurer> configurators = new ArrayList<>();

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context= context;
        for (Class<? extends ObjectConfigurer> aClass : context.getConfig().getScanner().getSubTypesOf(ObjectConfigurer.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {
        T t = create(implClass);
        configure(t);
        invokeInit(implClass, t);
        return t;
    }

    private <T> void configure(T t) {
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t,context));
    }

    @SneakyThrows
    private <T> T create(Class<T> implClass) {
        if(implClass.getDeclaredConstructors().length == 1 && Arrays.stream(implClass.getDeclaredConstructors()).iterator().next().getParameterTypes().length == 0)
            return implClass.getDeclaredConstructor().newInstance();

        Constructor<?> constructor = Arrays.stream(implClass.getDeclaredConstructors()).iterator().next();
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object[] parameters = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            parameters[i] = context.getObject(parameterTypes[i]);
        }
        return (T) constructor.newInstance(parameters);
    }

    @SneakyThrows
    private <T> void invokeInit(Class<T> implClass, T t) {
        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(t);
            }
        }
    }
}
