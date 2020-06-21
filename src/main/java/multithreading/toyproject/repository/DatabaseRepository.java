package multithreading.toyproject.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import multithreading.toyproject.model.Server;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseRepository extends AerospikeRepository<Server,Integer> {
}
