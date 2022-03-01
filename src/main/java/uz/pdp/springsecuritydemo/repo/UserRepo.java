package uz.pdp.springsecuritydemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecuritydemo.entity.User;

import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
    User findByPhoneNumberOrEmail(String phoneNumber, String email);
}
