/*
 * Repaso1 pinta un rectángulo en la esquina superior izquierda.
 */
import acm.graphics.*;
import acm.program.GraphicsProgram;


public class Repaso1 extends GraphicsProgram{
	GRect rectangulo;
	public void init(){
		setSize(800,600);
		rectangulo = new GRect(120,80);
	}
	public void run(){
		add(rectangulo);
	}
}
