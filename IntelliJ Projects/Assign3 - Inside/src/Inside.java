public class Inside {
    public static void main(String[] args) {
        // All points given by professor
        double[] ptX = {1, 2, 3, 4};
        double[] ptY = {1, 2, 3, 4};
        double[] circleX = {0, 5};
        double[] circleY = {0, 5};
        double[] circleRadius = {3, 3};
        double[] rectLeft = {-2.5, -2.5};
        double[] rectTop = {2.5, 5.0};
        double[] rectWidth = {6.0, 5.0};
        double[] rectHeight = {5.0, 2.5};

        // Check all circles with all points
        System.out.println("\t\t--- Report of Points and Circles ---\n");

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (isPointInsideCircle(ptX[j], ptY[j], circleX[i], circleY[i], circleRadius[i])) {
                    reportPoint(ptX[j], ptY[j]);
                    System.out.print(" is inside ");
                    reportCircle(circleX[i], circleY[i], circleRadius[i]);
                    System.out.println();
                }

                else {
                    reportPoint(ptX[j], ptY[j]);
                    System.out.print(" is outside ");
                    reportCircle(circleX[i], circleY[i], circleRadius[i]);
                    System.out.println();
                }
            }
        }

        System.out.println();

        // Check all rectangles with all points
        System.out.println("\t\t--- Report of Points and Rectangles ---\n");

        for (int k = 0; k < 2; k++) {
            for (int l = 0; l < 4; l++) {
                if (isPointInsideRectangle(ptX[l], ptY[l], rectLeft[k], rectTop[k], rectWidth[k], rectHeight[k])) {
                    reportPoint(ptX[l], ptY[l]);
                    System.out.print(" is inside ");
                    reportRectangle(rectLeft[k], rectTop[k], rectWidth[k], rectHeight[k]);
                    System.out.println();
                }

                else {
                    reportPoint(ptX[l], ptY[l]);
                    System.out.print(" is outside ");
                    reportRectangle(rectLeft[k], rectTop[k], rectWidth[k], rectHeight[k]);
                    System.out.println();
                }
            }
        }
    }

    // Reports point specified
    private static void reportPoint(double x, double y) {
        System.out.print("Point(" + x + ", " + y + ")");
    }

    // Reports circle specified
    private static void reportCircle(double x, double y, double r) {
        System.out.print("Circle(" + x + ", " + y + ") Radius: " + r);
    }

    // Reports rectangle specified
    private static void reportRectangle(double left, double top, double width, double height) {
        System.out.print("Rectangle(" + left + ", " + top + ", " + (left + width) + ", " + (top - height) + ")");
    }

    // Finds if point is inside circle
    static boolean isPointInsideCircle(double ptX, double ptY, double circleX, double circleY, double circleRadius) {
        boolean output;
        output = Math.pow((ptX - circleX), 2) + Math.pow((ptY - circleY), 2) <= Math.pow((circleRadius), 2);

        return output;
    }

    // Finds if point is inside rectangle
    static boolean isPointInsideRectangle(double ptX, double ptY, double rLeft, double rTop, double rWidth, double rHeight) {
        boolean output;
        output = ptX <= rLeft + rWidth && ptX >= rLeft && ptY <= rTop && ptY >= rTop - rHeight;

        return output;
    }
}
