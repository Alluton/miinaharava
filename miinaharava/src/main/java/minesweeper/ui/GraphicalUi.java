package minesweeper.ui;
import java.util.ArrayList;
import java.util.Collections;
import javafx.geometry.Insets;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import minesweeper.domain.Grid;
import minesweeper.dao.FileMinesweeperDao;
import minesweeper.dao.Result;

public class GraphicalUi extends Application{
    Grid grid;
    Tile[][] tiles;
    private int xSize;
    private int ySize;
    private int minesCount;
    private Timeline timeline;
    private int seconds;
    private Label timeElapsed=new Label("0");
    FileMinesweeperDao fileDao;
    /**
    * Opens dialog for player to input grid size and mine count. Validates that integer values are inputted.
    */
    public void Dialog(){
        Dialog<ArrayList<String>> dialog = new Dialog<>();
        dialog.setTitle("Settings");
        dialog.setContentText("Please enter game settings:");
        ButtonType startButtonType = new ButtonType("Start", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(startButtonType, ButtonType.CANCEL);
        GridPane settings = new GridPane();
        settings.setHgap(10);
        settings.setVgap(10);
        settings.setPadding(new Insets(20, 150, 10, 10));

        TextField horizontalSize = new TextField();
        horizontalSize.setPromptText("Horizontal size of grid ");
        TextField verticalSize = new TextField();
        verticalSize.setPromptText("Vertical size of grid ");
        TextField mineCount = new TextField();
        mineCount.setPromptText("Amount of mines ");
        
        horizontalSize.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        verticalSize.setTextFormatter(new TextFormatter<>(new IntegerStringConverter())); 
        mineCount.setTextFormatter(new TextFormatter<>(new IntegerStringConverter())); 
        
        settings.add(new Label("Horizontal size:"), 0, 0);
        settings.add(horizontalSize, 1, 0);
        settings.add(new Label("Vertical size: "), 0, 1);
        settings.add(verticalSize, 1, 1);
        settings.add(new Label("Mine amount: :"), 0, 2);
        settings.add(mineCount, 1, 2);
        
        dialog.getDialogPane().setContent(settings);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == startButtonType) {
                ArrayList<String> list= new ArrayList<>();
                list.add(horizontalSize.getText());
                list.add(verticalSize.getText());
                list.add(mineCount.getText());
                return list;
            }else{
                try {
                    fileDao.save();
                } catch (Exception ex) {
                    Logger.getLogger(GraphicalUi.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
            return null;
        });
        Optional<ArrayList<String>> result = dialog.showAndWait();
        result.ifPresent(xSizeySize -> {
            if(!xSizeySize.get(0).equals("")&&!xSizeySize.get(1).equals("")&&!xSizeySize.get(2).equals("")){
                xSize=Integer.parseInt(xSizeySize.get(0));
                ySize=Integer.parseInt(xSizeySize.get(1));
                minesCount=Integer.parseInt(xSizeySize.get(2));
                if(minesCount> xSize*ySize){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setContentText("Mine count must not exceed grid size!");
                    alert.showAndWait();
                    Dialog();
                }
            }else{    
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("You must enter all values before continuing!");
                alert.showAndWait();
                Dialog();
        }
        });
    }
    /**
    * Creates timer to keep track of time.
    */
    public void startTimer() {
        timeline = new Timeline(
            new KeyFrame(Duration.seconds(0),
                e ->advanceDuration()),
            new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    /**
    * Advances seconds variable and updates the corresponding label to display new value.
    */
    private void advanceDuration() {
        seconds++;
        timeElapsed.setText(String.valueOf(seconds));
    } 
    /**
    * Opens the main window to play a match of minesweeper according to player input.
    *
    * @param xSize User input for grid width.
    * @param ySize User input for grid height.
    * @param minesCount User input for mine amount.
    * @param gridPanel The gridpane that contains the play area.
    */
    public void PlayMatch(int xSize, int ySize,GridPane gridPanel, int minesCount){
        startTimer();
        grid=new Grid(xSize, ySize, minesCount);
        tiles=new Tile[xSize][ySize];
        gridPanel.setMinWidth(xSize*50+100);
        gridPanel.getChildren().removeAll(gridPanel.getChildren());
        gridPanel.add(new Label("Timer"),(xSize+ySize)/2+1,0);
        gridPanel.add(timeElapsed,(xSize+ySize)/2+1,1);
        gridPanel.add(new Label("Mines"),(xSize+ySize)/2-1,0);
        Label mines= new Label(String.valueOf(minesCount));
        gridPanel.add(mines,(xSize+ySize)/2-1,1);
        gridPanel.add(new Label("Highscore"),xSize+3,0);
        ArrayList<Integer>times = new ArrayList<>();
        ArrayList<Result>pastResults = new ArrayList<>();
        pastResults = fileDao.getAll();
        for(int i = 0; i < pastResults.size(); i++){
            if(pastResults.get(i).getX() == xSize && pastResults.get(i).getY() == ySize && pastResults.get(i).getMines() == minesCount){
                times.add(pastResults.get(i).getTime());
            }
        }
        Collections.sort(times);
        for(int i = 0; i < Math.min(times.size(),20); i++){
            gridPanel.add(new Label(String.valueOf(times.get(i))+"s"),xSize+3,i+1);
            //GridPane.setMargin(gridPanel.getChildren().get(i+5), new Insets(5, 20, 5, 20));
        }
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                final int x=i;
                final int y=j;
                Tile button = new Tile(i,j);
                button.setMinSize(51, 51);
                button.setMaxSize(51, 51);
                button.setStyle("-fx-border-width: 1px; -fx-border-color: #1b1b1c; -fx-font-size: 10px; ");
                tiles[i][j]=button;
                gridPanel.add(button, i+2,j+2);
                button.setOnMouseClicked((event) ->{
                    if(event.getButton()==MouseButton.PRIMARY && !grid.hasMineMarked(x, y)){
                        if(!grid.hasMine(x, y)){
                            grid.generateView(x, y);
                            int[][] view=grid.getView();
                            for(int k = 0; k < xSize; k++){
                                for(int p = 0; p < ySize; p++){
                                    if(view[k][p]>= 0 && grid.isVisited(k,p) == true && grid.hasMine(k, p)==false){
                                        if(!grid.hasMineMarked(k, p)){
                                            tiles[k][p].setText(String.valueOf(view[k][p]));
                                        }
                                    }
                                }
                            }
                            if(grid.checkWin()){
                                timeline.stop();
                                fileDao.add(xSize, ySize, minesCount, seconds);
                                seconds=0;
                                Alert alert = new Alert(AlertType.CONFIRMATION, "Congratulations, you have won the game!"+ "\n" +"Would you like to play again?");
                                Optional<ButtonType> result2 = alert.showAndWait();
                                if (result2.isPresent() && result2.get() == ButtonType.OK) {
                                    Dialog();
                                    PlayMatch(this.xSize, this.ySize, gridPanel, this.minesCount);
                                }else{
                                    Platform.exit();
                                }
                            }
                        }else{
                            timeline.stop();
                            seconds=0;
                            Alert alert = new Alert(AlertType.CONFIRMATION, "You clicked a mine and lost the game!"+ "\n" +"Would you like to play again?");
                                Optional<ButtonType> result2 = alert.showAndWait();
                                if (result2.isPresent() && result2.get() == ButtonType.OK) {
                                    Dialog();
                                    PlayMatch(this.xSize, this.ySize, gridPanel, this.minesCount);
                                }else{
                                    Platform.exit();
                                }
                        }
                    }
                    if(event.getButton()==MouseButton.SECONDARY){
                        if(!grid.hasMineMarked(x, y)){
                            button.setText("MINE");
                            grid.markMine(x, y);
                            mines.setText(String.valueOf(minesCount-grid.returnMinesMarked()));
                        } else {
                            button.setText("");
                            grid.unmarkMine(x, y);
                            mines.setText(String.valueOf(minesCount-grid.returnMinesMarked()));
                        }
                    }
                });
            }
        }
    }
    @Override
    public void stop() throws Exception{
        fileDao.save();
    }
    @Override
    public void start(Stage stage) throws Exception {
        fileDao = new FileMinesweeperDao("results.txt");
        Dialog();
        GridPane gridPanel = new GridPane();
        gridPanel.setHgap(0);
        gridPanel.setVgap(0);
        PlayMatch(xSize, ySize, gridPanel, minesCount);
        ScrollPane scrollPane = new ScrollPane(gridPanel);
        stage.setScene(new Scene(scrollPane));
        stage.setTitle("Minesweeper");
        stage.setMaximized(true);
        stage.show();
    }
    public static void main(String[] args) {
        launch(GraphicalUi.class);
    }    
}
