package org.example2.library;

import org.example2.library.util.BookValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParameterizedValidationTest {

    public ParameterizedValidationTest(String author, boolean result) {
        this.author = author;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Object[][] date(){
        return new Object[][]{
                {"alex", true},
                {"John Smith", true},
                {"123456", false},
                {"", false}
        };
    }

    private final String author;
    private final boolean result;

    @Test
    public void testAuthor(){
        assertEquals(result, BookValidator.isValidAuthor(author));
    }



}
