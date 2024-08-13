package senla.com.controller;

import senla.com.annotation.Autowire;
import senla.com.annotation.Component;
import senla.com.service.Service;

@Component
public class Controller {
    private Service serviceInterface;

    @Autowire
    public Controller(Service serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    public void execute(){
        System.out.println(serviceInterface.execute());
    }
}
