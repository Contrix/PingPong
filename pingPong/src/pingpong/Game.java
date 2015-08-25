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
    private static int pixel = 20;
    private double[] triangl = {0, 0, 0};
    private double course;
    private double oldCourse; //for bots
    private boolean gameOver = false;
    private static String text = "";
    private static int[] points = {0, 0};
    private boolean deduction =  false;
    
    public void newGame(){
        newRound();
        points[0] = 0;
        points[1] = 0;
    }
    
    public void newRound(){
        bat1 = 50;
        bat2 = 50;
        ball = new MyPoint(500, 500);
        course = random.nextDouble() * 2 * Math.PI;
        oldCourse = course;
        target = newTarget();
    }
    
    public void play(){
        gameOver = false;
        deduction = false;
        text = "";
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
    
    public void bounce(){
        if(ball.getX() == 0 && (26.7 * pixel / 1000. * ball.getY() + 1.15 * pixel  + pixel/2) > (23.5 * pixel / 100. * bat1 + 1.15 * pixel) && (26.7 * pixel / 1000. * ball.getY() + 1.15 * pixel  + pixel/2) < (23.5 * pixel / 100. * bat1 + 5.15 * pixel)){

        }
        else if(ball.getX() == 0){
            gameOver = true;
            text = "Player 2 win";
            points[1]++;
        }
        if(ball.getX() == 1000 && (26.7 * pixel / 1000. * ball.getY() + 1.15 * pixel  + pixel/2) > (23.5 * pixel / 100. * bat2 + 1.15 * pixel) && (26.7 * pixel / 1000. * ball.getY() + 1.15 * pixel  + pixel/2) < (23.5 * pixel / 100. * bat2 + 5.15 * pixel)){

        }
        else if(ball.getX() == 1000){
            gameOver = true;
            text = "Player 1 win";
            points[0]++;
        }
        ball.setPoint(target);
        oldCourse = course;
        if (course < Math.PI && course > 0){
            if(ball.getX() == 0){
                course =  2 * Math.PI - course;  
            }
            else{
                course = Math.PI - course;
            }
        }
        else if (course < 2 * Math.PI && course > Math.PI){
            if(ball.getX() == 1000){
                course = course - 2 * (course - Math.PI);
            }
            else{
                course = 3 * Math.PI - course;
            }
        }
        target = newTarget();
    }

    private MyPoint newTarget(){
        /*
        *0 - rozdíl x (a)
        *1 - rozdíl y (b)
        *2 - přepona (c)
        */
        //course = random.nextDouble()* 2 * Math.PI;
        if (course < 0.5 * Math.PI || course > 1.5 * Math.PI){// <90, >270
            triangl[1] = ball.getY();
        }
        else{
            triangl[1] = ball.getY() - 1000;
        }
        triangl[0] = Math.tan(course) * triangl[1];
        if((int)(ball.getX() - triangl[0]) < 0){
            triangl[0] = ball.getX();
            triangl[1] = triangl[0] / Math.tan(course);
        }
        else if((int)(ball.getX() - triangl[0]) > 1000){
            triangl[0] = ball.getX() - 1000;
            triangl[1] = triangl[0] / Math.tan(course);
        }
        triangl[2] = Math.sqrt(Math.pow(triangl[0], 2) + Math.pow(triangl[1], 2));
        return new MyPoint((int)(ball.getX()-triangl[0]), (int)(ball.getY()-triangl[1]));
    }
    
    public double getWay(){
        return (triangl[2]);
    }
    
    public MyPoint getTarget(){
        return target;
    }
    
    public String getText(){
        return text;
    }
    
    public void setText(String s){
        text = s;
    }
    
    public boolean getGameOver(){
        return gameOver;
    }
    
    public void setGameOver(){
        gameOver = !gameOver;
    }
    
    public int[] getPoints(){
        return points;
    }
    
    public void setDeduction(){
        deduction = !deduction;
    }
    
    public boolean getDeduction(){
        return deduction;
    }
    
    public double getOldCourse(){
        return oldCourse;
    }
}
