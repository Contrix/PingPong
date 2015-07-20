/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong;

/**
 *
 * @author Jirka
 */
public class MyPoint {
    private int x = 0;
    private int y = 0;
    
    public MyPoint(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public void setPoint(MyPoint p){
        this.x = p.getX();
        this.y = p.getY();
    }   
    
    public void setPoint(int x, int y){
        this.x = x;
        this.y = y;
    } 
    
    public int getX(){
        return(x);
    }
    
    public int getY(){
        return(y);
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public void decX(){
        x--;
    }
    
    public void decY(){
        y--;
    }
    
    public void incX(){
        x++;
    }
    
    public void incY(){
        y++;
    }
    
    @Override
    public String toString(){
        return (x + " - " + y);
    }
}
