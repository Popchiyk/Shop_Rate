package WebDiplom.InfoPage.config;

import WebDiplom.InfoPage.models.Role;
import WebDiplom.InfoPage.models.User;
import WebDiplom.InfoPage.repository.IRoleRepository;
import WebDiplom.InfoPage.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public SetupDataLoader(IUserRepository userRepository, IRoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        createRoles();
        createAdminUser();
        alreadySetup = true;
    }

    private void createRoles() {
        if(roleRepository.findByName(Role.ERole.ROLE_ADMIN).isPresent()){
            return;
        }
        roleRepository.save(new Role(Role.ERole.ROLE_ADMIN));
        roleRepository.save(new Role(Role.ERole.ROLE_USER));
    }

    private void createAdminUser() {
        if (userRepository.findByUserName("Admin").isPresent()) {
            return;
        }
        Role adminRole = roleRepository.findByName(Role.ERole.ROLE_ADMIN).orElse(null);
        User userEntity = new User();
        userEntity.setUserName("Admin");
        userEntity.setPassword(passwordEncoder.encode("admin"));
        userEntity.setRoles(Collections.singleton(adminRole));
        userRepository.save(userEntity);
    }
}
