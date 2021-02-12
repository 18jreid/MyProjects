import java.util.Arrays;

public class LifeSimulator {
    private int sizeX;
    private int sizeY;

    private Boolean[][] grid;

    public LifeSimulator(int sizeX, int sizeY) {
        // Sets grid size to parameters given
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        grid = new Boolean[sizeX][sizeY];

        // sets all cells to false
        for (Boolean[] booleans : grid) {
            Arrays.fill(booleans, false);
        }
    }

    // Returns grids width
    public int getSizeX() {
        return sizeX;
    }

    // Returns grids height
    public int getSizeY() {
        return sizeY;
    }

    // returns specific cell in grid
    public boolean getCell(int x, int y) {
        return grid[x][y];
    }

    // inserts specific pattern into the grid
    public void insertPattern(Pattern pattern, int startX, int startY) {
        int temp = startX;

        for (int y = 0; y < pattern.getSizeY(); y++) {
            startX = temp;
            for (int x = 0; x < pattern.getSizeX(); x++) {
                grid[startX][startY] = pattern.getCell(x, y);

                startX++;
            }
            startY++;
        }

    }

    public void update() {
        rules();
    }

    private void rules() {
        for (int y = 1; y < getSizeY() - 1; y++) {
            for (int x = 1; x < getSizeX() - 1; x++) {
                if (grid[x][y]) {
                    System.out.println();
                    System.out.println(grid[x - 1][y]);
                    System.out.println(grid[x + 1][y]);
                }
            }
        }
    }
}
