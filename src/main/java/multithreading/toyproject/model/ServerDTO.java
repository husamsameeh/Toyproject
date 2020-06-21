package multithreading.toyproject.model;

public class ServerDTO {
    Integer id;
    Integer capacity;
    Integer status;

    public ServerDTO(){

    }

    public ServerDTO(Integer id, Integer capacity, Integer status) {
        this.id = id;
        this.capacity = capacity;
        this.status = status;
    }

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
