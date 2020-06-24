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
        server.setId((int)Thread.currentThread().getId());
        databaseOperations.addIntoDatabase(server);
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
                    databaseOperations.updateServerInDatabase(server);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void beginAllocatingServerToClient(int wantedCapacity) throws InterruptedException {

        System.out.println("beginning the allocating process");
        Server server;
        synchronized (serversArrayList) {
            serversArrayList = databaseOperations.getAll();
            server = searchForServersByCapacity(wantedCapacity);

            if (server == null) {
                server = spinServer();

            }
        }
        allocateServer(server,wantedCapacity);
    }

    public void allocateServer(Server server , int wantedCapacity)
    {
        System.out.println("the server is being allocated");
        if (server.getStatus() != Constants.ACTIVE)
        {
            waitForAvalibility(server);
        }
        server.setCapacity(server.getCapacity()-wantedCapacity);
        databaseOperations.updateServerInDatabase(server);
    }
    
    public void waitForAvalibility(Server server){
        System.out.println("waiting for the server to become active");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(server.getStatus() != Constants.ACTIVE)
                {
                    System.out.println(server.getStatus());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("the server is now active");
            }
        });

        thread.start();
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
