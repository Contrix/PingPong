/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong;

import java.util.Random;

/**
 *
 * @author Jirka
 */
public class Game {
    private Random random = new Random();
    private static int bat1;
    private static int bat2;
    private static MyPoint ball;
    private static MyPoint target;
    private static int pixel = 5;
    private double[] triangl = {0, 0, 0};
    private double course;
    
    public void newGame(){
        bat1 = 50;
        bat2 = 50;
        ball = new MyPoint(500, 500);
        course = random.nextDouble()* 2 * Math.PI;
        target = newTarget();
    }
    
    public int getPixel(){
        return pixel;
    }
    
    public void setPixel(int p){
        this.pixel = p;
    }
    
    public void moveUpBat1(){
        bat1--;
        if (bat1 < 0){
            bat1 = 0;
        }
    }
    
    public void moveDownBat1(){
        bat1++;
        if (bat1 > 100){
            bat1 = 100;
        }
    }
    
    public void moveUpBat2(){
        bat2--;
        if (bat2 < 0){
            bat2 = 0;
        }
    }
    
    public void moveDownBat2(){
        bat2++;
        if (bat2 > 100){
            bat2 = 100;
        }
    }
    
    public int getBat1(){
        return bat1;
    }
    
    public int getBat2(){
        return bat2;
    }
    
    public MyPoint getBall(){
        return(ball);
    }
    
    public void setBall(MyPoint p){
        ball = p;
    }
    
    public void moveBall(){
        ball.setX(ball.getX() - (int)(triangl[0] / 100.));
        ball.setY(ball.getY() - (int)(triangl[1] / 100.));
        
       // newTarget();
    }
    
    private MyPoint newTarget(){
        /*
        *0 - rozdíl x (a)
        *1 - rozdíl y (b)
        *2 - přepona (c)
        */
        if (course < 0.5 * Math.PI || course > 1.5 * Math.PI){// <90, >270 horní okraj
            triangl[1] = ball.getY();
        }
        else{
            triangl[1] = ball.getY() - 1000;
        }
        triangl[0] = Math.tan(course) * triangl[1];
        if((int)(ball.getX()-triangl[0]) < 0){
            triangl[0] = ball.getX();
            triangl[1] = triangl[0] / Math.tan(course);
        }
        else if((int)(ball.getX()-triangl[0]) > 1000){
            triangl[0] = ball.getX() - 1000;
            triangl[1] = triangl[0] / Math.tan(course);
        }
        triangl[2] = Math.sqrt(Math.pow(triangl[0], 2) + Math.pow(triangl[1], 2));
        return new MyPoint((int)(ball.getX()-triangl[0]), (int)(ball.getY()-triangl[1]));
    }
    
    public MyPoint getTarget(){
        return target;
    }
}
