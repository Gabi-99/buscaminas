package buscaminas.buscamina;


import java.util.*;


import controlador.Juego;
import modelo.Tablero;

public class main {
	static Juego juego;
	
	/*
	 * Clase principal del juego.
	 */
	public static void main(String[] args) {
		iniciar();
		while(true){
			while(!juego.comprobarJuego(juego.leerTeclado("Introduce posición casilla, fila columna. Ejemplo:0 0"))) {
			}
			juego.mostrarTablero();
			if(juego.getEstadoJuego() == false) { //Finalizado
				int opcion = juego.menuOpcionesFinalizar(juego.leerTeclado("Volver a jugar: 1"+"\n"+"Salir : 0"));
				while(opcion == -1) {
					opcion = juego.menuOpcionesFinalizar(juego.leerTeclado("Volver a jugar: 1"+"\n"+"Salir : 0"));
				}
				if(opcion == 1) {
					iniciar();
				}else {
					System.out.println("Salir..."); 
					System.exit(0);
				}
			}
		}	
	}
	
	/*
	 * Método para inciar el juego.
	 */
	public static void iniciar() {
		juego = new Juego();
		while(!juego.iniciarJuego(juego.leerTeclado("Introduce datos, filas columnas minas. Ejemplo:2 2 2"))) {
		}
		juego.mostrarTablero();
	}
}
