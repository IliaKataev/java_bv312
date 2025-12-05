package org.example2.library;

import org.example2.library.util.BookValidator;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookValidatorTest {

    @Test
    public void testValidTitle(){
        assertTrue(BookValidator.isValidTitle("Some Book"));
    }

    @Test
    public void testValidAuthor(){
        assertTrue(BookValidator.isValidAuthor("au"));
    }

    @Test
    public void testValidYear(){
        assertTrue(BookValidator.isValidYear(1999));
        assertFalse(BookValidator.isValidYear(500));
    }
}
