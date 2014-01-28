package pathfinding;

import java.io.File;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {
	 	public static int[][] board = new int[30][30];
	    public static int xi = 0,yi = 0,xf = 0,yf = 0;
	    
	    private static int EuclideanDistance(Node current, Node end){
	            return (int)Math.floor(Math.sqrt(Math.pow(current.x - end.x, 2) + Math.pow(current.y - end.y, 2)));
	    }
	    private static int ManhattanDistance(Node current, Node end){
	            int d1 = Math.abs (current.x - end.x);
	            int d2 = Math.abs (current.y - end.y);
	            return d1 + d2;
	    }
	    public static ArrayList<Node> Astar(int[] inicio, int[] fim, int[][] tabuleiro){
	        Node start = new Node(inicio[0],inicio[1], 0,0,0);
	        Node end = new Node(fim[0],fim[1],0,0,0);
	        ArrayList<Node> opens = new ArrayList<Node>();
	        ArrayList<Node> closed = new ArrayList<Node>();
	        
	        opens.add(start);
	        while(opens.size() > 0){           
	            int bestF = 9999;
	            int bestNodeIndex = 0;
	            for (int i = 0; i < opens.size(); i++) {
	                if(opens.get(i).f < bestF){
	                    bestF = opens.get(i).f;
	                    bestNodeIndex = i;
	                }
	            }
	            Node current = opens.get(bestNodeIndex);
	           
	            if(current.x == end.x && current.y == end.y ){     
	               
	            	ArrayList<Node> path = new ArrayList<Node>();
	                path.add(end);
	                while(current.parentIndex != 0){
	                    current = closed.get(current.parentIndex);
	                    path.add(0,current);
	                }
	                path.add(0,start);
	                return path;              
	            }
	            
	            opens.remove(current);
	            closed.add(current);
	             
	            for (int i = max(0,current.x-1); i <= min(30-1, current.x+1); i++) {
	                for (int j = max(0,current.y-1); j <= min(30-1, current.y+1); j++) {
	                    
	                	if (i != current.x && j != current.y){
							continue;
	                	}
	                    if(tabuleiro[i][j] == 0 || (i == end.x && j == end.y)){

	                        boolean isClosedOrOpen = false;
	                        for (int k = 0; k < closed.size(); k++) {
	                            if(closed.get(k).x == i && closed.get(k).y == j){
	                                isClosedOrOpen = true;
	                                break;
	                                
	                            }
	                        }
	                        if(isClosedOrOpen){
	                            continue;
	                        }
	                        isClosedOrOpen = false;
	                        for (int k = 0; k < opens.size(); k++) {
	                            if(opens.get(k).x == i && opens.get(k).y == j){
	                                isClosedOrOpen = true;
	                                break;
	                            }
	                        }
	                        if(isClosedOrOpen){
	                            break;
	                        }else{
	                            Node newNodo = new Node(i,j,closed.size()-1, 0, 0);
	                            newNodo.g = current.g + (int)Math.floor(Math.sqrt(Math.pow(newNodo.x-current.x, 2)+ Math.pow(newNodo.y-current.y, 2))); // Euclidean Distance entre o nodo current e o novo nodo
	                            newNodo.f = newNodo.g + EuclideanDistance(newNodo, end);
	                            
	                            opens.add(newNodo);
	                        }                        
	                    }
	                }
	            }
	        }
	        return null;
	        
	    }
	    public static void Print(int[][] n){
	       for (int i = 0; i < 30; i++) {
	            for (int j = 0; j < 30; j++) {
	                System.out.print(n[i][j]+" ");
	            }
	            System.out.println();
	        }
	         System.out.println();
	    }
	    
	    public static void main(String[] args) {
			System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
			Random gerador = new Random();
	        yf=gerador.nextInt(30);
	        xf=gerador.nextInt(30);
	        yi =gerador.nextInt(30);
	        xi = gerador.nextInt(30);
	        
	        for (int i = 0; i < 30; i++) {
	            for (int j = 0; j < 30; j++) {
	              if((int)(gerador.nextDouble()*5) == 0){
	                  if((i != xi && i != xf) || (j != yi && j != yf )){
	                     board[i][j] = 1;    
	                  }               
	              }                
	            }
	        }
	        board[xi][yi] = 3;
	        board[xf][yf] = 3;
	        
	        Astar(new int[]{xi, yi}, new int[]{xf,yf}, board);
	        board[xi][yi] = 3;
	        board[xf][yf] = 3;
			try {
				AppGameContainer app = new AppGameContainer( new Game() );
				
				app.setDisplayMode(960, 960, false);
				app.setTargetFrameRate(2);
				app.start();
				
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}

}
