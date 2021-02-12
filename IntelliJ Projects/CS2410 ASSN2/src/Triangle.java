public class Triangle extends Shape {
    private double side;

    // Construct sets side length
    Triangle(double side) {
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

    // Returns area of Triangle
    @Override
    public double getArea() {
        return ((Math.sqrt(3)) / 4) * Math.pow(getSide(), 2);
    }
}
