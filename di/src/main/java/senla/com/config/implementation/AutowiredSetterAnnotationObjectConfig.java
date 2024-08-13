package senla.com.config.implementation;

import lombok.SneakyThrows;
import senla.com.annotation.Autowire;
import senla.com.config.ObjectConfigurer;
import senla.com.context.ApplicationContext;

import java.lang.reflect.Method;

public class AutowiredSetterAnnotationObjectConfig implements ObjectConfigurer {

    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context){
        Method[] methods = t.getClass().getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Autowire.class) && method.getName().startsWith("set") && method.getParameterTypes().length == 1) {
                method.setAccessible(true);

                Class<?> paramType = method.getParameterTypes()[0];
                Object param = context.getObject(paramType);

                method.invoke(t, param);
            }
        }
    }
}
