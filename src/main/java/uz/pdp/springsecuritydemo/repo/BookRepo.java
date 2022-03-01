package uz.pdp.springsecuritydemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecuritydemo.entity.Book;

import java.util.UUID;

public interface BookRepo extends JpaRepository<Book, UUID> {

}
