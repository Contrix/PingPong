/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Jirka
 */
public class PingPong extends Application {
    private Drawing drw = new Drawing();
    private Game gm = new Game();
    private boolean bot = false;
    
    
    private boolean[] keys = {false, false, false, false};
    @Override
    public void start(Stage primaryStage) {
        
        Pane root = new Pane();
        Scene scene = new Scene(root, 1000, 600);
        
        Canvas canvas = new Canvas(scene.getWidth(), scene.getHeight());
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.widthProperty().bind(scene.widthProperty());
        canvas.heightProperty().bind(scene.heightProperty());
        
        final Circle circle = new Circle(0, 0, 8);
        circle.setFill(Color.ORANGE);
        Path path = new Path();
        PathTransition pathTransition = new PathTransition();
        pathTransition.setPath(path);
        pathTransition.setNode(circle);
        pathTransition.setInterpolator(Interpolator.LINEAR);
        gm.newGame();
        
        Timeline timer = new Timeline(new KeyFrame(Duration.millis(10), (ActionEvent event) -> {
            if(!gm.getGameOver()){
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
                drw.drawAll(gc, canvas.getWidth(), canvas.getHeight());
                if(pathTransition.getStatus() == Animation.Status.STOPPED){
                    circle.setRadius(drw.getPixel()/2);
                    ball(path, pathTransition);
                }
            }
            else{
                pathTransition.stop();
                drw.drawAll(gc, canvas.getWidth(), canvas.getHeight());
                if(!gm.getDeduction()){
                    gm.setDeduction();
                    deduction();
                }
            }
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
                case F5:
                    gm.newGame();
                    break;
                case F6:
                    bot = !bot;
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
        root.getChildren().add(circle);
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
    
    private void ball(Path path, PathTransition pathTransition){
        path.getElements().clear();    
        path.getElements().add(new MoveTo(44 * drw.getPixel() / 1000. * gm.getBall().getX() + 3 * drw.getPixel() + drw.getMoveX(), 26.7 * drw.getPixel() / 1000. * gm.getBall().getY() + 1.65 * drw.getPixel() + drw.getMoveY()));
        path.getElements().add(new LineTo(44 * drw.getPixel() / 1000. * gm.getTarget().getX() + 3 * drw.getPixel() + drw.getMoveX(), 26.7 * drw.getPixel() / 1000. * gm.getTarget().getY() + 1.65 * drw.getPixel() + drw.getMoveY()));
        pathTransition.setDuration(Duration.millis(gm.getWay() * 1.5));
        
        pathTransition.play();
        gm.bounce();
    }
    
    private void resetBall(Path path, PathTransition pathTransition){
        path.getElements().clear();
        path.getElements().add(new MoveTo(44 * drw.getPixel() / 1000. * gm.getBall().getX() + 3 * drw.getPixel() + drw.getMoveX(), 26.7 * drw.getPixel() / 1000. * gm.getBall().getY() + 1.65 * drw.getPixel() + drw.getMoveY()));
        path.getElements().add(new LineTo(44 * drw.getPixel() / 1000. * gm.getBall().getX() + 3 * drw.getPixel() + drw.getMoveX(), 26.7 * drw.getPixel() / 1000. * gm.getBall().getY() + 1.65 * drw.getPixel() + drw.getMoveY()));
        pathTransition.setDuration(Duration.millis(1));
        
        pathTransition.play();
    }
    
    private void deduction(){
        Timeline timeline1 = new Timeline(new KeyFrame(
        Duration.millis(2000),
        ae -> {
            gm.newRound();
            gm.setText("3");
                }));
        timeline1.play();
        
        Timeline timeline2 = new Timeline(new KeyFrame(
        Duration.millis(3000),
        ae -> gm.setText("2")));
        timeline2.play();
        
        Timeline timeline3 = new Timeline(new KeyFrame(
        Duration.millis(4000),
        ae -> gm.setText("1")));
        timeline3.play();
        
        Timeline timeline4 = new Timeline(new KeyFrame(
        Duration.millis(5000),
        ae -> gm.play()));
        timeline4.play();
    }
    
    private void bot(){
        
    }
}