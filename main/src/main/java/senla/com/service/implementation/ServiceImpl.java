package senla.com.service.implementation;

import senla.com.annotation.Autowire;
import senla.com.annotation.Component;
import senla.com.repository.Database;
import senla.com.service.Service;

@Component
public class ServiceImpl implements Service {
    private Database databaseInterface;

    @Autowire
    public void setDatabaseInterface(Database databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

    @Override
    public String execute() {
        return databaseInterface.execute();
    }
}
