package azure.storage.demo.service;

import azure.storage.demo.config.AzureStorageConfiguration;
import com.azure.storage.blob.*;
import com.azure.storage.blob.models.UserDelegationKey;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.OffsetDateTime;


@Service
@AllArgsConstructor
@Slf4j
public class BlobService {

    BlobServiceClient blobServiceClient;

    AzureStorageConfiguration azureStorageConfiguration;

    public void uploadBlob(String container, String blobName, byte[] data) {
        BlobClient blobClient = getBlobClient(container, blobName);

        blobClient.upload(new ByteArrayInputStream(data), data.length, true);
    }

    public BlobClient getBlobClient(String container, String blobName) {
        BlobContainerClient blobContainerClient = getBlobContainerClient(container);
        return blobContainerClient.getBlobClient(blobName);
    }

    public BlobContainerClient getBlobContainerClient(String container) {
        return blobServiceClient.getBlobContainerClient(container);
    }
}