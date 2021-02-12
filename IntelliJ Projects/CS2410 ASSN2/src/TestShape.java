public class TestShape {
    public static void main(String[] args) {
        // 8 Shape classes instantiated
        Square sq1 = new Square(5);
        Square sq2 = new Square(10);
        Cube cube1 = new Cube(15);
        Cube cube2 = new Cube(20);
        Triangle tri1 =  new Triangle(3);
        Triangle tri2 =  new Triangle(6);
        Pyramid pyr1 = new Pyramid(12);
        Pyramid pyr2 = new Pyramid(30);

        // Shape Array created
        Shape[] myArray = new Shape[8];

        // All shape objects added to myArray
        myArray[0] = sq1;
        myArray[1] = sq2;
        myArray[2] = cube1;
        myArray[3] = cube2;
        myArray[4] = tri1;
        myArray[5] = tri2;
        myArray[6] = pyr1;
        myArray[7] = pyr2;

        // Prints out all areas of each object
        for (int x = 0; x < myArray.length; x++) {
            System.out.print("Array Object(" + myArray[x].getClass() + "): ");
            System.out.println(myArray[x].getArea());
        }

        System.out.println("\n(Note: All 3D objects areas are actually surface areas.)");
    }
}
