package WebDiplom.InfoPage.service;

import WebDiplom.InfoPage.dto.*;
import WebDiplom.InfoPage.repository.IRoleRepository;
import WebDiplom.InfoPage.repository.IUserRepository;
import WebDiplom.InfoPage.models.Role;
import WebDiplom.InfoPage.models.User;
import WebDiplom.InfoPage.security.JwtUtils;
import WebDiplom.InfoPage.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final IUserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final IRoleRepository roleRepository;


    public String signup(RegisterRequest registerRequest) {

        if (userRepository.existsByUserName(registerRequest.getUsername())) {
            throw new IllegalArgumentException("Username " + registerRequest.getUsername() + " already exist");
        }

        var user = User.builder()
                .userName(registerRequest.getUsername())
                .password(encoder.encode(registerRequest.getPassword()))
                .roles(new HashSet<>(Arrays.asList(mapStringToRole("ROLE_USER"))))
                .phone(registerRequest.getPhone())
                .email(registerRequest.getEmail())
                .surname(registerRequest.getSurname())
                .logo(null)
                .name(registerRequest.getName())
                .lastname(registerRequest.getLastname())
                .build();
        userRepository.save(user);
        return "User was successfully created";
    }

    public void UpdateLoginAndPassword(String username, UserRequestLoginAndPassword userRequestLoginAndPassword) {
        User user = userRepository.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("Користувача не знайдено"));
        if (encoder.matches(userRequestLoginAndPassword.getOldpassword(), user.getPassword())) {
            user.setPassword(encoder.encode(userRequestLoginAndPassword.getPassword()));
            user.setUserName(userRequestLoginAndPassword.getLogin());
        }

        userRepository.save(user);
    }


    public LoginResponse authenticateRequest(LoginRequest request) {

        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
        var jwt = jwtUtils.generateJwtToken(auth);
        var details = (UserDetailsImpl) auth.getPrincipal();
        var roles = details.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return LoginResponse.builder()
                .jwt(jwt)
                .username(details.getUsername())
                .roles(roles)
                .build();
    }

    private Role mapStringToRole(String roleString) {
        return roleRepository.findByName(Role.ERole.valueOf(roleString))
                .orElseThrow(() -> new IllegalArgumentException("Wrong name of role" + roleString));
    }
}
