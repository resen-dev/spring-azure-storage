package azure.storage.demo.controller;

import azure.storage.demo.config.AzureStorageConfiguration;
import azure.storage.demo.service.BlobService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/blobs")
@Slf4j
public class BlobController {

    private BlobService blobService;
    AzureStorageConfiguration azureStorageConfiguration;

    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) throws IOException {

        log.info("Received file:{}", file.getOriginalFilename());

        blobService.uploadBlob(azureStorageConfiguration.getStorageContainer(), file.getOriginalFilename(), file.getBytes());
        return ResponseEntity.ok().body("Uploaded");
    }
}
