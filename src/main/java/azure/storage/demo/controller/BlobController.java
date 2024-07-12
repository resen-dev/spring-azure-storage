package azure.storage.demo.controller;

import azure.storage.demo.entity.AppRegistryCredentials;
import azure.storage.demo.service.BlobService;
import com.azure.core.credential.AccessToken;
import com.azure.core.credential.TokenRequestContext;
import com.azure.identity.ClientSecretCredential;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping("/blobs")
@Slf4j
public class BlobController {

    private BlobService blobService;

    @PostMapping(path = "/default/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> defaultUploadFile(@RequestParam(value = "file") MultipartFile file,
                                                    @RequestParam(value = "container") String container) throws IOException {

        blobService.uploadBlob(null, container, file.getOriginalFilename(), file.getBytes());
        return ResponseEntity.ok().body("Uploaded");
    }

    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> uploadFile(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                             @RequestParam(value = "file") MultipartFile file,
                                             @RequestParam(value = "container") String container) throws IOException {

        blobService.uploadBlob(authorizationHeader.substring(7), container, file.getOriginalFilename(), file.getBytes());
        return ResponseEntity.ok().body("Uploaded");
    }

    @PostMapping(path = "/auth")
    public ResponseEntity<String> auth(@RequestBody AppRegistryCredentials credentials) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(blobService.auth(credentials));
    }
}
