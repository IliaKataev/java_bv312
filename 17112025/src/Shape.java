public abstract class Shape {
    protected String color = "red";
    public abstract void draw();
    public Shape(String color){
        this.color = color;
    }
}
