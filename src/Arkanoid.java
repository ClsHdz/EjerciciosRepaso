/*Autor: Carlos Hernández.
 * 
 * Este programa ejecuta una versión del juego "Arkanoid" con 6 niveles,
 * un botón de pausa y algunos códigos para terminar el juego.
 * 
 * 
 */
import acm.program.*;
import acm.util.RandomGenerator;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import acm.graphics.*;

import java.awt.Color;
public class Arkanoid extends GraphicsProgram{
	private static final int ANCHO_PANTALLA = 675;
	private static final int ALTO_PANTALLA = 900;
	GLabel GOver;
	GLabel ganar;
	GLabel Modo1;
	GLabel Modo2;
	GLabel puntuacion;
	GLabel puntJuego;
	GImage background;
	GImage dobleLadrillo;
	GImage ladrillo;
	GImage plataforma = new GImage("65938.png");
	GRect botonModo1 = new GRect(80, 60);
	GRect botonModo2 = new GRect(80, 60);
	int longLadrillo = 60;
	int altLadrillo = 30;
	int columnas = 11;
	int filas = 15;
	GOval bola = new GOval(15,15);
	int velocidadX = 4;
	int velocidadY = 8;
	int cuentaLadrillos = 0;
	int ladrillosNivel = 0;
	int score = 0;
	int level = 1;
	boolean pausado = false;
	boolean pausa = false;
	boolean choqueDoble = false;
	boolean choquePlataforma = false;
	boolean choqueLadrillo = false;
	boolean ladrilloRoto = false;
	boolean gameOver = true;
	boolean ganador = false;
	boolean inicio = false;
	boolean modo = false;
	boolean modo1 = false;
	boolean modo2 = false;
	boolean nivelTerminado = false;
	int nivel1 [][] = {											//Los niveles están definidos en matrices para mayor facilidad.
			{1,1,1,1,1,1,1,1,1,1,1},							//61 ladrillos
			{2,2,2,2,9,9,9,2,2,2,2},
			{3,3,3,3,9,9,9,3,3,3,3},
			{4,4,4,4,4,4,4,4,4,4,4},
			{5,5,5,5,5,5,5,5,5,5,5},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0}
	};
	int ladrillos1 = 61;										//Además cada nivel tiene el número de ladrillos que contiene para así poder saber cuando ha terminado cada nivel.
	int nivel2 [][] = {
			{1,0,0,0,0,0,0,0,0,0,0},							//66 ladrillos
			{2,2,0,0,0,0,0,0,0,0,0},
			{3,3,3,0,0,0,0,0,0,0,0},
			{4,4,4,4,0,0,0,0,0,0,0},
			{4,4,4,4,4,0,0,0,0,0,0},
			{3,3,3,3,3,3,0,0,0,0,0},
			{2,2,2,2,2,2,2,0,0,0,0},
			{1,1,1,1,1,1,1,1,0,0,0},
			{1,1,1,1,1,1,1,1,1,0,0},
			{9,9,9,9,9,9,9,9,9,9,4},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0}
	};
	int ladrillos2 = 66;
	int nivel3 [][] = {
			{3,3,3,3,3,3,3,3,3,3,3},							//120 ladrillos
			{0,0,0,0,0,0,0,0,0,0,0},
			{5,5,5,9,9,9,9,9,9,9,9},
			{0,0,0,0,0,0,0,0,0,0,0},
			{1,1,1,1,1,1,1,1,1,1,1},
			{0,0,0,0,0,0,0,0,0,0,0},
			{9,9,9,9,9,9,9,9,5,5,5},
			{0,0,0,0,0,0,0,0,0,0,0},
			{2,2,2,2,2,2,2,2,2,2,2},
			{0,0,0,0,0,0,0,0,0,0,0},
			{4,4,4,9,9,9,9,9,9,9,9},
			{0,0,0,0,0,0,0,0,0,0,0},
			{4,4,4,4,4,4,4,4,4,4,4},
			{0,0,0,0,0,0,0,0,0,0,0},
			{9,9,9,9,9,9,9,9,4,4,4}
	};
	int ladrillos3 = 120;
	int nivel4 [][] = {
			{0,0,0,0,0,0,0,0,0,0,0},							//126 ladrillos
			{0,9,2,3,6,0,2,3,4,9,0},
			{0,4,3,2,1,0,3,2,9,4,0},
			{0,3,6,1,2,0,4,9,2,3,0},
			{0,2,1,6,3,0,9,4,3,2,0},
			{0,1,2,3,4,0,2,3,6,1,0},
			{0,6,3,2,9,0,3,2,1,6,0},
			{0,3,4,9,2,0,6,1,2,3,0},
			{0,2,9,4,3,0,1,6,3,2,0},
			{0,9,2,3,6,0,2,3,4,9,0},
			{0,4,3,2,1,0,3,2,9,4,0},
			{0,3,6,1,2,0,4,9,2,3,0},
			{0,2,1,6,3,0,9,4,3,2,0},
			{0,1,2,3,4,0,2,3,6,1,0},
			{0,6,3,2,9,0,3,2,1,6,0}
	};
	int ladrillos4 = 126;
	int nivel5 [][] = {
			{0,0,0,2,0,0,0,2,0,0,0},							//124 ladrillos
			{0,0,0,0,2,0,2,0,0,0,0},
			{0,0,0,0,2,0,2,0,0,0,0},
			{0,0,0,9,9,9,9,9,0,0,0},
			{0,0,0,9,9,9,9,9,0,0,0},
			{0,0,9,9,1,9,1,9,9,0,0},
			{0,0,9,9,1,9,1,9,9,0,0},
			{0,9,9,9,9,9,9,9,9,9,0},
			{0,9,9,9,9,9,9,9,9,9,0},
			{0,9,0,9,9,9,9,9,0,9,0},
			{0,9,0,9,0,0,0,9,0,9,0},
			{0,9,0,9,0,0,0,9,0,9,0},
			{0,0,0,0,9,0,9,0,0,0,0},
			{0,0,0,0,9,0,9,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0}
	};
	int ladrillos5 = 124;
	public void init(){
		plataforma.setSize(80, 30);
		setSize(ANCHO_PANTALLA, ALTO_PANTALLA);
		GOver = new GLabel("Game Over");
		ganar = new GLabel("Enhorabuena, has ganado");
		Modo1 = new GLabel("Modo Normal");
		Modo2 = new GLabel("Modo Invisible");
		background = new GImage("hexagon_pattern.png");
		background.setSize(ANCHO_PANTALLA, ALTO_PANTALLA);
		add(background);
		addKeyListeners();
		addMouseListeners();
		add(plataforma,0,700);
		bola.setFilled(true);
		bola.setFillColor(Color.CYAN);
		add(botonModo1, ANCHO_PANTALLA/2, ALTO_PANTALLA/2);
		add(botonModo2, botonModo1.getX() - 80, botonModo1.getY());
		add(Modo1, botonModo1.getX(), botonModo1.getY());
		add(Modo2, botonModo2.getX(), botonModo2.getY());
	}

	public void run(){
		while(modo == false){										//Este while vacío está para que no haga nada antes de que elijas un modo.
		}
		if(modo1 == true){											//El modo invisible funciona al no añadir la bola aquí.
			add(bola, 600, 0);
		}
		remove(botonModo1);
		remove(botonModo2);
		remove(Modo1);
		remove(Modo2);
		gameOver = false;
		while(gameOver == false){
			if(level == 1 && pausado == false){						//Añade los niveles según por cual estés.
				niveles(nivel1);
				ladrillosNivel = ladrillos1;
			}
			if(level == 2 && pausado == false){
				niveles(nivel2);
				inicio = false;
				pausado = true;
				pausa = false;
				nivelTerminado = false;
				ladrillosNivel = ladrillos2;
				cuentaLadrillos = 0;
			}
			if(level == 3 && pausado == false){
				niveles(nivel3);
				inicio = false;
				pausado = true;
				pausa = false;
				nivelTerminado = false;
				ladrillosNivel = ladrillos3;
				cuentaLadrillos = 0;
			}
			if(level == 4 && pausado == false){
				niveles(nivel4);
				inicio = false;
				pausado = true;
				pausa = false;
				nivelTerminado = false;
				ladrillosNivel = ladrillos4;
				cuentaLadrillos = 0;
			}
			if(level == 5 && pausado == false){
				niveles(nivel5);
				inicio = false;
				pausado = true;
				pausa = false;
				nivelTerminado = false;
				ladrillosNivel = ladrillos5;
				cuentaLadrillos = 0;
			}
			if(level == 6 && pausado == false){
				pausa = true;
				gameOver = true;
			}
			while(pausa == false){											//Este while está para poder pausar el juego.
				pausado = false;
				if(velocidadX > 1){
					velocidadX = 1;
				}
				if(velocidadX < -1){
					velocidadX = -1;
				}
				if(velocidadY > 2){
					velocidadY = 2;
				}
				if(velocidadY < -2){
					velocidadY = -2;
				}
				RandomGenerator aleatorio = new RandomGenerator();
				puntJuego = new GLabel(""+score);							//Añade la puntación. Está dentro del juego para que se vaya actualizando.
				puntJuego.setLocation(0, 800);
				add(puntJuego);
				choqueDoble = false;
				choquePlataforma = false;
				choqueLadrillo = false;
				if(inicio == false){										//Inicio hace que no empiece a moverse la bola hasa que hagas click, y para que al mismo tiempo ponga la bola donde esté la plataforma.
					bola.setLocation(plataforma.getX() + (plataforma.getWidth()/2) - 7, plataforma.getY() - 15);
				}
				else{
					bola.move(velocidadX, -velocidadY);
				}
				pause(5);
				if(bola.getX() + 25 > ANCHO_PANTALLA){						//Esto sirve ara que bote en las paredes.
					velocidadX = velocidadX * -1;
				}
				if(bola.getX() < 0){
					velocidadX = velocidadX * -1;
				} 
				GObject choque = null;
				double posX = bola.getX();
				double posY = bola.getY();
				choque = getElementAt(posX,posY);
				if(choque != null && choque != bola && choque != plataforma && choque != background && choqueLadrillo == false){			//Para comprobar los choques. Están ordenados por los puntos de la bola.
					remove(choque);
					cuentaLadrillos++;
					score = score + 100;

					if(posX >= choque.getX() + ladrillo.getWidth() -1 && posY >= choque.getY() +2 && posY <= choque.getY() + choque.getHeight() -2){
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
					if(choque != null && choque != bola && choque != plataforma && choque != background && choqueLadrillo == false){
						remove(choque);
						cuentaLadrillos++;
						score = score + 100;

						if(posX >= choque.getX() + ladrillo.getWidth() -1 && posY >= choque.getY() +2 && posY <= choque.getY() + choque.getHeight() -2){
							velocidadX = velocidadX * -1;
						}
						else{
							velocidadY = velocidadY * -1;
						}
						choqueLadrillo = true;

					}
				}
				choque = getElementAt(posX + bola.getWidth(),posY);
				if(choque != null && choque != bola && choque != plataforma && choque != background && choqueLadrillo == false){
					remove(choque);
					cuentaLadrillos++;
					score = score + 100;

					if(posX <= choque.getX() + 1 && posY >= choque.getY() +2 && posY <= choque.getY() + choque.getHeight() -2){
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
					if(choque != null && choque != bola && choque != plataforma && choque != background && choqueLadrillo == false){
						remove(choque);
						cuentaLadrillos++;
						score = score + 100;
						if(posX <= choque.getX() + 1 && posY >= choque.getY() +2 && posY <= choque.getY() + choque.getHeight() -2){
							velocidadX = velocidadX * -1;
						}
						else{
							velocidadY = velocidadY * -1;
						}
						choqueLadrillo = true;

					}
				}
				if(bola.getY() < 0){ 									//Interacciones con el techo y suelo.
					velocidadY = velocidadY * -1;
				}
				if(bola.getY() + 15 > ALTO_PANTALLA){
					gameOver = true;
					pausa = true;
				}
				if(cuentaLadrillos == ladrillosNivel && nivelTerminado == false){   //Este if sirve para saber cuando has terminado el nivel.
					level++;
					pausa = true;
					bola.setLocation(plataforma.getX() + (plataforma.getWidth()/2) - 7, plataforma.getY() - 15);
					nivelTerminado = true;
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
	private void niveles(int[][] matriz){									//El método "niveles" añade los ladrillos de cada nivel según la matriz.
		int i = 0;
		int j = 0;
		while(i < filas){
			while(j < columnas){
				dobleLadrillo = new GImage("Arkanoid_Brick_Silver.png");
				dobleLadrillo.setSize(longLadrillo, altLadrillo);
				if(matriz[i][j] == 1){
					ladrillo = new GImage("Arkanoid_Brick_Red.png");
				}
				if(matriz[i][j] == 2){
					ladrillo = new GImage("Arkanoid_Brick_Yellow.png");
				}
				if(matriz[i][j] == 3){
					ladrillo = new GImage("Arkanoid_Brick_Green.png");
				}
				if(matriz[i][j] == 4){
					ladrillo = new GImage("Arkanoid_Brick_Blue.png");
				}
				if(matriz[i][j] == 5){
					ladrillo = new GImage("Arkanoid_Brick_Orange.png");
				}
				if(matriz[i][j] == 6){
					ladrillo = new GImage("Arkanoid_Brick_Violet.png");
				}
				if(matriz[i][j] == 9){
					ladrillo = new GImage("Arkanoid_Brick_White.png");
				}
				ladrillo.setSize(longLadrillo, altLadrillo);
				if(matriz[i][j] != 0 && matriz[i][j] != 8){
					add(ladrillo,longLadrillo * j,altLadrillo * i + 50);
				}
				if(matriz[i][j] == 9){
					add(dobleLadrillo,longLadrillo * j,altLadrillo * i + 50);	
				}
				j++;
			}
			i++;
			j = 0;
		}
	}
	public void mouseMoved(MouseEvent evento){									//El método para mover la plataforma.
		plataforma.setLocation(evento.getX(), 700);
	}
	public void mouseClicked(MouseEvent evento){								//El método para elegir modo, empezar el juego y salir de la pausa.
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
			pausado = true;
		}
	}
	public void keyPressed (KeyEvent eventos){									//El método para pausar, terminar el juego, y añadirte puntos.
		if(eventos.getKeyCode() == KeyEvent.VK_9){
			ganador = true;
			gameOver = true;
			pausa = true;
			score = 99999999;
		}
		if(eventos.getKeyCode() == KeyEvent.VK_P){
			score = score +100;
		}
		if(eventos.getKeyCode() == KeyEvent.VK_SPACE){
			pausa = true;
			pausado = true;
		}

	}

}