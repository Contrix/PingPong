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
    private int moveX = 0;
    private int moveY = 0;

    
    public void drawAll(GraphicsContext gc, double width, double height){
        checkPixel(width, height);
        drawBackGround(gc, width, height);
        drawPanel(gc, width, height);
        drawBats(gc);
        drawBall(gc);
        drawTarget(gc, width, height);
        //dddd(gc);
    }
    
    private void drawBackGround(GraphicsContext gc, double width, double height){
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height); //50 : 30
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(pixel / 4);
        gc.strokeRect(pixel + moveX, pixel + moveY, 48 * pixel, 28 * pixel);
        gc.strokeLine(25 * pixel + moveX, 1.25 * pixel + moveY, 25 * pixel + moveX, 28.75 * pixel + moveY);
    }
    
    private void drawPanel(GraphicsContext gc, double width, double height){
        
    }
    
    private void drawBats(GraphicsContext gc){
        gc.setFill(Color.AQUA);
        //gc.fillRect(1.75 * pixel + moveX, 23 * pixel / 100. * gm.getBat1() + 1.5 * pixel + moveY, 0.75 * pixel , 4 * pixel);
        gc.fillRect(1.75 * pixel + moveX, 23.5 * pixel / 100. * gm.getBat1() + 1.25 * pixel + moveY, 0.75 * pixel , 4 * pixel);
        gc.fillRect(47.50 * pixel + moveX, 23.5 * pixel / 100. * gm.getBat2() + 1.25 * pixel + moveY, 0.75 * pixel, 4 * pixel);
    }
    
    private void drawBall(GraphicsContext gc){
        gc.setFill(Color.AQUA);
        gc.fillOval(44 * pixel / 1000. * gm.getBall().getX() + 2.5 * pixel + moveX, 26.7 * pixel / 1000. * gm.getBall().getY() + 1.15 * pixel + moveY, pixel, pixel);
    }
    
    private void dddd(GraphicsContext gc){
        gc.setLineWidth(1);
        gc.setStroke(Color.RED);
        gc.strokeLine(0, 23 * pixel / 100. * gm.getBat1() + 1.5 * pixel + moveY, 1000, 23 * pixel / 100. * gm.getBat1() + 1.5 * pixel + moveY);
        gc.setStroke(Color.ORANGE);
        gc.strokeLine(0, 26.7 * pixel / 1000. * gm.getBall().getY() + 1.15 * pixel + moveY, 1000, 26.7 * pixel / 1000. * gm.getBall().getY() + 1.15 * pixel + moveY);
    }
    
    private void drawTarget(GraphicsContext gc, double width, double height){
        gc.setFill(Color.AQUA);
        gc.fillOval(44 * pixel / 1000. * gm.getTarget().getX() + 2.5 * pixel + moveX, 26.7 * pixel / 1000. * gm.getTarget().getY() + 1.15 * pixel + moveY, pixel, pixel);
    }
    
    private void checkPixel(double width, double height){
        // 5 : 3
        //pixel = (int)height / 30;
        
        
        while (height > 30 * pixel){
            pixel++;
            //System.out.println("plus" + pixel);
        }
        while (height < 29 * pixel){
            pixel--;
            //System.out.println("mínus" + pixel);
        }

        //System.out.println(width + " - " + height);
        
        gm.setPixel(pixel);
        moveX = ((int)width - 50 * pixel) / 2;
        moveY = ((int)height - 30 * pixel) / 2;
        
    }
    
    public int getPixel(){
        return pixel;
    }
    
    public int getMoveX(){
        return moveX;
    }
    
    public int getMoveY(){
        return moveY;
    }
}
