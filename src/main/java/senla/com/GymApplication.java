package senla.com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import senla.com.configuration.ApplicationConfiguration;
import senla.com.controller.UserController;


@Slf4j
public class GymApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        UserController userController = applicationContext.getBean(UserController.class);

        Runnable task1 = () -> {
            try {
                userController.update(14L,"""
                        {
                            "firstName":"jojo",
                            "lastName":"smith",
                            "email":"jojo@smith.com"
                        }
                        """);
                log.info(userController.findAll());
            }catch (Exception e) {
                log.error("Error in task 1", e);
            }
        };

        Runnable task2 = () -> {
            try {
                userController.update(2L,"""
                        {
                            "firstName":"jojo15",
                            "lastName":"smith",
                            "email":"jojo@smith.com"
                        }
                        """);
                log.info(userController.findAll());
            }catch (Exception e) {
                log.error("Error in task 2", e);
            }
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();
    }
}