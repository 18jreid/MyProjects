import java.util.Arrays;

public class PatternBlock extends Pattern {
    // width and size of pattern
    private int width = 2;
    private int height = 2;

    private boolean[][] pattern = new boolean[width][height];

    PatternBlock() {
        // fills entire pattern (because it's just a cube)
        for (boolean[] booleans : pattern) {
            Arrays.fill(booleans, true);
        }
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
