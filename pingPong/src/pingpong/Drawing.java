/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Jirka
 */
public class Drawing {
    private final Game gm = new Game();
    private int pixel = gm.getPixel();
    private int length = 7;
    
    public void drawAll(GraphicsContext gc, double width, double height){
        checkPixel(height);
        drawBackGround(gc, width, height);
        drawPanel(gc, width, height);
        drawBats(gc, width, height);
        drawBall(gc);
        drawTarget(gc, width, height);
    }
    
    private void drawBackGround(GraphicsContext gc, double width, double height){
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(pixel/2);
        gc.strokeRect(pixel, pixel, width - 2 * pixel, height - 2 * pixel);
        /*for (int i = 0; i <){
            
        }*/
        //gc.setStroke(Color.AZURE);
        gc.strokeLine(width/2, pixel + pixel/2, width/2, height - pixel/2*3);
    }
    
    private void drawPanel(GraphicsContext gc, double width, double height){
        
    }
    
    private void drawBats(GraphicsContext gc, double width, double height){
        gc.setFill(Color.AQUA);
        gc.fillRect(2 * pixel, (height - 3 * pixel - height / length) / 100 * gm.getBat1() + 1.5 * pixel, pixel, height / length); //3→4, 1,5→2
        gc.fillRect(width - 3 * pixel, (height - 3 * pixel - height / length) / 100 * gm.getBat2() + 1.5 * pixel, pixel, height / length);
    }
    
    private void drawBall(GraphicsContext gc){
        gc.setFill(Color.AQUA);
        gc.fillOval(gm.getBall().getX(), gm.getBall().getY(), pixel, pixel);
    }
    
    private void drawTarget(GraphicsContext gc, double width, double height){
         gc.setFill(Color.AQUA);
         gc.fillOval(gm.getTarget().getX(), gm.getTarget().getY(), pixel, pixel);
    }
    
    private void checkPixel(double height){
        pixel = (int)height / 35;
        gm.setPixel(pixel);
    }
    
    public int getPixel(){
        return pixel;
    }
    
    
}
