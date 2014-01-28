/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinding;

/**
 *
 * @author regis
 */
public class Node {
    public int x,y,parentIndex;
    public int f,g;
    Node(){
        this.x = this.y = this.parentIndex = 0;
        this.f = this.g  =  0;
    }
    Node(int x, int y, int parent, int f, int g){
        this.x = x;
        this.y = y;
        this.parentIndex = parent;
        this.f = f;       
        this.g = g;
    }
    public void Print(){
        System.out.println("X: "+this.x);
        System.out.println("Y: "+this.y);
        System.out.println("Parent Index: "+this.parentIndex);
        System.out.println("F(n): "+this.f);
    }
}
