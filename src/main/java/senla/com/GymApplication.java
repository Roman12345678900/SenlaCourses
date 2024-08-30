package senla.com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import senla.com.controller.ClassesController;
import senla.com.controller.UserController;


public class GymApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("Senla.com");
        UserController userController = applicationContext.getBean(UserController.class);
        ClassesController classesController = applicationContext.getBean(ClassesController.class);

        userController.save("""
                {
                    "firstName":"jojo",
                    "lastName":"smith",
                    "email":"jojo@smith.com"
                }
                """);

        classesController.save("""
                {
                    "name":"qwe"
                }
                """);


        System.out.println("All users" + userController.findAll());
        System.out.println("CardInfo with id" + classesController.findById(1L));

        userController.deleteById(1L);
        classesController.deleteById(1L);
    }
}