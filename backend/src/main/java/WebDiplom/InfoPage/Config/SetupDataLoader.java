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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;

    @Autowired
    private IUserRepository IUserRepository;

    @Autowired
    private IRoleRepository IRoleRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        createRoles();

        if (!IUserRepository.findByUserName("Admin").isPresent()) {
            Role adminRole = IRoleRepository.findByName(Role.ERole.ROLE_ADMIN).orElse(null);
            User userEntity = new User();
            userEntity.setUserName("Admin");
            userEntity.setPassword(passwordEncoder.encode("admin"));
            userEntity.setRoles(new HashSet<>(Arrays.asList(adminRole)));
            IUserRepository.save(userEntity);
        }
        alreadySetup = true;
    }


    @Transactional
    void createRoles() {
        Role admin = new Role(Role.ERole.ROLE_ADMIN);
        Role user = new Role(Role.ERole.ROLE_USER);
        IRoleRepository.save(admin);
        IRoleRepository.save(user);
    }
}
