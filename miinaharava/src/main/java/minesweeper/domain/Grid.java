package minesweeper.domain;

import java.util.Random;
//0 in grid means no mine, 1 means mine
public class Grid {
    private int[][] grid;
    private int[][] view;
    private int xSize;
    private int ySize;
    public int[][] visited;
    int minesMarked = 0;
    int tilesRevealed = 0;
    int minesAmount;
    public void createGrid(int xSize, int ySize, int minesAmount) {
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
    public boolean hasMine(int x, int y) {
        return grid[x][y] == 1;
    }
    public int[][] returnGrid() {
        return grid;
    }
    public void generateView(int x, int y) {
        searchGrid(x, y);
        //printView();
    }
    public void markMine(int x, int y) {
        view[x][y] = -1;
        printView();
        minesMarked++;
    }
    public boolean checkWin() {
        return tilesRevealed + minesAmount == xSize * ySize;
    }
    public int[][] getView() {
        return view;
    }
    public boolean isVisited(int x,int y) {
        return visited[x][y] == 1;
    }
    public void printView() {
        System.out.println("Current grid:");
        System.out.println("* indicates unknown tile, number indicates amount of mines adjacent to the tile and ! indicates marked mine");
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                if (view[i][j] > 0 || (view[i][j] == 0 && visited[i][j] == 1)) {
                    System.out.print(view[i][j]);
                } else if (view[i][j] == -1) {
                    System.out.print("!");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println("    ");
        }
    }
    public void searchGrid(int x, int y) {
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
        view[x][y] = count;
        searchGrid(x + 1, y);
        searchGrid(x - 1, y);
        searchGrid(x, y + 1);
        searchGrid(x, y - 1);
    }
    //following method is only for testing
    public void putMine(int x, int y) {
        grid[x][y] = 1;
    }
}
