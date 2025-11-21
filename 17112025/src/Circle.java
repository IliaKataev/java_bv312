public class Circle extends Shape implements Moveable{

    protected int x,y,r;
    public Circle(int x, int y, int r, String color){
        super(color);
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public void draw() {
        System.out.println("рисуем круг с характеристиками: x="+x+"; y= "+y+"; radius= " +r+"; color= "+super.color);
    }


    public void moveRight() {
        x+=1;
    }


    public void moveLeft() {
        x-=1;
    }

    public void moveUp() {
        y+=1;
    }

    public void moveDown() {
        y-=1;
    }

    public void moveToDefault() {
        x = defaultX;
        y = defaultY;
    }
}
