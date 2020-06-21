package multithreading.toyproject.business;

import multithreading.toyproject.model.Server;
import multithreading.toyproject.repository.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Component
public class DatabaseOperations {
    @Autowired
    private DatabaseRepository databaseRepository;

    public void addIntoDatabase(Server server)
    {
        databaseRepository.save(server);
    }

    public void updateServerInDatabase(Server server)
    {
        databaseRepository.save(server);
    }

    public ArrayList<Server> getAll()
    {
        System.out.println("getting all the servers");
        ArrayList<Server> serversArrayList = new ArrayList<>();

        databaseRepository.findAll().forEach(serversArrayList::add);
        return serversArrayList;
    }


}
