package WebDiplom.InfoPage.contollers;

import WebDiplom.InfoPage.dto.LoginRequest;
import WebDiplom.InfoPage.dto.LoginResponse;
import WebDiplom.InfoPage.dto.RegisterRequest;
import WebDiplom.InfoPage.service.AuthService;
import WebDiplom.InfoPage.service.AuthenticationResponse;
import WebDiplom.InfoPage.dto.UserRequestLoginAndPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.authenticateRequest(loginRequest);
    }

    @PutMapping("/update/login/password/{username}")
    public ResponseEntity loginAndPassword(@PathVariable String username, @RequestBody UserRequestLoginAndPassword userRequestLoginAndPassword) {
        authService.UpdateLoginAndPassword(username, userRequestLoginAndPassword);
        return new ResponseEntity(HttpStatus.OK);
    }
}
