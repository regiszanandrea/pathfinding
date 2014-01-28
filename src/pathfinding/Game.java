package pathfinding;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.tiled.TiledMap;

public class Game extends BasicGame 
{
	Sound back;
	private TiledMap map;
	private Animation sprite, up, down, left, right;
	private int x,y,nx = 0, nMap;
	private int[][] board = new int[30][30];
	
	ArrayList<Node> path = new ArrayList<Node>();
	//private ArrayList<ArrayList<Integer>> n = new ArrayList<ArrayList<Integer>>();
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		map.render(0, 0);
		gc.setShowFPS(false);
		
		int objectLayer = map.getLayerIndex("Objetos");
		
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 30; j++) {
				if(map.getTileId(j,i,objectLayer) == 0){
					board[i][j] = 0;
				}else{
					board[i][j] = 1;
				}				
			}
		}
		//g.fillRect(x*32, y*32, 32, 32);
		sprite.draw(x*32, y*32);
	}
 
	@Override
	public void update(GameContainer gc, int t) throws SlickException {
		int objectLayer = map.getLayerIndex("Objetos");
		map.getTileId(0, 0, objectLayer);
		Input in = gc.getInput();
	
		
		if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			if(map.getTileId(in.getMouseX()/32,in.getMouseY()/32,objectLayer) == 0){				
				nx=0;
				path.clear();
				path = new Main().Astar(new int[]{y, x},new int[]{in.getMouseY()/32, in.getMouseX()/32}, board);									
			}
		}
		
		if(path.size() != 0){			
			if(nx < path.size()-1){ 
				nx++; 
			}else{						
				if(isEasterEgg()){					
					return;
				}
			}
			
			if(x + 1 == path.get(nx).y ){				              
				sprite = right;		           
                sprite.update(t);
			}else if(x-1 == path.get(nx).y){
				sprite = left;		           
                sprite.update(t);
			}else if(y+1 == path.get(nx).x){
				sprite = down;	            
                sprite.update(t);
			}else if(y-1 == path.get(nx).x){
				sprite = up;		        
                sprite.update(t);
			}
			x = path.get(nx).y;
			y = path.get(nx).x;
		}
//		if(map.getTileId(x,y,objectLayer) == 0){
//			x++;
//		}
		
	}
	public boolean isEasterEgg() throws SlickException{
		if(nMap == 0){
			if(x == 20 && y == 20 ){								
				Node temp = new Node();
				temp.x = temp.y = 1;
				path.add(temp);
				nMap = 1;
				map = new TiledMap("res/mapa2.tmx");
				back.stop();				
				back = new Sound("res/mapa2.wav");
				back.loop();
				return true;
			}
		}else if(nMap == 1){
			if(x == 7 && y == 16){								
				Node temp = new Node();
				temp.x = temp.y = 1;
				path.add(temp);
				nMap = 2;
				map = new TiledMap("res/mapa3.tmx");
				back.stop();
				back = new Sound("res/mapa3.wav");
				back.loop();
				return true;
			}
		}else if(nMap == 2){
			if(x == 11 && y == 10){
				Node temp = new Node();
				temp.x = temp.y = 1;
				path.add(temp);
				nMap = 3;
				map = new TiledMap("res/mapa4.tmx");
				back.stop();
				back = new Sound("res/mapa4.wav");
				back.loop();
				return true;
			}
		}else {
			if(x == 28 && y == 28){
				// Game over
			}
		}
		return false;
		
	}
 
	@Override
	public void init(GameContainer gc) throws SlickException {
		map = new TiledMap("res/mapa.tmx");
		
		back = new Sound("res/mapa.wav");
		back.play();
		back.loop();
		
		nMap = 0 ; // Numero do mapa
		x = 1;
		y = 1;
		Image [] movementUp = {new Image("res/wmg1_bk1.png"), new Image("res/wmg1_bk2.png")};
        Image [] movementDown = {new Image("res/wmg1_fr1.png"), new Image("res/wmg1_fr2.png")};
        Image [] movementLeft = {new Image("res/wmg1_lf1.png"), new Image("res/wmg1_lf2.png")};
        Image [] movementRight = {new Image("res/wmg1_rt1.png"), new Image("res/wmg1_rt2.png")};      
        int [] duration = {100, 100};
        
        up = new Animation(movementUp, duration, false);
        down = new Animation(movementDown, duration, false);
        left = new Animation(movementLeft, duration, false);
        right = new Animation(movementRight, duration, false);
        
        sprite = right;
        
		
	}
 
	public Game ()
	{
		super("Game");
		
	}
}