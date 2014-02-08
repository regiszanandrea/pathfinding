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
	private Sound back;
	private TiledMap map;
	private Animation sprite, up, down, left, right;
	private int x,y,nx = 0, nMap;
	private int[][] board = new int[30][30]; // Tabuleiro 30x30
	
	private ArrayList<Node> path = new ArrayList<Node>();
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		map.render(0, 0); // Renderização do mapa
		gc.setShowFPS(false);
		
		int objectLayer = map.getLayerIndex("Objetos");
		// Acha todos os obstaculos de acordo com o mapa
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 30; j++) {
				if(map.getTileId(j,i,objectLayer) == 0){
					board[i][j] = 0;
				}else{
					board[i][j] = 1;
				}				
			}
		}		
		sprite.draw(x*32, y*32);
	}
 
	@Override
	public void update(GameContainer gc, int t) throws SlickException {
		int objectLayer = map.getLayerIndex("Objetos");
		map.getTileId(0, 0, objectLayer);
		Input in = gc.getInput();
	
		// Pega evento do mouse
		if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			if(map.getTileId(in.getMouseX()/32,in.getMouseY()/32,objectLayer) == 0){ // Verifica se é não é um obstaculo.				
				nx=0;
				path.clear();
				path = new Astar().run(new int[]{y, x},new int[]{in.getMouseY()/32, in.getMouseX()/32}, board); // Executa o A* da posição atual até aonde o usuario clicou									
			}
		}
		if(in.isKeyPressed(Input.KEY_DOWN)){
			sprite = down;	
			if(map.getTileId(x,y+1,objectLayer) == 0){
				y++;
			}
		}
		if(in.isKeyPressed(Input.KEY_LEFT)){
			sprite = left;
			if(map.getTileId(x-1,y,objectLayer) == 0){
				x--;
			}
		}
		if(in.isKeyPressed(Input.KEY_UP)){
			sprite = up;	
			if(map.getTileId(x,y-1,objectLayer) == 0){
				y--;
			}
		}
		if(in.isKeyPressed(Input.KEY_RIGHT)){
			sprite = right;
			if(map.getTileId(x+1,y,objectLayer) == 0){
				x++;
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
			// movimentação do personagem
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
	}
	// Função para verificar se a posição atual não é um EasterEgg. Se for é mudado o mapa do jogo.
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
				System.exit(1);
			}
		}
		return false;
		
	}
	// Inicialição do jogo
	@Override
	public void init(GameContainer gc) throws SlickException {
		map = new TiledMap("res/mapa.tmx");
		
		back = new Sound("res/mapa.wav");
		back.play();
		back.loop();
		
		nMap = 0 ; // Numero do mapa
		x = 1;
		y = 1;
		
		// Sprite
		Image [] movementUp = {new Image("res/wmg1_bk1.png"), new Image("res/wmg1_bk2.png")};
        Image [] movementDown = {new Image("res/wmg1_fr1.png"), new Image("res/wmg1_fr2.png")};
        Image [] movementLeft = {new Image("res/wmg1_lf1.png"), new Image("res/wmg1_lf2.png")};
        Image [] movementRight = {new Image("res/wmg1_rt1.png"), new Image("res/wmg1_rt2.png")};      
        int [] duration = {100, 100};
        
        // Animaçoes
        up = new Animation(movementUp, duration, false);
        down = new Animation(movementDown, duration, false);
        left = new Animation(movementLeft, duration, false);
        right = new Animation(movementRight, duration, false);
        
        sprite = down;
        
		
	}
 
	public Game ()
	{
		super("Game");
		
	}
}