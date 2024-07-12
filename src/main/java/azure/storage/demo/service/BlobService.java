package azure.storage.demo.service;

import azure.storage.demo.config.AzureStorageConfiguration;
import azure.storage.demo.entity.AppRegistryCredentials;
import com.azure.core.credential.AccessToken;
import com.azure.core.credential.TokenRequestContext;
import com.azure.identity.ClientSecretCredential;
import com.azure.storage.blob.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.concurrent.ExecutionException;


@Service
@AllArgsConstructor
@Slf4j
public class BlobService {

    AzureStorageConfiguration azureStorageConfiguration;

    public void uploadBlob(String token, String container, String blobName, byte[] data) {
        log.info("Try Upload in: {}", container);
        BlobClient blobClient = getBlobClient(token, container, blobName);
        blobClient.upload(new ByteArrayInputStream(data), data.length, true);
        log.info("Uploaded");
    }

    public BlobClient getBlobClient(String token, String container, String blobName) {

        BlobServiceClient blobServiceClient = token == null ?
                azureStorageConfiguration.getBlobServiceClient() :
                azureStorageConfiguration.getBlobServiceClient(token);

        return blobServiceClient.getBlobContainerClient(container).getBlobClient(blobName);
    }

    public String auth(AppRegistryCredentials credentials) throws ExecutionException, InterruptedException {

        TokenRequestContext requestContext = new TokenRequestContext().addScopes("https://storage.azure.com/.default");

        ClientSecretCredential credential = azureStorageConfiguration.generateClientSecretCredential(credentials.getClientId(), credentials.getClientSecret());

        AccessToken token = credential.getToken(requestContext).toFuture().get();

        return token.getToken();
    }
}
