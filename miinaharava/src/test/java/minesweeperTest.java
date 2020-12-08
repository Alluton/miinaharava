import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import minesweeper.domain.Grid;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
public class minesweeperTest {
    Grid grid;
    @Test
    public void gridExists() {
        grid=new Grid(1,1,0);
        assertTrue(grid!=null);      
    }
    @Test
    public void gridHasMines(){
        grid=new Grid(1,1,1);
        assertTrue(grid.hasMine(0, 0)==true);
    }
    @Test
    public void gridHasMines2(){
        grid=new Grid(10,10,100);
        assertTrue(grid.hasMine(7,7)==true);
    }
    @Test
    public void canGenerateView(){
        grid=new Grid(1,1,0);
        grid.generateView(0, 0);
    }
    @Test
    public void canGenerateView2(){
        grid=new Grid(10,10,10);
        int x=0;
        int y=0;
        while(true){
            if(!grid.hasMine(x, y)){
                grid.generateView(x, y);
                break;
            }
            x++;
            if(x>5){
                x=0;
                y++;
            }
        }
    }
    @Test
    public void canWin(){
        grid=new Grid(1,1,0);
        grid.generateView(0, 0);
        assertTrue(grid.checkWin()==true);
    }
    @Test
    public void noTooEarlyWin(){
        grid=new Grid(1,1,0);
        assertTrue(grid.checkWin()==false);
    }
    @Test
    public void canWin2(){
        grid=new Grid(3,3,0);
        grid.putMine(0,1);
        grid.putMine(1,1);
        grid.putMine(2,1);
        assertTrue(grid.checkWin()==false);
        grid.generateView(0, 0);
        grid.generateView(1, 0);
        grid.generateView(2, 0);
        assertTrue(grid.checkWin()==false);
        grid.generateView(0,2);
        grid.generateView(1,2);
        grid.generateView(2,2);
        assertTrue(grid.checkWin()==true);
    }

   
    
}
