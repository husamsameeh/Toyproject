package multithreading.toyproject.controller;


import multithreading.toyproject.Constants;

import multithreading.toyproject.business.ServerOperations;
import multithreading.toyproject.model.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/servers")
public class RestController {

    @Autowired
    private ServerOperations serverOperations ;

    @RequestMapping("/allocate")
    public String allocate(@RequestParam int capacity) throws InterruptedException {
        if (capacity > 100 || capacity < 0)
        {
            return Constants.CAPACITY_ERROR;
        }
        //---------------------
        System.out.println("the rest request arrived");
        serverOperations.beginAllocatingServerToClient(capacity);
        //---------------------
        return Constants.SUCCESS;
    }

}
