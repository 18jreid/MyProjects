import java.util.Arrays;

public class PatternBlinker extends Pattern {
    // width and size of pattern
    private int width = 3;
    private int height = 3;

    private boolean[][] pattern = new boolean[width][height];

    PatternBlinker() {
        // sets all cells to false
        for (boolean[] booleans : pattern) {
            Arrays.fill(booleans, false);
        }

        // sets only middle cells to true
        pattern[1][0] = true;
        pattern[1][1] = true;
        pattern[1][2] = true;
    }

    // returns width of pattern
    @Override
    public int getSizeX() {
        return width;
    }

    // returns height of pattern
    @Override
    public int getSizeY() {
        return height;
    }

    // returns state of a specific cell in the pattern
    @Override
    public boolean getCell(int x, int y) {
        return pattern[x][y];
    }
}
