package multithreading.toyproject.model;

import multithreading.toyproject.Constants;
import org.springframework.data.annotation.Id;

public class Server {
    @Id
    Integer id;
    Integer capacity = 100;
    Integer status = Constants.CREATING;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
