import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("One Piece", "Oda", 1999, 20.99));
        books.add(new Book("Brave New World", "Aldous Huxley", 1932, 12.99));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", 1937, 18.99));

        serialize(books, "books.dat");

        List<Book> loadedBooks = (List<Book>) deserialize("books.dat");

        for( Book b : loadedBooks){
            System.out.println(b);
        }
    }

    public static void serialize(Object obj, String fileName){
        try(FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(obj);
        } catch (FileNotFoundException ex){
            ex.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static Object deserialize(String fileName){
        Object obj = null;
        try(FileInputStream fos = new FileInputStream(fileName);
            ObjectInputStream oos = new ObjectInputStream(fos)){
            obj = oos.readObject();
        } catch (FileNotFoundException ex){
            ex.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return obj;
    }

    public static void FileReaderAndWriter(){
        try(FileReader fr = new FileReader("lines.txt");
            FileWriter fw = new FileWriter("lines1.txt", true);
            BufferedReader br = new BufferedReader(fr)){
            String line;
            int lineCounter = 0;
            while((line = br.readLine()) != null)  {
                if(lineCounter % 2 == 0) {
                    System.out.println(line);
                    fw.write(line+System.lineSeparator());
                }
                lineCounter++;
            }
        } catch (FileNotFoundException ex){
            System.out.println(ex.getMessage());
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void InputStreamExample(){
        InputStream in = null;
        OutputStream out = null;
        byte[] buffer = null;

        try{
            in = new FileInputStream(new File("test.txt"));
            buffer = new byte[in.available()];
            in.read(buffer);
            File file = new File("outputFile.tmp");
            out = new FileOutputStream(file);
            out.write(buffer);
        } catch(FileNotFoundException ex){
            ex.printStackTrace();
        } catch(IOException ex){
            ex.printStackTrace();
        } finally {
            try{
                if(in != null) in.close();
                if(in != null) out.close();
            } catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }

    public static void InputStreamTryWithResourses(){
        try(InputStream in = new FileInputStream("test.txt");
            OutputStream out = new FileOutputStream("outputFile.tmp")) {

            byte[] buffer = new byte[8192];
            int bytesRead;

            while((bytesRead = in.read(buffer)) != -1){
                out.write(buffer, 0, bytesRead);
            }
        } catch (FileNotFoundException ex){
            System.out.println("Файл не найден: "+ex.getMessage());
        } catch (IOException ex){
            System.out.println("Ошибка при работе с файлом: "+ex.getMessage());
        }
    }

    public static void InputStreamTryWithResoursesImage(){
        try(InputStream in = new FileInputStream("1.jpg");
            OutputStream out = new FileOutputStream("outputFile.tmp")) {

            byte[] buffer = new byte[8192];
            int bytesRead;

            while((bytesRead = in.read(buffer)) != -1){
                out.write(buffer, 0, bytesRead);
            }
        } catch (FileNotFoundException ex){
            System.out.println("Файл не найден: "+ex.getMessage());
        } catch (IOException ex){
            System.out.println("Ошибка при работе с файлом: "+ex.getMessage());
        }
    }

}