package multithreading.toyproject.business;

import multithreading.toyproject.Constants;
import multithreading.toyproject.model.Server;
import multithreading.toyproject.repository.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;

@Component
public class ServerOperations {

    private ArrayList<Server> serversArrayList;

    @Autowired
    private DatabaseRepository databaseRepository;

    @Autowired
    private DatabaseOperations databaseOperations;

    public Server spinServer() throws InterruptedException {

        System.out.println("spinning a server");
        Server server = new Server();
        waitForCreationToBeDone(server);

        return server;
    }

    private void waitForCreationToBeDone  (Server server) {
        System.out.println("waiting for the server to be created");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20000);
                    server.setStatus(Constants.ACTIVE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void beginAllocatingServerToClient(int wantedCapacity) throws InterruptedException {

        System.out.println("beginning the allocating process");

        serversArrayList = databaseOperations.getAll();
        Server server = searchForServersByCapacity(wantedCapacity);
        if (server == null)
        {
            server = spinServer();

        }
        allocateServer(server);
    }

    public void allocateServer(Server server)
    {
        System.out.println("the server is being allocated");

    }
    
    public void waitForAvalibility(Server server){
        System.out.println("waiting for the server to become active");
        while(server.getStatus() != Constants.ACTIVE)
        {

        }
    }

    public Server searchForServersByCapacity(int capacity)
    {
        System.out.println("searching for the server");
        serversArrayList.sort(new Comparator<Server>() {
            @Override
            public int compare(Server o1, Server o2) {
                if (o1.getCapacity() > o2.getCapacity())
                {
                    return 1;
                }
                else if(o1.getCapacity() < o2.getCapacity())
                {
                    return -1;
                }
                return 0;
            }
        });

        for (int i = 0 ; i < serversArrayList.size();i++)
        {
            if(serversArrayList.get(i).getCapacity() > capacity)
            {
                return serversArrayList.get(i);
            }
        }
        return null;
    }





}
