package senla.com;

import senla.com.context.ApplicationContext;
import senla.com.controller.Controller;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = Application.run("senla.com");
        Controller controller = context.getObject(Controller.class);
        controller.execute();
    }
}