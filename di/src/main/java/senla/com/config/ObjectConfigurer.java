package senla.com.config;

import senla.com.context.ApplicationContext;

public interface ObjectConfigurer {
    void configurer(Object t, ApplicationContext context);
}
