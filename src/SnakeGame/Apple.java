package SnakeGame;

import java.util.Random;

public class Apple {
	int AppleX = 0;
	int AppleY = 0;	
	
	public Apple() {
		SetNewPosition();
	}
	
	public void SetNewPosition(){
		Random x = new Random();
		Random y = new Random();
		AppleX = x.nextInt(20);
		AppleY = y.nextInt(14);
	}
}
