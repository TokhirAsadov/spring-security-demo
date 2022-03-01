package uz.pdp.springsecuritydemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.springsecuritydemo.payload.BookDto;
import uz.pdp.springsecuritydemo.service.BookService;
import uz.pdp.springsecuritydemo.service.FirstControlSecurityService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    FirstControlSecurityService securityService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/save")
    public HttpEntity<?> saveBook(@RequestBody BookDto dto, HttpServletRequest httpServletRequest){
        return ResponseEntity.status(201).body(bookService.save(dto));
    }
}
