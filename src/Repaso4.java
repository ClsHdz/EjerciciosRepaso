
import acm.program.*;
import acm.graphics.*;

public class Repaso4 extends GraphicsProgram {
	GRect rectangulo = new GRect(160,80);
	int distanciaX;
	int distanciaY;
	public void init(){
		setSize(800,600);
		rectangulo = new GRect(120,80);
		add(rectangulo);
	}
	public void run(){
		distanciaX = getWidth()/2;
		distanciaY = getHeight()/2;
		rectangulo.setLocation(distanciaX - (rectangulo.getWidth()/2), distanciaY - (rectangulo.getHeight()/2));
	}
	
}