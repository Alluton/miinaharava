package minesweeper.UI;

import static java.lang.Integer.parseInt;
import java.util.Scanner;
import minesweeper.Grid;
public class UI{
    static Grid grid=new Grid();
    public static void main(String[] args) {
        //launch(args);
        Scanner scanner =new Scanner(System.in);
        System.out.println("Enter grid size:");
        int size=parseInt(scanner.nextLine());
        System.out.println("Enter mine amount:");
        int amount=parseInt(scanner.nextLine());
        grid.createGrid(size, amount);
        boolean lost=false;
        while(true){
            System.out.println("Press 1 to click a tile, press 2 to mark mine location");
            int mode=parseInt(scanner.nextLine());
            if(mode==1){
                System.out.println("Enter x coordinate to click: ");
                int x=parseInt(scanner.nextLine())-1;
                System.out.println("Enter y coordinate to click: ");
                int y=parseInt(scanner.nextLine())-1;
                if(grid.hasMine(x, y)){
                    System.out.println("You hit a mine! Game over.");
                    lost=true;
                    break;
                }
                grid.generateView(x, y);
            }else{
                System.out.println("Enter x coordinate to mark mine at: ");
                int x=parseInt(scanner.nextLine())-1;
                System.out.println("Enter y coordinate to mark mine at: ");
                int y=parseInt(scanner.nextLine())-1;
                grid.markMine(x, y);
            }
            if(grid.checkWin()==true){
                break;
            }
        }
        if(lost==false){
            System.out.println("Congratulations: You won the game!");
        }
    }
    
}
