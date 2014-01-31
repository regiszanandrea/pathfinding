package pathfinding;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.ArrayList;

public class Astar {

	// Heuristica: Euclidean Distance
	private static int EuclideanDistance(Node bestNode, Node end){
        return (int)Math.floor(Math.sqrt(Math.pow(bestNode.x - end.x, 2) + Math.pow(bestNode.y - end.y, 2)));
	}
	// Heuristica: Manhattan Distance
	private static int ManhattanDistance(Node bestNode, Node end){
	        int d1 = Math.abs (bestNode.x - end.x);
	        int d2 = Math.abs (bestNode.y - end.y);
	        return d1 + d2;
	}
	Astar(){
       
    }
	// Execução do algoritmo A*
	public ArrayList<Node> run(int[] inicio, int[] fim, int[][] tabuleiro){
		// Inicializa o nodo Inicial e final e o Array de nodos abertos e fechados
		Node start = new Node(inicio[0],inicio[1], 0,0,0);
        Node end = new Node(fim[0],fim[1],0,0,0);
        ArrayList<Node> opens = new ArrayList<Node>();
        ArrayList<Node> closed = new ArrayList<Node>();
        
        // Adiciona o nodo raiz ao Array de nodos abertos
        opens.add(start);
        
        Node bestNode;
        // Loop para achar o menor caminho
        while(opens.size() > 0){             
            int bestF = 9999;
            int bestNodeIndex = 0;

            // Encontra o melhor nodo aberto, que possui o melhor F 
            for (int i = 0; i < opens.size(); i++) {
                if(opens.get(i).f < bestF){
                    bestF = opens.get(i).f;
                    bestNodeIndex = i;
                }
            }
            bestNode = opens.get(bestNodeIndex);
           
            // Verifica se é a solução, se sim sera criado o caminho através dos nodos que foram fechados e retorna esse camino 
            if(bestNode.x == end.x && bestNode.y == end.y ){     
               
            	ArrayList<Node> path = new ArrayList<Node>();
                path.add(end);
                while(bestNode.parentIndex != 0){
                    bestNode = closed.get(bestNode.parentIndex);
                    path.add(0,bestNode);
                }
                path.add(0,start);
                return path;              
            }
            // Se nao, o melhor nodo no momento será removido dos nodos abertos e adicionado aos nodos fechados
            opens.remove(bestNode);
            closed.add(bestNode);
             
            // Encontra todos os filhos do melhor nodo no momento.
            for (int i = max(0,bestNode.x-1); i <= min(29, bestNode.x+1); i++) { // Se a posição X do melhor nodo for 0, o resultado irá dar negativo, por isso se pega o maximo entre 0 e -1, é a mesma coisa que um if coringa: (bestNode.x-1 < 0)? 0:bestNode.x . Agora se a posição do X for 29, o resultado ira passar da posição maxima do Array, então se pega o minimo para restrigir o indice. 
                for (int j = max(0,bestNode.y-1); j <= min(29, bestNode.y+1); j++) {
                    
                	// Para não andar em diagonais
                	if (i != bestNode.x && j != bestNode.y){
						continue;
                	}
                	// Verifica se a posição não é um obstaculo ou se é a solução
                    if(tabuleiro[i][j] == 0 || (i == end.x && j == end.y)){

                        boolean isClosedOrOpen = false;
                        // Verifica se o filho a ser criado ja não está nos nodos fechados
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
                        // Verifica se o filho a ser criado ja não foi criado
                        for (int k = 0; k < opens.size(); k++) {
                            if(opens.get(k).x == i && opens.get(k).y == j){
                                isClosedOrOpen = true;
                                break;
                            }
                        }
                        if(isClosedOrOpen){
                            break;
                        }else{
                        	// Se o filho ja nao foi fechado ou aberto, é criado.
                            Node newNodo = new Node(i,j,closed.size()-1, 0, 0); // O pai sempre será o ultimo nodo fechado, que é o melhor nodo no momento (bestNode)                            				 	     
                            newNodo.g = bestNode.g + EuclideanDistance(newNodo, bestNode); // Euclidean Distance entre o nodo bestNode e o novo nodo
                            newNodo.f = newNodo.g + EuclideanDistance(newNodo, end); // f(nodo) = g+h 
                            
                            opens.add(newNodo); // Adiciona o novo nodo aos abertos
                        }                        
                    }
                }
            }
        }
        return null;	        
	}
}
