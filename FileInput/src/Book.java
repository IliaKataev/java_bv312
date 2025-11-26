import java.io.*;

public class Book implements Externalizable {
    private static final long serialVersionUID =1L;

    private String title;
    private String author;
    private int year;
    private double price;


    public Book(String title, String author, int year, double price) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(title);
        out.writeUTF(author);
        out.writeInt(year);
        out.writeDouble(price);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        title = in.readUTF();
        author = in.readUTF();
        year = in.readInt();
        price = in.readDouble();
    }
}
