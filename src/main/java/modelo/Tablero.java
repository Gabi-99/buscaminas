package modelo;
import java.util.Random;
import java.util.ArrayList;

/*
 * Clase que implemnta el objeto tablero.
 * @author Ridouan, William
 */
public class Tablero {

	private int nFilas;
	private int nColumnas;
	private int nMinas;
	private Casilla [][] tablero;
	
	/*
	 * Constructor de la clase.
	 */
	public Tablero() {}
	
	/*
	 * Crear tablero de filas * columnas casillas e inicializar las casellas.
	 * Posicionas las  N minas de forma aleatoria en el tablero.
	 * @Params: int filas, int columnas.
	 */
	public void crearTablero(int filas, int columnas) {
		setNFilas(filas);
		setNColumnas(columnas);
		
		//Inicializar el tablero
		tablero = new Casilla[filas][columnas];
		for(int i = 0; i< filas; i++) {
			for(int j = 0; j< columnas; j++) {
				tablero[i][j] = new Casilla(i, j);
			}
		}
		//Colocar minas
		for(int m = 0; m < getNMinas(); m++) {
			//Posici�n aleatoria
			int x, y;
			x = new Random().nextInt(filas);
			y = new Random().nextInt(columnas);
			while(tablero[x][y].esMina()) {
				x = new Random().nextInt(filas);
				y = new Random().nextInt(columnas);
			}
			tablero[x][y].colocarMina();
		}
	}
	
	/*
	 * Devolver el n�mero de filas del tablero.
	 * @Return int filas.
	 */
	public int getNFilas() {
		return nFilas;
	}
	
	/*
	 * Devolver el n�mero de columnas del tablero.
	 * @Return int columnas.
	 */
	public int getNColumnas() {
		return nColumnas;
	}
	
	/*
	 * Devolver la matriz del tablero.
	 * @Return Casilla [][] tablero.
	 */
	public Casilla[][] getTablero(){
		return tablero;
	}
	
	/*
	 * Devolver el n�mero de minas del.
	 * @Return int minas.
	 */
	public int getNMinas() {
		return nMinas;
	}
	
	/*
	 * Actualizar el número de filas del tablero
	 * @Params int filas
	 */
	public void setNFilas(int filas) {
		nFilas = filas;
	}
	/*
	 * Actualizar el número de columnas del tablero
	 * @Params int columnas
	 */
	public void setNColumnas(int columnas) {
		nColumnas = columnas;
	}
	/*
	 * Actualizar el número de minas.
	 * @Params int minas
	 */
	public void setNMinas(int minas) {
		nMinas = minas;
	}
	/*
	 * Actualizar la matriz de tablero.
	 * @Params Casilla[][] tablero
	 */
	public void setTablero(Casilla[][] tab) {
		tablero = tab;
	}

}
