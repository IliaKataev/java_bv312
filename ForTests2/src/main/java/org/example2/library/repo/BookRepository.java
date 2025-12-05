package org.example2.library.repo;

import org.example2.library.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private final List<Book> storage = new ArrayList<>();

    public void add(Book b){storage.add(b);};
    public void remove(Book b){storage.remove(b);};
    public List<Book> getAll(){return new ArrayList<>(storage);};
}
