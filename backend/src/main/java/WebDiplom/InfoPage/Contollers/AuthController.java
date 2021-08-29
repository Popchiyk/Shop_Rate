package WebDiplom.InfoPage.Contollers;

import WebDiplom.InfoPage.dto.LoginRequest;
import WebDiplom.InfoPage.dto.RegisterRequest;
import WebDiplom.InfoPage.Service.AuthService;
import WebDiplom.InfoPage.Service.AuthenticationResponse;
import WebDiplom.InfoPage.dto.UserRequestLoginAndPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

        @Autowired
        private AuthService authService;

        @PostMapping("/signup")
        public ResponseEntity signup(@RequestBody RegisterRequest registerRequest){
            authService.signup(registerRequest);
            return new ResponseEntity(HttpStatus.OK);
        }

        @PostMapping("/login")
        public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){
            return authService.login(loginRequest);
    }

    @PutMapping("/update/login/password/{username}")
    public ResponseEntity loginandpassword(@PathVariable String username, @RequestBody UserRequestLoginAndPassword userRequestLoginAndPassword){
            authService.UpdateLoginAndPassword(username,userRequestLoginAndPassword);
        return new ResponseEntity(HttpStatus.OK);
    }
}
