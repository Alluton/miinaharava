package minesweeper.ui;
import java.util.ArrayList;
import javafx.geometry.Insets;
import java.util.Optional;
import javafx.application.Application;
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
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import minesweeper.domain.Grid;
public class GraphicalUi extends Application{
    Grid grid;
    Tile[][] tiles;
    private int xSize;
    private int ySize;
    private int minesCount;
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
        horizontalSize.setPromptText("Horizontal size of minefield: ");
        TextField verticalSize = new TextField();
        verticalSize.setPromptText("Vertical size of minefield: ");
        TextField mineCount = new TextField();
        mineCount.setPromptText("Amount of mines: ");
        
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
                System.exit(0);
            }
            return null;
        });
        Optional<ArrayList<String>> result = dialog.showAndWait();
        result.ifPresent(xSizeySize -> {
            xSize=Integer.parseInt(xSizeySize.get(0));
            ySize=Integer.parseInt(xSizeySize.get(1));
            minesCount=Integer.parseInt(xSizeySize.get(2));
        });
    }
    public void PlayMatch(int xSize, int ySize,GridPane gridPanel, int minesCount){
        grid=new Grid(xSize, ySize, minesCount);
        tiles=new Tile[xSize][ySize];
           for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                final int x=i;
                final int y=j;
                Tile button = new Tile(i,j);
                button.setMinSize(50, 50);
                tiles[i][j]=button;
                gridPanel.add(button, i,j);
                button.setOnMouseClicked((event) ->{
                    if(event.getButton()==MouseButton.PRIMARY){
                        if(!grid.hasMine(x, y)){
                            grid.generateView(x, y);
                            int[][] view=grid.getView();
                            for(int k = 0; k < xSize; k++){
                                for(int p = 0; p < ySize; p++){
                                    if(view[k][p]>= 0 && grid.isVisited(k,p) == true && grid.hasMine(k, p)==false){
                                        tiles[k][p].setText(String.valueOf(view[k][p]));
                                    }
                                }
                            }
                            if(grid.checkWin()){
                                Alert alert = new Alert(AlertType.CONFIRMATION, "Congratulations, you found all the mines and have won the game!"+ "\n" +"Would you like to play again?");
                                Optional<ButtonType> result2 = alert.showAndWait();
                                if (result2.isPresent() && result2.get() == ButtonType.OK) {
                                    Dialog();
                                    PlayMatch(this.xSize, this.ySize, gridPanel, this.minesCount);
                                }else{
                                    System.exit(0);
                                }
                            }
                        }else{
                            Alert alert = new Alert(AlertType.CONFIRMATION, "You clicked a mine and lost the game!"+ "\n" +"Would you like to play again?");
                                Optional<ButtonType> result2 = alert.showAndWait();
                                if (result2.isPresent() && result2.get() == ButtonType.OK) {
                                    Dialog();
                                    PlayMatch(xSize, ySize, gridPanel, minesCount);
                                }else{
                                    System.exit(0);
                                }
                        }
                    }
                    if(event.getButton()==MouseButton.SECONDARY){
                        button.setText("MINE");
                    }
                });
            }
        }
    }
    @Override
    public void start(Stage stage) {
        Dialog();
        GridPane gridPanel = new GridPane();
        int BUTTON_PADDING=0;
        gridPanel.setHgap(BUTTON_PADDING);
        gridPanel.setVgap(BUTTON_PADDING);
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
