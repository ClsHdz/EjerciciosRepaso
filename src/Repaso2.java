import acm.program.*;
import acm.graphics.*;

public class Repaso2 extends GraphicsProgram {
	GRect rectangulo = new GRect(120,80);
	int distanciaX;
	public void init(){
		setSize(800,600);
		rectangulo = new GRect(120,80);
		add(rectangulo);
	}
	public void run(){
		distanciaX = getWidth()/2;
		rectangulo.setLocation(distanciaX, 0);
		
	}
}
