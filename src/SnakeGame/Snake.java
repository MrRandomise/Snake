package SnakeGame;


public class Snake {
	byte moveto = 0;
	int SnakeLength = 3;
	int SnakeP[][] = new int[2][100];
	//Создаем класс Яблако
	Apple a = new Apple();
	int X = 0;
	int Y = 0;
	boolean EndGame = false;
	
	
	//Констурктор класса
	public Snake(int x, int y) {
		X = x;
		Y = y;
		StartNewGame();
	}
	
	public void StartNewGame() {
		int x = X;
		int y = Y;
		SnakeLength = 3;
		SnakeP[0][0] = x;
		SnakeP[1][0] = y;
		EndGame = false;
		for(int i = 0; i < SnakeLength; i++) 
		{
			SnakeP[0][i] = x+i;
			SnakeP[1][i] = y;
		}
	}
	
	public void Move() {		
		for(int i = SnakeLength; i > 0; i--){
			SnakeP[0][i] = SnakeP[0][i-1];
			SnakeP[1][i] = SnakeP[1][i-1];
		}
		
		switch(moveto) {
		case 0: 
			SnakeP[0][0]++;
			break;
	    case 1: 
	    	SnakeP[1][0]++;
			break;
		case 2: 
			SnakeP[0][0]--;
			break;
		case 3: 
			SnakeP[1][0]--;
			break;
		default: 			
		    break;
		    }
		//Если змейка укусила сама себя то коне 
		for(int i = 3; i < SnakeLength; i ++){
			if(SnakeP[0][0] == SnakeP[0][i] && SnakeP[1][0] == SnakeP[1][i])
				EndGame = true;
		}
		//Проверяем что если змейка дошла до границы игрового поля, то конец игры
		if((SnakeP[0][0] == -1 || SnakeP[1][0] == -1) || (SnakeP[0][0] == 20 || SnakeP[1][0] == 14))
			EndGame = true;
		if(SnakeP[0][0] == a.AppleX && SnakeP[1][0] == a.AppleY){
			a.SetNewPosition();
			SnakeLength++;
		}
	}
}
