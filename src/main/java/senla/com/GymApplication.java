package senla.com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import senla.com.controller.UserController;


public class GymApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("senla.com");
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
                System.out.println(userController.findAll());
            }catch (Exception e) {
                e.printStackTrace();
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
                System.out.println(userController.findAll());
            }catch (Exception e) {
                e.printStackTrace();
            }
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();
    }
}