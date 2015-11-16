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
		respuesta = contesta(true,false,false);	
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
}
