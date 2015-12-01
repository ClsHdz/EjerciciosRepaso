import acm.program.*;
import acm.util.RandomGenerator;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import acm.graphics.*;

import java.awt.Color;
public class Arkanoid extends GraphicsProgram{
	private static final int ANCHO_PANTALLA = 615;
	private static final int ALTO_PANTALLA = 900;
	GLabel GOver;
	GLabel ganar;
	GLabel Modo1;
	GLabel Modo2;
	GLabel puntuacion;
	GLabel puntJuego;
	GRect dobleLadrillo;
	GRect ladrillo;
	GRect plataforma = new GRect(80, 30);
	GRect botonModo1 = new GRect(80, 60);
	GRect botonModo2 = new GRect(80, 60);
	int longLadrillo = 60;
	int altLadrillo = 30;
	int columnas = 10;
	int filas = 5;
	GOval bola = new GOval(15,15);
	int velocidadX = 5;
	int velocidadY = 10;
	int cuentaLadrillos = 0;
	int score = 0;
	int n;
	boolean pausa = false;
	boolean choqueDoble = false;
	boolean choquePlataforma = false;
	boolean choqueLadrillo = false;
	boolean ladrilloRoto = false;
	boolean choqueUpLeft = false;
	boolean choqueUpRight = false;
	boolean choqueDownLeft = false;
	boolean choqueDownRight = false;
	boolean gameOver = true;
	boolean ganador = false;
	boolean inicio = false;
	boolean modo = false;
	boolean modo1 = false;
	boolean modo2 = false;
	public void init(){
		setSize(ANCHO_PANTALLA, ALTO_PANTALLA);
		int i = 0;
		int j = 0;
		GOver = new GLabel("Game Over");
		ganar = new GLabel("Enhorabuena, has ganado");
		Modo1 = new GLabel("Modo Normal");
		Modo2 = new GLabel("Modo Invisible");
		while(i < filas){
			while(j < columnas){
				dobleLadrillo = new GRect(longLadrillo,altLadrillo);
				dobleLadrillo.setFilled(true);
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
				if(i == filas /2 && j == columnas /2){
					dobleLadrillo.setFillColor(Color.GRAY);
					add(dobleLadrillo, longLadrillo * j, altLadrillo * i +50);
				}
				if(i == (filas /2) -1 && j == (columnas /2)){
					dobleLadrillo.setFillColor(Color.GRAY);
					add(dobleLadrillo, longLadrillo * j, altLadrillo * i +50);
				}
				if(i == (filas /2) -1 && j == (columnas /2)-1){
					dobleLadrillo.setFillColor(Color.GRAY);
					add(dobleLadrillo, longLadrillo * j, altLadrillo * i +50);
				}
				if(i == (filas /2) && j == (columnas /2) -1){
					dobleLadrillo.setFillColor(Color.GRAY);
					add(dobleLadrillo, longLadrillo * j, altLadrillo * i +50);
				}
				j++;
			}
			i++;
			j = 0;
		}
		addKeyListeners();
		addMouseListeners();
		plataforma.setFilled(true);
		plataforma.setFillColor(Color.BLACK);
		add(plataforma,0,700);
		bola.setFilled(true);
		bola.setFillColor(Color.CYAN);
		add(botonModo1, ANCHO_PANTALLA/2, ALTO_PANTALLA/2);
		add(botonModo2, botonModo1.getX() - 80, botonModo1.getY());
		add(Modo1, botonModo1.getX(), botonModo1.getY());
		add(Modo2, botonModo2.getX(), botonModo2.getY());
	}

	public void run(){
		while(modo == false){
		}
		if(modo1 == true){
			add(bola, 600, 0);
		}
		remove(botonModo1);
		remove(botonModo2);
		remove(Modo1);
		remove(Modo2);
		gameOver = false;
		while(gameOver == false){
			while(pausa == false){
				if(velocidadX > 6){
					velocidadX = 6;
				}
				if(velocidadX < -6){
					velocidadX = -6;
				}
				if(velocidadY > 12){
					velocidadY = 12;
				}
				if(velocidadY < -12){
					velocidadY = -12;
				}
				RandomGenerator aleatorio = new RandomGenerator();
				n = aleatorio.nextInt(100);
				puntJuego = new GLabel(""+score+"");
				puntJuego.setLocation(0, 800);
				add(puntJuego);
				choqueDoble = false;
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
					cuentaLadrillos++;
					score = score + 100;
					choqueUpLeft = true;
					if(posX >= choque.getX() + ladrillo.getWidth() -1 && posY >= choque.getY() +1 && posY <= choque.getY() + choque.getHeight() -1){
						velocidadX = velocidadX * -1;
					}
					else{
						velocidadY = velocidadY * -1;
					}
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
						cuentaLadrillos++;
						score = score + 100;
						choqueDownLeft = true;
						if(posX >= choque.getX() + ladrillo.getWidth() -1 && posY >= choque.getY() +1 && posY <= choque.getY() + choque.getHeight() -1){
							velocidadX = velocidadX * -1;
						}
						else{
							velocidadY = velocidadY * -1;
						}
						choqueLadrillo = true;

					}
				}
				choque = getElementAt(posX + bola.getWidth(),posY);
				if(choque != null && choque != bola && choque != plataforma && choqueLadrillo == false){
					remove(choque);
					cuentaLadrillos++;
					score = score + 100;
					choqueUpRight = true;
					if(posX <= choque.getX() + 1 && posY >= choque.getY() +1 && posY <= choque.getY() + choque.getHeight() -1){
						velocidadX = velocidadX * -1;
					}
					else{
						velocidadY = velocidadY * -1;
					}
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
						cuentaLadrillos++;
						score = score + 100;
						choqueDownRight = true;
						if(posX <= choque.getX() + 1 && posY >= choque.getY() +1 && posY <= choque.getY() + choque.getHeight() -1){
							velocidadX = velocidadX * -1;
						}
						else{
							velocidadY = velocidadY * -1;
						}
						choqueLadrillo = true;

					}
				}
				if(bola.getY() < 0){
					velocidadY = velocidadY * -1;
				}
				if(bola.getY() + 15 > ALTO_PANTALLA){
					gameOver = true;
					pausa = true;
				}
				if(cuentaLadrillos == 27){
					if(velocidadX > 0){
						velocidadX = velocidadX + 1;
					}
					else{
						velocidadX = velocidadX - 1;
					}
					if(velocidadY > 0){
						velocidadY = velocidadY + 2;
					}
					else{
						velocidadY = velocidadY - 2;
					}
				}
				if(cuentaLadrillos == 54){
					gameOver = true;
					ganador = true;
					pausa = true;
				}
				remove(puntJuego);

			}
		}
		if(modo1 == false){
			add(bola);
		}
		GOver.setLocation(ANCHO_PANTALLA / 2,ALTO_PANTALLA / 2);
		GOver.setColor(Color.BLACK);
		add(GOver);
		puntuacion = new GLabel("Tu puntuacion es "+score+"");
		puntuacion.setLocation(ANCHO_PANTALLA / 2,ALTO_PANTALLA / 2 + 30);
		puntuacion.setColor(Color.BLACK);
		add(puntuacion);
		if(ganador == true){
			ganar.setLocation(ANCHO_PANTALLA / 2,ALTO_PANTALLA / 2 + 60);
			ganar.setColor(Color.BLACK);
			add(ganar);
		}
	}
	public void mouseMoved(MouseEvent evento){
		plataforma.setLocation(evento.getX(), 700);
	}
	public void mouseClicked(MouseEvent evento){
		if(gameOver == false){
			inicio = true;
		}
		if(getElementAt(evento.getX(),evento.getY()) == botonModo1){
			modo1 = true;
			modo = true;
		}
		if(getElementAt(evento.getX(),evento.getY()) == botonModo2){
			modo2 = true;
			modo = true;
		}
		if(pausa == true){
			pausa = false;
		}
	}
	public void keyPressed (KeyEvent eventos){
		if(eventos.getKeyCode() == KeyEvent.VK_9){
			ganador = true;
			gameOver = true;
		}
		if(eventos.getKeyCode() == KeyEvent.VK_SPACE){
			pausa = true;
		}

	}

}
