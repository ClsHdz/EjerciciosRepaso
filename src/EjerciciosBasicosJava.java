import acm.program.*;


public class EjerciciosBasicosJava extends ConsoleProgram{
	boolean respuesta;
	int solucion;
	public void run(){
//		respuesta = fiestaArdillas(70,true);	
//		println(""+respuesta+"");
//		solucion = multa(85,true);
//		println(""+solucion+"");
//		respuesta = muyVanidoso(24);
//		println(""+respuesta+"");
//		respuesta = contesta(true,false,false);	
//		println(""+respuesta+"");
//		respuesta = menorPor10(2,11,1);	
//		println(""+respuesta+"");
//		respuesta = digitoIgual(12,44);	
//		println(""+respuesta+"");
//		respuesta = multiploMultiple(7);	
//		println(""+respuesta+"");
		respuesta = menos20(40);	
		println(""+respuesta+"");
	}
	
	private boolean fiestaArdillas(int n ,boolean finde){
		if(finde == false){
			if(n < 60 && n > 30){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return true;
		}
	}
	private int multa(int n, boolean c){
		if(c == true){
			n = n -5;
		}
		if(n < 61){
			return 0;
		}
		else{
			if(n > 60 && n < 81){
				return 1;
			}
			else{
				return 2;
			}
		}
	}
	private boolean muyVanidoso(int n){
		if(n % 11 == 0 || n % 11 == 1){
			return true;
		}
		else{
			return false;
		}
	}
	private boolean contesta(boolean matinal, boolean madre, boolean dormido){
		if(dormido == true){
			return false;
		}
		else{
			if(matinal == true){
				if(madre == true){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				return true;
			}
		}
	}
	private boolean menorPor10(int numero, int cifra, int n){
		if(numero - cifra >= 10 || numero - n >= 10 || n - numero >= 10 || cifra - numero >= 10 || n - cifra >= 10 || cifra - n >= 10){
			return true;
		}
		else{
			return false;
		}
	}
	private boolean digitoIgual(int n, int cifra){
		int num1 = n/10;
		int num2 = n%10;
		int num3 = cifra/10;
		int num4 = cifra%10;
		if(num1 == num3 || num1 == num4 || num2 == num3 || num2 == num4){
			return true;
		}
		else{
			return false;
		}
	}
	private boolean multiploMultiple(int n){
		if(n % 3 == 0 || n % 5 == 0){
			if(n % 3 == 0 && n % 5 == 0){
				return false;
			}
			else{
				return true;
			}
		}
		else{
			return false;
		}
	}
	private boolean menos20(int n){
		if(n % 20 == 18 || n % 20 == 19){
			return true;
		}
		else{
			return false;
		}
	}
}
