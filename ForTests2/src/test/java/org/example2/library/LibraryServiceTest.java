package org.example2.library;

import org.example2.library.model.Book;
import org.example2.library.repo.BookRepository;
import org.example2.library.service.BookService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LibraryServiceTest {

    @Test
    public void testAddBook(){
        BookRepository repo = new BookRepository();
        BookService service = new BookService(repo);

        Book b = new Book("Title", "Author", 2000);
        Book c = new Book("Title", "Author", 2000);

        assertTrue(service.addBook(b));
        assertTrue(service.addBook(c));
        assertEquals(2, repo.getAll().size());
    }

    @Test
    public void testFinByAuthor(){
        BookRepository repo = new BookRepository();
        BookService service = new BookService(repo);

        repo.add(new Book("A", "A", 2000));
        repo.add(new Book("B", "B", 2001));

        assertEquals(1, service.findByAuthor("B").size());
    }
}
