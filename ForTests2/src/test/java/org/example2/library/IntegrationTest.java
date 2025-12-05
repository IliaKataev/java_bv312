package org.example2.library;

import org.example2.library.model.Book;
import org.example2.library.repo.BookRepository;
import org.example2.library.service.BookService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {
    @Test
    public void testFullScenario(){
        BookRepository repo = new BookRepository();
        BookService service = new BookService(repo);

        service.addBook(new Book("T1", "Alex", 2001));
        service.addBook(new Book("T2", "Alex", 2002));

        assertEquals(2, service.findByAuthor("Alex").size());
    }
}
