package mk.ukim.finki.labsemt2.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.labsemt2.model.dto.create.CreateUserDto;
import mk.ukim.finki.labsemt2.model.dto.display.DisplayUserDto;
import mk.ukim.finki.labsemt2.model.dto.login.LoginUserDto;
import mk.ukim.finki.labsemt2.service.application.impl.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "User API", description = "Endpoints for user authentication and registration") // Swagger tag
public class UserController {
    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @PostMapping("/register")
    public ResponseEntity<DisplayUserDto> register(@RequestBody CreateUserDto createUserDto) {
        try {
            return userApplicationService.register(createUserDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
    @Operation(summary = "User login", description = "Authenticates a user and starts a session")
    @PostMapping("/login")
    public ResponseEntity<DisplayUserDto> login(@RequestBody LoginUserDto loginUserDto, HttpServletRequest request) {
        DisplayUserDto body = userApplicationService.login(loginUserDto, request).orElseThrow();
        System.out.println(body.role());
        return ResponseEntity.ok(body);
    }

    @Operation(summary = "User logout", description = "Ends the user's session")
    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        userApplicationService.logout();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/role")
    public ResponseEntity<?> getRole(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(userDetails);
    }
}
