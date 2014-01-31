package pathfinding;

import java.io.File;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

	    public static void main(String[] args) {
			System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath());

			// Cria a janela para o jogo
	        try {
				AppGameContainer app = new AppGameContainer( new Game() );
				// Tamanho da tela: 960x960
				app.setDisplayMode(960, 960, false);
				app.setTargetFrameRate(2); // Seta o frame rate para 2 para não ser tão rapido
				app.start();
				
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}

}
