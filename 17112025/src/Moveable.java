public interface Moveable {
    int defaultX = 0, defaultY = 0;

    void moveRight();
    void moveLeft();
    void moveUp();
    void moveDown();
    void moveToDefault();
}
