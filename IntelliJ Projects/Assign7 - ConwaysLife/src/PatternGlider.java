import java.util.Arrays;

public class PatternGlider extends Pattern {
    // width and size of pattern
    private int width = 6;
    private int height = 6;
    private int phase = 1;

    private boolean[][] pattern = new boolean[width][height];

    public PatternGlider() {
        // sets all cells to false
        for (boolean[] booleans : pattern) {
            Arrays.fill(booleans, false);
        }

        pattern[1][2] = true;
        pattern[2][3] = true;
        pattern[3][1] = true;
        pattern[3][2] = true;
        pattern[3][3] = true;
    }

    // returns width
    @Override
    public int getSizeX() {
        return width;
    }

    // returns height
    @Override
    public int getSizeY() {
        return height;
    }

    // returns a specific cell
    @Override
    public boolean getCell(int x, int y) {
        return pattern[x][y];
    }
}
