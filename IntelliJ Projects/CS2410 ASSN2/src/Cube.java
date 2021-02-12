public class Cube extends Shape {
    private double side;

    // Constructor that sets side length
    Cube(double side) {
        setSide(side);
    }

    // Sets side length
    public void setSide(double side) {
        this.side = side;
    }

    // Gets side length
    public double getSide() {
        return this.side;
    }

    // Returns area of Cube
    @Override
    public double getArea() {
        return 6 * getSide() * getSide();
    }
}
