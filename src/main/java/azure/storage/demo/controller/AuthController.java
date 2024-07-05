package azure.storage.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping
    public ResponseEntity<String> getJwt(@AuthenticationPrincipal OidcUser principal) {
        String jwtToken = principal.getIdToken().getTokenValue();
        return ResponseEntity.ok(jwtToken);
    }
}
