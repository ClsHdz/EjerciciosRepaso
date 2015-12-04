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
	GRect dobleLadrillo;
	GRect ladrillo;
	GRect plataforma = new GRect(80, 30);
	GRect botonModo1 = new GRect(80, 60);
	GRect botonModo2 = new GRect(80, 60);
	int longLadrillo = 60;
	int altLadrillo = 30;
	int columnas = 11;
	int filas = 15;
	GOval bola = new GOval(15,15);
	int velocidadX = 1;
	int velocidadY = 2;
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
	int nivel1 [][] = {
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
	int ladrillos1 = 61;
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

		setSize(ANCHO_PANTALLA, ALTO_PANTALLA);
		GOver = new GLabel("Game Over");
		ganar = new GLabel("Enhorabuena, has ganado");
		Modo1 = new GLabel("Modo Normal");
		Modo2 = new GLabel("Modo Invisible");
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
			if(level == 1 && pausado == false){
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
			while(pausa == false){
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
				puntJuego = new GLabel(""+score);
				puntJuego.setLocation(0, 800);
				add(puntJuego);
				choqueDoble = false;
				choquePlataforma = false;
				choqueLadrillo = false;
				if(inicio == false){
					bola.setLocation(plataforma.getX() + (plataforma.getWidth()/2) - 7, plataforma.getY() - 15);
				}
				else{
					bola.move(velocidadX, -velocidadY);
				}
				pause(5);
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
					if(choque != null && choque != bola && choque != plataforma && choqueLadrillo == false){
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
				if(choque != null && choque != bola && choque != plataforma && choqueLadrillo == false){
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
					if(choque != null && choque != bola && choque != plataforma && choqueLadrillo == false){
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
				if(bola.getY() < 0){
					velocidadY = velocidadY * -1;
				}
				if(bola.getY() + 15 > ALTO_PANTALLA){
					gameOver = true;
					pausa = true;
				}
				if(cuentaLadrillos == ladrillosNivel && nivelTerminado == false){
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
	private void niveles(int[][] matriz){
		int i = 0;
		int j = 0;
		while(i < filas){
			while(j < columnas){
				dobleLadrillo = new GRect(longLadrillo,altLadrillo);
				dobleLadrillo.setFilled(true);
				dobleLadrillo.setFillColor(Color.GRAY);
				ladrillo = new GRect(longLadrillo,altLadrillo);
				ladrillo.setFilled(true);
				if(matriz[i][j] == 1){
					ladrillo.setFillColor(Color.RED);
				}
				if(matriz[i][j] == 2){
					ladrillo.setFillColor(Color.YELLOW);
				}
				if(matriz[i][j] == 3){
					ladrillo.setFillColor(Color.GREEN);
				}
				if(matriz[i][j] == 4){
					ladrillo.setFillColor(Color.BLUE);
				}
				if(matriz[i][j] == 5){
					ladrillo.setFillColor(Color.WHITE);
				}
				if(matriz[i][j] == 6){
					ladrillo.setFillColor(Color.MAGENTA);
				}
				if(matriz[i][j] == 9){
					ladrillo.setFillColor(Color.LIGHT_GRAY);
				}
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
			pausado = true;
		}
	}
	public void keyPressed (KeyEvent eventos){
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