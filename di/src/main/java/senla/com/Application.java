package senla.com;

import senla.com.config.implementation.JavaConfig;
import senla.com.context.ApplicationContext;


public class Application {
    public static ApplicationContext run(String packageToScan) {
        JavaConfig config = new JavaConfig(packageToScan);
        ApplicationContext context = ApplicationContext.getInstance(config);
        return context;
    }
}