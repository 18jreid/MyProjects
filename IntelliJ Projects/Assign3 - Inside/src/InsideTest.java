import org.junit.Assert;
import java.util.ArrayList;

class Pair<X, Y> {
    public X x;
    public Y y;
    public Pair(X x, Y y) {
        this.x = x;
        this.y = y;
    }
}

/**
 * Unit tests for the isPointInsideCircle and isPointInsideRectangle methods.
 *
 * @author Brandon Holdaway
 */
public class InsideTest {
    /**
     * @brief Test if a point is inside of a circle
     */
    @org.junit.Test
    public void isPointInsideCircle() {
        double circleX = 2.3;
        double circleY = 4.2;
        double circleR = 5.1;
        ArrayList<Pair<Pair<Double, Double>, Boolean>> pointList = new ArrayList<Pair<Pair<Double, Double>, Boolean>>();
        pointList.add(new Pair<>(new Pair<>(1.123, 2.234), true));
        pointList.add(new Pair<>(new Pair<>(2.123, 3.234), true));
        pointList.add(new Pair<>(new Pair<>(3.123, 4.234), true));
        pointList.add(new Pair<>(new Pair<>(4.123, 5.234), true));
        pointList.add(new Pair<>(new Pair<>(5.123, 6.234), true));
        pointList.add(new Pair<>(new Pair<>(6.123, 7.234), true));
        pointList.add(new Pair<>(new Pair<>(7.123, 8.234), false));
        pointList.add(new Pair<>(new Pair<>(8.123, 9.234), false));
        pointList.add(new Pair<>(new Pair<>(9.123, 10.234), false));
        pointList.add(new Pair<>(new Pair<>(10.123, 111.234), false));

        pointList.add(new Pair<>(new Pair<>(-2.8, 4.2), true));
        pointList.add(new Pair<>(new Pair<>(7.3999, 4.2), true));
        pointList.add(new Pair<>(new Pair<>(2.3, 9.2999), true));
        pointList.add(new Pair<>(new Pair<>(2.3, -0.8999), true));

        for (Pair<Pair<Double, Double>, Boolean> item : pointList)
        {
            System.out.printf("Circle - testing point (%.2f,%.2f)\n", item.x.x, item.x.y);
            Assert.assertEquals(item.y, Inside.isPointInsideCircle(item.x.x, item.x.y, circleX, circleY, circleR));
        }
    }

    /**
     * @brief Test if a point is inside of a rectangle
     */
    @org.junit.Test
    public void isPointInsideRectangle() {
        double rLeft = 3.2;
        double rTop = 2.4;
        double width = 1.5;
        double height = 7.2;
        ArrayList<Pair<Pair<Double, Double>, Boolean>> pointList = new ArrayList<Pair<Pair<Double, Double>, Boolean>>();
        pointList.add(new Pair<>(new Pair<>(1.123, 2.234), false));
        pointList.add(new Pair<>(new Pair<>(2.123, 3.234), false));
        pointList.add(new Pair<>(new Pair<>(3.123, 4.234), false));
        pointList.add(new Pair<>(new Pair<>(4.123, 5.234), false));
        pointList.add(new Pair<>(new Pair<>(5.123, 6.234), false));
        pointList.add(new Pair<>(new Pair<>(6.123, 7.234), false));
        pointList.add(new Pair<>(new Pair<>(7.123, 8.234), false));
        pointList.add(new Pair<>(new Pair<>(8.123, 9.234), false));
        pointList.add(new Pair<>(new Pair<>(9.123, 10.234), false));
        pointList.add(new Pair<>(new Pair<>(10.123, 111.234), false));

        pointList.add(new Pair<>(new Pair<>(3.2, 2.4), true));
        pointList.add(new Pair<>(new Pair<>(4.7, 2.4), true));
        pointList.add(new Pair<>(new Pair<>(3.2, -4.8), true));
        pointList.add(new Pair<>(new Pair<>(4.7, -4.8), true));

        for (Pair<Pair<Double, Double>, Boolean> item : pointList)
        {
            System.out.printf("Rectangle - testing point (%.2f,%.2f)\n", item.x.x, item.x.y);
            Assert.assertEquals(item.y, Inside.isPointInsideRectangle(item.x.x, item.x.y, rLeft, rTop, width, height));
        }
    }
}
