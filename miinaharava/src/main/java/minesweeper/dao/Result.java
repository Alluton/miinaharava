
package minesweeper.dao;
public class Result {
    private int x;
    private int y;
    private int mines;
    private int time;
    public Result(int x, int y, int mines, int time) {
        this.x = x;
        this.y = y;
        this.mines = mines;
        this.time = time;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getMines() {
        return mines;
    }
    public int getTime() {
        return time;
    }
}
