package org.example2.library;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runners.Suite;

import java.io.File;
import java.io.PrintWriter;

import static org.junit.Assert.assertTrue;

public class FileImportExportTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testWriteFile() throws Exception{
        File file = folder.newFile("books.txt");

        try(PrintWriter pw = new PrintWriter(file)){
            pw.println("Hello world!");
        }

        assertTrue(file.length() > 0);
    }
}
