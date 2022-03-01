package uz.pdp.springsecuritydemo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.springsecuritydemo.entity.Role;
import uz.pdp.springsecuritydemo.entity.User;
import uz.pdp.springsecuritydemo.entity.enums.RoleName;
import uz.pdp.springsecuritydemo.repo.RoleRepo;
import uz.pdp.springsecuritydemo.repo.UserRepo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = roleRepo.save(new Role(RoleName.ROLE_ADMIN));
        Role roleCustomer = roleRepo.save(new Role(RoleName.ROLE_CUSTOMER));
        userRepo.save(new User(
                "Admin FIO",
                "+998993632587",
                "alisherabuhabiba@gmail.com",
                passwordEncoder.encode("root123"),
                new HashSet<>(List.of(roleAdmin))
        ));
        userRepo.save(new User(
                "Customer FIO",
                "+998993638725",
                "alisherabuhabiba@mail.ru",
                passwordEncoder.encode("root123"),
                new HashSet<>(List.of(roleCustomer))
        ));
    }
}
