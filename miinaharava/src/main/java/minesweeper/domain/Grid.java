package minesweeper.domain;

import java.util.Random;
//0 in grid means no mine, 1 means mine
public class Grid {
    private int[][] grid;
    private int[][] view;
    private int xSize;
    private int ySize;
 /**
 *Array of revealed tiles in grid. 
 */
    public int[][] visited;
    int minesMarked = 0;
    int tilesRevealed = 0;
    int minesAmount;
    /**
 * Constructor creates grid according to user input
 * Mines placed randomly according to java.util.random.
 *
 * @param xSize User input for grid width.
 * @param ySize User input for grid height.
 * @param minesAmount User input for mine amount.
 */
    public Grid(int xSize, int ySize, int minesAmount) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.minesAmount = minesAmount;
        grid = new int[xSize][ySize];
        view = new int[xSize][ySize];
        visited = new int[xSize][ySize];
        Random rand = new Random();
        int count = 0;
        while (count < minesAmount) {
            int x = rand.nextInt(xSize);
            int y = rand.nextInt(ySize);
            if (grid[x][y] == 0) {
                grid[x][y] = 1;
                count++;
            }
        }
    }
 /**
 * Checks tile of coordinates (x,y) for mine.
 *
 * @param x x coordinate of grid.
 * @param y y coordinate of grid.
 *
 * @return true if tile contains mine, false otherwise.
 */
    public boolean hasMine(int x, int y) {
        return grid[x][y] == 1;
    }
 /**
 * Method calculates the effects of user clicking a tile (x,y).
 * Arrays visited and view are updated accordingly.
 * 
 * @param x x coordinate of grid.
 * @param y y coordinate of grid.
 *
 */
    public void generateView(int x, int y) {
        searchGrid(x, y);
    }
 /**
 * Checks if game has been won by comparing the sum of tiles revealed and mines to the size of the grid.
 *
 * @return true if game has been won, false otherwise.
 */
    public boolean checkWin() {
        return tilesRevealed + minesAmount == xSize * ySize;
    }
 /**
 *Returns view array.
 * @return Returns view array.
 */
    public int[][] getView() {
        return view;
    }
 /**
 * Checks tile of coordinates (x,y) if it has been revealed yet.
 *
 * @param x x coordinate of grid.
 * @param y y coordinate of grid.
 *
 * @return true if tile has been visited, false otherwise.
 */
    public boolean isVisited(int x, int y) {
        return visited[x][y] == 1;
    }
    private void searchGrid(int x, int y) {
        if (x >= xSize || y >= ySize || x < 0 || y < 0) {
            return;
        }
        if (grid[x][y] == 1 || visited[x][y] == 1 || view[x][y] == -1) {
            return;
        }
        visited[x][y] = 1;
        tilesRevealed++;
        int count = 0;
        if (x + 1 < xSize) {
            if (grid[x + 1][y] == 1) {
                count++;
            }
        }
        if (x - 1 >= 0) {
            if (grid[x - 1][y] == 1) {
                count++;
            }
        }
        if (y + 1 < ySize) {
            if (grid[x][y + 1] == 1) {
                count++;
            }
        }
        if (y - 1 >= 0) {
            if (grid[x][y - 1] == 1) {
                count++;
            }
        }
        if (x + 1 < xSize && y + 1 < ySize) {
            if (grid[x + 1][y + 1] == 1) {
                count++;
            }
        }
        if (x + 1 < xSize && y - 1 >= 0) {
            if (grid[x + 1][y - 1] == 1) {
                count++;
            }
        }
        if (x - 1 >= 0 && y + 1 < ySize) {
            if (grid[x - 1][y + 1] == 1) {
                count++;
            }
        }
        if (x - 1 >= 0 && y - 1 >= 0) {
            if (grid[x - 1][y - 1] == 1) {
                count++;
            }
        }
        view[x][y] = count;
        searchGrid(x + 1, y);
        searchGrid(x - 1, y);
        searchGrid(x, y + 1);
        searchGrid(x, y - 1);
    }
 /**
 * Puts mine to tile (x,y). NOTE: Method is only intended for testing purposes.
 *
 * @param x x coordinate of grid.
 * @param y y coordinate of grid.
 */
    public void putMine(int x, int y) {
        grid[x][y] = 1;
        minesAmount++;
    }
}
