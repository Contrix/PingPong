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
    private static int bat1 = 50;
    private static int bat2 = 50;
    private static MyPoint ball = new MyPoint(300, 300);
    private static MyPoint target = new MyPoint(0, 0);
    private double[] math = {0, 0, 0, 0, 0};
    private double course = 4.58318;//random.nextDouble();
    private static int pixel = 20;
    
    public int getPixel(){
        return pixel;
    }
    
    public void setPixel(int p){
        this.pixel = p;
    }
    
    public void moveUpBat1(){
        /*bat1--;
        if (bat1 < 0){
            bat1 = 0;
        }*/
        course = course + 0.1;
        if(course >= 2*Math.PI){
            course = 0;
        }
    }
    
    public void moveDownBat1(){
        /*bat1++;
        if (bat1 > 100){
            bat1 = 100;
        }*/
        course = course - 0.1;
        if(course < 0){
            course = 2*Math.PI;
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
        
        
        checkBounce();
    }
    
    private void checkBounce(){
        
        /*
        *0 - rozdíl x (a)
        *1 - rozdíl y (b)
        *2 - přepona (c)
        *3 - úhel a
        *4 - úhlel b
        */
        
        if (course < 1.571 || course > 4.712){
            math[1] = 9.35 * pixel - ball.getY();//dolní hranice
        }
        else{
            math[1] = ball.getY() - 1.25 * pixel;//horní hranice
        }
        math[0] = (int)(Math.tan(course) * math[1]);//course * Math.PI / 180
        
        if(ball.getX()-math[0] < 3 * pixel){//levá hranice
            math[0] = ball.getX() - 3 * pixel;
            math[1] = (int)(1/Math.tan(course) * math[0]);
        }
        else if(ball.getX()-math[0] > 40* pixel){
            math[0] = 40 * pixel - ball.getX();
            math[1] = (int)(1/Math.tan(course) * math[0]);
            System.out.println("vpravo");
        }
        
        target = new MyPoint((int)(ball.getX()-math[0]), (int)(ball.getY()-math[1]));
        //math[2] = Math.sqrt(Math.pow(math[0], 2) + Math.pow(math[1], 2));
        
        System.out.println(target + " - " + course);
        //System.out.println(math[0]);
    }
    
    public MyPoint getTarget(){
        return target;
    }
}
