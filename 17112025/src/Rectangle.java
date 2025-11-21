public class Rectangle extends Shape {
    protected int x;
    protected int y;
    protected int x1;
    protected int y1;

    public Rectangle(int x, int y, int x1, int y1, String color) {
        super(color);
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
    }


    public void draw() {
        System.out.println("рисуем прямоугольник с характеристиками: x="+x+"; y= "+y+"; x1="+x1+"; y1= "+y1+"; color= "+super.color);
    }
}
