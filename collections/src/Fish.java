import java.io.Serializable;

public class Fish implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private double weight;
    private int length; //см
    private double price;

    public Fish(String name, double weight, int length, double price){
        this.name = name;
        this.weight = weight;
        this.length =length;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public int getLength() {
        return length;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString(){
        return "Рыба: " + name +". Вес и длина: " + weight+ " кг. ;" + length + "см." + "Цена: " + price + " руб.";
    }


}
