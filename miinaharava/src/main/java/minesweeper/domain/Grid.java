package minesweeper.domain;

import java.util.Random;
//0 in grid means no mine, 1 means mine
public class Grid {
    public int[][] grid;
    public int[][] view;
    public int size;
    public int[][] visited;
    int minesMarked = 0;
    int tilesRevealed = 0;
    int minesAmount;
    public void createGrid(int size, int minesAmount) {
        this.size = size;
        this.minesAmount = minesAmount;
        grid = new int[size][size];
        view = new int[size][size];
        visited = new int[size][size];
        Random rand = new Random();
        int count = 0;
        while (count < minesAmount) {
            int x = rand.nextInt(size);
            int y = rand.nextInt(size);
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
    public int getSize() {
        return size;
    }

    public void generateView(int x, int y) {
        searchGrid(x, y);
        printView();
    }
    public void markMine(int x, int y) {
        view[x][y] = -1;
        printView();
        minesMarked++;
    }
    public boolean checkWin() {
        return tilesRevealed + minesAmount == size * size;
    }
    public void printView() {
        System.out.println("Current grid:");
        System.out.println("* indicates unknown tile, number indicates amount of mines adjacent to the tile and ! indicates marked mine");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
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
        if (x >= size || y >= size || x < 0 || y < 0) {
            return;
        }
        if (grid[x][y] == 1 || visited[x][y] == 1 || view[x][y] == -1) {
            return;
        }
        visited[x][y] = 1;
        tilesRevealed++;
        int count = 0;
        if (x + 1 < size) {
            if (grid[x + 1][y] == 1) {
                count++;
            }
        }
        if (x - 1 >= 0) {
            if (grid[x - 1][y] == 1) {
                count++;
            }
        }
        if (y + 1 < size) {
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
