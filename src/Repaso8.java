import acm.program.*;
import acm.util.RandomGenerator;
import acm.graphics.*;

import java.awt.event.MouseEvent;
public class Repaso8 extends GraphicsProgram {
	GRect rectangulo;
	RandomGenerator aleatorio = new RandomGenerator();
	int distanciaX;
	int distanciaY;
	public void init(){
		setSize(800,600);
		rectangulo = new GRect(120,80);
		addMouseListeners();
	}
	public void run(){
		distanciaX = getWidth()/2;
		distanciaY = getHeight()/2;
		add(rectangulo,distanciaX - (rectangulo.getWidth()/2), distanciaY - (rectangulo.getHeight()/2));
	}
	public void mouseClicked(MouseEvent evento){
		if(getElementAt(evento.getX(),evento.getY()) == rectangulo){
			if(getElementAt(evento.getX() - rectangulo.getWidth()/2, evento.getY()) != rectangulo){
				rectangulo.move(-20, 0);
			}
			else{
				rectangulo.move(20, 0);
			}
		}
	}
}