import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import minesweeper.domain.Grid;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
public class minesweeperTest {
    Grid grid;
    @Before
    public void setUp(){
        grid=new Grid();
        grid.createGrid(2, 0);
    }
    @Test
    public void gridExists() {
        assertTrue(grid!=null);      
    }
    @Test
    public void gridHasMines(){
        grid.putMine(0,0);
        assertTrue(grid.hasMine(0, 0)==true);
    }
    @Test
    public void canWin(){
        grid.generateView(0, 0);
        assertTrue(grid.checkWin()==true);
    }

   
    
}
