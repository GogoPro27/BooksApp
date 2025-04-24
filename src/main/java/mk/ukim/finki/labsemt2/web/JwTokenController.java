package mk.ukim.finki.labsemt2.web;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.labsemt2.service.application.IJwTokenApplicationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/jwts")
@RestController
public class JwTokenController {
    private final IJwTokenApplicationService jwTokenApplicationService;

    public JwTokenController(IJwTokenApplicationService jwTokenApplicationService) {
        this.jwTokenApplicationService = jwTokenApplicationService;
    }

    @GetMapping
    @Operation(summary = "List all jwTokens")
    public ResponseEntity<?> listAllTokens() {
        return ResponseEntity.status(HttpStatus.OK).body(jwTokenApplicationService.findAll());
    }
}
