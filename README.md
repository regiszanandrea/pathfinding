Pathfinding - A*
===========
<h3>Fundamentos de Inteligência Artificial</h3>

Nome: Regis Zanandrea<br />
Matricula: 11200318

O trabalho foi desenvolvido em Java utilizando a biblioteca gráfica Slick2d, que possui o recurso TileMap, uma biblioteca para construir mapas para jogos 2d.

A maior dificuldade encontrada foi vincular o caminho gerado pelo A* ao mapa, onde foi preciso fazer calculos para ajustar a posição do personagem no mapa.  

Para construção dos mapas foi usado o programa Tiled: mapeditor.org , onde é extremamente fácil de montar mapas 2d para jogos.

Execução
===========

Para executar o jogo siga os passos abaixo:<br />
  - Faça o download do arquivo jar "pathfinding-regiszanandrea-final.jar", que está na raiz do repositório. <br />
  - Faça o download da pasta "native", que também está na raiz do repositório.<br />
  - Deixe a pasta "native" no mesmo diretório da arquivo jar.
  - Execute no terminal o seguinte comando:<br /> 
               <code> $ java -jar pathfinding-regiszanandrea-final.jar</code>

EasterEggs
===========

O jogo possui eastereggs pelo mapa, quando um for achado, o usuário será "transportado" para um novo mapa, onde também terá outros easteeggs. Para saber onde está todos, basta olhar a função isEasterEgg (linha 109) no arquivo Game.java .
