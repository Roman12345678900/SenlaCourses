package senla.com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import senla.com.controller.CardInfoController;
import senla.com.controller.UserController;

public class GymApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("Senla.com");
        UserController userController = applicationContext.getBean(UserController.class);
        CardInfoController cardInfoController = applicationContext.getBean(CardInfoController.class);

        System.out.println("All users" + userController.findAll());
        System.out.println("CardInfo with id" + cardInfoController.findById(1L));

        userController.deleteById(1L);
        cardInfoController.deleteById(1L);
    }
}