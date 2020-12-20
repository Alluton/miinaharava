import java.io.File;
import java.util.ArrayList;
import minesweeper.domain.Grid;
import minesweeper.dao.FileMinesweeperDao;
import minesweeper.dao.Result;
import static org.junit.Assert.*;
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
    public void canMarkMine(){
        grid=new Grid(1,1,0);
        grid.markMine(0, 0);
        assertTrue(grid.hasMineMarked(0, 0)==true);
    }
    @Test
    public void canMarkAndUnMarkMine(){
        grid=new Grid(1,1,0);
        grid.markMine(0, 0);
        assertTrue(grid.hasMineMarked(0, 0)==true);
        grid.unmarkMine(0, 0);
        assertTrue(grid.hasMineMarked(0,0)==false);
    }
    @Test
    public void isVisitedWorks(){
        grid=new Grid(1,1,0);
        assertTrue(grid.isVisited(0, 0)==false);
        grid.generateView(0, 0);
        assertTrue(grid.isVisited(0, 0)==true);
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
    @Test
    public void canReadFile() throws Exception{
        FileMinesweeperDao fileDao = new FileMinesweeperDao("resultsTest.txt");
    }
    @Test
    public void canSave() throws Exception{
        File myObj = new File("resultsTest.txt"); 
        myObj.delete();
        FileMinesweeperDao fileDao = new FileMinesweeperDao("resultsTest.txt");
        fileDao.add(1,1,1,1);
        fileDao.save();
    }
    @Test
    public void canSaveAndRead() throws Exception{
        File myObj = new File("resultsTest.txt"); 
        myObj.delete();
        FileMinesweeperDao fileDao = new FileMinesweeperDao("resultsTest.txt");
        fileDao.add(1,1,1,1);
        fileDao.save();
        fileDao = new FileMinesweeperDao("resultsTest.txt");
        ArrayList<Result> results = fileDao.getAll();
        assertEquals(results.get(0).getX(), 1);
        assertEquals(results.get(0).getY(), 1);
        assertEquals(results.get(0).getMines(), 1);
        assertEquals(results.get(0).getTime(),1);
    }
    @Test
    public void canSaveAndRead2() throws Exception{
        File myObj = new File("resultsTest.txt"); 
        myObj.delete();
        FileMinesweeperDao fileDao = new FileMinesweeperDao("resultsTest.txt");
        fileDao.add(1,1,1,1);
        fileDao.add(1,1,2,2);
        fileDao.add(1,1,1,3);
        fileDao.save();
        fileDao = new FileMinesweeperDao("resultsTest.txt");
        ArrayList<Result> results = new ArrayList<>();
        results=fileDao.getAll();
        assertEquals(results.get(1).getX(), 1);
        assertEquals(results.get(1).getY(), 1);
        assertEquals(results.get(1).getMines(), 2);
        assertEquals(results.get(1).getTime(), 2);
    }
    
}
