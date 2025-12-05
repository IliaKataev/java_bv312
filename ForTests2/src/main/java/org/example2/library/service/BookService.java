package org.example2.library.service;

import org.example2.library.model.Book;
import org.example2.library.repo.BookRepository;
import org.example2.library.util.BookValidator;

import java.util.List;
import java.util.stream.Collectors;

public class BookService {

    private final BookRepository repo;


    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public boolean addBook(Book b){
        if(!BookValidator.isValidTitle(b.getTitle())) return false;
        if(!BookValidator.isValidAuthor(b.getAuthor())) return false;
        if(!BookValidator.isValidYear(b.getYear())) return false;

        repo.add(b);
        return true;
    }

    public List<Book> findByAuthor(String a){
        return repo.getAll().stream()
                .filter(b -> b.getAuthor().equalsIgnoreCase(a))
                .collect(Collectors.toList());
    }
}
