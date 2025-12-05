package org.example2.library;

import org.example2.library.model.Book;
import org.example2.library.repo.BookRepository;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookRepositoryTest {
    @Test
    public void testAddAndRemove(){
        BookRepository repo = new BookRepository();
        Book b = new Book("A", "B", 2000);

        repo.add(b);
        assertEquals(1, repo.getAll().size());

        repo.remove(b);
        assertEquals(0, repo.getAll().size());
    }



}
