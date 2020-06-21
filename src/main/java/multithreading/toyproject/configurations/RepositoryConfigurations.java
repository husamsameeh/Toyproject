package multithreading.toyproject.configurations;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.policy.ClientPolicy;
import multithreading.toyproject.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.aerospike.core.AerospikeTemplate;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableAerospikeRepositories(basePackages = "multithreading.toyproject.repository")
public class RepositoryConfigurations {

    @Bean
    public AerospikeTemplate aerospikeTemplate ()
    {
        return new AerospikeTemplate(aerospikeClient(),"test");
    }
    @Bean
    public AerospikeClient aerospikeClient(){
        ClientPolicy clientPolicy = new ClientPolicy();
        clientPolicy.failIfNotConnected=true;
        return new AerospikeClient(clientPolicy, Constants.DATABASE_IP,Constants.PORT);
    }
}
