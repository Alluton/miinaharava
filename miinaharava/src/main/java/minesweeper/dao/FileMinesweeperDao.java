package minesweeper.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
    /**
 * Class handles reading and writing results to results.txt file.
 */
public class FileMinesweeperDao {
    public ArrayList<Result> results;
    private String file;
    public FileMinesweeperDao(String file) throws Exception {
        results = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                int mines = Integer.parseInt(parts[2]);
                int time = Integer.parseInt(parts[3]);
                Result result = new Result(x, y, mines, time);
                results.add(result);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }
        /**
 * Writes results to file.
     * @throws java.lang.Exception
 */
    public void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Result result : results) {
                writer.write(result.getX() + ";" + result.getY() + ";" + result.getMines() + ";" + result.getTime() + "\n");
            }
        }
    }
    /**
 * Adds new result to list of results.
 * @param x Grid width.
 * @param y Grid height.
 * @param mines Mine amount.
 * @param time Time in seconds.
 */    
    public void add(int x, int y, int mines, int time) {
        Result result = new Result(x, y, mines, time);
        results.add(result);
    }
        /**
 * @return Returns arrayList containing all results.
 */
    public ArrayList<Result> getAll() {
        return results;
    }
      
}
