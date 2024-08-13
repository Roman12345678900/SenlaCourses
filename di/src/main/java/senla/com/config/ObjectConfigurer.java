package senla.com.config;

import senla.com.context.ApplicationContext;

public interface ObjectConfigurer {
    void configure(Object t, ApplicationContext context);
}
