package senla.com.config.implementation;

import lombok.SneakyThrows;
import senla.com.annotation.Value;
import senla.com.config.ObjectConfigurer;
import senla.com.context.ApplicationContext;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class ValueAnnotationObjectConfig implements ObjectConfigurer {
    private Properties appProps;

    private static final String app_properties = "application.properties";

    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        if (appProps == null) {
            loadAppProperties(context);
        }

        Class<?> implClass = t.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            Value annotation = field.getAnnotation(Value.class);

            if (annotation != null) {
                String value = annotation.value().isEmpty() ? appProps.getProperty(field.getName()) : appProps.getProperty(annotation.value());
                field.setAccessible(true);
                field.set(t, value);
            }
        }
    }

    @SneakyThrows
    private void loadAppProperties(ApplicationContext context) {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + app_properties;
        appProps = new Properties();
        appProps.load(new FileInputStream(appConfigPath));
    }
}