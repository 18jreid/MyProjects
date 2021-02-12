public class Pyramid extends Shape {
    private double side;

    // Constructor sets side length
    Pyramid(double side) {
        setSide(side);
    }

    // Sets side length
    public void setSide(double side) {
        this.side = side;
    }

    // Get side length
    public double getSide() {
        return this.side;
    }

    // Returns area of Pyramid
    @Override
    public double getArea() {
        return ((Math.sqrt(3)) / 4) * Math.pow(getSide(), 2) * 4;
    }
}
