package azure.storage.demo.config;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@Slf4j
public class AzureStorageConfiguration {

    @Value("${app.config.azure.storage-endpoint}")
    private String storageEndpoint;
    @Value("${app.config.azure.storage.container}")
    private String storageContainer;

    @Bean
    public BlobServiceClient blobServiceClient() {
        return new BlobServiceClientBuilder()
                .endpoint(storageEndpoint)
                .buildClient();
    }
}
