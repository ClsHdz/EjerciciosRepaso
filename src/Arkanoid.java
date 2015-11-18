import acm.program.*;
import java.awt.event.MouseEvent;
import acm.graphics.*;

import java.awt.Color;
public class Arkanoid extends GraphicsProgram{
	private static final int ANCHO_PANTALLA = 615;
	private static final int ALTO_PANTALLA = 900;
	GLabel GOver;
	GLabel ganar;
	GRect ladrillo;
	GRect plataforma = new GRect(80, 30);
	int longLadrillo = 60;
	int altLadrillo = 30;
	int columnas = 10;
	int filas = 5;
	GOval bola = new GOval(15,15);
	int velocidadX = 5;
	int velocidadY = 10;
	int cuentaLadrillos = 0;
	boolean choquePlataforma = false;
	boolean choqueLadrillo = false;
	boolean choqueUpLeft = false;
	boolean choqueUpRight = false;
	boolean choqueDownLeft = false;
	boolean choqueDownRight = false;
	boolean gameOver = false;
	boolean ganador = false;
	boolean inicio = false;
	public void init(){
		setSize(ANCHO_PANTALLA, ALTO_PANTALLA);
		int i = 0;
		int j = 0;
		GOver = new GLabel("Game Over");
		ganar = new GLabel("Enhorabuena, has ganado");
		while(i < filas){
			while(j < columnas){
				ladrillo = new GRect(longLadrillo,altLadrillo);
				ladrillo.setFilled(true);
				if(i == 0){
					ladrillo.setFillColor(Color.RED);
				}
				if(i == 1){
					ladrillo.setFillColor(Color.YELLOW);
				}
				if(i == 2){
					ladrillo.setFillColor(Color.GREEN);
				}
				if(i == 3){
					ladrillo.setFillColor(Color.BLUE);
				}
				if(i == 4){
					ladrillo.setFillColor(Color.BLUE);
				}
				add(ladrillo,longLadrillo * j,altLadrillo * i + 50);
				j++;
			}
			i++;
			j = 0;
		}
		
		addMouseListeners();
		plataforma.setFilled(true);
		plataforma.setFillColor(Color.BLACK);
		add(plataforma,0,700);
		bola.setFilled(true);
		bola.setFillColor(Color.CYAN);
		add(bola, 600, 0);
	}
	
	public void run(){
		while(gameOver == false){
			choquePlataforma = false;
			choqueLadrillo = false;
			choqueUpLeft = false;
			choqueUpRight = false;
			choqueDownLeft = false;
			choqueDownRight = false;
			if(inicio == false){
				bola.setLocation(plataforma.getX(), plataforma.getY() - 15);
			}
			else{
				bola.move(velocidadX, -velocidadY);
			}
			pause(30);
			if(bola.getX() + 25 > ANCHO_PANTALLA){
				velocidadX = velocidadX * -1;
			}
			if(bola.getX() < 0){
				velocidadX = velocidadX * -1;
			}
			GObject choque = null;
			double posX = bola.getX();
			double posY = bola.getY();
			choque = getElementAt(posX,posY);
			if(choque != null && choque != bola && choque != plataforma && choqueLadrillo == false){
				remove(choque);
				velocidadY = velocidadY * (-1);
				cuentaLadrillos++;
				choqueUpLeft = true;
				choqueLadrillo = true;
			}
			choque = getElementAt(posX,posY + bola.getHeight());
			if(choque == plataforma && choquePlataforma == false){
				velocidadY = velocidadY * (-1);
				bola.setLocation(bola.getX(), bola.getY()-15);
				choquePlataforma = true;
			}
			else{
				if(choque != null && choque != bola && choque != plataforma && choqueLadrillo == false){
					remove(choque);
					velocidadY = velocidadY * (-1);
					cuentaLadrillos++;
					choqueDownLeft = true;
					choqueLadrillo = true;
				}
			}
			choque = getElementAt(posX + bola.getWidth(),posY);
			
			if(choque != null && choque != bola && choque != plataforma && choqueLadrillo == false){
				remove(choque);
				velocidadY = velocidadY * (-1);
				cuentaLadrillos++;
				choqueUpRight = true;
				choqueLadrillo = true;
			}
			
			choque = getElementAt(posX + bola.getWidth(),posY + bola.getHeight());
			if(choque == plataforma && choquePlataforma == false){
				velocidadY = velocidadY * (-1);
				bola.setLocation(bola.getX(), bola.getY()-15);
				choquePlataforma = true;
			}
			else{
				if(choque != null && choque != bola && choque != plataforma && choqueLadrillo == false){
					remove(choque);
					velocidadY = velocidadY * (-1);
					cuentaLadrillos++;
					choqueDownRight = true;
					choqueLadrillo = true;
				}
			}
			if(bola.getY() < 0){
				velocidadY = velocidadY * -1;
			}
			if(bola.getY() + 15 > ALTO_PANTALLA){
				gameOver = true;
			}
			if(cuentaLadrillos == 50){
				gameOver = true;
				ganador = true;
			}
		}
		GOver.setLocation(ANCHO_PANTALLA / 2,ALTO_PANTALLA / 2);
		GOver.setColor(Color.BLACK);
		add(GOver);
		if(ganador == true){
			ganar.setLocation(ANCHO_PANTALLA / 2,ALTO_PANTALLA / 2 + 30);
			ganar.setColor(Color.BLACK);
			add(ganar);
		}
	}
	public void mouseMoved(MouseEvent evento){
		plataforma.setLocation(evento.getX(), 700);
	}
	public void mouseClicked(MouseEvent evento){
		inicio = true;
	}
	
	
}
