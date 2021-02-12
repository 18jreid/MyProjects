import java.util.Arrays;

public class PatternAcorn extends Pattern {
    private int width = 9;
    private int height = 5;

    private boolean[][] pattern = new boolean[width][height];

    PatternAcorn() {
        for (boolean[] booleans : pattern) {
            Arrays.fill(booleans, false);
        }

        pattern[1][3] = true;
        pattern[2][1] = true;
        pattern[2][3] = true;
        pattern[4][2] = true;
        pattern[5][3] = true;
        pattern[6][3] = true;
        pattern[7][3] = true;

    }

    @Override
    public int getSizeX() {
        return width;
    }

    @Override
    public int getSizeY() {
        return height;
    }

    @Override
    public boolean getCell(int x, int y) {
        return pattern[x][y];
    }
}
