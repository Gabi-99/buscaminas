package controlador;

import modelo.Tablero;

import java.util.Scanner;

import modelo.Casilla;

/*
 * Clase que implemnta el juego.
 * @author Ridouan, William
 */
public class Juego {

	//Variable
	private Tablero tablero = null;
	private int casillasDestapadas = 0;
	private boolean estadoJuego = true;
	private int resultado = -1;
	private Casilla casillaSeleccionada;
	private boolean datosCorrectos = false;
	//Constructor de la clase.
	public Juego() {
		tablero = new Tablero();
	}
	
	/*
	 * Método actualizar juego despues de cada jugada
	 * @params: Casilla casillaDestapar
	 */
	public boolean comprobarJuego(String posicion) {
		Casilla casilla = selecionarCasilla(posicion);
		if(datosCorrectos) {
			if(casilla.destaparCasilla() == 1) { //caso perdido
				casillasDestapadas++;
				resultado = 0;
			}else { //caso ganado
				casillasDestapadas++;
				if(casillasDestapadas >= (tablero.getNFilas() * tablero.getNColumnas()) - tablero.getNMinas()) {
					resultado = 1;
				}
			}
			if(resultado != -1) {
				finalizarJuego();
			}
		}
		return datosCorrectos;
	}
	
	/*
	 * Método para inicializar el tama�o del tablero y iniciar el juego.
	 */
	public boolean iniciarJuego(String entraddatos) {
		estadoJuego = true;
		int [] datos = leerDatosInicio(entraddatos);
		if(datosCorrectos){
			tablero.setNMinas(datos[2]);
			tablero.crearTablero(datos[0], datos[1]);
		}
		return datosCorrectos;
	}
	
	/*
	 * Método para mostrar la matriz que compone el table filas * columas.
	 * T: casilla tapada; D: casilla destapada; M: casilla con mina.
	 */
	public void mostrarTablero() {
	
		System.out.println("***** Juego del BUSCAMINAS ******");
		System.out.print("---------------------------\n");
		System.out.println("MINAS: "+getTablero().getNMinas()+ " | DESTAPADAS: "+casillasDestapadas); 
		System.out.print("---------------------------\n");
		System.out.print("\n");
		String linea = "   ";
		 for (int j = 0;j < getTablero().getNColumnas(); j++){
			 linea = linea + "  " + (new StringBuilder()).append(String.valueOf(j)).toString();
		 }
		 System.out.print(linea);
		 System.out.print("\n");
		for (int i = 0; i < getTablero().getNFilas(); i++) {
			System.out.print("  " + i + " ");
			for (int j = 0;j < getTablero().getNColumnas(); j++) {
				if(getTablero().getTablero()[i][j].esTapada()) {
					System.out.print(" T ");
				}else {
					if(getTablero().getTablero()[i][j].esMina()) {
						System.out.print(" M ");
					}else {
						System.out.print(" D ");
					}
				}
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	/*
	 * Método para leer la entrada por teclado.
	 * @Params: String texto
	 * @Return: String entradaTeclado
	 */
	public String leerTeclado(String texto) {
		System.out.println(texto);  
		Scanner s = new Scanner(System.in);
		return s.nextLine();
	}
	
	/*
	 * Método para leer las fila y columna para selecionar la casilla a destapar.
	 * @Params: string entradaTeclado
	 * @Return: Casilla casillSeleccionada
	 */
	public Casilla selecionarCasilla(String entradaTeclado) {
		datosCorrectos =true;
		int fila, columna;
		Casilla casilla = null;
		String[] d = entradaTeclado.split(" ");
		if(d.length == 2) {
			if(d[0].matches("[+-]?\\d*(\\.\\d+)?") && d[1].matches("[+-]?\\d*(\\.\\d+)?")) {
				fila = Integer.parseInt(d[0]);
				columna = Integer.parseInt(d[1]);
				if(fila >= 0 && columna >= 0 && fila < getTablero().getNFilas() && columna < getTablero().getNColumnas()) {
					casilla = tablero.getTablero()[fila][columna];
				}else {
					System.out.println("Posición incorrecta! Introduce posición dentro del tablero");  
					datosCorrectos =false;
				}
			}else {
				System.out.println("Datos incorrectos!! Introduce valores nómericos.");  
				datosCorrectos =false;
			}
		}else {
			System.out.println("Datos incorrectos!! Introduce 2 numeros fila columna."); 
			datosCorrectos =false;
		}
		return casilla;
	}
	
	/*
	 * Método para leer las filas, columnas y minas para poder incializar el tablero.
	 * @Params: string entradaTeclado
	 * @Return: int []datosEntrada
	 */
	public int[] leerDatosInicio(String entradaTeclado){
		datosCorrectos = true;
		int filas, columnas, minas;
		int[]results = {-1,-1,-1};
		String[] d = entradaTeclado.split(" ");

		if(d.length == 3) {
			if(d[0].matches("[+-]?\\d*(\\.\\d+)?") && d[1].matches("[+-]?\\d*(\\.\\d+)?") && d[2].matches("[+-]?\\d*(\\.\\d+)?")) {
				filas = Integer.parseInt(d[0]);
				columnas = Integer.parseInt(d[1]);
				minas = Integer.parseInt(d[2]);
				if(filas >= 2 && columnas >=2 && minas >=2 && minas < filas * columnas) {
					results[0]=filas;
					results[1]=columnas;
					results[2]=minas;
				}else {
					System.out.println("Datos incorrectos!");  
					datosCorrectos =false;
				}
			}else {
				System.out.println("Datos incorrectos!! Introduce valores n�mericos.");  
				datosCorrectos =false;
			}
		}else {
			System.out.println("Datos incorrectos!! Introduce 3 n�meros fila columna minas"); 
			datosCorrectos =false;
		}
		return results;
	}
	
	/*
	 * Método para finalizar el juego.
	 * @Params: resultado
	 */
	public void finalizarJuego() {
		estadoJuego = false;
		if(juegoGanado()) {
			System.out.println("En hora buena! Has ganado el juego.");
		}else {
			System.out.println("Has Perdido! Vuelve a intentarlo."); 
		}
	}
	
	/*
	 * Método para mostrar la opciones al finalizar el juego. Salir o volver a jugar.
	 * @params String entradaTeclado
	 * @Return int opcion
	 */
	public int menuOpcionesFinalizar(String entradaTeclado) {
		int opcion = -1 ;
		if (entradaTeclado.matches("[+-]?\\d*(\\.\\d+)?")) {
			if(Integer.parseInt(entradaTeclado) == 0 || Integer.parseInt(entradaTeclado) == 1) {
				opcion = Integer.parseInt(entradaTeclado);
			}else {
				System.out.println("Valor incorrecto!! Introduce un número (0 o 1)");
			}
			
		}else {
			System.out.println("Valor incorrecto!! Introduce un valor númerico");
		}
		return opcion;
	}
	
	/*
	 * Método que devuelve el objeto tablero
	 * @Return Tablero tablero
	 */
	public Tablero getTablero() {
		return tablero;
	}
	
	/*
	 * Método que devuelve el estado del juego.
	 * @Return boolean estadoJuego
	 */
	public boolean getEstadoJuego() {
		return estadoJuego;
	}
	
	/*
	 * Método que devuelve el número de casillas destapadas.
	 * @Return int casillasDestapadas
	 */
	public int getCasillasDestapadas() {
		return casillasDestapadas;
	}
	/*
	 * Método para actualizar el número de casillas destapadas.
	 * @Params int casillasDestapadas
	 */
	public void setCasillasDestapadas(int n) {
		casillasDestapadas = n;
	}
	
	/*
	 * Método que comprueba el estado de juego.
	 * @return boolean resultado.
	 */
	public boolean juegoGanado() {
		boolean result = false;
		if(resultado == 0) {
			result = false;;
		}else {
			result = true;
		}
		return result;
	}
	
	/*
	 * Método que devuelve si los datos de entrada son correctos.
	 * @Return boolean datosCorrectos
	 */
	public boolean datosCorrectos() {
		return datosCorrectos;
	}
}
