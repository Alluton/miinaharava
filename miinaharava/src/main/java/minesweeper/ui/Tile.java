
package minesweeper.ui;


import javafx.scene.control.Button;


public class Tile extends Button {
    int x;
    int y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
