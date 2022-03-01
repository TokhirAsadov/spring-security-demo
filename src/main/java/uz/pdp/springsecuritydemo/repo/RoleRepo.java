package uz.pdp.springsecuritydemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecuritydemo.entity.Role;

public interface RoleRepo extends JpaRepository<Role,Long> {
}
