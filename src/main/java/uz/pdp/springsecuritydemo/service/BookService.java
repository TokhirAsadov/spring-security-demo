package uz.pdp.springsecuritydemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecuritydemo.entity.Book;
import uz.pdp.springsecuritydemo.payload.ApiResponse;
import uz.pdp.springsecuritydemo.payload.BookDto;
import uz.pdp.springsecuritydemo.repo.BookRepo;

@Service
public class BookService {

    @Autowired
    BookRepo bookRepo;

    public ApiResponse save(BookDto dto) {
        Book book=new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPrice(dto.getPrice());
        bookRepo.save(book);
        return new ApiResponse(true,"Saved");
    }
}
