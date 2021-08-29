package WebDiplom.InfoPage.Service;

import WebDiplom.InfoPage.Repository.RoleRepository;
import WebDiplom.InfoPage.Repository.UserRepository;
import WebDiplom.InfoPage.Security.JwtProvider;
import WebDiplom.InfoPage.dto.LoginRequest;
import WebDiplom.InfoPage.dto.RegisterRequest;
import WebDiplom.InfoPage.dto.RoleDto;
import WebDiplom.InfoPage.Models.RoleEntity;
import WebDiplom.InfoPage.Models.UserEntity;
import WebDiplom.InfoPage.dto.UserRequestLoginAndPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AdminService adminService;
    public void signup(RegisterRequest registerRequest) {
        UserEntity user = new UserEntity();
        user.setUserName(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodePassword(registerRequest.getPassword()));
        user.setName(registerRequest.getName());
        user.setLastname(registerRequest.getLastname());
        user.setSurname(registerRequest.getSurname());
        user.setPhone(registerRequest.getPhone());
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        userRepository.save(user);
    }

    public void UpdateLoginAndPassword(String username, UserRequestLoginAndPassword userRequestLoginAndPassword){
        UserEntity userEntity = userRepository.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("Користувача не знайдено"));
        PasswordEncoder passencoder = new BCryptPasswordEncoder();
        if(passencoder.matches(userRequestLoginAndPassword.getOldpassword(),userEntity.getPassword())){
            userEntity.setPassword(encodePassword(userRequestLoginAndPassword.getPassword()));
            userEntity.setUserName(userRequestLoginAndPassword.getLogin());
        }

        userRepository.save(userEntity);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        UserEntity user = userRepository.findByUserName(loginRequest.getUsername()).orElseThrow(()->
                new UsernameNotFoundException("Користувача не знайдено"));
        Collection<RoleEntity> role = user.getRoles();
        RoleEntity[] roles = user.getRoles().toArray(new RoleEntity[0]);
        RoleDto[] roleDto = new RoleDto[roles.length];
        for(int i=0;i<roles.length;i++){
            roleDto[i] = adminService.roleEntityToDto(roles[i]);
        }

            return new AuthenticationResponse(authenticationToken, loginRequest.getUsername(),roleDto[0].getName());
    }
}
