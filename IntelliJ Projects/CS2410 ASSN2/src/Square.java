public class Square extends Shape {
    private double side;

    // Constructor to set the correct side
    Square(double side) {
        setSide(side);
    }

    // Sets side
    public void setSide(double side) {
        this.side = side;
    }

    // Gets side length
    public double getSide() {
        return this.side;
    }

    // Returns area of square
    @Override
    public double getArea() {
        return getSide() * getSide();
    }
}
