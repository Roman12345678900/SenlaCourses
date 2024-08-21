package senla.com.repository.implementation;

import senla.com.annotation.Autowire;
import senla.com.annotation.Component;
import senla.com.entity.ParametersHolder;
import senla.com.repository.Database;

@Component
public class DatabaseImpl implements Database {

    @Autowire
    private ParametersHolder parametersHolder;

    @Override
    public String execute() {
        return parametersHolder.getSomeText();
    }
}
