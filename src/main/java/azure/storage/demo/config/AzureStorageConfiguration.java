package azure.storage.demo.config;

import com.azure.core.credential.AccessToken;
import com.azure.core.credential.TokenCredential;
import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;

@Data
@Configuration
@Slf4j
public class AzureStorageConfiguration {

    @Value("${app.config.azure.client-id}")
    private String clientId;
    @Value("${app.config.azure.client-secret}")
    private String clientSecret;
    @Value("${app.config.azure.tenant-id}")
    private String tenantId;
    @Value("${app.config.azure.storage-id}")
    private String storageId;
    @Value("${app.config.azure.storage-endpoint}")
    private String storageEndpoint;

    public BlobServiceClient getBlobServiceClient() {
        return new BlobServiceClientBuilder()
                .credential(generateClientSecretCredential(clientId, clientSecret))
                .endpoint(storageEndpoint)
                .buildClient();
    }

    public BlobServiceClient getBlobServiceClient(String token) {

        TokenCredential tokenCredential = request -> Mono.just(new AccessToken(token, OffsetDateTime.now()));

        return new BlobServiceClientBuilder()
                .credential(tokenCredential)
                .endpoint(storageEndpoint)
                .buildClient();
    }

    public ClientSecretCredential generateClientSecretCredential(String clientId, String clientSecret){
        return new ClientSecretCredentialBuilder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .tenantId(tenantId)
                .build();
    }
}

