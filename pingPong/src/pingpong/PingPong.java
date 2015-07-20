/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Jirka
 */
public class PingPong extends Application {
    private Drawing drw = new Drawing();
    private Game gm = new Game();
    
    private boolean[] keys = {false, false, false, false};
    @Override
    public void start(Stage primaryStage) {
        
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 700, 500);
        
        Canvas canvas = new Canvas(scene.getWidth(), scene.getHeight());
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.widthProperty().bind(scene.widthProperty());
        canvas.heightProperty().bind(scene.heightProperty());
        
        //gm.setBall(new MyPoint((int)canvas.getWidth() / 2 - drw.getPixel()/3, (int)canvas.getHeight() / 2 - drw.getPixel()/3));
        
        Timeline timer = new Timeline(new KeyFrame(Duration.millis(100), (ActionEvent event) -> { //10
            if(keys[0]){
                gm.moveUpBat1();
            }
            else if(keys[1]){
                gm.moveDownBat1();
            }
            
            if(keys[2]){
                gm.moveUpBat2();
            }
            else if(keys[3]){
                gm.moveDownBat2();
            }
            
            gm.moveBall();
            
            drw.drawAll(gc, canvas.getWidth(), canvas.getHeight());
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
        
        
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
            switch(e.getCode()){
                case W:
                    keys[0] = true;
                    break;
                case S:
                    keys[1] = true;
                    break;
                case UP:
                    keys[2] = true;
                    break;
                case DOWN:
                    keys[3] = true;
                    break;
                    
                case ESCAPE:
                    primaryStage.close();
                    break;
                    
                default:
                    break;
            }
        });
        
        scene.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent e) -> {
            switch(e.getCode()){
                case W:
                    keys[0] = false;
                    break;
                case S:
                    keys[1] = false;
                    break;
                case UP:
                    keys[2] = false;
                    break;
                case DOWN:
                    keys[3] = false;
                    break;
                default:
                    break;
            }
        });
        
        root.getChildren().add(canvas);
        primaryStage.setTitle("PingPong");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
